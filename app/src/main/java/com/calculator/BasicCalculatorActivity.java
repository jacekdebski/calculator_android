package com.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BasicCalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_calculator);

        setPromptText(Calculator.getCurrentEnteredText());

        initBasicButtonsListeners();

        Button advancedCalculatorSwitcherButton = findViewById(R.id.advancedCalculatorSwitcherButton);
        advancedCalculatorSwitcherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToAdvancedCalculator();
            }
        });
    }

    protected void initBasicButtonsListeners() {
        Button zeroButton = findViewById(R.id.zeroButton);
        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = BasicCalculator.setNumber(0);
                setPromptText(result);
            }
        });

        Button oneButton = findViewById(R.id.oneButton);
        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = BasicCalculator.setNumber(1);
                setPromptText(result);
            }
        });

        Button twoButton = findViewById(R.id.twoButton);
        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = BasicCalculator.setNumber(2);
                setPromptText(result);
            }
        });

        Button threeButton = findViewById(R.id.threeButton);
        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = BasicCalculator.setNumber(3);
                setPromptText(result);
            }
        });

        Button fourButton = findViewById(R.id.fourButton);
        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = BasicCalculator.setNumber(4);
                setPromptText(result);
            }
        });

        Button fiveButton = findViewById(R.id.fiveButton);
        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = BasicCalculator.setNumber(5);
                setPromptText(result);
            }
        });

        Button sixButton = findViewById(R.id.sixButton);
        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = BasicCalculator.setNumber(6);
                setPromptText(result);
            }
        });

        Button sevenButton = findViewById(R.id.sevenButton);
        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = BasicCalculator.setNumber(7);
                setPromptText(result);
            }
        });

        Button eightButton = findViewById(R.id.eightButton);
        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = BasicCalculator.setNumber(8);
                setPromptText(result);
            }
        });

        Button nineButton = findViewById(R.id.nineButton);
        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = BasicCalculator.setNumber(9);
                setPromptText(result);
            }
        });

        Button plusButton = findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasicCalculator.add();
            }
        });

        Button minusButton = findViewById(R.id.minusButton);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasicCalculator.subtract();
            }
        });

        Button divisionButton = findViewById(R.id.divisionButton);
        divisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BasicCalculator.divide();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button multiplicationButton = findViewById(R.id.multiplicationButton);
        multiplicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasicCalculator.multiply();
            }
        });

        Button changeSignButton = findViewById(R.id.changeSignButton);
        changeSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String result = BasicCalculator.changeSign();
                    setPromptText(result);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button commaButton = findViewById(R.id.commaButton);
        commaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String result = BasicCalculator.setComma();
                    setPromptText(result);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button backspaceButton = findViewById(R.id.backspaceButton);
        backspaceButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String result = BasicCalculator.undoLastSign();
                    setPromptText(result);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }));

        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = BasicCalculator.clearLastNumber();
                setPromptText(result);
            }
        }));

        Button allClearButton = findViewById(R.id.allClearButton);
        allClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = BasicCalculator.resetCalculator();
                setPromptText(result);
            }
        });

        Button equalButton = findViewById(R.id.equalButton);
        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String result = BasicCalculator.setResult();
                    setPromptText(result);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button backToMenuButton = findViewById(R.id.backToMenuButton);
        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void setPromptText(String textToWrite) {
        TextView promptTextView = findViewById(R.id.promptTextView);
        promptTextView.setText(textToWrite);
    }

    private void switchToAdvancedCalculator() {
        finish();
        Intent intent = new Intent(this, AdvancedCalculatorActivity.class);
        startActivity(intent);
    }
}