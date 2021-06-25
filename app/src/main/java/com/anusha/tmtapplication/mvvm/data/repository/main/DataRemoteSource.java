package com.anusha.tmtapplication.mvvm.data.repository.main;

import com.anusha.tmtapplication.mvvm.data.api.DataApi;
import com.anusha.tmtapplication.mvvm.data.model.Card;
import com.anusha.tmtapplication.mvvm.data.model.PageModelResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRemoteSource implements IDataRemoteSource {

    private static DataRemoteSource instance;

    private final DataApi dataApi;

    private DataRemoteSource(DataApi dataApi) {
        this.dataApi = dataApi;
    }

    public static DataRemoteSource getInstance(DataApi dataApi) {
        if (instance == null) {
            instance = new DataRemoteSource(dataApi);
        }
        return instance;
    }

    @Override
    public void getData(DataRepository.LoadDataCallback callback) {
        dataApi.getData().enqueue(new Callback<PageModelResponse>() {
            @Override
            public void onResponse(Call<PageModelResponse> call, Response<PageModelResponse> response) {
                if (response.body() != null && response.body().page != null) {
                    List<Card> cards = response.body().page.cards;
                    if (cards != null && !cards.isEmpty()) {
                        callback.onDataLoaded(cards);
                    } else {
                        callback.onDataNotAvailable();
                    }
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<PageModelResponse> call, Throwable t) {
                callback.onError();
            }
        });
    }
}
