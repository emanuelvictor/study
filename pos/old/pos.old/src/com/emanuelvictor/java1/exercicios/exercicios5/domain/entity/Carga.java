package com.emanuelvictor.java1.exercicios.exercicios5.domain.entity;

/**
 * Emanuel Victor De Oliveira Fonseca
 */
public final class Carga extends Veiculo {

    private Integer taxa;
    private Integer cargaMax;

    public Carga() {
        super();
        this.taxa = 0;
        this.cargaMax = 0;
    }

    @Override
    public final Integer calcVel() {
        // CALCAULA C/H
        return this.getVelocMax() * 1000 * 100;
    }

    @Override
    public int calcular() {
        return this.taxa + this.cargaMax + this.getVelocMax() + this.getMotor().getPotencia() + this.getMotor().getQtdPist();
    }

    public final Integer getTaxa() {
        return taxa;
    }

    public final void setTaxa(Integer taxa) {
        this.taxa = taxa;
    }

    public final Integer getCargaMax() {
        return cargaMax;
    }

    public final void setCargaMax(Integer cargaMax) {
        this.cargaMax = cargaMax;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("| Taxa: " + this.getTaxa());
        System.out.println("| Carga máxima: " + this.getCargaMax());
        System.out.println("| Velocidade máxima: " + this.calcVel() + " centímetros por segundo");
        System.out.println("| Soma dos valores numéricos: " + this.calcular());
        System.out.println("| ------------------------------------------------------------------ |");

    }

}
