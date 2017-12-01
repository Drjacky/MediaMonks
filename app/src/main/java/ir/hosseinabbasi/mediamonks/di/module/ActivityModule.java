package ir.hosseinabbasi.mediamonks.di.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ir.hosseinabbasi.mediamonks.di.ActivityContext;
import ir.hosseinabbasi.mediamonks.di.PerActivity;
import ir.hosseinabbasi.mediamonks.ui.main.IMainActivityPresenter;
import ir.hosseinabbasi.mediamonks.ui.main.IMainActivityView;
import ir.hosseinabbasi.mediamonks.ui.main.MainActivityPresenter;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    IMainActivityPresenter<IMainActivityView> provideMainActivityPresenter(MainActivityPresenter<IMainActivityView>
                                                                                   presenter) {
        return presenter;
    }

}
