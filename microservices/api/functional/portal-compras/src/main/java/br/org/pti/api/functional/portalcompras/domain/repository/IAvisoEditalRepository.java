package br.org.pti.api.functional.portalcompras.domain.repository;

import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.AvisoEdital;
import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IAvisoEditalRepository extends JpaRepository<AvisoEdital, Long> {

    @Query("SELECT new AvisoEdital (avisoEdital.id, " +
            "                       avisoEdital.prazoPropostas, " +
            "                       avisoEdital.status, " +
            "                       avisoEdital.numeroProcesso, " +
            "                       avisoEdital.numeroEdital, " +
            "                       avisoEdital.dataPublicacao, " +
            "						avisoEdital.objeto, " +
            "						avisoEdital.updated, " +
            "                       MIN(categoriaAvisoEdital.categoria.nome) as categoria) " +
            "   FROM AvisoEdital avisoEdital, CategoriaAvisoEdital categoriaAvisoEdital " +
            "WHERE " +
            "   (   " +
            "           (FILTER(:filter, avisoEdital.objeto, avisoEdital.numeroProcesso) = TRUE) " +
            "       AND ( avisoEdital.status = :status OR '%'||:status||'%' = NULL ) " +
            "       AND ( categoriaAvisoEdital.avisoEdital.id = avisoEdital.id ) " +
            "       AND (( avisoEdital.dataPublicacao >= :dataInicio  OR '%'||:dataInicio||'%' = NULL ) " +
            "       AND ( avisoEdital.dataPublicacao <= :dataFim OR '%'||:dataFim||'%' = null ))" +
            "       AND (categoriaAvisoEdital.categoria.id IN :categoriasFilter OR :categoriasFilter IS NULL)  " +
            " )" +
            "GROUP BY avisoEdital.id, avisoEdital.prazoPropostas, avisoEdital.status, avisoEdital.dataPublicacao, avisoEdital.objeto, avisoEdital.numeroProcesso, avisoEdital.updated"

    )
    Page<AvisoEdital> listByFilters(@Param("filter") final String filter, @Param("categoriasFilter") final List<Long> categoriasFilter,
                                    @Param("status") Status status, @Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim, final Pageable pageable);

}
