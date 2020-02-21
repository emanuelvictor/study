package br.org.pti.compras.domain.repository;

import br.org.pti.compras.domain.entity.cadastros.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBancoRepository extends JpaRepository<Banco, Long> {

    Banco findBancoByCodigo(final String codigo);

}
