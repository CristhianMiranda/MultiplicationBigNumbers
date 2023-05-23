package org.example.algorithms;

import java.math.BigInteger;

public class _9RusaIEstatica {

    private static BigInteger dos = new BigInteger("2");

    private static BigInteger resultado = BigInteger.ZERO;

    public static void rusoEstatico(BigInteger[] num1, BigInteger[] num2) {
        if (num2[0].equals(BigInteger.ZERO)) {
            resultado = BigInteger.ZERO;
        } else if (num2[0].equals(BigInteger.ONE)) {
            resultado = num1[0];
        } else {
            if (num2[0].remainder(dos).equals(BigInteger.ZERO)) {
                num1[0] = num1[0].multiply(dos);
                num2[0] = num2[0].divide(dos);
                rusoEstatico(num1, num2);
            } else {
                num1[0] = num1[0].multiply(dos);
                num2[0] = num2[0].divide(dos);
                rusoEstatico(num1, num2);
                resultado = resultado.add(num1[0]);
            }
        }

        num1[0] = resultado;
    }
}