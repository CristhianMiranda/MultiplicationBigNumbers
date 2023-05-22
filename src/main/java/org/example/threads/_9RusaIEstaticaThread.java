package org.example.threads;

import org.example.algorithms.Algorithms;

import java.math.BigInteger;
import java.util.ArrayList;

public class _9RusaIEstaticaThread implements Runnable{

    private BigInteger num1;

    private BigInteger num2;

    @Override
    public void run() {
        Algorithms.rusoEstatico(num1,num2);
    }
}
