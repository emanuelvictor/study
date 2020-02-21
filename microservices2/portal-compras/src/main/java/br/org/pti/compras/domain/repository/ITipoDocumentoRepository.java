package br.org.pti.compras.domain.repository;

import br.org.pti.compras.domain.entity.cadastros.TipoDocumento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ITipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {

    @Query("FROM TipoDocumento tipoDocumento WHERE" +
            "   (   " +
            "       ((:ativo IS NOT NULL AND tipoDocumento.ativo = :ativo) OR :ativo IS NULL) " +
            "       AND " +
            "       (" +
            "               FILTER(:filter, tipoDocumento.nome) = TRUE" +
            "       )" +
            "   )"
    )
    Page<TipoDocumento> listByFilters(@Param("filter") final String filter, @Param("ativo") final Boolean ativo, final Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE TipoDocumento" +
            "    SET ativo = CASE WHEN ativo = TRUE THEN FALSE ELSE TRUE END" +
            "    WHERE id = :id")
    void updateAtivo(@Param("id") final long id);

}
