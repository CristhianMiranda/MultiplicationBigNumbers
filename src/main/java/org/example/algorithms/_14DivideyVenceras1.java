package org.example.algorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class _14DivideyVenceras1 {


    // ---------------------------- Divide y vencer�s 1 (est�tico) -------------------------
    public static BigInteger divideyVenceras1(BigInteger num1, BigInteger num2) {

        // Verificar si alguno de los números es cero
        if (num1.equals(BigInteger.ZERO) || num2.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }

        // Verificar si los n�meros tienen un solo d�gito
        if (num1.compareTo(BigInteger.TEN) < 0 || num2.compareTo(BigInteger.TEN) < 0) {
            return num1.multiply(num2);
        }

        // Obtener el tama�o de los n�meros
        int n = Math.max(num1.bitLength(), num2.bitLength());

        // Verificar si n es una potencia de dos
        if (n % 2 != 0) {
            n++;
        }

        // Dividir los n�meros en dos mitades
        int m = n / 2;
        BigInteger powerOfTwo = BigInteger.TWO.pow(m);

        BigInteger a = num1.divide(powerOfTwo);
        BigInteger b = num1.mod(powerOfTwo);
        BigInteger c = num2.divide(powerOfTwo);
        BigInteger d = num2.mod(powerOfTwo);

        // Calcular las tres multiplicaciones necesarias
        BigInteger ac = divideyVenceras1(a, c);
        BigInteger bd = divideyVenceras1(b, d);
        BigInteger adPlusBc = divideyVenceras1(a.add(b), c.add(d)).subtract(ac).subtract(bd);

        // Combinar los resultados parciales para obtener el resultado final
        return ac.multiply(powerOfTwo.pow(2)).add(adPlusBc.multiply(powerOfTwo)).add(bd);
    }

}
