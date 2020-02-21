package br.org.pti.compras.domain.repository;

import br.org.pti.compras.domain.entity.endereco.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICidadeRepository extends JpaRepository<Cidade, Long> {

    @Query("FROM Cidade cidade WHERE" +
            "   (   " +
            "           (FILTER(:filter, cidade.nome) = TRUE) " +
            "		AND ( cidade.estado.id = :estadoId OR '%'||:estadoId||'%' = NULL ) " +
            "		AND ( cidade.estado.uf = :uf OR '%'||:uf||'%' = NULL ) " +
            "       AND " +
            "       (" +
            "           (" +
            "               cidade.estado.id IN :estadosFilter" +
            "           ) OR :estadosFilter IS NULL" +
            "       )" +
            "   )"
    )
    Page<Cidade> listByFilters(@Param("filter") final String filter,
                               @Param("estadosFilter") final List<Long> estadosFilter,
                               @Param("estadoId") Long estadoId, @Param("uf") String uf, final Pageable pageable);

}
