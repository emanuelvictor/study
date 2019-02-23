package com.emanuelvictor.java1.exercicios.exercicios5.domain.entity;

/**
 * Emanuel Victor De Oliveira Fonseca
 */
public final class Passeio extends Veiculo {

    private Integer qtdePassageiros;

    public Passeio() {
        super();
        this.qtdePassageiros = 0;
    }

    @Override
    public final Integer calcVel() {
        // CALCAULA M/H
        return this.getVelocMax() * 1000;
    }

    @Override
    public int calcular() {
        return this.getPlaca().length() + this.getMarca().length() + this.getModelo().length();
    }

    public final Integer getQtdePassageiros() {
        return qtdePassageiros;
    }

    public final void setQtdePassageiros(Integer qtdePassageiros) {
        this.qtdePassageiros = qtdePassageiros;
    }

    @Override
    public final void print() {
        super.print();
        System.out.println("| Quantidade de passageiros: " + this.getQtdePassageiros());
        System.out.println("| Velocidade máxima: " + this.calcVel() + " metros por segundo");
        System.out.println("| Quantidade de letras: " + this.calcular());
        System.out.println("| ------------------------------------------------------------------ |");
    }
}
