package br.org.pti.compras.domain.repository;

import br.org.pti.compras.domain.entity.endereco.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstadoRepository extends JpaRepository<Estado, Long> {

    @Query("FROM Estado estado WHERE" +
            "   (   " +
            "       (" +
            "               FILTER(:filter, estado.nome) = TRUE" +
            "       ) "
            + "AND ( estado.pais.id = :paisId OR '%'||:paisId||'%' = NULL ) " +
            "   )"
    )
    Page<Estado> listByFilters(@Param("filter") final String filter, @Param("paisId") Long paisId, final Pageable pageable);

}
