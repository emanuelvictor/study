package br.org.pti.compras.domain.repository.mail.impl;

import br.org.pti.compras.domain.entity.fornecedor.Fornecedor;
import br.org.pti.compras.domain.repository.dtos.email.Email;
import br.org.pti.compras.domain.repository.mail.IFornecedorAbstractMailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Future;

import static br.org.pti.compras.domain.entity.configuracao.Pessoa.formatDocumento;

@Component
@RequiredArgsConstructor
public class FornecedorMailRepositoryImpl implements IFornecedorAbstractMailRepository {

    /**
     *
     */
    private final String logo = "logo-pti.png";

    /**
     *
     */
    private final JavaMailSender mailSender;

    /**
     *
     */
    private final TemplateEngine templateEngine;

    /**
     *
     */
    @Value("${spring.mail.from}")
    private String mailFrom;

    /**
     * @param fornecedor Fornecedor
     * @param url        String
     * @return Future<Void>
     */
    @Override
    public Future<Void> sendFornecedorAprovado(final Fornecedor fornecedor, final String url) {
        final MimeMessagePreparator preparator = mimeMessage -> {
            final String title = "Portal de Compras PTI-BR - Aprovação de Cadastro";

            final MimeMessageHelper message = getMimeMessageHelper(mimeMessage, title, fornecedor.getUsuario().getEmail(), mailFrom);

            final Context context = getContext(title, logo, fornecedor.getUsuario(), url);

            final String content = templateEngine.process("mail-templates/fornecedor-aprovado", context);
            message.setText(content, true);

            //Add the inline image, referenced from the HTML code as "cid:${logo}"
            message.addInline(logo, new ClassPathResource("META-INF/resources/static/images/logo-pti-small.png"));
        };

        this.mailSender.send(preparator);

        return new AsyncResult<>(null);
    }

    /**
     * @param fornecedor Fornecedor
     * @param url        String
     * @return Future<Void>
     */
    @Override
    public Future<Void> sendFornecedorRecusado(final Fornecedor fornecedor, final String url) {
        final MimeMessagePreparator preparator = mimeMessage -> {
            final String title = "Portal de Compras PTI-BR - Recusa de Cadastro";

            final MimeMessageHelper message = getMimeMessageHelper(mimeMessage, title, fornecedor.getUsuario().getEmail(), mailFrom);

            final Context context = getContext(title, logo, fornecedor.getUsuario(), url);

            final String content = templateEngine.process("mail-templates/fornecedor-recusado", context);
            message.setText(content, true);

            //Add the inline image, referenced from the HTML code as "cid:${logo}"
            message.addInline(logo, new ClassPathResource("META-INF/resources/static/images/logo-pti-small.png"));
        };

        this.mailSender.send(preparator);

        return new AsyncResult<>(null);
    }

    @Override
    public Future<Void> sendMailToFornecedoresVencidos(final Fornecedor fornecedor, final String url) {

        final MimeMessagePreparator preparator = mimeMessage -> {

            final String title = "Portal de Compras PTI-BR - Aviso de Vencimento do CRC";

            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setSubject(title);

            helper.setTo(fornecedor.getUsuario().getEmail());

            helper.setFrom(mailFrom);

            final Context context = new Context();
            context.setVariable("title", title);
            context.setVariable("logo", logo);
            context.setVariable("fornecedor", fornecedor);
            context.setVariable("cnpj", formatDocumento(fornecedor.getUsuario().getDocumento()));
            context.setVariable("dataVencimento", fornecedor.getDataExpiracao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            context.setVariable("url", url);

            final String content = templateEngine.process("mail-templates/fornecedor-vencendo", context);
            helper.setText(content, true);

            //Add the inline image, referenced from the HTML code as "cid:${logo}"
            helper.addInline(logo, new ClassPathResource("META-INF/resources/static/images/logo-pti-small.png"));

        };

        this.mailSender.send(preparator);

        return new AsyncResult<>(null);
    }

    @Override
    public Future<Void> sendMassMail(final Email email) {
        email.getFornecedores().forEach(fornecedor -> this.sendCustomEmail(email, fornecedor));
        return new AsyncResult<>(null);
    }

    /**
     * Auxiliar, utilizado para enviar um e-mail customizado para um único fornecedor.
     *
     * @param email      {Email}
     * @param fornecedor {Fornecedor}
     */
    private void sendCustomEmail(final Email email, final Fornecedor fornecedor) {

        final MimeMessagePreparator preparator = mimeMessage -> {

            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            // Adiciona os anexos
            email.getAnexosEmail().forEach(anexoEmail -> {
                try {
                    helper.addAttachment(anexoEmail.getNome(), new ByteArrayResource(anexoEmail.getConteudo()), anexoEmail.getType());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });

            helper.setTo(fornecedor.getUsuario().getEmail());

            helper.setFrom(mailFrom);

            helper.setSubject(email.getAssunto());

            final Context context = new Context();
            context.setVariable("title", email.getAssunto());
            context.setVariable("conteudo", email.getConteudo());

            final String content = templateEngine.process("mail-templates/custom-mail", context);
            helper.setText(content, true);

            //Add the inline image, referenced from the HTML code as "cid:${logo}"
            helper.addInline(logo, new ClassPathResource("META-INF/resources/static/images/logo-pti-small.png"));

        };
        this.mailSender.send(preparator);

    }
}
