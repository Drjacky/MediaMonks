package ir.hosseinabbasi.mediamonks.di.component;

import javax.inject.Singleton;

import ir.hosseinabbasi.mediamonks.data.network.ApiService;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

@Singleton
public interface NetComponent {

    @Singleton
    ApiService exposeApiService();

}
