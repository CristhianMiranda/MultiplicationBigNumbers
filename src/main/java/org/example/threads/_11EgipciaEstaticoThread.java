package org.example.threads;

import org.example.algorithms.Algorithms;

import java.math.BigInteger;

public class _11EgipciaEstaticoThread implements Runnable{

    private BigInteger[] num1;

    private BigInteger[] num2;

    public _11EgipciaEstaticoThread(BigInteger[] num1, BigInteger[] num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public void run(){
        Algorithms.egipcioEstatico(num1,num2);
    }
}
