package com.calculator;

import android.util.Log;

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
        isNumberEntering = true;
        return currentEnteredText;
    }

    public static String setComma() {
        Log.i("BasicCalculator", "current entered text " + currentEnteredText);
        if (currentEnteredText.contains(".")) {
            if (currentEnteredText.endsWith(".")) {
                currentEnteredText = currentEnteredText.substring(0, currentEnteredText.length() - 1);
            } else {
                throw new ArithmeticException("invalid inputted comma");
            }
        } else {
            currentEnteredText = currentEnteredText.concat(".");
        }
        Log.i("BasicCalculator", "current entered text " + currentEnteredText);
        isNumberEntering = true;
        return currentEnteredText;
    }

    public static String clearLastNumber() {
        currentEnteredText = "0";
        return currentEnteredText;
    }

    public static String resetCalculator() {
        result = 0.0;
        secondNumber = 0.0;
        currentWaitingNumberToSave = NUMBER.FIRST_NUMBER;
        currentEnteredText = "0";
        isNumberEntering = true;
        lastMathOperations = MATH_OPERATIONS.NONE;
        return currentEnteredText;
    }

    public static String changeSign() {
        if (!currentEnteredText.equals("0")) {
            Double parsedEnteredText = Double.parseDouble(currentEnteredText);
            parsedEnteredText = parsedEnteredText * (-1);

            currentEnteredText = parseDoubleToString(parsedEnteredText);
        } else {
            throw new ArithmeticException("change sign for zero");
        }
        return currentEnteredText;
    }

    public static void add() {
        lastMathOperations = MATH_OPERATIONS.ADD;
        if (isNumberEntering) {
            if (currentWaitingNumberToSave == NUMBER.FIRST_NUMBER) {
                result = Double.parseDouble(currentEnteredText);
                currentWaitingNumberToSave = NUMBER.SECOND_NUMBER;
            } else if (currentWaitingNumberToSave == NUMBER.SECOND_NUMBER) {
                secondNumber = Double.parseDouble(currentEnteredText);
                result += secondNumber;
            }
            currentEnteredText = "0";
            isNumberEntering = false;
        }
    }

    public static void subtract() {
        lastMathOperations = MATH_OPERATIONS.SUBTRACT;
        if (isNumberEntering) {
            if (currentWaitingNumberToSave == NUMBER.FIRST_NUMBER) {
                result = Double.parseDouble(currentEnteredText);
                currentWaitingNumberToSave = NUMBER.SECOND_NUMBER;
            } else if (currentWaitingNumberToSave == NUMBER.SECOND_NUMBER) {
                secondNumber = Double.parseDouble(currentEnteredText);
                result -= secondNumber;
            }
            currentEnteredText = "0";
            isNumberEntering = false;
        }
    }

    public static void divide() {
        lastMathOperations = MATH_OPERATIONS.DIVIDE;
        if (isNumberEntering) {
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
            currentEnteredText = "0";
            isNumberEntering = false;
        }
    }

    public static void multiply() {
        lastMathOperations = MATH_OPERATIONS.MULTIPLY;
        if (isNumberEntering) {
            if (currentWaitingNumberToSave == NUMBER.FIRST_NUMBER) {
                result = Double.parseDouble(currentEnteredText);
                currentWaitingNumberToSave = NUMBER.SECOND_NUMBER;
            } else if (currentWaitingNumberToSave == NUMBER.SECOND_NUMBER) {
                secondNumber = Double.parseDouble(currentEnteredText);
                result *= secondNumber;
            }
            currentEnteredText = "0";
            isNumberEntering = false;
        }
    }

    public static String undoLastSign() {
        if (!currentEnteredText.equals("0")) {
            currentEnteredText = currentEnteredText.substring(0, currentEnteredText.length() - 1);
            if (currentEnteredText.isEmpty() || currentEnteredText.equals("-")) {
                currentEnteredText = "0";
            }
        } else {
            throw new RuntimeException("undo operation is not permitted now");
        }
        return currentEnteredText;
    }

    public static String setResult() {
        if (!currentEnteredText.equals("0")) {
            secondNumber = Double.parseDouble(currentEnteredText);
            currentEnteredText = "0";
        }

        isNumberEntering = false;

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
        } else if (lastMathOperations == MATH_OPERATIONS.MULTIPLY) {
            result *= secondNumber;
        }

        return parseDoubleToString(result);
    }
}
