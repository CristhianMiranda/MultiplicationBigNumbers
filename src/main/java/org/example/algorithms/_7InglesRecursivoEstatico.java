package org.example.algorithms;

import java.math.BigInteger;

public class _7InglesRecursivoEstatico {

    public static void multiplicarArreglosInglesRecursivo(BigInteger[] arreglo1, BigInteger[] arreglo2) {
        int tam = arreglo1.length + arreglo2.length;
        BigInteger[] resultado = new BigInteger[tam];
        int i = 0;
        int j = 0;
        int k = tam - 1;

        for (int x = 0; x < resultado.length; x++) {
            resultado[x] = BigInteger.ZERO;
        }

        while (i < arreglo2.length) {
            while (j < arreglo1.length) {
                resultado[i + j + 1] = resultado[i + j + 1].add(arreglo1[j].multiply(arreglo2[i]));
                j++;
            }

            j = 0;
            i++;
        }

        for (int x = k; x > 0; x--) {
            resultado[x - 1] = resultado[x - 1].add(resultado[x].divide(BigInteger.TEN));
            resultado[x] = resultado[x].mod(BigInteger.TEN);
        }

        // Aquí puedes utilizar el arreglo 'resultado' según tus necesidades
    }


}
