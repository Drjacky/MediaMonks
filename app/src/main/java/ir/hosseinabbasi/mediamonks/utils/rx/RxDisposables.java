package ir.hosseinabbasi.mediamonks.utils.rx;

import io.reactivex.disposables.Disposable;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public interface RxDisposables {

    void add(Disposable disposable);

    void clear();

}
