package br.org.pti.authorizationserver.domain.repositories.security;

import br.org.pti.authorizationserver.domain.entities.security.Permissao;
import br.org.pti.authorizationserver.domain.repositories.DefaultRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 2.0.0, 01/01/2020
 */
@Repository
public interface PermissaoRepository extends DefaultRepository<Permissao> { }
