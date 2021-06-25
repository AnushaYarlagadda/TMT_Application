package com.anusha.tmtapplication.mvvm.data.api;

import com.anusha.tmtapplication.mvvm.data.model.PageModelResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataApi {
    @GET("test/home")
    Call<PageModelResponse> getData();
}
