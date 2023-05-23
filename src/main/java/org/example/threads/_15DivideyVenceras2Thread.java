package org.example.threads;

import org.example.algorithms.Algorithms;

import java.math.BigInteger;

public class _15DivideyVenceras2Thread implements Runnable{
    private BigInteger num1;

    private BigInteger num2;

    @Override
    public void run(){
        Algorithms.divideVenceras2(num1,num2);
    }
}
