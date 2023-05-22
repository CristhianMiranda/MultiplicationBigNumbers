package org.example.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class _6InglesIterativoDinamico {

    public static void multiplicarInglesArrayList (ArrayList<BigInteger> arrayList1, ArrayList<BigInteger> arrayList2) {

        int longitud = arrayList1.size() + arrayList2.size();
        ArrayList<BigInteger> resultado = new ArrayList<>(Collections.nCopies(longitud, null));

        for (int x = 0; x < longitud; x++) {
            resultado.set(x, BigInteger.ZERO);
        }

        for (int i = 0; i < arrayList2.size(); i++) {
            for(int j = 0; j < arrayList1.size(); j++) {
                resultado.set(i + j + 1, resultado.get(i + j + 1).add(arrayList1.get(j).multiply(arrayList2.get(i))));

            }

        }
        for (int k = longitud-1; k > 0; k--) {
            resultado.set(k - 1, resultado.get(k - 1).add(resultado.get(k).divide(BigInteger.TEN)));
            resultado.set(k, resultado.get(k).mod(BigInteger.TEN));

        }

    }

}
