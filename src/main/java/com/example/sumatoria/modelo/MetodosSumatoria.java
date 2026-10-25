package com.example.sumatoria.modelo;

public class MetodosSumatoria {

    // Solución ITERATIVA - O(n)
    public static long iterativa(long n) {
        long suma = 0;
        for (long i = 1; i <= n; i++) {
            suma += i;
        }
        return suma;
    }

    // Solución de GAUSS - O(1)
    public static long gauss(long n) {
        return n * (n + 1) / 2;
    }

    // Solución RECURSIVA - O(n)
    // Solo segura para n pequeños (~10 000)
    public static long recursiva(long n) {
        if (n <= 0) {
            return 0;
        }
        return n + recursiva(n - 1);
    }
}