package org.example.algorithms;

import java.math.BigInteger;

public class _11EgipciaEstatico {

    public static BigInteger multiplicacionEgipcia(BigInteger[] a, BigInteger[] b) {
        BigInteger result = BigInteger.ZERO;
        while (a[0].compareTo(BigInteger.ONE) >= 0) {
            if (a[0].mod(BigInteger.valueOf(2)).equals(BigInteger.ONE)) {
                result = result.add(b[0]);
            }
            a[0] = a[0].divide(BigInteger.valueOf(2));
            b[0] = b[0].multiply(BigInteger.valueOf(2));
        }
        return result;
    }
}
