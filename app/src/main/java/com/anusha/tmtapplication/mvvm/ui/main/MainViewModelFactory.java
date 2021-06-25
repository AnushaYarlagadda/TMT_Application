package com.anusha.tmtapplication.mvvm.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.anusha.tmtapplication.mvvm.data.repository.main.DataRepository;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final DataRepository dataRepository;

    public MainViewModelFactory(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(dataRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}