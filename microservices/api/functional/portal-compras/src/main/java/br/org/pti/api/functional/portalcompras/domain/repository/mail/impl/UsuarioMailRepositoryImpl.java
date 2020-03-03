package br.org.pti.api.functional.portalcompras.domain.repository.mail.impl;

import br.org.pti.api.functional.portalcompras.domain.entity.configuracao.Usuario;
import br.org.pti.api.functional.portalcompras.domain.repository.mail.IUsuarioAbstractMailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.concurrent.Future;

@Component
@RequiredArgsConstructor
public class UsuarioMailRepositoryImpl implements IUsuarioAbstractMailRepository {

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
     * @param usuario Usuario
     * @param url     String
     * @return Future<Void>
     */
    @Override
    public Future<Void> sendNewAccountCreated(final Usuario usuario, final String url) {
        final MimeMessagePreparator preparator = mimeMessage -> {
            final String title = "Portal de Compras Fundação PTI-BR – Credenciais de acesso";

            final MimeMessageHelper message = getMimeMessageHelper(mimeMessage, title, usuario.getEmail(), mailFrom);

            final Context context = getContext(title, logo, usuario, url);

            final String content = templateEngine.process("mail-templates/new-account-created", context);
            message.setText(content, true);

            //Add the inline image, referenced from the HTML code as "cid:${logo}"
            message.addInline(logo, new ClassPathResource("META-INF/resources/static/images/logo-pti-small.png"));
        };

        this.mailSender.send(preparator);

        return new AsyncResult<>(null);
    }

    /**
     * @param usuario Usuario
     * @param url     String
     * @return Future<Void>Eu tin
     */
    @Async
    @Override
    public Future<Void> sendNewUserAccount(final Usuario usuario, final String url) {
        final MimeMessagePreparator preparator = mimeMessage -> {
            final String title = "Portal de Compras Fundação PTI-BR – Credenciais de acesso";

            final MimeMessageHelper message = getMimeMessageHelper(mimeMessage, title, usuario.getEmail(), mailFrom);

            final Context context = getContext(title, logo, usuario, url);

            final String content = templateEngine.process("mail-templates/new-account", context);
            message.setText(content, true);

            //Add the inline image, referenced from the HTML code as "cid:${logo}"

            message.addInline(logo, new ClassPathResource("META-INF/resources/static/images/logo-pti.png"));
        };

        this.mailSender.send(preparator);
        return new AsyncResult<>(null);
    }

    /**
     * @param usuario Usuario
     * @param url     String
     * @return Future<Void>
     */
    @Async
    @Override
    public Future<Void> sendPasswordReset(final Usuario usuario, final String url) {
        final MimeMessagePreparator preparator = mimeMessage -> {
            final String title = "Portal de Compras Fundação PTI-BR – Recuperar senha";

            final MimeMessageHelper message = getMimeMessageHelper(mimeMessage, title, usuario.getEmail(), mailFrom);

            final Context context = getContext(title, logo, usuario, url);


            final String content = templateEngine.process("mail-templates/reset-password", context);
            message.setText(content, true);

            //Add the inline image, referenced from the HTML code as "cid:${logo}"
            message.addInline(logo, new ClassPathResource("META-INF/resources/static/images/logo-pti-small.png"));
        };

        this.mailSender.send(preparator);

        return new AsyncResult<>(null);
    }

}
