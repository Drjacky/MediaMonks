package ir.hosseinabbasi.mediamonks.ui.album;

import java.util.List;

import ir.hosseinabbasi.mediamonks.data.db.model.Album;
import ir.hosseinabbasi.mediamonks.ui.base.IBaseView;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public interface IAlbumListView extends IBaseView{

    void loadAlbumList(List<Album> albumList);

    void loadAlbumDetail(Album album);

    void setIsLoading(Boolean isLoading);

}
