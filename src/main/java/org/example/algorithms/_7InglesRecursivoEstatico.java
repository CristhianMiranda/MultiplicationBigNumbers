package org.example.algorithms;

import java.math.BigInteger;

public class _7InglesRecursivoEstatico {

    public static void multiplicarArreglosInglesRecursivo(BigInteger[] arreglo1, BigInteger[] arreglo2) {

        int tam = arreglo1.length + arreglo2.length;
        BigInteger[] resultado = new BigInteger [tam];
        int i = 0;
        int j = 0;
        int k = tam - 1;

        for (int x = 0; x < resultado.length; x++) {
            resultado[x] = BigInteger.ZERO;
        }

        multiplicarRecursivo(arreglo1, arreglo2, resultado, i, j, k);
    }

    private static BigInteger[] multiplicarRecursivo(BigInteger[] arreglo1, BigInteger[] arreglo2, BigInteger[] resultado, int i, int j, int k) {

        if(i == arreglo2.length -1 && j == arreglo1.length){

            if (k > 0){
                resultado[k - 1] = resultado[k - 1].add(resultado[k].divide(BigInteger.TEN));
                resultado[k] = resultado[k].mod(BigInteger.TEN);
                k--;
                multiplicarRecursivo(arreglo1,arreglo2,resultado,i,j,k);
            } else {
                return resultado;
            }
        } else if (j == arreglo1.length && i < arreglo2.length-1) {

            j = 0;
            i++;

            multiplicarRecursivo(arreglo1,arreglo2,resultado,i,j,k);

        } else if (j < arreglo1.length){
            resultado[i + j + 1] = resultado[i + j + 1].add(arreglo1[j].multiply(arreglo2[i]));
            j++;
            multiplicarRecursivo(arreglo1,arreglo2,resultado,i,j,k);
        }

        return resultado;
    }

}
