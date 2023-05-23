package org.example.algorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class _14DivideyVenceras1 {
    private BigInteger[] num1;
    private BigInteger[] num2;

    public _14DivideyVenceras1(BigInteger[] num1, BigInteger[] num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    // ---------------------------- Divide y vencer�s 1 (est�tico) -------------------------
    public static void divideyVenceras1(BigInteger[] num1, BigInteger[] num2) {
        // Verificar si alguno de los números es cero
        if (num1[0].equals(BigInteger.ZERO) || num2[0].equals(BigInteger.ZERO)) {
            num1[0] = BigInteger.ZERO;
            return;
        }

        // Verificar si los números tienen un solo dígito
        if (num1[0].compareTo(BigInteger.TEN) < 0 || num2[0].compareTo(BigInteger.TEN) < 0) {
            num1[0] = num1[0].multiply(num2[0]);
            return;
        }

        // Obtener el tamaño de los números
        int n = Math.max(num1[0].bitLength(), num2[0].bitLength());

        // Verificar si n es una potencia de dos
        if (n % 2 != 0) {
            n++;
        }

        // Dividir los números en dos mitades
        int m = n / 2;
        BigInteger powerOfTwo = BigInteger.TWO.pow(m);

        BigInteger a = num1[0].divide(powerOfTwo);
        BigInteger b = num1[0].mod(powerOfTwo);
        BigInteger c = num2[0].divide(powerOfTwo);
        BigInteger d = num2[0].mod(powerOfTwo);

        // Calcular las tres multiplicaciones necesarias
        BigInteger[] ac = new BigInteger[] { BigInteger.ZERO };
        BigInteger[] bd = new BigInteger[] { BigInteger.ZERO };
        BigInteger[] adPlusBc = new BigInteger[] { BigInteger.ZERO };

        divideyVenceras1(new BigInteger[] { a }, new BigInteger[] { c });
        ac[0] = num1[0];

        divideyVenceras1(new BigInteger[] { b }, new BigInteger[] { d });
        bd[0] = num1[0];

        BigInteger[] aPlusB = new BigInteger[] { a.add(b) };
        BigInteger[] cPlusD = new BigInteger[] { c.add(d) };
        divideyVenceras1(aPlusB, cPlusD);

        adPlusBc[0] = aPlusB[0].multiply(cPlusD[0]).subtract(ac[0]).subtract(bd[0]);

        // Combinar los resultados parciales para obtener el resultado final
        num1[0] = ac[0].multiply(powerOfTwo.pow(2)).add(adPlusBc[0].multiply(powerOfTwo)).add(bd[0]);
    }

}
