package ir.hosseinabbasi.mediamonks.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.hosseinabbasi.mediamonks.R;
import ir.hosseinabbasi.mediamonks.di.ApplicationContext;
import ir.hosseinabbasi.mediamonks.ui.album.AlbumListView;
import ir.hosseinabbasi.mediamonks.ui.base.BaseActivity;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public class MainActivity extends BaseActivity implements IMainActivityView {

    @Inject
    IMainActivityPresenter<IMainActivityView> mPresenter;

    @Inject
    @ApplicationContext
    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        initViews();
        mPresenter.onAttach(this);
    }

    @Override
    public void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    private void initViews() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.v_container, AlbumListView.getInstance(), AlbumListView.TAG)
                .commit();
    }

}
