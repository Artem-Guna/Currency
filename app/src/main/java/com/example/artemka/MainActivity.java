package com.example.artemka;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.artemka.Currency;
import com.example.artemka.CurrencyViewModel;

public class MainActivity extends AppCompatActivity {
    private CurrencyViewModel viewModel;
    private Spinner spinnerFrom, spinnerTo;
    private EditText etAmount;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        ArrayAdapter<Currency> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, viewModel.getCurrencies());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
        spinnerTo.setSelection(3);
    }

    private void setupButtons() {
        Button btnConvert = findViewById(R.id.btnConvert);
        btnConvert.setOnClickListener(v -> convertCurrency());
    }

    private void convertCurrency() {
        try {
            Currency from = (Currency) spinnerFrom.getSelectedItem();
            Currency to = (Currency) spinnerTo.getSelectedItem();
            double amount = Double.parseDouble(etAmount.getText().toString());

            double result = (amount * from.getRate()) / to.getRate();
            tvResult.setText(String.format("%.2f %s = %.2f %s",
                    amount, from.getName(), result, to.getName()));

        } catch (NumberFormatException e) {
            tvResult.setText("Введите корректную сумму");
        }
    }
}