package org.example.threads;

import org.example.algorithms.Algorithms;

import java.math.BigInteger;
import java.util.ArrayList;

public class _7InglesRecursivoEstaticoThread implements Runnable{

    private BigInteger[] num1;

    private BigInteger[] num2;

    public _7InglesRecursivoEstaticoThread(BigInteger[] num1, BigInteger[] num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public void run() {
        Algorithms.inglesRecursivoEstatico(num1,num2);
    }

}
