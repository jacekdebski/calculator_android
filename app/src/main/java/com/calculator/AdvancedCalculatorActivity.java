package com.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class AdvancedCalculatorActivity extends BasicCalculatorActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calculator);

        setPromptText(Calculator.getCurrentEnteredText());
        initBackToMenuButtonsListener();

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

        Button customLogarithmButton = findViewById(R.id.customLogarithmButton);
        customLogarithmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvancedCalculator.calculateCustomLogarithm();
            }
        });

        Button percentButton = findViewById(R.id.percentButton);
        percentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvancedCalculator.calculatePercentage();
            }
        });

        Button sinButton = findViewById(R.id.sinButton);
        sinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = AdvancedCalculator.calculateSin();
                setPromptText(result);
            }
        });

        Button cosButton = findViewById(R.id.cosButton);
        cosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = AdvancedCalculator.calculateCos();
                setPromptText(result);
            }
        });

        Button tanButton = findViewById(R.id.tanButton);
        tanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String result = AdvancedCalculator.calculateTan();
                    setPromptText(result);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button cotButton = findViewById(R.id.cotButton);
        cotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String result = AdvancedCalculator.calculateCot();
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

    private void initBackToMenuButtonsListener() {
        Button backToMenuButton = findViewById(R.id.backToMenuButton);
        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvancedCalculator.resetCalculator();
                finish();
            }
        });
    }

    private void switchToBasicCalculator() {
        finish();
        Intent intent = new Intent(this, BasicCalculatorActivity.class);
        startActivity(intent);
    }
}