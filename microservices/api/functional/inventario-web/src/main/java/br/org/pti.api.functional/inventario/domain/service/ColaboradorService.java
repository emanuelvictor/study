package br.org.pti.api.functional.inventario.domain.service;

import br.org.pti.api.functional.inventario.application.ldap.LdapConfig;
import br.org.pti.api.functional.inventario.domain.entity.configuracao.Usuario;
import br.org.pti.api.functional.inventario.domain.entity.pessoal.dto.ColaboradorDTO;
import br.org.pti.api.functional.inventario.domain.repository.feign.IColaboradorFeignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Service
@RequiredArgsConstructor
public class ColaboradorService {

    /**
     *
     */
    private final LdapConfig ldapConfig;

    /**
     *
     */
    private final LdapTemplate ldapTemplate;
    /**
     *
     */
    private final IColaboradorFeignRepository colaboradorFeignRepository;

    /**
     * @param centroCustoCodigo String
     * @param pageable          Page
     * @return Page<Colaborador>
     */
    public Page<ColaboradorDTO> listColaboradoresByCentroCustoCodigo(final String centroCustoCodigo, final Pageable pageable) {

        final Page<ColaboradorDTO> colaboradores = this.colaboradorFeignRepository.listColaboradoresByCentroCustoCodigo(centroCustoCodigo, pageable);

        colaboradores.forEach(colaboradorDTO ->
                colaboradorDTO.setEmail(Optional.ofNullable(findByLdapName(colaboradorDTO.getNome())).orElse(new Usuario(colaboradorDTO.getEmail())).getEmail())
        );

        return colaboradores;
    }

    /**
     * @param nome {String}
     * @return Usuario
     */
    private Usuario findByLdapName(final String nome) {

        final LdapQuery query = LdapQueryBuilder.query().base(ldapConfig.getBaseDN()).where("name").is(nome);

        final List<Usuario> usuarios = this.ldapTemplate.search(query, (AttributesMapper<Usuario>) attributes -> {
            final Usuario usuario = new Usuario();

            if (attributes.get("sAMAccountName") != null) {
                usuario.setUsername((String) attributes.get("sAMAccountName").get());
            }

            if (attributes.get("cn") != null) {
                usuario.setNome((String) attributes.get("cn").get());
            }

            if (attributes.get("mail") != null) {
                usuario.setEmail((String) attributes.get("mail").get());
            } else if (attributes.get("userPrincipalName") != null) {
                usuario.setEmail((String) attributes.get("userPrincipalName").get());
            } else {
                usuario.setEmail("naocadastrado@pti.org.br");
            }

            return usuario;
        });

        // A busca é feita pelo nome do usuário, se retornar mais de um, então retorna nulo
        return usuarios == null || usuarios.size() != 1 ? null : usuarios.get(0);
    }
}
