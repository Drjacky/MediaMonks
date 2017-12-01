package ir.hosseinabbasi.mediamonks.ui.detail;

import javax.inject.Inject;

import ir.hosseinabbasi.mediamonks.data.DataManager;
import ir.hosseinabbasi.mediamonks.ui.base.BasePresenter;
import ir.hosseinabbasi.mediamonks.utils.rx.RxDisposableFactory;
import ir.hosseinabbasi.mediamonks.utils.rx.RxDisposables;
import ir.hosseinabbasi.mediamonks.utils.rx.ThreadTransformer;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public class DetailPresenter<V extends IDetailView> extends BasePresenter<V> implements IDetailPresenter<V> {

    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;

    @Inject
    public DetailPresenter(DataManager dataManager,
                           ThreadTransformer threadTransformer,
                           RxDisposableFactory rxDisposableFactory) {
        super(dataManager, threadTransformer, rxDisposableFactory);
        this.threadTransformer = getThreadTransformer();
        this.disposables = getRxDisposables();
    }

}
