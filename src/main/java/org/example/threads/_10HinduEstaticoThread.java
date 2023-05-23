package org.example.threads;

import org.example.algorithms.Algorithms;

import java.math.BigInteger;

public class _10HinduEstaticoThread implements Runnable{

    private BigInteger[] num1;

    private BigInteger[] num2;

    public _10HinduEstaticoThread(BigInteger[] num1, BigInteger[] num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public void run(){
        Algorithms.hinduEstatico(num1,num2);
    }
}
