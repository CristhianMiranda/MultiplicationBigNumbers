package org.example.threads;

import org.example.algorithms.Algorithms;

public class _13RepresentadaPorCadenasThread {

    private String[] num1;

    private String[] num2;

    public _13RepresentadaPorCadenasThread(String[] num1, String[] num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public void run(){
        Algorithms.representadaPorCadenas(num1,num2);
    }
}
