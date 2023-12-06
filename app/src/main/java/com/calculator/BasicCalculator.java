package com.calculator;

import android.util.Log;
import android.widget.Toast;

import java.text.DecimalFormat;

public class BasicCalculator extends Calculator {

    public static String setNumber(Integer number) {
        if (currentEnteredText.equals("0")) {
            if (number != 0) {
                currentEnteredText = number.toString();
            }
        } else {
            currentEnteredText = currentEnteredText.concat(number.toString());
        }
        Log.i("BasicCalculator", "current entered text " + currentEnteredText);
        return currentEnteredText;
    }

    public static String clearLastNumber() {
        currentEnteredText = "0";
        return currentEnteredText;
    }

    public static void add() {
        lastMathOperations = MATH_OPERATIONS.ADD;
        if (!currentEnteredText.isEmpty()) {
            if (currentWaitingNumberToSave == NUMBER.FIRST_NUMBER) {
                result = Double.parseDouble(currentEnteredText);
                currentWaitingNumberToSave = NUMBER.SECOND_NUMBER;
            } else if (currentWaitingNumberToSave == NUMBER.SECOND_NUMBER) {
                secondNumber = Double.parseDouble(currentEnteredText);
                result += secondNumber;
            }
            currentEnteredText = "";
        }
    }

    public static void subtract() {
        lastMathOperations = MATH_OPERATIONS.SUBTRACT;
        if (!currentEnteredText.isEmpty()) {
            if (currentWaitingNumberToSave == NUMBER.FIRST_NUMBER) {
                result = Double.parseDouble(currentEnteredText);
                currentWaitingNumberToSave = NUMBER.SECOND_NUMBER;
            } else if (currentWaitingNumberToSave == NUMBER.SECOND_NUMBER) {
                secondNumber = Double.parseDouble(currentEnteredText);
                result -= secondNumber;
            }
            currentEnteredText = "";
        }
    }

    public static void divide() {
        lastMathOperations = MATH_OPERATIONS.DIVIDE;
        if (!currentEnteredText.isEmpty()) {
            if (currentWaitingNumberToSave == NUMBER.FIRST_NUMBER) {
                result = Double.parseDouble(currentEnteredText);
                currentWaitingNumberToSave = NUMBER.SECOND_NUMBER;
            } else if (currentWaitingNumberToSave == NUMBER.SECOND_NUMBER) {
                secondNumber = Double.parseDouble(currentEnteredText);
                if (secondNumber == 0) {
                    throw new ArithmeticException("divide by zero");
                } else {
                    result /= secondNumber;
                }
            }
            currentEnteredText = "";
        }
    }

    public static String setResult() {
        if (!currentEnteredText.isEmpty()) {
            secondNumber = Double.parseDouble(currentEnteredText);
            currentEnteredText = "";
        }

        if (lastMathOperations == MATH_OPERATIONS.NONE) {
            result = secondNumber;
        } else if (lastMathOperations == MATH_OPERATIONS.ADD) {
            result += secondNumber;
        } else if (lastMathOperations == MATH_OPERATIONS.SUBTRACT) {
            result -= secondNumber;
        } else if (lastMathOperations == MATH_OPERATIONS.DIVIDE) {
            if (secondNumber == 0) {
                throw new ArithmeticException("divide by zero");
            } else {
                result /= secondNumber;
            }
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.#######");
        return decimalFormat.format(result);
    }
}
