package com.calculator;

import android.util.Log;

public class BasicCalculator extends Calculator {

    public static String setNumber(Integer number){
        currentEnteredText = currentEnteredText.concat(number.toString());
        Log.i("BasicCalculator", "current entered text " + currentEnteredText);
        return currentEnteredText;
    }

    public static void add(){
        if (currentWaitingNumberToSave == NUMBER.FIRST_NUMBER){
            firstNumber = Double.valueOf(currentEnteredText);
            currentWaitingNumberToSave = NUMBER.SECOND_NUMBER;
            currentEnteredText = "";
        }else if (currentWaitingNumberToSave == NUMBER.SECOND_NUMBER){
            secondNumber = Double.parseDouble(currentEnteredText);
            result = firstNumber + secondNumber;
        }
    }

    public static String setResult(){
        if (currentWaitingNumberToSave == NUMBER.FIRST_NUMBER){

        }else if (currentWaitingNumberToSave == NUMBER.SECOND_NUMBER){
            currentWaitingNumberToSave = NUMBER.FIRST_NUMBER;
            secondNumber = Double.parseDouble(currentEnteredText);
            currentEnteredText = "";
            result = firstNumber + secondNumber;
        }

        return Double.toString(result);
    }
}
