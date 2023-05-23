package org.example.algorithms;

import java.math.BigInteger;

public class _11EgipciaEstatico {

    public static void multiplicacionEgipcia(BigInteger[] a, BigInteger[] b) {
        BigInteger result = BigInteger.ZERO;
        BigInteger dos = BigInteger.valueOf(2);

        while (a[0].compareTo(BigInteger.ONE) >= 0) {
            if (a[0].mod(dos).equals(BigInteger.ONE)) {
                result = result.add(b[0]);
            }
            a[0] = a[0].divide(dos);
            b[0] = b[0].multiply(dos);
        }

        a[0] = result;
    }

}
