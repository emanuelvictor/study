package br.org.pti.compras.domain.repository;

import br.org.pti.compras.domain.entity.fornecedor.AtividadeEconomica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAtividadeEconomicaRepository extends JpaRepository<AtividadeEconomica, String> {

    @Query("FROM AtividadeEconomica atividadeEconomica WHERE" +
            "   (   " +
            "       FILTER(:filter, atividadeEconomica.text, atividadeEconomica.code) = TRUE" +
            "   )"
    )
    Page<AtividadeEconomica> listByFilters(@Param("filter") final String filter, final Pageable pageable);

}
