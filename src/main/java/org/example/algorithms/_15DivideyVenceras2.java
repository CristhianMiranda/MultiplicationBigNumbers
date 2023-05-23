package org.example.algorithms;

import java.math.BigInteger;

public class _15DivideyVenceras2 {

    // ---------------------------- Divide y vencer�s 2 (est�tico) -------------------------
    public static BigInteger divideyVenceras2(BigInteger num1, BigInteger num2) {
        int n = Math.max(num1.bitLength(), num2.bitLength());
        if (n <= 2000) { // Caso base: n�meros peque�os
            return num1.multiply(num2);
        }
        // Dividir x e y en dos partes iguales
        BigInteger[] partesX = dividirEnPartes(num1);
        BigInteger[] partesY = dividirEnPartes(num2);
        BigInteger a = partesX[0], b = partesX[1];
        BigInteger c = partesY[0], d = partesY[1];
        // Calcular los cuatro productos parciales
        BigInteger ac = divideyVenceras2(a, c);
        BigInteger bd = divideyVenceras2(b, d);
        BigInteger suma = divideyVenceras2(a.add(b), c.add(d));
        BigInteger adbc = suma.subtract(ac).subtract(bd);
        // Calcular el resultado final
        BigInteger resultado = ac.multiply(BigInteger.TEN.pow(2 * n))
                .add(adbc.multiply(BigInteger.TEN.pow(n)))
                .add(bd);
        return resultado;
    }

    // Divide un n�mero en dos partes iguales (redondeo hacia abajo)
    private static BigInteger[] dividirEnPartes(BigInteger num) {
        int n = num.bitLength() / 2;
        BigInteger a = num.shiftRight(n);
        BigInteger b = num.subtract(a.shiftLeft(n));
        return new BigInteger[] {a, b};
    }

}
