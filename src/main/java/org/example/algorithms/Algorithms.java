package org.example.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;

public class Algorithms {

    public static void americanoIterativoEstatico(BigInteger[] arr1, BigInteger[] arr2){
        _1AmericanoIterativoEstatico.multiplicarAmericano(arr1,arr2);
    }

    public static void americanoIterativoDinamico(ArrayList<BigInteger> arrayList1, ArrayList<BigInteger> arrayList2){
        _2AmericanoIterativoDinamico.multiplicarAmericanoArrayList(arrayList1,arrayList2);
    }

    public static void americanoRecursivoEstatico(BigInteger[] arr1, BigInteger[] arr2){
        _3AmericanoRecursivoEstatico.multiplicarArreglosAmericanoRecursivo(arr1, arr2);
    }

    public static void americanoRecursivoDinamico(ArrayList<BigInteger> arrayList1, ArrayList<BigInteger> arrayList2){
        _4AmericanoRecursivoDinamico.multiplicarArrayListRecursivo(arrayList1, arrayList2);
    }

    public static void inglesIterativoEstatico(BigInteger[] arr1, BigInteger[] arr2){
        _5InglesIterativoEstatico.multiplicaInglesa(arr1,arr2);
    }

    public static void inglesIterativoDinamico(ArrayList<BigInteger> arrayList1, ArrayList<BigInteger> arrayList2){
        _6InglesIterativoDinamico.multiplicarInglesArrayList(arrayList1,arrayList2);
    }

    public static void inglesRecursivoEstatico(BigInteger[] arr1, BigInteger[] arr2){
        _7InglesRecursivoEstatico.multiplicarArreglosInglesRecursivo(arr1, arr2);
    }

    public static void inglesRecursivoDinamico(ArrayList<BigInteger> arrayList1, ArrayList<BigInteger> arrayList2){
        _8InglesRecursivoDinamico.multiplicarArrayListAmericanoRecursivo(arrayList1,arrayList2);
    }

    public static void rusoEstatico(BigInteger num1, BigInteger num2){
        _9RusaIEstatica.rusoEstatico(num1,num2);
    }
}
