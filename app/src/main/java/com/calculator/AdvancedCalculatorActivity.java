package com.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdvancedCalculatorActivity extends BasicCalculatorActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calculator);

        setPromptText(Calculator.getCurrentEnteredText());

        initBasicButtonsListeners();

        Button customExponentiationButton = findViewById(R.id.customExponentiation);
        customExponentiationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvancedCalculator.raiseToCustomExponent();
            }
        });

        Button basicCalculatorSwitcherButton = findViewById(R.id.basicCalculatorSwitcherButton);
        basicCalculatorSwitcherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToBasicCalculator();
            }
        });
    }

    private void switchToBasicCalculator() {
        finish();
        Intent intent = new Intent(this, BasicCalculatorActivity.class);
        startActivity(intent);
    }
}