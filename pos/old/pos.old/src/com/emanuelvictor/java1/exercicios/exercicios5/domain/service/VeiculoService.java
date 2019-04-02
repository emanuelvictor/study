package com.emanuelvictor.java1.exercicios.exercicios5.domain.service;

import com.emanuelvictor.java1.exercicios.exercicios5.application.exceptions.VeicExistException;
import com.emanuelvictor.java1.exercicios.exercicios5.domain.entity.Carga;
import com.emanuelvictor.java1.exercicios.exercicios5.domain.entity.Passeio;
import com.emanuelvictor.java1.exercicios.exercicios5.domain.repository.CargaRepository;
import com.emanuelvictor.java1.exercicios.exercicios5.domain.repository.PasseioRepository;

import java.util.List;

/**
 * Emanuel Victor De Oliveira Fonseca
 */
public class VeiculoService {

    private final PasseioRepository passeioRepository = new PasseioRepository();

    private final CargaRepository cargaRepository = new CargaRepository();

    public Passeio save(final Passeio passeio) throws VeicExistException {
        this.verifyIfExists(passeio.getPlaca());
        return passeioRepository.save(passeio);
    }

    public Carga save(final Carga carga) throws VeicExistException {
        this.verifyIfExists(carga.getPlaca());
        return cargaRepository.save(carga);
    }

    public Passeio update(final String placa, final Passeio passeio) {
        return passeioRepository.update(placa, passeio);
    }

    public Carga update(final String placa, final Carga carga) {
        return cargaRepository.update(placa, carga);
    }

    public void verifyIfExists(final String placa) throws VeicExistException {
        if (this.findPasseioByPlaca(placa) != null)
            throw new VeicExistException("Esse veículo já existe!");

        if (this.findCargaByPlaca(placa) != null)
            throw new VeicExistException("Esse veículo já existe!");
    }

    public List<Passeio> getAllPasseio() {
        return passeioRepository.getAll();
    }

    public List<Carga> getAllCarga() {
        return cargaRepository.getAll();
    }

    public Passeio findPasseioByPlaca(final String placa) {
        return passeioRepository.findByPlaca(placa);
    }

    public Carga findCargaByPlaca(final String placa) {
        return cargaRepository.findByPlaca(placa);
    }
}
