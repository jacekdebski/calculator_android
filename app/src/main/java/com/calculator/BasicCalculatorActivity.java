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
                BasicCalculator basicCalculator = new BasicCalculator();
                Double result = basicCalculator.onClickOne(1.0);
                setPromptText(result.toString());
            }
        });
    }

    void setPromptText(String textToWrite) {
        TextView promptTextView = findViewById(R.id.promptTextView);
        promptTextView.setText(textToWrite);
    }
}