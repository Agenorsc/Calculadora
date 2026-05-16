package com.exemplo;

import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {
        iniciarServidor(5000);
    }

    public static Javalin iniciarServidor(int porta) {
        Calculadora calc = new Calculadora();
        Javalin app = Javalin.create().start(porta);

        app.post("/adicao", ctx -> {
            String body = ctx.body();
            double v1 = extrairValor(body, "valor1");
            double v2 = extrairValor(body, "valor2");
            double resultado = calc.somar(v1, v2);
            
            // Monta o JSON manualmente como texto puro para não depender do Jackson
            ctx.contentType("application/json");
            ctx.result("{\"resultado\":" + resultado + "}");
        });

        app.post("/subtracao", ctx -> {
            String body = ctx.body();
            double v1 = extrairValor(body, "valor1");
            double v2 = extrairValor(body, "valor2");
            double resultado = calc.subtrair(v1, v2);
            
            ctx.contentType("application/json");
            ctx.result("{\"resultado\":" + resultado + "}");
        });

        app.post("/multiplicacao", ctx -> {
            String body = ctx.body();
            double v1 = extrairValor(body, "valor1");
            double v2 = extrairValor(body, "valor2");
            double resultado = calc.multiplicar(v1, v2);
            
            ctx.contentType("application/json");
            ctx.result("{\"resultado\":" + resultado + "}");
        });

        app.post("/divisao", ctx -> {
            String body = ctx.body();
            double v1 = extrairValor(body, "valor1");
            double v2 = extrairValor(body, "valor2");
            try {
                double resultado = calc.dividir(v1, v2);
                ctx.contentType("application/json");
                ctx.result("{\"resultado\":" + resultado + "}");
            } catch (ArithmeticException e) {
                ctx.status(400);
                ctx.contentType("application/json");
                ctx.result("{\"erro\":\"" + e.getMessage() + "\"}");
            }
        });

        return app;
    }

    private static double extrairValor(String json, String chave) {
        try {
            // Remove espaços, quebras de linha e aspas para limpar a String
            String limpo = json.replaceAll("\\s+", "").replaceAll("\"", "");
            // Procura pela chave exata (ex: valor1:)
            String busca = chave + ":";
            int inicioIdx = limpo.indexOf(busca) + busca.length();
            int fimIdx = limpo.indexOf(",", inicioIdx);
            
            if (fimIdx == -1) {
                fimIdx = limpo.indexOf("}", inicioIdx);
            }
            
            String valorTexto = limpo.substring(inicioIdx, fimIdx);
            return Double.parseDouble(valorTexto);
        } catch (Exception e) {
            return 0.0;
        }
    }
}