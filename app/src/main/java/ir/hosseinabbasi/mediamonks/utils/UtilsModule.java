package ir.hosseinabbasi.mediamonks.utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ir.hosseinabbasi.mediamonks.utils.rx.RxDisposables;
import ir.hosseinabbasi.mediamonks.utils.rx.ThreadTransformer;
import ir.hosseinabbasi.mediamonks.utils.rx.impl.CompositeDisposablesImpl;
import ir.hosseinabbasi.mediamonks.utils.rx.impl.ViewThreadTransformer;
import ir.hosseinabbasi.mediamonks.utils.rx.impl.WorkThreadTransformer;
import ir.hosseinabbasi.mediamonks.utils.rx.qualifiers.IOThreadPref;
import ir.hosseinabbasi.mediamonks.utils.rx.qualifiers.UIThreadPref;
import ir.hosseinabbasi.mediamonks.utils.rx.qualifiers.WorkThreadTransformerPref;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

@Module
public class UtilsModule {

    @Provides
    @Singleton
    ThreadTransformer providesViewThreadTransformer(
            ViewThreadTransformer transformer) {
        return transformer;
    }

    @Provides
    @Singleton
    @WorkThreadTransformerPref
    ThreadTransformer providesWorkThreadTransformer(
            WorkThreadTransformer transformer) {
        return transformer;
    }

    @Provides
    @Singleton
    @IOThreadPref
    Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @UIThreadPref
    Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    public RxDisposables provideCompositeDisposables(
            CompositeDisposablesImpl disposables) {
        return disposables;
    }

}
