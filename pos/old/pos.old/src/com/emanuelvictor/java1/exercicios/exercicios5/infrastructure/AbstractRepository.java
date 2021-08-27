package com.emanuelvictor.java1.exercicios.exercicios5.infrastructure;

import com.emanuelvictor.java1.exercicios.exercicios5.application.exceptions.VeicExistException;
import com.emanuelvictor.java1.exercicios.exercicios5.domain.entity.Veiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Emanuel Victor De Oliveira Fonseca
 */
public abstract class AbstractRepository<T> {

    private List<T> veiculos = new ArrayList<>();

    public T save(final T veiculo) throws VeicExistException {

        if (this.findByPlaca(((Veiculo) veiculo).getPlaca()) != null)
            throw new VeicExistException("Esse veículo já existe!");

        this.veiculos.add(veiculo);
        return veiculo;
    }

    public T update(final String placa, final T t) {

        ((Veiculo) t).setPlaca(placa);

        for (int i = 0; i < veiculos.size(); i++)
            if (((Veiculo) veiculos.get(i)).getPlaca().equals(placa))
                this.veiculos.set(i, t);

        return t;
    }

    public List<T> getAll() {
        return this.veiculos;
    }

    public T findByPlaca(final String placa) {

        final List<T> veiculos = this.veiculos.stream().filter(t ->
                ((Veiculo) t).getPlaca().equals(placa)
        ).collect(Collectors.toList());

        if (veiculos.isEmpty())
            return null;
        else
            return veiculos.get(0);

    }
}
