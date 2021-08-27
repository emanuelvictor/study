package com.emanuelvictor.java1.exercicios.exercicios4;

import com.emanuelvictor.java1.exercicios.exercicios4.entity.Carga;
import com.emanuelvictor.java1.exercicios.exercicios4.entity.Passeio;
import com.emanuelvictor.java1.exercicios.exercicios4.entity.Veiculo;

/**
 * Emanuel Victor De Oliveira Fonseca
 */
public class Teste {
    public static void main(final String args[]) {

        final Veiculo[] veiculos = new Veiculo[10];

        for (int k = 0; k < 5; k++) {

            for (int i = 0; i < 10; i++) {
                System.out.println("-------------------------------------------------------------------------------");
                if (i > 5) {
                    final Carga veiculo = new Carga();
                    veiculo.setMarca("Marca " + (i + 1));
                    veiculo.setModelo("Modelo " + (i + 1));
                    veiculo.setPlaca("Placa " + (i + 1));
                    veiculo.setCargaMax(10000);
                    veiculo.setTaxa(250);

                    veiculo.setVelocMax(200);
                    veiculo.getMotor().setQtdPist(k + 1);
                    veiculo.getMotor().setPotencia(k + 1);

                    veiculos[i] = veiculo;

                    veiculos[i].print();
                } else {
                    final Passeio veiculo = new Passeio();
                    veiculo.setMarca("Marca: " + (i + 1));
                    veiculo.setModelo("Modelo: " + (i + 1));
                    veiculo.setPlaca("Placa: " + (i + 1));
                    veiculo.setVelocMax(220);
                    veiculo.setQtdePassageiros(5);

                    veiculo.getMotor().setQtdPist(k + 1);
                    veiculo.getMotor().setPotencia(k + 1);

                    veiculos[i] = veiculo;

                    veiculos[i].print();
                }

            }
        }
    }
}
