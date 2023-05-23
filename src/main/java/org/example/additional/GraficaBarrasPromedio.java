package org.example.additional;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class GraficaBarrasPromedio extends JFrame {

    private int[] ids;
    private double[] promedios;

    public GraficaBarrasPromedio(int[] ids, double[] promedios) {
        super("Diagrama de Barras de Promedio de TE(ns)");
        this.ids = ids;
        this.promedios = promedios;

        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 1020));

        setContentPane(chartPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Crear la imagen del gráfico
        int width = 1920; // Ancho de la imagen en píxeles
        int height = 1080; // Altura de la imagen en píxeles
        String filename = "assets/graficos/TiempoPromedio/grafico.png"; // Nombre del archivo de la imagen
        File file = new File(filename);
        try {
            ChartUtilities.saveChartAsPNG(file, createChart(createDataset()), width, height);
            System.out.println("Imagen del gráfico guardada en " + file.getAbsolutePath());
        } catch (IOException ex) {
            System.err.println("Error al guardar la imagen del gráfico: " + ex.getMessage());
        }

    }

    public CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Agregar los datos a la tabla
        for (int i = 0; i <= ids.length; i++) {
            switch (i)
            {
                case 0:
                    dataset.addValue(promedios[i], "Algoritmos", "NaivStandard");
                    break;

                case 1:
                    dataset.addValue(promedios[i], "Algoritmos", "NaivOnArray");
                    break;

                case 2:
                    dataset.addValue(promedios[i], "Algoritmos", "NaivKahan");
                    break;
                case 3:
                    dataset.addValue(promedios[i], "Algoritmos", "NaivLoopUnrollingTwo");
                    break;
                case 4:
                    dataset.addValue(promedios[i], "Algoritmos", "NaivLoopUnrollingThree");
                    break;
                case 5:
                    dataset.addValue(promedios[i], "Algoritmos", "NaivLoopUnrollingFour");
                    break;
                case 6:
                    dataset.addValue(promedios[i], "Algoritmos", "WinogradOriginal");
                    break;
                case 7:
                    dataset.addValue(promedios[i], "Algoritmos", "WinogradScaled");
                    break;
                case 8:
                    dataset.addValue(promedios[i], "Algoritmos", "StrassenNaiv");
                    break;
                case 9:
                    dataset.addValue(promedios[i], "Algoritmos", "StrassenWinograd");
                    break;
                case 10:
                    dataset.addValue(promedios[i], "Algoritmos", "V1_Sequential block");
                    break;
                case 11:
                    dataset.addValue(promedios[i], "Algoritmos", "V1_Parallel Block");
                    break;
                case 12:
                    dataset.addValue(promedios[i], "Algoritmos", "V2_Sequential block");
                    break;
                case 13:
                    dataset.addValue(promedios[i], "Algoritmos", "V2_Parallel Block");
                    break;
                case 14:
                    dataset.addValue(promedios[i], "Algoritmos", "V3_Sequential block");
                    break;
            }

        }

        return dataset;
    }

    public JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Tiempo promedio de ejecucion (nanosegundos)", // Título del gráfico
                "ID",              // Etiqueta del eje X
                "TE(ns)",              // Etiqueta del eje Y
                dataset,              // Datos para el gráfico
                PlotOrientation.VERTICAL, // Orientación del gráfico
                true,                 // Incluir leyenda
                true,                 // Incluir tooltips
                false                 // Incluir URLs
        );

        // Personalizar el gráfico
        chart.setBackgroundPaint(Color.white);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 7));

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        rangeAxis.setNumberFormatOverride(formatter);
        rangeAxis.setAutoRangeIncludesZero(false);

        return chart;
    }

   /* public static void main(String[] args) {
        int[] ids = {1, 2, 3, 4, 5};
        double[] promedios = {20.5, 18.2, 22.3, 25.0, 19.1};

        BarChartExample demo = new BarChartExample(ids, promedios);
    }*/
}
