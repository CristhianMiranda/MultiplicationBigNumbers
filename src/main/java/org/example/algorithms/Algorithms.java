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

    public static void russianMultiplicationIterative (int num1, int num2)
    {
        RussianMultiplicationIterative.russianMultiply(num1,num2);
    }

    public static void hinduMultiplicationStatic (int num1, int num2)
    {
        HinduMultiplicationStatic.hinduMultiplication(num1,num2);
    }

    public static void egyptianMultiplicationStatic (int num1, int num2)
    {
        EgyptianMultiplicationStatic.egyptianMultiplication(num1,num2);
    }

    public static void karatsubaMultiplicationStatic (int num1, int num2)
    {
        KaratsubaMultiplicationStatic.karatsubaMultiplication(num1,num2);
    }

    public static void stringMultiplication (String num1, String num2)
    {
        StringMultiplication.stringMultiplication(num1,num2);
    }

    public static void divideConquerOne (String num1, String num2)
    {
        DivideConquerOne.multiply(num1,num2);
    }

    public static void divideConquerTwo (BigInteger num1, BigInteger num2)
    {
        DivideConquerTwo.divideConquerTwo(num1,num2);
    }

}
