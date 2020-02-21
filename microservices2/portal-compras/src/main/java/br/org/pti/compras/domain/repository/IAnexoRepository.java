package br.org.pti.compras.domain.repository;

import br.org.pti.compras.domain.entity.publicacoes.Anexo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnexoRepository extends JpaRepository<Anexo, Long> {

    Anexo findByPublicacaoIdAndNome(final long publicacaoId, final String nome);

    Page<Anexo> findAllByPublicacaoId(final long publicacaoId, final Pageable pageable);

}
