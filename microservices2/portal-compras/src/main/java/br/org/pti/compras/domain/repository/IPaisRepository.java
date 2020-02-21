package br.org.pti.compras.domain.repository;

import br.org.pti.compras.domain.entity.endereco.Pais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaisRepository extends JpaRepository<Pais, Long> {

    @Query("FROM Pais pais WHERE" +
            "   (   " +
            "       (" +
            "               FILTER(:filter, pais.nome) = TRUE" +
            "       )" +
            "   )"
    )
    Page<Pais> listByFilters(@Param("filter") final String filter, final Pageable pageable);

}
