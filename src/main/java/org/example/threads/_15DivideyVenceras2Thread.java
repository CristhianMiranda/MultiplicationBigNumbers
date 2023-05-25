package org.example.threads;

import org.example.algorithms.Algorithms;

import java.math.BigInteger;

public class _15DivideyVenceras2Thread implements Runnable{
    private int[] num1;

    private int[] num2;

    public _15DivideyVenceras2Thread(int[] num1, int[] num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public void run(){
        Algorithms.divideVenceras2(num1,num2);
    }
}
