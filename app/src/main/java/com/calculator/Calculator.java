package com.calculator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Calculator {
    protected static Double secondNumber = 0.0;
    protected static Double result = 0.0;
    protected static NUMBER currentWaitingNumberToSave = NUMBER.FIRST_NUMBER;
    protected static String currentEnteredText = "0";
    protected static Boolean isNumberEntering = true;
    protected static MATH_OPERATIONS lastMathOperations = MATH_OPERATIONS.NONE;

    enum NUMBER {
        FIRST_NUMBER,
        SECOND_NUMBER
    }

    enum MATH_OPERATIONS {
        NONE,
        ADD,
        SUBTRACT,
        DIVIDE,
        MULTIPLY,
        CUSTOM_EXPONENTIATION,
        EXPONENTIATION_TO_POWER_TWO,
        SQRT,
        NATURAL_LOGARITHM,
        CUSTOM_LOGARITHM,
        PERCENTAGE
    }

    public static String getCurrentEnteredText() {
        return currentEnteredText;
    }

    protected static String parseDoubleToString(Double numberToParse) {
        Locale locale = new Locale("en", "US");
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern("#.#######");
        return decimalFormat.format(numberToParse);
    }
}
