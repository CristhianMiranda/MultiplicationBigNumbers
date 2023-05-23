package org.example.algorithms;

import java.math.BigInteger;

public class _10HinduEstatico {
    public static void induEstatico(BigInteger[] arr1, BigInteger[] arr2) {

        BigInteger[] resultado = new BigInteger[arr1.length+ arr2.length];
        int k;
        BigInteger[] acarreo = new BigInteger[resultado.length];

        System.out.print("\n");

        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = BigInteger.ZERO;
            acarreo[i] = BigInteger.ZERO;
        }


        //Recorre el arreglo multiplicador desde la última posición
        for (int i = arr2.length -1; i>=0; i--){

            //Verifica a qué tan lejos está del borde derecho del arreglo resultado
            k  = resultado.length - (arr2.length - i);
            //Recorre el arreglo multiplicando desde la última posición
            for (int j = arr1.length - 1; j >= 0; j--) {

                //Realiza la multiplicación y suma sobre el resultado anterior
                resultado[k] = resultado[k].add(arr1[j].multiply(arr2[i]));
                //resultado[k] += arr1[j] * arr2[i];

                //Condición que verifica si el resultado es igual o mayor a 10
                if(resultado[k].compareTo(BigInteger.TEN) >= 0){

                    //indicar que se acumula en el acarreo en k-1
                    acarreo[k-1] = acarreo[k-1].add(resultado[k].divide(BigInteger.TEN));
                    //y que queda almacenado en la posicion [k]
                    resultado[k] = resultado[k].mod(BigInteger.TEN);
                } else {
                    acarreo[k-1] = BigInteger.ZERO;
                }

                k--;
            }
        }
        for (int i = resultado.length - 1; i>=0; i--){
            resultado[i] = resultado[i].add(acarreo[i]);
            //resultado[i] += acarreo[i];

            if(resultado[i].compareTo(BigInteger.TEN) >= 0){
                acarreo[i-1] = acarreo[i-1].add(resultado[i].divide(BigInteger.TEN));
                resultado[i] = resultado[i].mod(BigInteger.TEN);
            }
        }
    }

}
