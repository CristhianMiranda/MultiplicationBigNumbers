package org.example.algorithms;

public class KaratsubaMultiplicationStatic {
    public static int karatsubaMultiplication(int num1, int num2) {
        int result;

        if (num1 < 10 || num2 < 10) {
            result = num1 * num2;
        }
        else {
            int n = Math.max(Integer.toString(num1).length(), Integer.toString(num2).length());
            int m = (int) Math.ceil(n / 2.0);
            int a = (int) (num1 / Math.pow(10, m));
            int b = (int) (num1 % Math.pow(10, m));
            int c = (int) (num2 / Math.pow(10, m));
            int d = (int) (num2 % Math.pow(10, m));

            int ac = karatsubaMultiplication(a, c);
            int bd = karatsubaMultiplication(b, d);
            int adPlusBc = karatsubaMultiplication(a + b, c + d) - ac - bd;

            result = (int) (ac * Math.pow(10, 2 * m) + adPlusBc * Math.pow(10, m) + bd);
        }

        return result;
    }

}
