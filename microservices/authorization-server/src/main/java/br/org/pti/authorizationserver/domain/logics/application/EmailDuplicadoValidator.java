package br.org.pti.authorizationserver.domain.logics.application;

import br.org.pti.authorizationserver.domain.entities.configuration.Application;
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
public class EmailDuplicadoValidator implements ApplicationSavingLogic, ApplicationUpdatingLogic {

    private final AplicacaoRepository userRepository;

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
     * @param user
     */
    private void duplicatedException(Application user) {
        throw new BusinessLogicException("Endereço de e-mail já utilizado por: " + user.getNome());
    }
}
