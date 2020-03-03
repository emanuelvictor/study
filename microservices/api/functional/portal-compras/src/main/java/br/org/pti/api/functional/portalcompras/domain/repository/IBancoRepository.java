package br.org.pti.api.functional.portalcompras.domain.repository;

import br.org.pti.api.functional.portalcompras.domain.entity.cadastros.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBancoRepository extends JpaRepository<Banco, Long> {

    Banco findBancoByCodigo(final String codigo);

}
