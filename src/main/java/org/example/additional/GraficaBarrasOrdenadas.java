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
import java.util.ArrayList;

public class GraficaBarrasOrdenadas extends JFrame {

    private final ArrayList<String> ids;
    private double[] promedios;

    public GraficaBarrasOrdenadas(ArrayList<String> ids, double[] promedios) {
        super("Diagrama de Barras En orden ascendente de los TE(ns)");
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
        String filename = "assets/graficos/TiempoOrdenCaso8/grafico.png"; // Nombre del archivo de la imagen
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
        for (int i = 0; i < 14; i++) {
            dataset.addValue(promedios[i], "Algoritmos", ids.get(i));
        }

        return dataset;
    }

    public JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Tiempo de ejecucion (nanosegundos) En el caso 8", // Título del gráfico
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

}