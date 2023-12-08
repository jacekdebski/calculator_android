package com.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdvancedCalculatorActivity extends BasicCalculatorActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calculator);

        setPromptText(Calculator.getCurrentEnteredText());

        initBasicButtonsListeners();
        initAdvancedButtonsListeners();
    }

    private void initAdvancedButtonsListeners() {
        Button squareRootButton = findViewById(R.id.squareRootButton);
        squareRootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String result = AdvancedCalculator.extractToTheRootOfTwo();
                    setPromptText(result);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button customExponentiationButton = findViewById(R.id.customExponentiationButton);
        customExponentiationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvancedCalculator.raiseToCustomExponent();
            }
        });

        Button exponentiationToPowerTwoButton = findViewById(R.id.exponentiationToPowerTwoButton);
        exponentiationToPowerTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = AdvancedCalculator.raiseToPowerTwo();
                setPromptText(result);
            }
        });

        Button naturalLogarithmButton = findViewById(R.id.naturalLogarithmButton);
        naturalLogarithmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String result = AdvancedCalculator.calculateNaturalLogarithm();
                    setPromptText(result);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
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