package org.example.threads;

import org.example.algorithms.Algorithms;

public class DivideConquerOneThread implements Runnable{

    private String num1;

    private String num2;

    @Override
    public void run() {
        Algorithms.divideConquerOne(num1,num2);
    }
}
