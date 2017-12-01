package ir.hosseinabbasi.mediamonks.data.network;

import java.util.List;

import io.reactivex.Observable;
import ir.hosseinabbasi.mediamonks.data.db.model.Album;
import ir.hosseinabbasi.mediamonks.data.db.model.Photo;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public interface ApiService {

    @GET("albums")
    Observable<List<Album>> getAlbums(@Query("_start") int pageNumber, @Query("_limit") int perPage);

    @GET("photos")
    Observable<List<Photo>> getPhotos(@Query("albumId") String albumId);

}
