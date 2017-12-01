package ir.hosseinabbasi.mediamonks.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;
import ir.hosseinabbasi.mediamonks.MainApp;
import ir.hosseinabbasi.mediamonks.di.component.ActivityComponent;
import ir.hosseinabbasi.mediamonks.di.component.DaggerActivityComponent;
import ir.hosseinabbasi.mediamonks.di.module.ActivityModule;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public class BaseActivity extends AppCompatActivity
        implements IBaseView, BaseFragment.Callback {

    private ActivityComponent mActivityComponent;

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MainApp) getApplication()).getApplicationComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

}
