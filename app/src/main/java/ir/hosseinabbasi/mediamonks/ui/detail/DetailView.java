package ir.hosseinabbasi.mediamonks.ui.detail;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.hosseinabbasi.mediamonks.R;
import ir.hosseinabbasi.mediamonks.data.db.model.Photo;
import ir.hosseinabbasi.mediamonks.di.ActivityContext;
import ir.hosseinabbasi.mediamonks.ui.base.BaseFragment;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public class DetailView extends BaseFragment implements IDetailView {

    public static final String TAG = "DetailView";

    @Inject
    @ActivityContext
    Context mContext;

    @Inject
    Resources mResources;

    @Inject
    DetailPresenter<DetailView> mPresenter;

    @BindView(R.id.fragment_detail_txtTitle)
    TextView txtTitle;

    @BindView(R.id.fragment_detail_imgThumbnail)
    ImageView imgPhotoThumbnail;

    public static DetailView getInstance() {
        return new DetailView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
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
        Photo photo = bundle.getParcelable("photo");
        if(photo != null) {
            txtTitle.setText(photo.getTitle());
            Picasso.with(mContext)
                    .load(photo.getUrl())
                    .placeholder(mContext.getResources().getDrawable(R.mipmap.ic_launcher))
                    .into(imgPhotoThumbnail);
        }
    }

}
