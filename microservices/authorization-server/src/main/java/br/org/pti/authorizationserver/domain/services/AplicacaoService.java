package br.org.pti.authorizationserver.domain.services;
import br.org.pti.authorizationserver.domain.entities.security.Aplicacao;
import br.org.pti.authorizationserver.domain.entities.security.Permissao;
import br.org.pti.authorizationserver.domain.logics.application.ApplicationSavingLogic;
import br.org.pti.authorizationserver.domain.logics.application.ApplicationUpdatingLogic;
import br.org.pti.authorizationserver.domain.repositories.security.AplicacaoRepository;
import br.org.pti.authorizationserver.domain.repositories.security.PermissaoRepository;
import br.org.pti.authorizationserver.infrastructure.misc.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 2.0.0, 04/01/2020
 */
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AplicacaoService implements ClientDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final PermissaoRepository permissaoRepository;
    private final AplicacaoRepository aplicacaoRepository;

    private final List<ApplicationSavingLogic> applicationSavingLogics;
    private final List<ApplicationUpdatingLogic> userUpdatingLogics;

    /**
     * @param aplicacao Usuario
     */
    @Transactional
    public Aplicacao save(final Aplicacao aplicacao) {

        Assert.isNull(aplicacao.getId(), "Você não tem acesso a essa aplicação");

        this.applicationSavingLogics.forEach(logic -> logic.perform(aplicacao));

        return this.aplicacaoRepository.save(aplicacao);
    }

    /**
     * @param aplicacao Usuario
     */
    @Transactional
    public Aplicacao save(final long id, final Aplicacao aplicacao) {

        this.aplicacaoRepository.findById(id).ifPresentOrElse(actual -> {
            this.userUpdatingLogics.forEach(logic -> logic.perform(aplicacao));

            this.aplicacaoRepository.save(aplicacao);
        }, () -> {
            throw new IllegalArgumentException("O id não pode ser nulo para atualização");
        });

        return aplicacao;

    }

    /**
     * @param id long
     */
    @Transactional
    public void delete(final long id) {
        this.aplicacaoRepository.findById(id).ifPresent(user -> {
            if (user.getClientId().equals("admin")) {
                throw new IllegalArgumentException("Impossivel deletar o Administrador");
            } else {
                this.aplicacaoRepository.delete(user);
            }
        });
    }

    /**
     * @param id long
     * @return String
     */
    @Transactional
    public String changePassword(final long id) {

        final String password = PasswordGenerator.generate();

        this.aplicacaoRepository.findById(id)
                .ifPresent(user -> {
                    user.setSenha(this.passwordEncoder.encode(password));
                    this.aplicacaoRepository.save(user);
                });

        return password;
    }

    /**
     * @param clientId String
     * @return ClientDetails
     * @throws ClientRegistrationException
     */
    @Override
    public ClientDetails loadClientByClientId(final String clientId) throws ClientRegistrationException {
        return this.aplicacaoRepository.findByIdentificador(clientId)
                .orElseThrow(() -> new UsernameNotFoundException("ClientId " + clientId + " não localizado!"));
    }

    /**
     * @return List<Aplicacao>
     */
    public List<Aplicacao> findAll() {
        return this.aplicacaoRepository.findAll();
    }

    /**
     * @param filtro   String
     * @param pageable Pageable
     * @return Page<Aplicaca
     */
    public Page<Aplicacao> findByFiltro(final String filtro, final Pageable pageable) {
        return aplicacaoRepository.findByFiltro(filtro, pageable);
    }

    /**
     * @param id Long
     * @return Optional<Aplicacao>
     */
    public Optional<Aplicacao> findById(final long id) {
        return this.aplicacaoRepository.findById(id);
    }

    /**
     * @return Page<Permissao>
     */
    public Page<Permissao> findAllPermissions() {
        return permissaoRepository.findAll(PageRequest.of(0, 100000));
    }
}
