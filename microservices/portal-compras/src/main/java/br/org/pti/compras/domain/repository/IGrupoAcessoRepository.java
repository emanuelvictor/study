package br.org.pti.compras.domain.repository;

import br.org.pti.compras.domain.entity.configuracao.GrupoAcesso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IGrupoAcessoRepository extends JpaRepository<GrupoAcesso, Long> {

    @Query("FROM GrupoAcesso grupoAcesso WHERE" +
            "   (" +
            "       FILTER(:filter, grupoAcesso.nome) = TRUE" +
            "   )"
    )
    Page<GrupoAcesso> listByFilters(@Param("filter") final String filter, final Pageable pageable);

}
