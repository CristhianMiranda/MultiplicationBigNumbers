package org.example.algorithms;

public class StringMultiplication {
    public static String stringMultiplication(String num1, String num2) {
        // Obtener la longitud de los números
        int n1 = num1.length();
        int n2 = num2.length();

        // Invertir los números para poder empezar a multiplicar desde el dígito más bajo
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();

        // Crear un arreglo para almacenar los resultados parciales de la multiplicación
        int[] result = new int[n1 + n2];

        // Realizar la multiplicación
        for (int i = 0; i < n1; i++) {
            int digit1 = num1.charAt(i) - '0';

            for (int j = 0; j < n2; j++) {
                int digit2 = num2.charAt(j) - '0';

                result[i + j] += digit1 * digit2;
                result[i + j + 1] += result[i + j] / 10;
                result[i + j] %= 10;
            }
        }

        // Convertir el arreglo de resultados en una cadena de caracteres
        StringBuilder sb = new StringBuilder();
        int i = n1 + n2 - 1;
        while (i > 0 && result[i] == 0) {
            i--;
        }
        while (i >= 0) {
            sb.append(result[i]);
            i--;
        }

        // Devolver el resultado como una cadena de caracteres
        return sb.toString();
    }

}
