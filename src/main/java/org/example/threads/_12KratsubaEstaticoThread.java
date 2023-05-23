package org.example.threads;

import org.example.algorithms.Algorithms;

import java.math.BigInteger;

public class _12KratsubaEstaticoThread implements Runnable{

    private BigInteger num1;

    private BigInteger num2;

    @Override
    public void run(){
        Algorithms.karatsubaEstatico(num1,num2);
    }
}
