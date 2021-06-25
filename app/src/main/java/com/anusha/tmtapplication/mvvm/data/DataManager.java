package com.anusha.tmtapplication.mvvm.data;

import com.anusha.tmtapplication.mvvm.data.api.DataApi;
import com.anusha.tmtapplication.mvvm.data.repository.main.DataRemoteSource;
import com.anusha.tmtapplication.mvvm.data.repository.main.DataRepository;
import com.anusha.tmtapplication.mvvm.data.repository.main.DataRepositoryImpl;
import com.anusha.tmtapplication.mvvm.data.service.DataService;

public class DataManager {

    private static DataManager sInstance;

    private DataManager() {
        // This class is not publicly instantiable
    }

    public static synchronized DataManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataManager();
        }
        return sInstance;
    }

    public DataRepository getDataRepository() {
        DataApi dataApi = DataService.getInstance().getDataApi();
        DataRemoteSource dataRemote = DataRemoteSource.getInstance(dataApi);
        return DataRepositoryImpl.getInstance(dataRemote);
    }
}
