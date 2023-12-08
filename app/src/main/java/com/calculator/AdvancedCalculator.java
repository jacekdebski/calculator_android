package com.calculator;

public class AdvancedCalculator extends BasicCalculator {

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

    public static String raiseToPowerTwo() {
        lastMathOperations = MATH_OPERATIONS.EXPONENTIATION_TO_POWER_TWO;
        if (isNumberEntering) {
            result = Double.parseDouble(currentEnteredText);
        }
        result = Math.pow(result, 2);

        secondNumber = 0.0;
        currentEnteredText = "0";
        isNumberEntering = false;
        return parseDoubleToString(result);
    }

    public static String extractToTheRootOfTwo() {
        lastMathOperations = MATH_OPERATIONS.SQRT;
        if (isNumberEntering) {
            result = Double.parseDouble(currentEnteredText);
        }

        if (result < 0) {
            throw new ArithmeticException("square for negative number");
        }
        result = Math.sqrt(result);

        secondNumber = 0.0;
        currentEnteredText = "0";
        isNumberEntering = false;

        return parseDoubleToString(result);
    }

    public static String calculateNaturalLogarithm() {
        lastMathOperations = MATH_OPERATIONS.NATURAL_LOGARITHM;
        if (isNumberEntering) {
            result = Double.parseDouble(currentEnteredText);
        }

        if (result <= 0) {
            throw new ArithmeticException("natural logarithm for a negative number or zero");
        }
        result = Math.log(result);

        secondNumber = 0.0;
        currentEnteredText = "0";
        isNumberEntering = false;

        return parseDoubleToString(result);
    }

}
