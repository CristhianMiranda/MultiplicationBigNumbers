package org.example.threads;

import org.example.algorithms.Algorithms;

import java.math.BigInteger;
import java.util.ArrayList;

public class _4AmericanoRecursivoDinamicoThread implements Runnable{

    private ArrayList<BigInteger> num1 = new ArrayList<>();

    private ArrayList<BigInteger> num2 = new ArrayList<>();

    @Override
    public void run() {
        Algorithms.americanoRecursivoDinamico(num1,num2);
    }
}
