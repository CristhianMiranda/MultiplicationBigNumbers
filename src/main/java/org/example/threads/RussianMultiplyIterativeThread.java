package org.example.threads;

import org.example.algorithms.Algorithms;

public class RussianMultiplyIterativeThread implements Runnable{

    private int num1;

    private int num2;

    @Override
    public void run() {
        Algorithms.russianMultiplicationIterative(num1,num2);
    }
}
