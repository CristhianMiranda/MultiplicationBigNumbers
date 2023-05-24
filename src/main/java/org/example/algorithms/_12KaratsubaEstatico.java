package org.example.algorithms;

import java.math.BigInteger;
import java.util.Arrays;

public class _12KaratsubaEstatico {


    public static BigInteger[] karatsubaEstatico(BigInteger[] num1, BigInteger[] num2) {
        int posiciones = Math.max(num1.length, num2.length);
        // Para n menor que mil, es más eficiente la multiplicación normal.
        if (posiciones <= 1000) {
            return multiplyArrays(num1, num2);
        }
        posiciones = posiciones / 2;
        /*
         * Repartimos en trocitos:
         * u = w * 2^n + x
         * v = y * 2^n + z
         */
        BigInteger[] w = new BigInteger[num1.length - posiciones];
        BigInteger[] x = new BigInteger[posiciones];
        System.arraycopy(num1, 0, w, 0, w.length);
        System.arraycopy(num1, w.length, x, 0, x.length);

        BigInteger[] y = new BigInteger[num2.length - posiciones];
        BigInteger[] z = new BigInteger[posiciones];
        System.arraycopy(num2, 0, y, 0, y.length);
        System.arraycopy(num2, y.length, z, 0, z.length);

        // Calculamos los resultados parciales
        BigInteger[] p = karatsubaEstatico(w, y); // p = w * y
        BigInteger[] q = karatsubaEstatico(x, z); // q = x * z
        BigInteger[] r = karatsubaEstatico(addArrays(x, w), addArrays(z, y)); // r = (x + w) * (z + y)
        BigInteger[] z1 = subtractArrays(subtractArrays(r, p), q); // z1 = r - p - q

        // Se juntan los resultados parciales para obtener el resultado global.
        return addArrays(addArrays(shiftLeft(p, 2 * posiciones), shiftLeft(z1, posiciones)), q);
    }

    // Helper method to multiply two arrays of BigIntegers
    public static BigInteger[] multiplyArrays(BigInteger[] arr1, BigInteger[] arr2) {
        int resultLength = arr1.length + arr2.length - 1;
        BigInteger[] result = new BigInteger[resultLength];
        Arrays.fill(result, BigInteger.ZERO);

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                result[i + j] = result[i + j].add(arr1[i].multiply(arr2[j]));
            }
        }

        return result;
    }

    // Helper method to add two arrays element-wise
    public static BigInteger[] addArrays(BigInteger[] arr1, BigInteger[] arr2) {
        BigInteger[] result = new BigInteger[Math.max(arr1.length, arr2.length)];
        for (int i = 0; i < result.length; i++) {
            if (i < arr1.length && i < arr2.length) {
                result[i] = arr1[i].add(arr2[i]);
            } else if (i < arr1.length) {
                result[i] = arr1[i];
            } else {
                result[i] = arr2[i];
            }
        }
        return result;
    }

    // Helper method to subtract two arrays element-wise
    public static BigInteger[] subtractArrays(BigInteger[] arr1, BigInteger[] arr2) {
        BigInteger[] result = new BigInteger[Math.max(arr1.length, arr2.length)];
        for (int i = 0; i < result.length; i++) {
            if (i < arr1.length && i < arr2.length) {
                result[i] = arr1[i].subtract(arr2[i]);
            } else if (i < arr1.length) {
                result[i] = arr1[i];
            } else {
                result[i] = arr2[i].negate();
            }
        }
        return result;
    }

    // Helper method to shift an array of BigIntegers to the left by n positions
    public static BigInteger[] shiftLeft(BigInteger[] arr, int n) {
        BigInteger[] result = new BigInteger[arr.length + n];
        System.arraycopy(arr, 0, result, n, arr.length);
        return result;
    }


}
