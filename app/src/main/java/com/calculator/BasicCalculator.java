package com.calculator;

import android.util.Log;

public class BasicCalculator extends Calculator {

    public static String setNumber(Integer number){
        currentEnteredText = currentEnteredText.concat(number.toString());
        Log.i("BasicCalculator", "current entered text " + currentEnteredText);
        return currentEnteredText;
    }

    public static String clearLastNumber(){
        currentEnteredText = "0";
        return currentEnteredText;
    }

    public static void add(){
        lastMathOperations = MATH_OPERATIONS.ADD;
        if (!currentEnteredText.isEmpty()){
            if (currentWaitingNumberToSave == NUMBER.FIRST_NUMBER){
                result = Double.parseDouble(currentEnteredText);
                currentWaitingNumberToSave = NUMBER.SECOND_NUMBER;
            }else if (currentWaitingNumberToSave == NUMBER.SECOND_NUMBER){
                secondNumber = Double.parseDouble(currentEnteredText);
                result += secondNumber;
            }
            currentEnteredText = "";
        }
    }

    public static void subtract(){
        lastMathOperations = MATH_OPERATIONS.SUBTRACT;
        if (!currentEnteredText.isEmpty()){
            if (currentWaitingNumberToSave == NUMBER.FIRST_NUMBER){
                result = Double.parseDouble(currentEnteredText);
                currentWaitingNumberToSave = NUMBER.SECOND_NUMBER;
            }else if (currentWaitingNumberToSave == NUMBER.SECOND_NUMBER){
                secondNumber = Double.parseDouble(currentEnteredText);
                result -= secondNumber;
            }
            currentEnteredText = "";
        }
    }

    public static String setResult(){
        if (!currentEnteredText.isEmpty()){
            secondNumber = Double.parseDouble(currentEnteredText);
            currentEnteredText = "";
        }

        if (lastMathOperations == MATH_OPERATIONS.NONE){
            result = secondNumber;
        } else if (lastMathOperations == MATH_OPERATIONS.ADD){
            result += secondNumber;
        } else if (lastMathOperations == MATH_OPERATIONS.SUBTRACT){
            result -= secondNumber;
        }

        return Double.toString(result);
    }
}
