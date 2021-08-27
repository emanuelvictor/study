package com.emanuelvictor.java1.exercicios.questionario1;


import com.emanuelvictor.java1.exercicios.questionario1.entity.Veiculo;
import com.emanuelvictor.java1.exercicios.questionario1.entity.VeiculoAquatico;
import com.emanuelvictor.java1.exercicios.questionario1.entity.VeiculoPesado;
import com.emanuelvictor.java1.exercicios.questionario1.entity.VeiculoSimples;

public class Main {

    public static void main(final String args[]) {
        Veiculo veiculo = new VeiculoAquatico();
        veiculo.setValor(20000);
        veiculo.calculeImposto();
        veiculo.println();

        // Polimorfismo por coerção
        veiculo = new VeiculoPesado();
        veiculo.setValor(50000);
        veiculo.calculeImposto();
        veiculo.println();

        // Polimorfismo por coerção
        veiculo = new VeiculoSimples();
        veiculo.setValor(15000);
        veiculo.calculeImposto();
        veiculo.println();
    }
}
