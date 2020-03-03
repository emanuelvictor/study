package br.org.pti.api.functional.portalcompras.domain.repository;

import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.AvisoContratacao;
import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.Modalidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IAvisoContratacaoRepository extends JpaRepository<AvisoContratacao, Long> {

    @Query("FROM AvisoContratacao avisoContratacao WHERE (   " +
            "       (FILTER(:filter, avisoContratacao.numeroModalidade, avisoContratacao.objeto, avisoContratacao.numeroProcesso) = TRUE)"
            + "AND ( avisoContratacao.modalidade = :modalidade OR '%'||:modalidade||'%' = NULL ) "
            + "AND (( avisoContratacao.dataPublicacao >= :dataInicio  OR '%'||:dataInicio||'%' = NULL ) "
            + "AND ( avisoContratacao.dataPublicacao <= :dataFim OR '%'||:dataFim||'%' = null )) "
            + "   )"
    )
    Page<AvisoContratacao> listByFilters(@Param("filter") final String filter, @Param("modalidade") final Modalidade modalidade,
                                         @Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim, final Pageable pageable);

}
