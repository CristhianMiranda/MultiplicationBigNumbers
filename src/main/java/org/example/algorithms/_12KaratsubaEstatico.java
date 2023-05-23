package org.example.algorithms;

import java.math.BigInteger;

public class _12KaratsubaEstatico {

    /**
     * Función para multiplicar dos números por el método Karatsuba
     * @param num1 multiplicando
     * @param num2 multiplicador
     * @return resultado multiplicación
     */
    public static void karatsubaEstatico(BigInteger[] num1, BigInteger[] num2) {
        int posiciones = Math.max(num1[0].bitLength(), num2[0].bitLength());
        // Para n menor que mil, es más eficiente la multiplicación normal.
        if (posiciones <= 1000) {
            num1[0] = num1[0].multiply(num2[0]);
            return;
        }
        posiciones = posiciones / 2;
        /*
         * Repartimos en trocitos:
         * u = w * 2^n + x
         * v = y * 2^n + z
         */
        BigInteger w = num1[0].shiftRight(posiciones);
        BigInteger x = num1[0].subtract(w.shiftLeft(posiciones));
        BigInteger y = num2[0].shiftRight(posiciones);
        BigInteger z = num2[0].subtract(y.shiftLeft(posiciones));
        // Calculamos los resultados parciales
        BigInteger p = BigInteger.ZERO;
        BigInteger q = BigInteger.ZERO;
        BigInteger r = BigInteger.ZERO;
        karatsubaEstatico(new BigInteger[] { w }, new BigInteger[] { y }); // p = w * y
        p = num1[0];
        karatsubaEstatico(new BigInteger[] { x }, new BigInteger[] { z }); // q = x * z
        q = num1[0];
        karatsubaEstatico(new BigInteger[] { x.add(w) }, new BigInteger[] { z.add(y) }); // r = (x + w) * (z + y)
        r = num1[0];
        BigInteger z1 = r.subtract(p).subtract(q); // z1 = r - p - q
        // Se juntan los resultados parciales para obtener el resultado global.
        num1[0] = p.shiftLeft(2 * posiciones).add(z1.shiftLeft(posiciones)).add(q);
    }
}
