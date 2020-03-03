package br.org.pti.api.functional.portalcompras.domain.repository.mail;

import br.org.pti.api.functional.portalcompras.domain.repository.dtos.email.Email;
import br.org.pti.api.functional.portalcompras.domain.entity.fornecedor.Fornecedor;
import br.org.pti.api.functional.portalcompras.infrastructure.mail.IAbstractMailRepository;

import java.util.concurrent.Future;

/**
 *
 */
public interface IFornecedorAbstractMailRepository extends IAbstractMailRepository {

    Future<Void> sendFornecedorAprovado(final Fornecedor fornecedor, final String serverURL);

    Future<Void> sendFornecedorRecusado(final Fornecedor fornecedor, final String serverURL);

    Future<Void> sendMailToFornecedoresVencidos(final Fornecedor fornecedor, final String serverURL);

    Future<Void> sendMassMail(final Email email);
}
