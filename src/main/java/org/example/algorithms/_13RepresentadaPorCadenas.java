package org.example.algorithms;

import java.math.BigInteger;

public class _13RepresentadaPorCadenas {

    public static void representadaPorCadenas(String num1, String num2) {
        int n = num1.length();
        int m = num2.length();
        int[] product = new int[n + m];

        for (int i = n - 1; i >= 0; i--) {
            int carry = 0;
            int digit1 = num1.charAt(i) - '0';

            for (int j = m - 1; j >= 0; j--) {
                int digit2 = num2.charAt(j) - '0';
                int temp = digit1 * digit2 + product[i + j + 1] + carry;
                product[i + j + 1] = temp % 10;
                carry = temp / 10;
            }

            product[i] += carry;
        }

        StringBuilder sb = new StringBuilder();
        for (int digit : product) {
            sb.append(digit);
        }

        // Remove leading zeros
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        System.out.println(sb.toString());
    }
}