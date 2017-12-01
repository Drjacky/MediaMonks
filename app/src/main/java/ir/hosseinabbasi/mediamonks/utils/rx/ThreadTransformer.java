package ir.hosseinabbasi.mediamonks.utils.rx;

import io.reactivex.ObservableTransformer;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public interface ThreadTransformer {

    <T> ObservableTransformer<T, T> applySchedulers();

}
