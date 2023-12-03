package com.calculator;

import android.util.Log;

public class BasicCalculator extends Calculator {

    public static String setNumber(Integer number){
        currentEnteredText = currentEnteredText.concat(number.toString());
        Log.i("BasicCalculator", "current entered text " + currentEnteredText);
        if (currentWaitingNumberToSave == NUMBER.FIRST_NUMBER){
            firstNumber = Double.valueOf(currentEnteredText);
        }else if (currentWaitingNumberToSave == NUMBER.SECOND_NUMBER){
            secondNumber = Double.parseDouble(currentEnteredText);;
        }
        return currentEnteredText;
    }
}
