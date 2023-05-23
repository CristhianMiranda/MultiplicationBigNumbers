package org.example.algorithms;

import java.math.BigInteger;

public class _15DivideyVenceras2 {

    private BigInteger[] num1;
    private BigInteger[] num2;

    public _15DivideyVenceras2(BigInteger[] num1, BigInteger[] num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    // ---------------------------- Divide y vencer�s 2 (est�tico) -------------------------
    public static void divideyVenceras2(BigInteger[] num1, BigInteger[] num2) {
        int n = Math.max(num1[0].bitLength(), num2[0].bitLength());
        if (n <= 2000) { // Caso base: números pequeños
            num1[0] = num1[0].multiply(num2[0]);
            return;
        }

        // Igualar la cantidad de dígitos a la potencia de dos más cercana
        n = (n / 2) + (n % 2);

        // Dividir los números en mitades
        BigInteger tenPowN = BigInteger.TEN.pow(n);
        BigInteger a = num1[0].divide(tenPowN);
        BigInteger b = num1[0].mod(tenPowN);
        BigInteger c = num2[0].divide(tenPowN);
        BigInteger d = num2[0].mod(tenPowN);

        // Calcular las multiplicaciones intermedias
        BigInteger[] ac = new BigInteger[] { BigInteger.ZERO };
        BigInteger[] bd = new BigInteger[] { BigInteger.ZERO };
        BigInteger[] adPlusBc = new BigInteger[] { BigInteger.ZERO };

        divideyVenceras2(new BigInteger[] { a }, new BigInteger[] { c });
        ac[0] = num1[0];

        divideyVenceras2(new BigInteger[] { b }, new BigInteger[] { d });
        bd[0] = num1[0];

        BigInteger[] aPlusB = new BigInteger[] { a.add(b) };
        BigInteger[] cPlusD = new BigInteger[] { c.add(d) };
        divideyVenceras2(aPlusB, cPlusD);

        adPlusBc[0] = aPlusB[0].multiply(cPlusD[0]).subtract(ac[0]).subtract(bd[0]);

        // Calcular el resultado final
        num1[0] = ac[0].multiply(tenPowN.pow(2)).add(adPlusBc[0].multiply(tenPowN)).add(bd[0]);
    }
}
