package ir.hosseinabbasi.mediamonks.ui.photo;

import ir.hosseinabbasi.mediamonks.ui.base.IBasePresenter;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public interface IPhotoListPresenter<V extends IPhotoListView> extends IBasePresenter<V> {

    void getPhotoList(int albumId, int pageNumber, int perPage);

}
