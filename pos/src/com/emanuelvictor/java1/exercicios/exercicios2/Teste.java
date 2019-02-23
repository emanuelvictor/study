package com.emanuelvictor.java1.exercicios.exercicios2;

import com.emanuelvictor.java1.exercicios.exercicios2.entity.Veiculo;

/**
 * Emanuel Victor De Oliveira Fonseca
 */
public class Teste {
    public static void main(final String args[]) {

        final Veiculo[] veiculos = new Veiculo[10];

        for (int k = 0; k < 5; k++) {

            for (int i = 0; i < 10; i++) {
                final Veiculo veiculo = new Veiculo();
                veiculo.setMarca("Marca: " + (i + 1));
                veiculo.setModelo("Modelo: " + (i + 1));
                veiculo.setPlaca("Placa: " + (i + 1));
                veiculo.setVelocMax(220);
                veiculo.getMotor().setQtdPist(k + 1);
                veiculo.getMotor().setPotencia(k + 1);

                veiculos[i] = veiculo;

                veiculos[i].print();
            }
        }
    }
}
