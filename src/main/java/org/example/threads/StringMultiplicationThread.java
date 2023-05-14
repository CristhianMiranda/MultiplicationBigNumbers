package org.example.threads;

import org.example.algorithms.Algorithms;

public class StringMultiplicationThread implements Runnable{

    private String num1;

    private String num2;

    @Override
    public void run() {
        Algorithms.stringMultiplication(num1,num2);
    }
}
