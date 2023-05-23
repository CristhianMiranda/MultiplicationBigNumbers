package org.example.algorithms;

import java.math.BigInteger;

public class _10HinduEstatico {
    public static void induEstatico(BigInteger[] num1, BigInteger[] num2) {
        int len1 = num1.length;
        int len2 = num2.length;
        int len = len1 + len2;
        BigInteger[] result = new BigInteger[len];

        for (int i = 0; i < len; i++) {
            result[i] = BigInteger.ZERO;
        }

        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int index = i + j + 1;
                BigInteger mul = num1[i].multiply(num2[j]);

                result[index] = result[index].add(mul);

                if (result[index].compareTo(BigInteger.TEN) >= 0) {
                    BigInteger carry = result[index].divide(BigInteger.TEN);
                    result[index] = result[index].mod(BigInteger.TEN);
                    result[index - 1] = result[index - 1].add(carry);
                }
            }
        }


    }
}