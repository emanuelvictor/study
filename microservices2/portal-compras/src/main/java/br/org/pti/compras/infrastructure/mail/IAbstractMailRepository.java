package br.org.pti.compras.infrastructure.mail;

import br.org.pti.compras.domain.entity.configuracao.Usuario;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Interface para o envio de e-mails { Para extensão }
 */
public interface IAbstractMailRepository {


    default MimeMessageHelper getMimeMessageHelper(final MimeMessage mimeMessage, final String title, final String email, final String from) throws MessagingException {
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        message.setSubject(title);
        message.setTo(email);
        message.setFrom(from);

        return message;
    }

    default Context getContext(final String title, final String logo, final Usuario usuario, final String url) {
        final Context context = new Context();
        context.setVariable("title", title);
        context.setVariable("logo", logo);
        context.setVariable("user", usuario);
        context.setVariable("url", url);
        return context;
    }

}
