package com.anusha.tmtapplication.mvvm.data.repository.main;

import com.anusha.tmtapplication.mvvm.data.model.Card;

import java.util.List;

public interface DataRepository {

    interface LoadDataCallback {
        void onDataLoaded(List<Card> cards);

        void onDataNotAvailable();

        void onError();
    }

    void getDataFromApi(LoadDataCallback callback);
}
