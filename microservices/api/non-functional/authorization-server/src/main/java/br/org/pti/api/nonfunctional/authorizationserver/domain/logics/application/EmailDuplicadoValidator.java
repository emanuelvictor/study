package br.org.pti.api.nonfunctional.authorizationserver.domain.logics.application;

import br.org.pti.api.nonfunctional.authorizationserver.domain.entities.Application;
import br.org.pti.api.nonfunctional.authorizationserver.domain.repositories.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 2.0.0, 04/01/2020
 */
@Component
@RequiredArgsConstructor
public class EmailDuplicadoValidator implements ApplicationSavingLogic, ApplicationUpdatingLogic {

    private final ApplicationRepository userRepository;

    /**
     * @param value
     */
    @Override
    public void perform(Application value) {

//        if (value.isAplicacao()) {
//            return;
//        }
//
//        if (value.isSaved()) {
//            this.userRepository.findByEmailAndIdNot(value.getEmail(), value.getId())
//                    .ifPresent(this::duplicatedException);
//        } else {
//            this.userRepository.findByEmail(value.getEmail())
//                    .ifPresent(this::duplicatedException);
//        }
    }

    /**
     * @param application
     */
    private void duplicatedException(final Application application) {
//        throw new BusinessLogicException("Endereço de e-mail já utilizado por: " + application.getPermissionsName());
    }
}
