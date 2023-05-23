package org.example.algorithms;

import java.math.BigInteger;

public class _15DivideyVenceras2 {

    // ---------------------------- Divide y vencer�s 2 (est�tico) -------------------------
    public static BigInteger divideyVenceras2(BigInteger num1, BigInteger num2) {
        int n = Math.max(num1.bitLength(), num2.bitLength());
        if (n <= 2000) { // Caso base: n�meros peque�os
            return num1.multiply(num2);
        }

        // Igualar la cantidad de dígitos a la potencia de dos más cercana
        n = (n / 2) + (n % 2);

        // Dividir los números en mitades
        BigInteger tenPowN = BigInteger.TEN.pow(n);
        BigInteger a = num1.divide(tenPowN);
        BigInteger b = num1.mod(tenPowN);
        BigInteger c = num2.divide(tenPowN);
        BigInteger d = num2.mod(tenPowN);

        // Calcular las multiplicaciones intermedias
        BigInteger ac = divideyVenceras2(a, c);
        BigInteger bd = divideyVenceras2(b, d);
        BigInteger adPlusBc = divideyVenceras2(a.add(b), c.add(d)).subtract(ac).subtract(bd);

        // Calcular el resultado final
        return ac.multiply(tenPowN.pow(2)).add(adPlusBc.multiply(tenPowN)).add(bd);

    }
}
