package com.emanuelvictor.java1.exercicios.questionario1.entity;

public class VeiculoPesado extends Veiculo {

    // Sobrescrita de método
    public void calculeImposto() {
        double ALIQUOTA = 0.035;
        super.calculeImposto(ALIQUOTA);
    }
}
