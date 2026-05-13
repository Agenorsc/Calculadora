package com.exemplo;

/**
 * Classe responsavel por operacoes matematicas basicas.
 */
public final class Calculadora { // final evita erro de DesignForExtension

    /**
     * Soma dois numeros.
     */
    public double somar(final double a, final double b) { // final nos parametros
        return a + b;
    }

    /**
     * Subtrai dois numeros.
     */
    public double subtrair(final double a, final double b) {
        return a - b;
    }

    /**
     * Multiplica dois numeros.
     */
    public double multiplicar(final double a, final double b) {
        return a * b;
    }

    /**
     * Divide dois numeros.
     */
    public double dividir(final double a, final double b) {
        if (b == 0) {
            throw new ArithmeticException("Divisao por zero nao permitida.");
        }
        return a / b;
    }
}
