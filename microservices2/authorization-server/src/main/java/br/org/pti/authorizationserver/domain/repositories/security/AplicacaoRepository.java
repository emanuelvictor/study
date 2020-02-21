package br.org.pti.authorizationserver.domain.repositories.security;

import br.org.pti.authorizationserver.domain.entities.security.Aplicacao;
import br.org.pti.authorizationserver.domain.repositories.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 2.0.0, 01/01/2020
 */
@Repository
public interface AplicacaoRepository extends DefaultRepository<Aplicacao> {

    /**
     * @param identificador
     * @return
     */
    Optional<Aplicacao> findByIdentificador(final String identificador);

    /**
     * @param identificador
     * @param id
     * @return
     */
    Optional<Aplicacao> findByIdentificadorAndIdNot(final String identificador, final Long id);

    /**
     * @param filter
     * @return
     */
    @Query("from Aplicacao u " +
            "where filter(:filter, u.nome, u.identificador) = true " +
            "order by u.nome")
    Page<Aplicacao> findByFiltro(final String filter, final Pageable pageable);
}
