package org.example.algorithms;

import java.math.BigInteger;

public class _3AmericanoRecursivoEstatico {

    //Este método evita que en la ejecución se desborde
    //Si el valor que hay en el arreglo 1 es mayor al valor del arreglo 2 entonces se intercambian
    //Es utilizado en el método recursivo
    public static void multiplicarArreglosAmericanoRecursivo(BigInteger[] arr1, BigInteger[] arr2) {
        BigInteger[] resultado = new BigInteger[arr1.length + arr2.length];

        for (int index = 0; index < resultado.length; index++) {
            resultado[index] = BigInteger.ZERO;
        }

        BigInteger acarreo = BigInteger.ZERO;
        int i = arr2.length - 1;
        int j = arr1.length - 1;
        int k = resultado.length - 1;

        if (arr1.length > arr2.length) {
            BigInteger[] arrAux1 = arr1;
            arr1 = arr2;
            arr2 = arrAux1;
        }

        while (i >= 0) {
            while (j >= 0) {
                resultado[k] = resultado[k].add(arr1[j].multiply(arr2[i])).add(acarreo);

                if (resultado[k].compareTo(BigInteger.TEN) >= 0) {
                    acarreo = resultado[k].divide(BigInteger.TEN);
                    resultado[k] = resultado[k].mod(BigInteger.TEN);
                } else {
                    acarreo = BigInteger.ZERO;
                }

                k--;
                j--;
            }

            resultado[k] = acarreo;
            acarreo = BigInteger.ZERO;
            i--;
            j = arr1.length - 1;
            k = resultado.length - (arr2.length - i);
        }

        // Aquí puedes utilizar el arreglo 'resultado' según tus necesidades
    }

}
