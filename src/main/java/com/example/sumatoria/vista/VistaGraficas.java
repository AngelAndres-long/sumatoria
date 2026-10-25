package com.example.sumatoria.vista;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;

public class VistaGraficas {

    private BorderPane root;

    public VistaGraficas(ArrayList<Long> valoresN,
                         ArrayList<Long> tIterativa,
                         ArrayList<Long> tGauss,
                         ArrayList<Long> tRecursiva) {

        root = new BorderPane();

        // ===== EJES CON ESCALA LOGARÍTMICA =====
        NumberAxis x = new NumberAxis();
        x.setLabel("n (tamaño de entrada)");
        x.setForceZeroInRange(false);

        NumberAxis y = new NumberAxis();
        y.setLabel("Tiempo (ns)");
        y.setForceZeroInRange(false);

        LineChart<Number, Number> chart = new LineChart<>(x, y);
        chart.setTitle("Análisis Empírico - Sumatoria de 1 a n (escala log)");
        chart.setCreateSymbols(true);

        // Series
        XYChart.Series<Number, Number> sIter = new XYChart.Series<>();
        sIter.setName("Iterativa O(n)");

        XYChart.Series<Number, Number> sGauss = new XYChart.Series<>();
        sGauss.setName("Gauss O(1)");

        XYChart.Series<Number, Number> sRec = new XYChart.Series<>();
        sRec.setName("Recursiva O(n)");

        for (int i = 0; i < valoresN.size(); i++) {
            long n = valoresN.get(i);

            sIter.getData().add(new XYChart.Data<>(n, tIterativa.get(i)));
            sGauss.getData().add(new XYChart.Data<>(n, tGauss.get(i)));

            if (tRecursiva.get(i) >= 0) {
                sRec.getData().add(new XYChart.Data<>(n, tRecursiva.get(i)));
            }
        }

        chart.getData().addAll(sIter, sGauss, sRec);
        root.setCenter(chart);
    }

    public BorderPane getRoot() {
        return root;
    }
}