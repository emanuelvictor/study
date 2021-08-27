package com.emanuelvictor.java1.exercicios.exercicios4.entity;

/**
 * Emanuel Victor De Oliveira Fonseca
 */
public abstract class Veiculo {

    private String placa;
    private String marca;
    private String modelo;

    private int velocMax;

    private Motor motor;

    public Veiculo() {
        this.placa = "";
        this.marca = "";
        this.modelo = "";
        this.velocMax = 0;
        this.motor = new Motor();
    }

    public String getPlaca() {
        return placa;
    }

    public final void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public final void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public final void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getVelocMax() {
        return this.velocMax;
    }

    public final void setVelocMax(int velocMax) {
        this.velocMax = velocMax;
    }

    public Motor getMotor() {
        return motor;
    }

    public final void setMotor(Motor motor) {
        this.motor = motor;
    }

    public abstract int calcVel();

    public void print() {
        System.out.println(this.marca);
        System.out.println(this.modelo);
        System.out.println(this.placa);

        System.out.println("Potência: " + this.motor.getPotencia());
        System.out.println("Quantidade de pistões: " + this.motor.getQtdPist());
    }
}
