package ir.hosseinabbasi.mediamonks.ui.photo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.hosseinabbasi.mediamonks.R;
import ir.hosseinabbasi.mediamonks.data.db.model.Photo;
import ir.hosseinabbasi.mediamonks.di.ActivityContext;
import ir.hosseinabbasi.mediamonks.ui.base.BaseFragment;
import ir.hosseinabbasi.mediamonks.ui.detail.DetailView;
import ir.hosseinabbasi.mediamonks.ui.main.MainActivity;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public class PhotoListView extends BaseFragment implements IPhotoListView {

    public static final String TAG = "PhotoListView";

    private int mAlbumId;

    @Inject
    @ActivityContext
    Context mContext;

    @Inject
    PhotoListPresenter<PhotoListView> mPresenter;

    @BindView(R.id.fragment_photo_rcyPhoto)
    RecyclerView mRcyPhoto;

    private PhotoListAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private boolean mIsRecyclerViewLoading = false;
    private boolean mBottomReached = false;
    private int mCurrentPage = 0;
    private int mPageSize = 10;
    private int mTotal = 50;
    private List<Photo> mPhotoList = new ArrayList<>();

    public static PhotoListView getInstance() {
        return new PhotoListView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
        initViews();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    private void initViews() {
        Bundle bundle = this.getArguments();
        mAlbumId = bundle.getInt("albumId");
        mLayoutManager = new LinearLayoutManager(mContext);
        mRcyPhoto.setLayoutManager(mLayoutManager);
        mRcyPhoto.setHasFixedSize(true);
        mRcyPhoto.addOnScrollListener(recyclerViewOnScrollListener);
        adapter = new PhotoListAdapter(mContext, this);
        mRcyPhoto.setAdapter(adapter);

        if(isNetworkConnected())
            getPhotoList();
        else
            Toast.makeText(mContext, R.string.no_internet_connection, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadPhotoList(List<Photo> photoList) {
        mPhotoList = photoList;
        adapter.addAll(mPhotoList);
        adapter.notifyDataSetChanged();
    }

    public void getPhotoList() {
        mPresenter.getPhotoList(mAlbumId, mCurrentPage, mPageSize);
        mCurrentPage++;
    }

    @Override
    public void loadPhotoDetail(Photo photo) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("photo", photo);
        DetailView detailFragment = DetailView.getInstance();
        detailFragment.setArguments(bundle);

        ((MainActivity) mContext).getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.v_container, detailFragment, DetailView.TAG)
                .addToBackStack(DetailView.TAG)
                .commit();
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            View view = (View) recyclerView.getChildAt(recyclerView.getChildCount() - 1);
            int diff = (view.getBottom() - (recyclerView.getHeight() + recyclerView.getScrollY()));
            mBottomReached = diff <= 0;

            if (dy > 0) {
                int mVisibleItemCount = mLayoutManager.getChildCount();
                int mTotalItemCount = mLayoutManager.getItemCount();
                int mFirstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

                if (!isRecyclerViewLoading()) {
                    if (((mVisibleItemCount + mFirstVisibleItemPosition) >= mTotalItemCount) && mBottomReached && (mTotalItemCount < mTotal)) {
                        getPhotoList();
                    }
                }
            }

            super.onScrolled(recyclerView, dx, dy);
        }
    };

    @Override
    public void setIsLoading(Boolean isLoading) {
        this.mIsRecyclerViewLoading = isLoading;
    }

    public boolean isRecyclerViewLoading() {
        return mIsRecyclerViewLoading;
    }

}
