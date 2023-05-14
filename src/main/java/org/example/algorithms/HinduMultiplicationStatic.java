package org.example.algorithms;

public class HinduMultiplicationStatic {
    public static int hinduMultiplication(int num1, int num2) {
        int result = 0;
        int multiplicand = num1;
        int multiplier = num2;
        int digit, factor = 1;

        while (multiplier != 0) {
            digit = multiplier % 10;
            result += multiplicand * digit * factor;
            multiplier /= 10;
            factor *= 10;
        }
        return result;
    }

}
