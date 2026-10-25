package com.example.sumatoria;

import com.example.sumatoria.modelo.MetodosSumatoria;
import com.example.sumatoria.vista.VistaGraficas;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        ArrayList<Long> valoresN   = new ArrayList<>();
        ArrayList<Long> tIterativa = new ArrayList<>();
        ArrayList<Long> tGauss     = new ArrayList<>();
        ArrayList<Long> tRecursiva = new ArrayList<>();

        long MAX = 100_000_000L;

        System.out.println("N\tIterativa(ns)\tGauss(ns)\tRecursiva(ns)");

        // Warm-up ligero
        MetodosSumatoria.iterativa(1000);
        MetodosSumatoria.gauss(1000);
        MetodosSumatoria.recursiva(500);

        for (long n = 100; n <= MAX; n *= 10) {

            // ----- Iterativa -----
            long t1 = System.nanoTime();
            MetodosSumatoria.iterativa(n);
            long t2 = System.nanoTime();
            long ti = t2 - t1;

            // ----- Gauss -----
            t1 = System.nanoTime();
            MetodosSumatoria.gauss(n);
            t2 = System.nanoTime();
            long tg = t2 - t1;

            // ----- Recursiva (solo hasta 10 000) -----
            long tr = -1;   // -1 significa "no medido"
            if (n <= 10_000) {
                t1 = System.nanoTime();
                MetodosSumatoria.recursiva(n);
                t2 = System.nanoTime();
                tr = t2 - t1;
            }

            // Guardar datos
            valoresN.add(n);
            tIterativa.add(ti);
            tGauss.add(tg);
            tRecursiva.add(tr);

            // Imprimir en consola
            System.out.println(n + "\t" + ti + "\t" + tg + "\t" + (tr >= 0 ? tr : "N/A"));
        }

        // Crear la gráfica
        VistaGraficas vista = new VistaGraficas(valoresN, tIterativa, tGauss, tRecursiva);

        Scene scene = new Scene(vista.getRoot(), 900, 600);
        stage.setTitle("Análisis Empírico - Sumatoria 1 a n");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}