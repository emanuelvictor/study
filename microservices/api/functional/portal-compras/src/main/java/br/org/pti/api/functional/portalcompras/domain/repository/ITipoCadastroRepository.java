package br.org.pti.api.functional.portalcompras.domain.repository;

import br.org.pti.api.functional.portalcompras.domain.entity.cadastros.TipoCadastro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ITipoCadastroRepository extends JpaRepository<TipoCadastro, Long> {

    @Query("FROM TipoCadastro tipoCadastro WHERE" +
            "   (   " +
            "       ((:ativo IS NOT NULL AND tipoCadastro.ativo = :ativo) OR :ativo IS NULL) " +
            "       AND " +
            "       (" +
            "               FILTER(:filter, tipoCadastro.nome) = TRUE" +
            "       )" +
            "   )"
    )
    Page<TipoCadastro> listByFilters(@Param("filter") final String filter, @Param("ativo") final Boolean ativo, final Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE TipoCadastro" +
            "    SET ativo = CASE WHEN ativo = TRUE THEN FALSE ELSE TRUE END" +
            "    WHERE id = :id")
    void updateAtivo(@Param("id") final long id);
}
