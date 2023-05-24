package org.example;

import org.example.additional.ExcelController;
import org.example.additional.GraficaBarrasOrdenadas;
import org.example.additional.GraficaBarrasPromedio;
import org.example.threads.*;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        calculoTiempoEjecucionMultiplicacion();

    }

    public static void calculoTiempoEjecucionMultiplicacion() throws InterruptedException {

        int tamano = 1;
        eliminarArchivo();

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 15; j++) {
                // Tamaño de las matrices
                int size = tamano * i * 2;

                BigInteger[] arregloA = generateRandomBigIntegerArray(size);


                BigInteger[] arregloB = generateRandomBigIntegerArray(size);


                if(j==1) {
                    System.out.println("\n\nCaso" + i + ": / Tamaño:" + size /*+ " / Algoritmo:" + j*/);
                }

                tiempoRespuesta(arregloA, arregloB, j, i, size);
                // Cálculo del tiempo promedio
                double averageTime = calculateAverageExecutionTime(j);

                // Formateo del tiempo promedio con 2 decimales
                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
                DecimalFormat decimalFormat = new DecimalFormat("#0.00", symbols);
                String formattedAverageTime = decimalFormat.format(averageTime);

                guardarPromedioTiempoEjecucion(j, i, Double.parseDouble(formattedAverageTime));
                ExcelController.escribirEnHojaEspecifica(Double.parseDouble(formattedAverageTime), j - 1);

                if (i == 8) {
                    System.out.println("Tiempo promedio de ejecución: " + formattedAverageTime + " ns\n");
                }

            }
        }
        try {
            guardarValoresFinales();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        graficaBarras();
        graficaBarrasOrden();


    }

    private static void graficaBarras() {
        unify();
        double[] promedios = readNumbersFromFile("assets/promedio/promedios.txt");
        GraficaBarrasPromedio graficaBarrasPromedio = new GraficaBarrasPromedio(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},promedios);
    }
    private static void graficaBarrasOrden() {
        //unify();
        double[] tiempos = readNumbersFromFile("assets/datos/execution_times_sorted.txt");
        GraficaBarrasOrdenadas barChartExample = new GraficaBarrasOrdenadas(readTxtFile("assets/datos/ids.txt"),tiempos);
    }

    public static void unify() {
        String folderPath = "assets/promedio/";
        String outputFilePath = folderPath + "promedios.txt";
        DecimalFormat df = new DecimalFormat("#.00");

        try (PrintWriter writer = new PrintWriter(outputFilePath)) {
            for (int i = 1; i <= 15; i++) {
                String inputFilePath = folderPath + "promedio_times" + i + ".txt";
                File inputFile = new File(inputFilePath);

                if (!inputFile.exists()) {
                    continue;
                }

                try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        line = line.replace(',', '.');
                        double value = Double.parseDouble(line.trim());
                        String formatted = df.format(value);
                        writer.print(formatted + ".");
                    }
                } catch (IOException e) {
                    System.err.println("Error reading file: " + inputFilePath);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + outputFilePath);
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readTxtFile(String fileName) {
        ArrayList<String> data = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                data.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static double[] readNumbersFromFile(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            scanner.useDelimiter("\\.");

            // Contar la cantidad de números en el archivo
            int count = 0;
            while (scanner.hasNextDouble()) {
                count++;
                scanner.nextDouble();
            }

            // Crear el arreglo y volver a leer el archivo
            double[] numbers = new double[count];
            scanner = new Scanner(new File(filename));
            scanner.useDelimiter("\\.");
            for (int i = 0; i < count; i++) {
                numbers[i] = scanner.nextDouble();
            }

            scanner.close();

            return numbers;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + filename);
            return null;
        }
    }



    public static void tiempoRespuesta(BigInteger[] arregloA, BigInteger[] arregloB, int id, int caso, int size) throws InterruptedException {
        long startTime, endTime;
        Thread t;

        switch (id) {
            case 1:
                startTime = System.nanoTime();
                t = new Thread(new _1AmericanoIterativoEstaticoThread(arregloA, arregloB));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (Americana iterativo (estático))");
                ExcelController.escribirEnHoja(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;

            case 2:
                startTime = System.nanoTime();
                t = new Thread(new _2AmericanoIterativoDinamicoThread(convertArrayToArrayList(arregloA),convertArrayToArrayList(arregloB)));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (Americana iterativo (dinámico))");
                ExcelController.escribirEnHoja(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 3:
                startTime = System.nanoTime();
                /*t = new Thread(new _3AmericanoRecursivoEstaticoThread(arregloA, arregloB));
                t.run();*/

                t = new Thread(() -> {
                    try {
                        increaseStackSize();
                        new _3AmericanoRecursivoEstaticoThread(arregloA, arregloB);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                t.start();
                t.join();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (Americana recursivo (estático))");
                ExcelController.escribirEnHoja(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 4:
                startTime = System.nanoTime();
                /*t = new Thread(new _4AmericanoRecursivoDinamicoThread(convertArrayToArrayList(arregloA),convertArrayToArrayList(arregloB)));
                t.run();*/

                t = new Thread(() -> {
                    try {
                        increaseStackSize();
                        new _4AmericanoRecursivoDinamicoThread(convertArrayToArrayList(arregloA),convertArrayToArrayList(arregloB));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                t.start();
                t.join();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (Americana recursivo (dinámico))");
                ExcelController.escribirEnHoja(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 5:
                startTime = System.nanoTime();
                t = new Thread(new _5InglesIterativoEstaticoThread(arregloA, arregloB));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (Inglesa iterativo (estático))");
                ExcelController.escribirEnHoja(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 6:
                startTime = System.nanoTime();
                t = new Thread(new _6InglesIterativoDinamicoThread(convertArrayToArrayList(arregloA),convertArrayToArrayList(arregloB)));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (Inglesa iterativo (dinámico))");
                ExcelController.escribirEnHoja(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 7:
                startTime = System.nanoTime();
                /*t = new Thread(new _7InglesRecursivoEstaticoThread(arregloA, arregloB));
                t.run();*/

                t = new Thread(() -> {
                    try {
                        increaseStackSize();
                        new _7InglesRecursivoEstaticoThread(arregloA, arregloB);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                t.start();
                t.join();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (Inglesa recursivo (estático))");
                ExcelController.escribirEnHoja(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 8:
                startTime = System.nanoTime();
                /*t = new Thread(new _8InglesRecursivoDinamicoThread(convertArrayToArrayList(arregloA),convertArrayToArrayList(arregloB)));
                t.run();*/

                t = new Thread(() -> {
                    try {
                        increaseStackSize();
                        new _8InglesRecursivoDinamicoThread(convertArrayToArrayList(arregloA),convertArrayToArrayList(arregloB));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                t.start();
                t.join();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (Inglesa recursivo (dinámico))");
                ExcelController.escribirEnHoja(id, caso, size + "", (endTime - startTime));
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 9:
                startTime = System.nanoTime();
                t = new Thread(new _9RusaIEstaticaThread(arregloA, arregloB));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (Rusa (estático))");
                ExcelController.escribirEnHoja(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 10:
                startTime = System.nanoTime();
                /*t = new Thread(new _10HinduEstaticoThread(arregloA, arregloB));
                t.run();*/

                t = new Thread(() -> {
                    try {
                        increaseStackSize();
                        new _10HinduEstaticoThread(arregloA, arregloB);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                t.start();
                t.join();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (Hindú (estático))");
                ExcelController.escribirEnHoja(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 11:
                startTime = System.nanoTime();
                t = new Thread(new _11EgipciaEstaticoThread(arregloA, arregloB));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (Egipcia (estático))");
                ExcelController.escribirEnHoja(id, caso, size + "", (endTime - startTime));
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 12:
                startTime = System.nanoTime();
                /*t = new Thread(new _12KratsubaEstaticoThread(arregloA, arregloB));
                t.run();*/

                t = new Thread(() -> {
                    try {
                        increaseStackSize();
                        new _12KratsubaEstaticoThread(arregloA, arregloB);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                t.start();
                t.join();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (Algoritmo de Karatsuba (estático))");
                //ExcelController.escribirEnHoja(id, caso, size + "", (endTime - startTime));
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 13:
                startTime = System.nanoTime();
                t = new Thread((Runnable) new _13RepresentadaPorCadenasThread(convertBigIntegerToStringArray(arregloA), convertBigIntegerToStringArray(arregloB)));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (Multiplicación representada con cadenas)");
                ExcelController.escribirEnHoja(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 14:
                startTime = System.nanoTime();
                t = new Thread(new _14DivideyVenceras1Thread(convertArrayToBigInteger(arregloA), convertArrayToBigInteger(arregloB)));
                t.run();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (Divide y vencerás 1 (estático))");
                ExcelController.escribirEnHoja(id, caso, size + "", (endTime - startTime));
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
            case 15:
                startTime = System.nanoTime();
                /*t = new Thread(new _15DivideyVenceras2Thread(convertArrayToBigInteger(arregloA), convertArrayToBigInteger(arregloB)));
                t.run();*/

                t = new Thread(() -> {
                    try {
                        increaseStackSize();
                        new _15DivideyVenceras2Thread(convertArrayToBigInteger(arregloA), convertArrayToBigInteger(arregloB));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                t.start();
                t.join();
                endTime = System.nanoTime();
                System.out.println("Tiempo de respuesta en nanosegundos: " + (endTime - startTime) + " (Divide y vencerás 2 (estático))");
                ExcelController.escribirEnHoja(id, caso, size + "", (endTime - startTime) );
                acumularValores((endTime - startTime), String.valueOf(id));
                break;
        }

    }

    public static String[] convertBigIntegerToStringArray(BigInteger[] arreglo) {
        String[] strings = new String[arreglo.length];

        for (int i = 0; i < arreglo.length; i++) {
            strings[i] = arreglo[i].toString();
        }

        return strings;
    }

    public static BigInteger convertArrayToBigInteger(BigInteger[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        for (BigInteger element : array) {
            stringBuilder.append(element.toString());
        }
        return new BigInteger(stringBuilder.toString());
    }

    public static ArrayList<BigInteger> convertArrayToArrayList(BigInteger[] array) {
        // Crea un nuevo ArrayList a partir del array usando Arrays.asList()
        ArrayList<BigInteger> arrayList = new ArrayList<>(Arrays.asList(array));

        // Devuelve el ArrayList resultante
        return arrayList;
    }

    private static void guardarPromedioTiempoEjecucion(int id, int caso, double averageTime) {
        if (caso == 8) {
            File file = new File("assets/promedio/promedio_times" + id + ".txt");
            if (!file.exists()) {
                FileWriter writer = null;
                try {
                    writer = new FileWriter(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    DecimalFormat df = new DecimalFormat("#.##");
                    String formatted = df.format(averageTime);
                    writer.write(formatted);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static void increaseStackSize() throws Exception {
        Field field = ClassLoader.class.getDeclaredField("sys_paths");
        field.setAccessible(true);
        field.set(null, null);

        String os = System.getProperty("os.name").toLowerCase();
        String command;

        if (os.contains("win")) {
            command = "cmd /C start /B /MIN cmd.exe /C \"\"set \"JAVA_OPTS=%JAVA_OPTS% -Xss8m\"\" && \"\"%~s0\"\" %*";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            command = "sh -c \"exec nohup \"$0\" \"$@\" 2> /dev/null &\" ";
        } else {
            throw new Exception("Unsupported operating system: " + os);
        }

        Runtime.getRuntime().exec(command);
    }

    public static BigInteger[] generateRandomBigIntegerArray(int rows) {
        // Create a new one-dimensional array of BigInteger objects of size rows x columns.
        BigInteger[] array = new BigInteger[rows];

        // Create a new Random object to generate random values.
        Random random = new Random();

        // Iterate over each element of the array and assign a random BigInteger value between 1000 and 9000.
        for (int i = 0; i < rows; i++) {
                BigInteger value = BigInteger.valueOf(random.nextInt(8001) + 1000);
                array[i] = value;
        }

        // Return the array with random values.
        return array;
    }

    public static void guardarValoresFinales() throws IOException {
        List<List<Double>> executionTimesList = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        int numFiles = 15;

        // Leer los archivos y extraer los valores y los ids
        for (int i = 1; i <= numFiles; i++) {
            String fileName = "assets/datos/execution_times" + i + ".txt";
            File file = new File(fileName);

            List<Double> executionTimes = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double lastValue = Double.parseDouble(values[values.length - 1]);
                executionTimes.add(lastValue);
            }

            executionTimesList.add(executionTimes);

            // Agregar el id correspondiente
            if (i == 1) {
                ids.add("Americana iterativo (estático)");
            } else if (i == 2) {
                ids.add("Americana iterativo (dinámico)");
            } else if (i == 3) {
                ids.add("Americana recursivo (estático)");
            } else if (i == 4) {
                ids.add("Americana recursivo (dinámico)");
            } else if (i == 5) {
                ids.add("Inglesa iterativo (estático)");
            } else if (i == 6) {
                ids.add("Inglesa iterativo (dinámico)");
            } else if (i == 7) {
                ids.add("Inglesa recursivo (estático)");
            } else if (i == 8) {
                ids.add("Inglesa recursivo (dinámico)");
            } else if (i == 9) {
                ids.add("Rusa (estático)");
            } else if (i == 10) {
                ids.add("Hindú (estático)");
            } else if (i == 11) {
                ids.add("Egipcia (estático)");
            } else if (i == 12) {
                ids.add("Algoritmo de Karatsuba (estático)");
            } else if (i == 13) {
                ids.add("Multiplicación representada con cadenas");
            } else if (i == 14) {
                ids.add("Divide y vencerás 1 (estático)");
            }  else {
                ids.add("Divide y vencerás 2 (estático)");
            }

            br.close();
        }

        // Ordenar los valores de menor a mayor y crear los archivos de salida
        List<Double> sortedValues = new ArrayList<>();
        FileWriter valuesFileWriter = new FileWriter("assets/datos/execution_times_sorted.txt");
        FileWriter idsFileWriter = new FileWriter("assets/datos/ids.txt");

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

        for (int i = 0; i < numFiles; i++) {
            sortedValues.addAll(executionTimesList.get(i));
        }

        Collections.sort(sortedValues);

        for (int i = 0; i < sortedValues.size(); i++) {
            double value = sortedValues.get(i);
            valuesFileWriter.write(decimalFormat.format(value) + ",");
            int index = -1;
            for (int j = 0; j < numFiles; j++) {
                if (executionTimesList.get(j).contains(value)) {
                    index = j;
                    break;
                }
            }
            idsFileWriter.write(ids.get(index) + "\n");
        }

        valuesFileWriter.close();
        idsFileWriter.close();
    }



    public static void acumularValores(long time,String id)
    {
        try {
            FileWriter fileWriter = new FileWriter("assets/datos/execution_times"+id+".txt", true);
            fileWriter.write(time + ",");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static double calculateAverageExecutionTime(int i) {
        double sum = 0;
        int count = 0;
        try {
            Scanner scanner = new Scanner(new File("assets/datos/execution_times" + i + ".txt"));
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                sum += Double.parseDouble(scanner.next());
                count++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Formatear el resultado con dos decimales

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        return count > 0 ? Double.parseDouble(df.format(sum / count)) : 0;
    }


    public static void eliminarArchivo() {
        File fileExc = new File("assets/datos/execution_times_sorted.txt");
        fileExc.delete();
        File id = new File("assets/datos/ids.txt");
        id.delete();
        for (int i = 1; i <= 15; i++) {
            File filePromds = new File("assets/promedio/promedios.txt");
            File file = new File("assets/datos/execution_times" + i + ".txt");
            File fileProm = new File("assets/promedio/promedio_times" + i + ".txt");

            if (file.exists()) {
                filePromds.delete();
                fileProm.delete();
                file.delete();
            }
        }
    }
}
