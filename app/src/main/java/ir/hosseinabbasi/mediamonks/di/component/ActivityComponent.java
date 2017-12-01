package ir.hosseinabbasi.mediamonks.di.component;

import dagger.Component;
import ir.hosseinabbasi.mediamonks.di.PerActivity;
import ir.hosseinabbasi.mediamonks.di.module.ActivityModule;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
}
