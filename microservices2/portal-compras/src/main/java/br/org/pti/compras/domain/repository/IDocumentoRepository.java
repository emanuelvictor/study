package br.org.pti.compras.domain.repository;

import br.org.pti.compras.domain.entity.fornecedor.Documento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentoRepository extends JpaRepository<Documento, Long> {

    Documento findByFornecedorIdAndNome(final long fornecedorId, final String nome);

    Page<Documento> findAllByFornecedorId(final long fornecedorId, final Pageable pageable);

}
