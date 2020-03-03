package br.org.pti.api.functional.portalcompras.domain.repository;

import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.ExtratoContrato;
import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.InstrumentoJuridico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IExtratoContratoRepository extends JpaRepository<ExtratoContrato, Long> {

    @Query("FROM ExtratoContrato extratoContratacao WHERE (   " +
            "       (FILTER(:filter, extratoContratacao.objeto, extratoContratacao.numeroProcesso) = TRUE) "
            + "AND ( extratoContratacao.instrumentoJuridico = :instrumentoJuridico OR '%'||:instrumentoJuridico||'%' = NULL ) "
            + "AND (( extratoContratacao.dataPublicacao >= :dataInicio  OR '%'||:dataInicio||'%' = NULL ) "
            + "AND ( extratoContratacao.dataPublicacao <= :dataFim OR '%'||:dataFim||'%' = null )) "
            + "   )"
    )
    Page<ExtratoContrato> listByFilters(@Param("filter") final String filter, @Param("instrumentoJuridico") final InstrumentoJuridico instrumentoJuridico,
                                        @Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim, final Pageable pageable);

}
