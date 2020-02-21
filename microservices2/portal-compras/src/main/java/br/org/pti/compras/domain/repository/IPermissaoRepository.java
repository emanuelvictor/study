package br.org.pti.compras.domain.repository;

import br.org.pti.compras.domain.entity.configuracao.Permissao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissaoRepository extends JpaRepository<Permissao, Long> {

    @Query("FROM Permissao permissao WHERE" +
            "   ((:branch = TRUE AND permissao.permissaoSuperior.id IS NULL) OR (:branch IS NULL)) " +
            "   AND " +
            "   (   " +
            "       (" +
            "               FILTER(:filter, permissao.nome) = TRUE" +
            "       )" +
            "   )"
    )
    Page<Permissao> listByFilters(@Param("filter") final String filter, @Param("branch") final Boolean branch, final Pageable pageable);

}
