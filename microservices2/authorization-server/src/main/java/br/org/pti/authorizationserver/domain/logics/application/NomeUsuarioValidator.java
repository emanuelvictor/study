package br.org.pti.authorizationserver.domain.logics.application;

import br.org.pti.authorizationserver.domain.entities.security.Aplicacao;
import br.org.pti.authorizationserver.domain.exceptions.BusinessLogicException;
import br.org.pti.authorizationserver.domain.repositories.security.AplicacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 2.0.0, 04/01/2020
 */
@Component
@RequiredArgsConstructor
public class NomeUsuarioValidator implements ApplicationSavingLogic, ApplicationUpdatingLogic {

    private final AplicacaoRepository userRepository;

    /**
     * @param value
     */
    @Override
    public void perform(final Aplicacao value) {
        if (value.isSaved()) {
            this.userRepository.findByIdentificadorAndIdNot(value.getClientId(), value.getId())
                    .ifPresent(this::duplicatedException);
        } else {
            this.userRepository.findByIdentificador(value.getClientId())
                    .ifPresent(this::duplicatedException);
        }
    }

    /**
     * @param user
     */
    private void duplicatedException(final Aplicacao user) {
        throw new BusinessLogicException("Nome de usuário já utilizado por: " + user.getNome());
    }
}
