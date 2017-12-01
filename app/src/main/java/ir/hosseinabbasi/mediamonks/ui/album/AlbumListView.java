package ir.hosseinabbasi.mediamonks.ui.album;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import ir.hosseinabbasi.mediamonks.data.db.model.Album;
import ir.hosseinabbasi.mediamonks.di.ActivityContext;
import ir.hosseinabbasi.mediamonks.ui.base.BaseFragment;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public class AlbumListView extends BaseFragment implements IAlbumListView {

    public static final String TAG = "AlbumListView";

    @Inject
    @ActivityContext
    Context mContext;

    @Inject
    AlbumListPresenter<AlbumListView> mPresenter;

    @BindView(R.id.fragment_album_rcyAlbum)
    RecyclerView mRcyAlbum;

    private AlbumListAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private boolean mIsRecyclerViewLoading = false;
    private boolean mBottomReached = false;
    private int mCurrentPage = 0;
    private int mPageSize = 20;
    private int mTotal = 100;
    private List<Album> mAlbumList = new ArrayList<>();

    public static AlbumListView getInstance() {
        return new AlbumListView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false);
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
        mLayoutManager = new LinearLayoutManager(mContext);
        mRcyAlbum.setLayoutManager(mLayoutManager);
        mRcyAlbum.setHasFixedSize(true);
        mRcyAlbum.addOnScrollListener(recyclerViewOnScrollListener);
        adapter = new AlbumListAdapter(mContext, this);
        mRcyAlbum.setAdapter(adapter);

        if(isNetworkConnected())
            getAlbumList();
        else
            Toast.makeText(mContext, R.string.no_internet_connection, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadAlbumList(List<Album> albumList) {
        mAlbumList = albumList;
        adapter.addAll(mAlbumList);
        adapter.notifyDataSetChanged();
    }

    public void getAlbumList() {
        mPresenter.getAlbumList(mCurrentPage, mPageSize);
        mCurrentPage++;
    }

    @Override
    public void loadAlbumDetail(Album item) {
        Bundle bundle = new Bundle();
        //bundle.putParcelable("album", album);
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
                        getAlbumList();
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
