package org.example.algorithms;

import java.math.BigInteger;

public class _9RusaIEstatica {

    private static BigInteger dos = new BigInteger("2");
    public static BigInteger rusoEstatico(BigInteger num1, BigInteger num2) {
        if (num2.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        } else if (num2.equals(BigInteger.ONE)) {
            return num1;
        } else {
            if (num2.remainder(dos).equals(BigInteger.ZERO)) {
                return rusoEstatico(num1.multiply(dos), num2.divide(dos));
            } else {
                return rusoEstatico(num1.multiply(dos), num2.divide(dos)).add(num1);
            }
        }
    }
}
