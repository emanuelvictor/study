package br.org.pti.api.functional.portalcompras.domain.repository;

import br.org.pti.api.functional.portalcompras.domain.entity.configuracao.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 23/10/2019
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    /**
     *
     * @param nome
     * @return
     */
    Optional<Pessoa> findByNome(String nome);
}
