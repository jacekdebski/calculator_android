package com.calculator;

public class Calculator {
    protected static Double firstNumber = 0.0;
    protected static Double secondNumber = 0.0;
    protected static Double result = 0.0;
    protected static NUMBER currentWaitingNumberToSave = NUMBER.FIRST_NUMBER;
    protected static String currentEnteredText = "";

    enum NUMBER {
        FIRST_NUMBER,
        SECOND_NUMBER
    }
}
