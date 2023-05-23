package org.example.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class _8InglesRecursivoDinamico {

    public static void multiplicarArrayListAmericanoRecursivo(ArrayList<BigInteger> arrayList1, ArrayList<BigInteger> arrayList2) {
        int longitud = arrayList1.size() + arrayList2.size();
        ArrayList<BigInteger> resultado = new ArrayList<>(Collections.nCopies(longitud, BigInteger.ZERO));

        int i = 0;
        int j = 0;
        int k = longitud - 1;

        while (i < arrayList2.size()) {
            while (j < arrayList1.size()) {
                resultado.set(i + j + 1, resultado.get(i + j + 1).add(arrayList1.get(j).multiply(arrayList2.get(i))));
                j++;
            }

            j = 0;
            i++;
        }

        for (int x = k; x > 0; x--) {
            resultado.set(x - 1, resultado.get(x - 1).add(resultado.get(x).divide(BigInteger.TEN)));
            resultado.set(x, resultado.get(x).mod(BigInteger.TEN));
        }

        // Aquí puedes utilizar el ArrayList 'resultado' según tus necesidades
    }

}
