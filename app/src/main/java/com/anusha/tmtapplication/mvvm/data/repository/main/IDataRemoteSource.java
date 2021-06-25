package com.anusha.tmtapplication.mvvm.data.repository.main;

public interface IDataRemoteSource {
    void getData(DataRepository.LoadDataCallback callback);
}
