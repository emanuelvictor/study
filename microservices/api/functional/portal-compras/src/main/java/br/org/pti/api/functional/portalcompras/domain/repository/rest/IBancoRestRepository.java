package br.org.pti.api.functional.portalcompras.domain.repository.rest;

import br.org.pti.api.functional.portalcompras.domain.entity.cadastros.Banco;

import java.util.List;

public interface IBancoRestRepository {

    List<Banco> listByFilters(final String defaultFilter);

}
