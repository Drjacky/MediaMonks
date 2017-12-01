package ir.hosseinabbasi.mediamonks.data;

import java.util.List;

import io.reactivex.Observable;
import ir.hosseinabbasi.mediamonks.data.db.model.Album;
import ir.hosseinabbasi.mediamonks.data.db.model.Photo;
import ir.hosseinabbasi.mediamonks.data.network.ApiService;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public interface DataManager extends ApiService {

    @Override
    Observable<List<Album>> getAlbums(int pageNumber, int perPage);

    @Override
    Observable<List<Photo>> getPhotos(String albumId);

}
