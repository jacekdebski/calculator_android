package com.calculator;

public class AdvancedCalculator extends BasicCalculator{

    public static void raiseToCustomExponent() {
        lastMathOperations = MATH_OPERATIONS.CUSTOM_EXPONENTIATION;
        if (isNumberEntering) {
            if (currentWaitingNumberToSave == NUMBER.FIRST_NUMBER) {
                result = Double.parseDouble(currentEnteredText);
                currentWaitingNumberToSave = NUMBER.SECOND_NUMBER;
                secondNumber = 0.0;
            } else if (currentWaitingNumberToSave == NUMBER.SECOND_NUMBER) {
                secondNumber = Double.parseDouble(currentEnteredText);
                result = Math.pow(result, secondNumber);
            }
            currentEnteredText = "0";
            isNumberEntering = false;
        }
    }

}
