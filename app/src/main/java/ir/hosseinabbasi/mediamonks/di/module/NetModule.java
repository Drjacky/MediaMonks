package ir.hosseinabbasi.mediamonks.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ir.hosseinabbasi.mediamonks.data.network.ApiEndPoint;
import ir.hosseinabbasi.mediamonks.data.network.ApiService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

@Singleton
@Module
public class NetModule {

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ApiEndPoint.ENDPOINT_JSONPLACEHOLDER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
