package org.example.threads;

import org.example.algorithms.Algorithms;

import java.math.BigInteger;

public class _1AmericanoIterativoEstaticoThread implements Runnable{
    private BigInteger[] num1;

    private BigInteger[] num2;

    @Override
    public void run() {
        Algorithms.americanoIterativoEstatico(num1,num2);
    }
}
