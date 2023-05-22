package org.example.algorithms;

import java.math.BigInteger;

public class _5InglesIterativoEstatico {

    public static void multiplicaInglesa (BigInteger[] arr1, BigInteger[] arr2) {

        int tam = arr1.length + arr2.length;
        BigInteger[] resultado = new BigInteger [tam];

        for (int x = 0; x < resultado.length; x++) {
            resultado[x] = BigInteger.ZERO;
        }

        for (int i = 0; i < arr2.length; i++) {
            for(int j = 0; j < arr1.length; j++) {
                resultado[i + j + 1] = resultado[i + j + 1].add(arr1[j].multiply(arr2[i]));

            }

        }
        for (int k=tam-1; k>0; k--) {
            resultado[k - 1] = resultado[k - 1].add(resultado[k].divide(BigInteger.TEN));
            resultado[k] = resultado[k].mod(BigInteger.TEN);

        }

    }
}
