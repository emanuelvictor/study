package br.org.pti.authorizationserver.domain.logics.application;

import br.org.pti.authorizationserver.domain.entities.Application;
import br.org.pti.authorizationserver.domain.exceptions.BusinessLogicException;
import br.org.pti.authorizationserver.domain.repositories.ApplicationRepository;
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

    private final ApplicationRepository userRepository;

    /**
     * @param value
     */
    @Override
    public void perform(final Application value) {
        if (value.isSaved()) {
            this.userRepository.findByIdentificadorAndIdNot(value.getClientId(), value.getId())
                    .ifPresent(this::duplicatedException);
        } else {
            this.userRepository.findByIdentificador(value.getClientId())
                    .ifPresent(this::duplicatedException);
        }
    }

    /**
     *
     * @param application
     */
    private void duplicatedException(final Application application) {
//        throw new BusinessLogicException("Nome de usuário já utilizado por: " + application.getPermissionsName());
    }
}
