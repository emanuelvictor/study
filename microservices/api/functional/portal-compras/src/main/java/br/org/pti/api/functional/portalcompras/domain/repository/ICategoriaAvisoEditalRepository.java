package br.org.pti.api.functional.portalcompras.domain.repository;

import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.CategoriaAvisoEdital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ICategoriaAvisoEditalRepository extends JpaRepository<CategoriaAvisoEdital, Long> {

    Set<CategoriaAvisoEdital> findByAvisoEditalIdOrderByCategoriaNome(final long id);

}
