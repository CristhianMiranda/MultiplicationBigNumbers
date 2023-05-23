package org.example.algorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class _14DivideyVenceras1 {


    // ---------------------------- Divide y vencer�s 1 (est�tico) -------------------------
    public static BigInteger divideyVenceras1(BigInteger num1, BigInteger num2) {

        // Verificar si alguno de los números es cero
        if (x.equals(BigInteger.ZERO) || y.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }

        // Verificar si los n�meros tienen un solo d�gito
        if (x.compareTo(BigInteger.TEN) < 0 || y.compareTo(BigInteger.TEN) < 0) {
            return x.multiply(y);
        }

        // Obtener el tama�o de los n�meros
        int n = Math.max(x.bitLength(), y.bitLength());

        // Verificar si n es una potencia de dos
        if (n % 2 != 0) {
            n++;
        }

        // Dividir los n�meros en dos mitades
        int m = n / 2;
        BigInteger powerOfTwo = BigInteger.TWO.pow(m);

        BigInteger a = x.divide(powerOfTwo);
        BigInteger b = x.mod(powerOfTwo);
        BigInteger c = y.divide(powerOfTwo);
        BigInteger d = y.mod(powerOfTwo);

        // Calcular las tres multiplicaciones necesarias
        BigInteger ac = dv1Multiplicacion(a, c);
        BigInteger bd = dv1Multiplicacion(b, d);
        BigInteger adPlusBc = dv1Multiplicacion(a.add(b), c.add(d)).subtract(ac).subtract(bd);

        // Combinar los resultados parciales para obtener el resultado final
        return ac.multiply(powerOfTwo.pow(2)).add(adPlusBc.multiply(powerOfTwo)).add(bd);
    }

}
