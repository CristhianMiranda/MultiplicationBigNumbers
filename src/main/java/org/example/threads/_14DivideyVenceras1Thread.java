package org.example.threads;

import org.example.algorithms.Algorithms;

import java.math.BigInteger;

public class _14DivideyVenceras1Thread implements Runnable{

    private int[] num1;

    private int[] num2;

    private int n;

    public _14DivideyVenceras1Thread(int[] num1, int[] num2, int n) {
        this.num1 = num1;
        this.num2 = num2;
        this.n = n;
    }


    @Override
    public void run(){
        Algorithms.divideVenceras1(num1,num2,n);
    }
}
