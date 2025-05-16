package com.example.artemka;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProvider;
import com.example.artemka.HistoryAdapter;
import com.example.artemka.SharedPrefsHelper;
import com.example.artemka.CurrencyViewModel;

public class HistoryActivity extends AppCompatActivity {
    private CurrencyViewModel viewModel;
    private HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (SharedPrefsHelper.loadTheme(this)) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        viewModel = new ViewModelProvider(this).get(CurrencyViewModel.class);
        initViews();
        setupRecyclerView();
    }

    private void initViews() {
        findViewById(R.id.btnClearHistory).setOnClickListener(v -> {
            viewModel.clearHistory();
            adapter.notifyDataSetChanged();
        });
    }

    private void setupRecyclerView() {
        RecyclerView rvHistory = findViewById(R.id.rvHistory);
        adapter = new HistoryAdapter(viewModel.getHistory());
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(adapter);
    }
}