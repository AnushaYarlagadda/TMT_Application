package com.anusha.tmtapplication.mvvm.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anusha.tmtapplication.mvvm.data.model.Card;
import com.anusha.tmtapplication.mvvm.data.repository.main.DataRepository;
import com.anusha.tmtapplication.mvvm.ui.base.BaseViewModel;

import java.util.List;

public class MainViewModel extends BaseViewModel {

    private final MutableLiveData<List<Card>> cardsLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> showErrorMessageLiveData = new MutableLiveData<>();
    private final MutableLiveData<Void> showLoadingLiveData = new MutableLiveData<>();
    private final MutableLiveData<Void> hideLoadingLiveData = new MutableLiveData<>();

    private final DataRepository dataRepository;

    private final DataCallback dataCallback = new DataCallback();

    MainViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void loadData() {
        setIsLoading(true);
        dataRepository.getDataFromApi(dataCallback);
    }

    private void setIsLoading(boolean loading) {
        if (loading) {
            showLoadingLiveData.postValue(null);
        } else {
            hideLoadingLiveData.postValue(null);
        }
    }

    private void setCardLiveData(List<Card> cardsLiveData) {
        setIsLoading(false);
        this.cardsLiveData.postValue(cardsLiveData);
    }

    /**
     * Callback
     **/
    private class DataCallback implements DataRepository.LoadDataCallback {

        @Override
        public void onDataLoaded(List<Card> cards) {
            setCardLiveData(cards);
        }

        @Override
        public void onDataNotAvailable() {
            setIsLoading(false);
             //TODO given more time I would prefer to show no items available text on layout
            showErrorMessageLiveData.postValue("There are no items!");
        }

        @Override
        public void onError() {
            setIsLoading(false);
            //TODO given more time I would prefer to have Error screen to uset
            showErrorMessageLiveData.postValue("Something Went Wrong!");
        }
    }

    /**
     * LiveData
     **/
    public LiveData<List<Card>> getCardLiveData() {
        return cardsLiveData;
    }

    public LiveData<Void> getShowLoadingLiveData() {
        return showLoadingLiveData;
    }

    public LiveData<String> getShowErrorMessageLiveData() {
        return showErrorMessageLiveData;
    }

    public LiveData<Void> getHideLoadingLiveData() {
        return hideLoadingLiveData;
    }

}
