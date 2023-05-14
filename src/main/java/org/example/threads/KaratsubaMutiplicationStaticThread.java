package org.example.threads;

import org.example.algorithms.Algorithms;

public class KaratsubaMutiplicationStaticThread implements Runnable{

    private int num1;

    private int num2;

    @Override
    public void run() {
        Algorithms.karatsubaMultiplicationStatic(num1,num2);
    }
}
