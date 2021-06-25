package com.anusha.tmtapplication.mvvm.data.service;

import com.anusha.tmtapplication.mvvm.data.api.DataApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataService {

    private static final String URL = "https://private-8ce77c-tmobiletest.apiary-mock.com/";

    private final DataApi dataApi;

    private static DataService singleton;

    private DataService() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL).build();

        dataApi = mRetrofit.create(DataApi.class);
    }

    public static DataService getInstance() {
        if (singleton == null) {
            synchronized (DataService.class) {
                if (singleton == null) {
                    singleton = new DataService();
                }
            }
        }
        return singleton;
    }

    public DataApi getDataApi() {
        return dataApi;
    }
}
