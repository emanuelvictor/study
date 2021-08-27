package com.emanuelvictor.java1.exercicios.questionario1.entity;

public class Veiculo {

    // Atributos privados, impedem o acesso externo
    private double imposto;

    // Atributos protegidos, liberam acesso para as classes filhas
    private double valor;

    public void calculeImposto() {
        final double ALIQUOTA = 0.0125;
        this.calculeImposto(ALIQUOTA);
    }

    void calculeImposto(final double aliquota) {
        imposto = this.valor * aliquota;
    }

    public void println() {
        final String label = this.getClass().getName().substring(this.getClass().getName().indexOf("Veiculo"));

        System.out.println("\t O imposto do " + label.substring(0, 7) + " " + label.substring(7) + " a ser pago " + this.imposto);
    }

    // Troca de mensagens
    public void setValor(final double valor) {
        this.valor = valor;
    }

    // Troca de mensagens
    public double getImposto() {
        return imposto;
    }
}
