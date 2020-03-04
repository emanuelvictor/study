package br.org.pti.api.functional.accountmanager.domain.logics.application;

import br.org.pti.api.functional.accountmanager.domain.entities.Application;
import br.org.pti.api.functional.accountmanager.domain.repositories.ApplicationRepository;
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

    private final ApplicationRepository applicationRepository;

    /**
     * @param value
     */
    @Override
    public void perform(final Application value) {
        if (value.isSaved()) {
            this.applicationRepository.findByClientIdAndIdNot(value.getClientId(), value.getId())
                    .ifPresent(this::duplicatedException);
        } else {
            this.applicationRepository.findByClientId(value.getClientId())
                    .ifPresent(this::duplicatedException);
        }
    }

    /**
     * @param application
     */
    private void duplicatedException(final Application application) {
//        throw new BusinessLogicException("Nome de usuário já utilizado por: " + application.getPermissionsName());
    }
}
