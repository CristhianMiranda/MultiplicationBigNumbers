package org.example.algorithms;

public class RussianMultiplicationIterative {
    public static int russianMultiply(int num1, int num2) {
        int result = 0;
        while (num1 > 0) {
            if (num1 % 2 != 0) {
                result += num2;
            }
            num1 /= 2;
            num2 *= 2;
        }
        return result;
    }

}
