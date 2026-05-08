package com.exemplo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculadoraTest {
    Calculadora calc = new Calculadora();

    @Test
    void testeSomaSimples() {
        assertEquals(10, calc.somar(7, 3));
    }

    @Test
    void testeDivisaoPorZero() {
        assertThrows(ArithmeticException.class, () -> calc.dividir(10, 0));
    }

    @ParameterizedTest
    @CsvSource({
        "10, 5, 2",
        "20, 4, 5",
        "100, 10, 10",
        "9, 3, 3"
    })
    void testeDivisoesEmGrupo(double a, double b, double resultadoEsperado) {
        assertEquals(resultadoEsperado, calc.dividir(a, b));
    }
}