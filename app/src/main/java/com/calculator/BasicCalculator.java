package com.calculator;

public class BasicCalculator extends Calculator {

    public static Double setNumber(Double number){
        if (currentWaitingNumberToSave == NUMBER.FIRST_NUMBER){
            firstNumber = number;
            currentWaitingNumberToSave = NUMBER.SECOND_NUMBER;
        }else if (currentWaitingNumberToSave == NUMBER.SECOND_NUMBER){
            secondNumber = number;
        }
        return number;
    }
}
