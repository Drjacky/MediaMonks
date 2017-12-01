package ir.hosseinabbasi.mediamonks.ui.photo;

import android.util.Log;

import javax.inject.Inject;

import ir.hosseinabbasi.mediamonks.data.DataManager;
import ir.hosseinabbasi.mediamonks.ui.base.BasePresenter;
import ir.hosseinabbasi.mediamonks.utils.rx.RxDisposableFactory;
import ir.hosseinabbasi.mediamonks.utils.rx.RxDisposables;
import ir.hosseinabbasi.mediamonks.utils.rx.ThreadTransformer;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public class PhotoListPresenter<V extends IPhotoListView> extends BasePresenter<V> implements IPhotoListPresenter<V> {

    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;

    @Inject
    public PhotoListPresenter(DataManager dataManager,
                              ThreadTransformer threadTransformer,
                              RxDisposableFactory rxDisposableFactory) {
        super(dataManager, threadTransformer, rxDisposableFactory);
        this.threadTransformer = getThreadTransformer();
        this.disposables = getRxDisposables();
    }

    @Override
    public void getPhotoList(int albumId, int pageNumber, int perPage) {
        getMvpView().showLoading();
        getMvpView().setIsLoading(true);

        disposables.add(getDataManager().getPhotos(albumId, pageNumber, perPage)
                .compose(threadTransformer.applySchedulers())
                .subscribe(result -> {
                    getMvpView().hideLoading();
                    getMvpView().loadPhotoList(result);
                    getMvpView().setIsLoading(false);
                }, throwable -> {
                    getMvpView().hideLoading();
                    getMvpView().setIsLoading(false);
                    Log.wtf("getPhotoList", throwable.getMessage()+"");
                }));
    }

}
