package ir.hosseinabbasi.mediamonks.di.component;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Component;
import ir.hosseinabbasi.mediamonks.data.DataManager;
import ir.hosseinabbasi.mediamonks.di.ApplicationContext;
import ir.hosseinabbasi.mediamonks.di.module.ApplicationModule;
import ir.hosseinabbasi.mediamonks.di.module.NetModule;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class})
public interface ApplicationComponent{

    @ApplicationContext
    Context exposeContext();

    Resources exposeResources();

    Application exposeApplication();

    DataManager exposeDataManager();

}
