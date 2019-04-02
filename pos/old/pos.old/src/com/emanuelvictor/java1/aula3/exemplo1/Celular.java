package com.emanuelvictor.java1.aula3.exemplo1;

public class Celular extends Telefone {
    private int numTorres;

    public Celular() {
        super();
        numTorres = 0;
    }

    public int getNumTorres() {
        return numTorres;
    }

    public void setNumTorres(int numTorres) {
        this.numTorres = numTorres;
    }

    public void impDados() {
        super.impDados();
    }

}
