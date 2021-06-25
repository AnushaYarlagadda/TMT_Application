package com.anusha.tmtapplication.mvvm.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.anusha.tmtapplication.R;
import com.anusha.tmtapplication.mvvm.data.DataManager;
import com.anusha.tmtapplication.mvvm.data.model.Card;
import com.anusha.tmtapplication.mvvm.data.repository.main.DataRepository;
import com.anusha.tmtapplication.mvvm.ui.base.BaseActivity;

import java.util.List;


public class MainActivity extends BaseActivity<MainViewModel> implements CardAdapter.CardListener {

    RecyclerView recyclerView;
    ProgressBar progressBar;

    private CardAdapter cardAdapter;

    @NonNull
    @Override
    protected MainViewModel createViewModel() {
        DataRepository dataRepository = DataManager.getInstance().getDataRepository();
        MainViewModelFactory factory = new MainViewModelFactory(dataRepository);
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        cardAdapter = new CardAdapter(this);
        recyclerView.setAdapter(cardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        observeViewModel();
        viewModel.loadData();
    }

    private void observeViewModel() {
        viewModel.getShowLoadingLiveData().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        viewModel.getHideLoadingLiveData().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                progressBar.setVisibility(View.GONE);
            }
        });

        viewModel.getCardLiveData().observe(this, new Observer<List<Card>>() {
            @Override
            public void onChanged(@Nullable List<Card> cards) {
                cardAdapter.setItems(cards);
            }
        });

        viewModel.getShowErrorMessageLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCardClicked(Card card) {
        //If we want to implement further to details page, we can handle it here
    }
}