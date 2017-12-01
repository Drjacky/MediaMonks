package ir.hosseinabbasi.mediamonks.utils.rx;

import javax.inject.Inject;

import ir.hosseinabbasi.mediamonks.utils.rx.impl.CompositeDisposablesImpl;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public class RxDisposableFactory {

    @Inject
    public RxDisposableFactory() {
    }

    public RxDisposables get() {
        return new CompositeDisposablesImpl();
    }

}
