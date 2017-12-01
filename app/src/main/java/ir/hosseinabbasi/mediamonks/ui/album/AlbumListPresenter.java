package ir.hosseinabbasi.mediamonks.ui.album;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import ir.hosseinabbasi.mediamonks.data.DataManager;
import ir.hosseinabbasi.mediamonks.data.db.model.Album;
import ir.hosseinabbasi.mediamonks.ui.base.BasePresenter;
import ir.hosseinabbasi.mediamonks.utils.rx.RxDisposableFactory;
import ir.hosseinabbasi.mediamonks.utils.rx.RxDisposables;
import ir.hosseinabbasi.mediamonks.utils.rx.ThreadTransformer;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public class AlbumListPresenter<V extends IAlbumListView> extends BasePresenter<V> implements IAlbumListPresenter<V> {

    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;

    @Inject
    public AlbumListPresenter(DataManager dataManager,
                             ThreadTransformer threadTransformer,
                             RxDisposableFactory rxDisposableFactory) {
        super(dataManager, threadTransformer, rxDisposableFactory);
        this.threadTransformer = getThreadTransformer();
        this.disposables = getRxDisposables();
    }

    @Override
    public void getAlbumList(int pageNumber, int perPage) {
        getMvpView().showLoading();
        getMvpView().setIsLoading(true);

        disposables.add(getDataManager().getAlbums(pageNumber, perPage)
                .compose(threadTransformer.applySchedulers())
                .subscribe(result -> {
                    getMvpView().hideLoading();
                    getMvpView().loadAlbumList(result);
                    getMvpView().setIsLoading(false);
                }, throwable -> {
                    getMvpView().hideLoading();
                    getMvpView().setIsLoading(false);
                    Log.wtf("getAlbumList", throwable.getMessage()+"");
                }));
    }

}
