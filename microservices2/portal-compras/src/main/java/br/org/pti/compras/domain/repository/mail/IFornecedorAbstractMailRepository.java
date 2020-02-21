package br.org.pti.compras.domain.repository.mail;

import br.org.pti.compras.domain.entity.fornecedor.Fornecedor;
import br.org.pti.compras.domain.repository.dtos.email.Email;
import br.org.pti.compras.infrastructure.mail.IAbstractMailRepository;

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
