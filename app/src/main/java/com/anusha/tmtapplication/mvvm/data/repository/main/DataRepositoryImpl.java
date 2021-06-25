package com.anusha.tmtapplication.mvvm.data.repository.main;

public class DataRepositoryImpl implements DataRepository {

    private final DataRemoteSource dataRemoteSource;

    private static DataRepositoryImpl instance;

    private DataRepositoryImpl(DataRemoteSource dataRemoteSource) {

        this.dataRemoteSource = dataRemoteSource;
    }

    public static DataRepositoryImpl getInstance(DataRemoteSource dataRemoteSource) {
        if (instance == null) {
            instance = new DataRepositoryImpl(dataRemoteSource);
        }
        return instance;
    }

    @Override
    public void getDataFromApi(LoadDataCallback callback) {
        dataRemoteSource.getData(callback);
    }
}