package org.example.algorithms;

import java.math.BigInteger;

public class _1AmericanoIterativoEstatico {

    public static void multiplicarAmericano(BigInteger[] arr1, BigInteger[] arr2) {

        BigInteger[] resultado = new BigInteger[arr1.length+ arr2.length];
        int k;
        BigInteger acarreo = BigInteger.ZERO;


        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = BigInteger.ZERO;
        }

        System.out.print("\n");

        if(arr1.length > arr2.length){
            BigInteger[] arrAux1 = arr1;
            arr1 = arr2;
            arr2 = arrAux1;
        }
        //Recorre el arreglo multiplicador desde la última posición
        for (int i = arr2.length -1; i>=0; i--){

            //Verifica a qué tan lejos está del borde derecho del arreglo resultado
            k  = resultado.length - (arr2.length - i);
            //Recorre el arreglo multiplicando desde la última posición
            for (int j = arr1.length - 1; j >= 0; j--) {

                //Realiza la multiplicación y suma sobre el resultado anterior
                resultado[k] = resultado[k].add(arr1[j].multiply(arr2[i])).add(acarreo);

                //Condición que verifica si el resultado es igual o mayor a 10
                if (resultado[k].compareTo(BigInteger.TEN) >= 0) {

                    //indicar que se acumula en el acarreo
                    acarreo = resultado[k].divide(BigInteger.TEN);
                    //y que queda almacenado en la posicion [k]
                    resultado[k] = resultado[k].mod(BigInteger.TEN);
                } else {
                    acarreo = BigInteger.ZERO;
                }

                k--;
            }
            resultado[k]=acarreo;
            acarreo = BigInteger.ZERO;
        }
    }


}
