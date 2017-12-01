package ir.hosseinabbasi.mediamonks.ui.album;

import ir.hosseinabbasi.mediamonks.ui.base.IBasePresenter;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public interface IAlbumListPresenter<V extends IAlbumListView> extends IBasePresenter<V> {

    void getAlbumList(int pageNumber, int perPage);

}
