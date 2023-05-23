package org.example.algorithms;

import java.math.BigInteger;

public class _12KaratsubaEstatico {

    /**
     * Función para multiplicar dos números por el método Karatsuba
     * @param num1 multiplicando
     * @param num2 multiplicador
     * @return resultado multiplicación
     */
    public static BigInteger karatsubaEstatico(BigInteger num1, BigInteger num2) {
        int posiciones = Math.max(num1.bitLength(), num2.bitLength());
        //Para n menor que mil, es más eficiente la multiplicación normal.
        if (posiciones <= 1000) {
            return num1.multiply(num2);
        }
        posiciones = posiciones / 2;
        /*
         * Repartimos en trocitos:
         * u = w * 2^n + x
         * v = y * 2^n + z
         */
        BigInteger w = num1.shiftRight(posiciones);
        BigInteger x = num1.subtract(w.shiftLeft(posiciones));
        BigInteger y = num2.shiftRight(posiciones);
        BigInteger z = num2.subtract(y.shiftLeft(posiciones));
        // Calculamos los resultados parciales
        BigInteger p = karatsubaEstatico(w, y); //p=w*y
        BigInteger q = karatsubaEstatico(x, z); //q=x*z
        BigInteger r = karatsubaEstatico(x.add(w), z.add(y)); //r=(x+w)*(z+y)
        BigInteger z1 = r.subtract(p).subtract(q); //r-p-q
        // Se juntan los resultados parciales para obtener el resultado global.
        return p.shiftLeft(2 * posiciones).add(z1.shiftLeft(posiciones)).add(q);
    }
}
