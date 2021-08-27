package com.emanuelvictor.java1.exercicios.exercicios4.entity;

public class Carga extends Veiculo {

    private int taxa;
    private int cargaMax;

    public Carga() {
        super();
        this.taxa = 0;
        this.cargaMax = 0;
    }

    @Override
    public int calcVel() {
        // CALCAULA C/H
        return this.getVelocMax() * 1000 * 100;
    }

    public int getTaxa() {
        return taxa;
    }

    public final void setTaxa(int taxa) {
        this.taxa = taxa;
    }

    public int getCargaMax() {
        return cargaMax;
    }

    public final void setCargaMax(int cargaMax) {
        this.cargaMax = cargaMax;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Taxa: " + this.getTaxa());
        System.out.println("Carga máxima: " + this.getCargaMax());
        System.out.println("Velocidade máxima: " + this.calcVel() + " centímetros por segundo");
    }

}
