package org.example.threads;

import org.example.algorithms.Algorithms;

import java.math.BigInteger;
import java.util.ArrayList;

public class _4AmericanoRecursivoDinamicoThread implements Runnable{

    private ArrayList<BigInteger> num1 = new ArrayList<>();

    private ArrayList<BigInteger> num2 = new ArrayList<>();

    public _4AmericanoRecursivoDinamicoThread(ArrayList<BigInteger> num1, ArrayList<BigInteger> num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public void run() {
        Algorithms.americanoRecursivoDinamico(num1,num2);
    }
}
