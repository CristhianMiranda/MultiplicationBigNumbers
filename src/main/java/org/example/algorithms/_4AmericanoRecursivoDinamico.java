package org.example.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class _4AmericanoRecursivoDinamico {

    public static void multiplicarArrayListRecursivo(ArrayList<BigInteger> arrayList1, ArrayList<BigInteger> arrayList2) {
        int longitud = arrayList1.size() + arrayList2.size();
        ArrayList<BigInteger> resultado = new ArrayList<>(Collections.nCopies(longitud, BigInteger.ZERO));

        if (arrayList1.size() > arrayList2.size()) {
            ArrayList<BigInteger> arrAux1 = arrayList1;
            arrayList1 = arrayList2;
            arrayList2 = arrAux1;
        }

        BigInteger acarreo = BigInteger.ZERO;
        int i = arrayList2.size() - 1;
        int j = arrayList1.size() - 1;
        int k = resultado.size() - 1;

        while (i >= 0) {
            while (j >= 0) {
                resultado.set(k, resultado.get(k).add(arrayList1.get(j).multiply(arrayList2.get(i))).add(acarreo));

                if (resultado.get(k).compareTo(BigInteger.TEN) >= 0) {
                    acarreo = resultado.get(k).divide(BigInteger.TEN);
                    resultado.set(k, resultado.get(k).mod(BigInteger.TEN));
                } else {
                    acarreo = BigInteger.ZERO;
                }

                k--;
                j--;
            }

            resultado.set(k, acarreo);
            acarreo = BigInteger.ZERO;
            i--;
            j = arrayList1.size() - 1;
            k = resultado.size() - (arrayList2.size() - i);
        }

        // Aquí puedes utilizar el ArrayList 'resultado' según tus necesidades
    }


}
