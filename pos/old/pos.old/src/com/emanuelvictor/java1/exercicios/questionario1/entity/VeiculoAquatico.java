package com.emanuelvictor.java1.exercicios.questionario1.entity;

public class VeiculoAquatico extends Veiculo {

    // Sobrescrita de método
    public void calculeImposto() {
        final double ALIQUOTA = 0.02;
        super.calculeImposto(ALIQUOTA);
    }
}
