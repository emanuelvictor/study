package br.com.emanuelvictor.domain.entities;

import lombok.Getter;

import java.util.Arrays;
import java.util.Random;

public class Questao {

    @Getter
    private final int[] valores = new int[2];

    public Questao() {
        this.gerarNumerosAleatoriosInterios();
    }

    public int somar() {
        return somar(valores);
    }

    public int somar(int... valores) {
        return Arrays.stream(valores).sum();
    }

    public void gerarNumerosAleatoriosInterios() {
        for (int i = 0; i < valores.length; i++) {
            valores[i] = new Random().nextInt(50);
        }
    }
}
