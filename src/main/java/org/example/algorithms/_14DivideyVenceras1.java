package org.example.algorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class _14DivideyVenceras1 {


    // ---------------------------- Divide y vencer�s 1 (est�tico) -------------------------
    public static BigInteger divideyVenceras1(BigInteger num1, BigInteger num2) {
        // Verificar si los n�meros tienen un solo d�gito
        if (num1.compareTo(BigInteger.TEN) < 0 || num2.compareTo(BigInteger.TEN) < 0) {
            return num1.multiply(num2);
        }

        // Obtener el tama�o de los n�meros
        int n = Math.max(num1.bitLength(), num2.bitLength());

        // Dividir los n�meros en dos mitades
        int m = n / 2;
        BigInteger a = num1.shiftRight(m);
        BigInteger b = num1.subtract(a.shiftLeft(m));
        BigInteger c = num2.shiftRight(m);
        BigInteger d = num2.subtract(c.shiftLeft(m));

        // Calcular las tres multiplicaciones necesarias
        BigInteger ac = divideyVenceras1(a, c);
        BigInteger bd = divideyVenceras1(b, d);
        BigInteger abcd = divideyVenceras1(a.add(b), c.add(d));

        // Calcular el resultado final
        return ac.shiftLeft(2 * m).add(abcd.subtract(ac).subtract(bd).shiftLeft(m)).add(bd);
    }

}
