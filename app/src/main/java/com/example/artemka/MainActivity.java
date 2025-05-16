package com.example.artemka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import com.example.artemka.SharedPrefsHelper;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CurrencyViewModel viewModel;
    private Spinner spinnerFrom, spinnerTo;
    private EditText etAmount;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Установка темы перед setContentView
        if (SharedPrefsHelper.loadTheme(this)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(CurrencyViewModel.class);
        initViews();
        setupSpinners();
        setupButtons();
    }

    private void initViews() {
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        etAmount = findViewById(R.id.etAmount);
        tvResult = findViewById(R.id.tvResult);
    }

    private void setupSpinners() {
        List<Currency> currencies = viewModel.getCurrencies();
        ArrayAdapter<Currency> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
        spinnerTo.setSelection(3); // Доллар по умолчанию
    }

    private void setupButtons() {
        Button btnConvert = findViewById(R.id.btnConvert);
        Button btnHistory = findViewById(R.id.btnHistory);
        Button btnTheme = findViewById(R.id.btnTheme);

        btnConvert.setOnClickListener(v -> convertCurrency());
        btnHistory.setOnClickListener(v -> openHistory());
        btnTheme.setOnClickListener(v -> toggleTheme());
    }

    private void convertCurrency() {
        try {
            Currency from = (Currency) spinnerFrom.getSelectedItem();
            Currency to = (Currency) spinnerTo.getSelectedItem();
            double amount = Double.parseDouble(etAmount.getText().toString());

            double result = CurrencyConverter.convert(amount, from, to);
            tvResult.setText(String.format("%.2f %s = %.2f %s", amount, from.getName(), result, to.getName()));

            // Сохраняем в историю
            ConversionHistory historyItem = new ConversionHistory(
                    from.getName(), to.getName(), amount, result, new Date());
            viewModel.addToHistory(historyItem);
        } catch (NumberFormatException e) {
            tvResult.setText("Введите корректную сумму");
        }
    }

    private void openHistory() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    private void toggleTheme() {
        boolean isDarkTheme = SharedPrefsHelper.loadTheme(this);
        SharedPrefsHelper.saveTheme(this, !isDarkTheme);
        recreate();
    }
}