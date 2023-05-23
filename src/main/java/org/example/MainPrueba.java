package org.example;

import java.math.BigInteger;
import java.util.Random;

public class MainPrueba {
    public static void main(String[] args) {
        BigInteger tamano = BigInteger.valueOf(5);
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            BigInteger caso = tamano.multiply(BigInteger.valueOf(i)).multiply(BigInteger.valueOf(2));
            BigInteger datoAleatorio = generarDatoAleatorio(caso, random);
            System.out.println("Caso " + i + ": " + datoAleatorio);
        }
    }
    public static BigInteger generarDatoAleatorio(BigInteger limiteSuperior, Random random) {
        BigInteger datoAleatorio;
        do {
            datoAleatorio = new BigInteger(limiteSuperior.bitLength(), random);
        } while (datoAleatorio.compareTo(limiteSuperior) >= 0);

        return datoAleatorio;
    }

}
