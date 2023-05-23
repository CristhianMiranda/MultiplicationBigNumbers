package org.example.threads;

import org.example.algorithms.Algorithms;

import java.math.BigInteger;

public class _11EgipciaEstaticoThread implements Runnable{

    private BigInteger num1;

    private BigInteger num2;

    @Override
    public void run(){
        Algorithms.egipcioEstatico(num1,num2);
    }
}
