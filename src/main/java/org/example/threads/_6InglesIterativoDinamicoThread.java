package org.example.threads;

import org.example.algorithms.Algorithms;

import java.math.BigInteger;
import java.util.ArrayList;

public class _6InglesIterativoDinamicoThread implements Runnable{
    private ArrayList<BigInteger> num1 = new ArrayList<>();

    private ArrayList<BigInteger> num2 = new ArrayList<>();

    @Override
    public void run() {
        Algorithms.inglesIterativoDinamico(num1,num2);
    }

}
