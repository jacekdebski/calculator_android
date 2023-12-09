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
        PERCENTAGE,
        SIN,
        COS,
        TAN,
        COT
    }

    public static String getCurrentEnteredText() {
        return currentEnteredText;
    }

    public static String setResult() {
        if (!currentEnteredText.equals("0")) {
            secondNumber = Double.parseDouble(currentEnteredText);
            currentEnteredText = "0";
        }

        isNumberEntering = false;
        currentWaitingNumberToSave = NUMBER.FIRST_NUMBER;

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
        } else if (lastMathOperations == MATH_OPERATIONS.CUSTOM_EXPONENTIATION) {
            result = Math.pow(result, secondNumber);
        } else if (lastMathOperations == MATH_OPERATIONS.EXPONENTIATION_TO_POWER_TWO) {
            result = Math.pow(result, 2);
        } else if (lastMathOperations == MATH_OPERATIONS.SQRT) {
            if (result < 0) {
                throw new ArithmeticException("square for negative number");
            }
            result = Math.sqrt(result);
        } else if (lastMathOperations == MATH_OPERATIONS.NATURAL_LOGARITHM) {
            if (result < 0) {
                throw new ArithmeticException("natural logarithm for a negative number or zero");
            }
            result = Math.log(result);
        } else if (lastMathOperations == MATH_OPERATIONS.CUSTOM_LOGARITHM) {
            if (result <= 0) {
                throw new ArithmeticException("logarithm base is a negative number or zero");
            }
            if (secondNumber <= 0) {
                throw new ArithmeticException("logarithm is a negative number or zero");
            }
            result = Math.log(secondNumber) / Math.log(result);
        } else if (lastMathOperations == MATH_OPERATIONS.PERCENTAGE) {
            result = result * secondNumber / 100;
        } else if (lastMathOperations == MATH_OPERATIONS.SIN) {
            result = Math.sin(result);
        } else if (lastMathOperations == MATH_OPERATIONS.COS) {
            result = Math.cos(result);
        } else if (lastMathOperations == MATH_OPERATIONS.TAN) {
            Double temp = Math.tan(result);
            if (temp == Double.POSITIVE_INFINITY || temp == Double.NEGATIVE_INFINITY) {
                throw new ArithmeticException("incorrect inputted value for tangent");
            }
            result = temp;
        } else if (lastMathOperations == MATH_OPERATIONS.COT) {
            Double temp = 1 / Math.tan(result);
            if (temp == Double.POSITIVE_INFINITY || temp == Double.NEGATIVE_INFINITY) {
                throw new ArithmeticException("incorrect inputted value for cotangent");
            }
            result = temp;
        }

        return parseDoubleToString(result);
    }

    protected static String parseDoubleToString(Double numberToParse) {
        Locale locale = new Locale("en", "US");
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern("#.#########");
        return decimalFormat.format(numberToParse);
    }

    protected static void assertPromptSize() {
        if (currentEnteredText.length() > 9) {
            throw new ArithmeticException("too big  inputted number");
        }
    }
}
