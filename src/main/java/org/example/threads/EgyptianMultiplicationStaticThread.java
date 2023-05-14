package org.example.threads;

import org.example.algorithms.Algorithms;

public class EgyptianMultiplicationStaticThread implements Runnable{

    private int num1;

    private int num2;

    @Override
    public void run() {
        Algorithms.egyptianMultiplicationStatic(num1,num2);
    }
}
