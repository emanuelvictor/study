package br.org.pti.api.functional.portalcompras.domain.repository.mail;

import br.org.pti.api.functional.portalcompras.domain.entity.configuracao.Usuario;
import br.org.pti.api.functional.portalcompras.infrastructure.mail.IAbstractMailRepository;

import java.util.concurrent.Future;

/**
 * Interface para o envio de e-mails
 */
public interface IUsuarioAbstractMailRepository extends IAbstractMailRepository {

    Future<Void> sendNewUserAccount(final Usuario usuario, final String url);

    Future<Void> sendPasswordReset(final Usuario usuario, final String url);

    Future<Void> sendNewAccountCreated(final Usuario usuario, final String url);

}
