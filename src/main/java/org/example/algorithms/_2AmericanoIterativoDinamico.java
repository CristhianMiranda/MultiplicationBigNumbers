package org.example.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;

public class _2AmericanoIterativoDinamico {

    public static void multiplicarAmericanoArrayList(ArrayList<BigInteger> arrayList1, ArrayList<BigInteger> arrayList2){
        ArrayList<BigInteger>resultado = new ArrayList<>();
        BigInteger k;
        BigInteger acarreo = BigInteger.ZERO;
        int longitud = arrayList1.size() + arrayList2.size();

        for(int i = 0; i< longitud; i++){
            resultado.add(BigInteger.ZERO);
        }

        System.out.print("\n");

        if(arrayList1.size() > arrayList2.size()){
            ArrayList<BigInteger> arrAux1 = arrayList1;
            arrayList1 = arrayList2;
            arrayList2 = arrAux1;
        }

        //Recorre el arreglo multiplicador desde la última posición
        System.out.println("Tamanio del Arraylist Resultado: "+resultado.size());
        for (int i = arrayList2.size() - 1; i >= 0; i--){

            //Verifica a qué tan lejos está del borde derecho de resultado
            k = BigInteger.valueOf(resultado.size() - (arrayList2.size() - i));
            //Recorre el arreglo multiplicando desde la última posición
            for (int j = arrayList1.size() - 1; j >= 0; j--) {

                //Realiza la multiplicación y suma sobre el resultado anterior
                resultado.set(k.intValue(), arrayList1.get(j).multiply(arrayList2.get(i)).add(acarreo).add(resultado.get(k.intValue())));

                if(resultado.get(k.intValue()).compareTo(BigInteger.TEN) >= 0){
                    acarreo = resultado.get(k.intValue()).divide(BigInteger.TEN);
                    resultado.set(k.intValue(), resultado.get(k.intValue()).mod(BigInteger.TEN));
                } else {
                    acarreo = BigInteger.ZERO;
                }
                k = k.subtract(BigInteger.ONE);
            }
            resultado.set(k.intValue(), acarreo);
            //resultado[k]=acarreo;
            acarreo = BigInteger.ZERO;
        }
    }

}
