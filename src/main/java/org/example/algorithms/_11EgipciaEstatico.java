package org.example.algorithms;

import java.math.BigInteger;

public class _11EgipciaEstatico {

    public static BigInteger multiplicacionEgipcia(BigInteger a, BigInteger b) {
        BigInteger result = BigInteger.ZERO;
        while (a.compareTo(BigInteger.ONE) >= 0) {
            if (a.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE)) {
                result = result.add(b);
            }
            a = a.divide(BigInteger.valueOf(2));
            b = b.multiply(BigInteger.valueOf(2));
        }
        return result;
    }
}
