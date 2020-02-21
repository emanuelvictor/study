package br.org.pti.inventario.domain.repository;

import br.org.pti.inventario.domain.entity.configuracao.GrupoAcesso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Repository
public interface IGrupoAcessoRepository extends JpaRepository<GrupoAcesso, Long> {

    /**
     * @param filter   String
     * @param pageable Pageable
     * @return Page<GrupoAcesso>
     */
    @Query("FROM GrupoAcesso grupoAcesso WHERE" +
            "   (" +
            "       FILTER(:filter, grupoAcesso.nome) = TRUE" +
            "   )"
    )
    Page<GrupoAcesso> listByFilters(@Param("filter") final String filter, final Pageable pageable);

}
