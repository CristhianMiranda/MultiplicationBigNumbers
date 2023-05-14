package org.example.threads;

import org.example.algorithms.HinduMultiplicationStatic;

public class HinduMultiplicationStaticThread implements Runnable{
    private int num1;

    private int num2;

    @Override
    public void run() {
        HinduMultiplicationStatic.hinduMultiplication(num1,num2);
    }
}
