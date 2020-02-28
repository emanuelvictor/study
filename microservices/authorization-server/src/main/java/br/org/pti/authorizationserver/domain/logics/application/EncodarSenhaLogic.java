package br.org.pti.authorizationserver.domain.logics.application;

import br.org.pti.authorizationserver.domain.entities.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 2.0.0, 04/01/2020
 */
@Component
@RequiredArgsConstructor
public class EncodarSenhaLogic implements ApplicationSavingLogic {

    private final PasswordEncoder passwordEncoder;

    /**
     * @param value Aplicacao
     */
    @Override
    public void perform(final Application value) {
        value.setClientSecret(this.passwordEncoder.encode(value.getClientSecret()));
    }
}
