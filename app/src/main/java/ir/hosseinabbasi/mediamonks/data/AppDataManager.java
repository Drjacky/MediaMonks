package ir.hosseinabbasi.mediamonks.data;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import ir.hosseinabbasi.mediamonks.data.db.model.Album;
import ir.hosseinabbasi.mediamonks.data.db.model.Photo;
import ir.hosseinabbasi.mediamonks.data.network.ApiService;
import ir.hosseinabbasi.mediamonks.di.ApplicationContext;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final ApiService mApiService;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          ApiService apiService) {
        mContext = context;
        mApiService = apiService;
    }

    @Override
    public Observable<List<Album>> getAlbums(int pageNumber, int perPage) {
        return mApiService.getAlbums(pageNumber, perPage);
    }

    @Override
    public Observable<List<Photo>> getPhotos(String albumId) {
        return mApiService.getPhotos(albumId);
    }

}
