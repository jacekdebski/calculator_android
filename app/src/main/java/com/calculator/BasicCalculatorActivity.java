package com.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BasicCalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_calculator);

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

        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = BasicCalculator.clearLastNumber();
                setPromptText(result);
            }
        }));

        Button equalButton = findViewById(R.id.equalButton);
        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = BasicCalculator.setResult();
                setPromptText(result);
            }
        });
    }

    void setPromptText(String textToWrite) {
        TextView promptTextView = findViewById(R.id.promptTextView);
        promptTextView.setText(textToWrite);
    }
}