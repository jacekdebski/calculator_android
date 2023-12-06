package com.calculator;

public class Calculator {
    protected static Double secondNumber = 0.0;
    protected static Double result = 0.0;
    protected static NUMBER currentWaitingNumberToSave = NUMBER.FIRST_NUMBER;
    protected static String currentEnteredText = "0";
    protected static MATH_OPERATIONS lastMathOperations = MATH_OPERATIONS.NONE;

    enum NUMBER {
        FIRST_NUMBER,
        SECOND_NUMBER
    }

    enum MATH_OPERATIONS {
        NONE,
        ADD,
        SUBTRACT,
        DIVIDE
    }

    public static String getCurrentEnteredText() {
        return currentEnteredText;
    }
}
