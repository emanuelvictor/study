package br.org.pti.compras.domain.repository.rest;

import br.org.pti.compras.domain.entity.cadastros.Banco;

import java.util.List;

public interface IBancoRestRepository {

    List<Banco> listByFilters(final String defaultFilter);

}
