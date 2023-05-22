package org.example.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;

public class Algorithms {

    public static void americanoIterativoEstatico(BigInteger[] arr1, BigInteger[] arr2){
        _1AmericanoIterativoEstatico.multiplicarAmericano(arr1,arr2);

    }

    public static void americanoIterativoDinamico(ArrayList<BigInteger> arr1, ArrayList<BigInteger> arr2){
        _2AmericanoIterativoDinamico.multiplicarAmericanoArrayList(arr1,arr2);
    }

    public static void americanoRecursivoEstatico(BigInteger[] arr1, BigInteger[] arr2){
        _3AmericanoRecursivoEstatico.multiplicarArreglosAmericanoRecursivo(arr1, arr2);

    }
    public static void americanoRecursivoDinamico(ArrayList<BigInteger> arr1, ArrayList<BigInteger> arr2){
        _4AmericanoRecursivoDinamico.multiplicarArrayListRecursivo(arr1, arr2);

    }
}
