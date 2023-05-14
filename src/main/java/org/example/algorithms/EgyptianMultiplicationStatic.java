package org.example.algorithms;

public class EgyptianMultiplicationStatic {
    public static int egyptianMultiplication(int num1, int num2) {
        int result = 0;
        int multiplicand = num1;
        int multiplier = num2;

        while (multiplier > 0) {
            if (multiplier % 2 == 1) {
                result += multiplicand;
            }
            multiplicand *= 2;
            multiplier /= 2;
        }
        return result;
    }

}
