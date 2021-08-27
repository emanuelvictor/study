package com.emanuelvictor.java1.exercicios.exercicios4.entity;

public final class Passeio extends Veiculo {

    private int qtdePassageiros;

    public Passeio() {
        super();
        this.qtdePassageiros = 0;
    }

    @Override
    public int calcVel() {
        // CALCAULA M/H
        return this.getVelocMax() * 1000;
    }

    public int getQtdePassageiros() {
        return qtdePassageiros;
    }

    public final void setQtdePassageiros(int qtdePassageiros) {
        this.qtdePassageiros = qtdePassageiros;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Quantidade de passageiros: " + this.getQtdePassageiros());
        System.out.println("Velocidade máxima: " + this.calcVel() + " metros por segundo");
    }
}
