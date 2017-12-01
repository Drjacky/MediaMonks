package ir.hosseinabbasi.mediamonks.di.component;

import dagger.Component;
import ir.hosseinabbasi.mediamonks.di.PerActivity;
import ir.hosseinabbasi.mediamonks.di.module.ActivityModule;
import ir.hosseinabbasi.mediamonks.ui.album.AlbumListView;
import ir.hosseinabbasi.mediamonks.ui.detail.DetailView;
import ir.hosseinabbasi.mediamonks.ui.main.MainActivity;
import ir.hosseinabbasi.mediamonks.ui.photo.PhotoListView;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
    void inject(AlbumListView fragment);
    void inject(PhotoListView fragment);
    void inject(DetailView fragment);

}
