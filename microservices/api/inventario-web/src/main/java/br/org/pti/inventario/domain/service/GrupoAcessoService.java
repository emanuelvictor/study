package br.org.pti.inventario.domain.service;

import br.org.pti.inventario.domain.entity.configuracao.GrupoAcesso;
import br.org.pti.inventario.domain.entity.configuracao.GrupoAcessoPermissao;
import br.org.pti.inventario.domain.repository.IGrupoAcessoPermissaoRepository;
import br.org.pti.inventario.domain.repository.IGrupoAcessoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Service
@RequiredArgsConstructor
public class GrupoAcessoService {

    /**
     *
     */
    private final IGrupoAcessoRepository grupoAcessoRepository;

    /**
     *
     */
    private final IGrupoAcessoPermissaoRepository grupoAcessoPermissaoRepository;

    /**
     * @param defaultFilter String
     * @param pageable      Pageable
     * @return Page<GrupoAcesso>
     */
    public Page<GrupoAcesso> listByFilters(final String defaultFilter, final Pageable pageable) {
        return this.grupoAcessoRepository.listByFilters(defaultFilter, pageable);
    }

    /**
     * @param id long
     * @return {@link Optional<GrupoAcesso>}
     */
    public Optional<GrupoAcesso> findById(final long id) {
        return this.grupoAcessoRepository.findById(id);
    }

    /**
     * @param grupoAcesso {@link GrupoAcesso}
     * @return {@link GrupoAcesso}
     */
    @Transactional
    public GrupoAcesso save(final GrupoAcesso grupoAcesso) {
        Assert.notEmpty(grupoAcesso.getGruposAcessoPermissoes(), "Defina permissões para esse Grupo de Acesso");
        return grupoAcessoRepository.save(grupoAcesso);
    }

    /**
     * @param id          long
     * @param grupoAcesso {@link GrupoAcesso}
     * @return {@link GrupoAcesso}
     */
    public GrupoAcesso save(final long id, final GrupoAcesso grupoAcesso) {
        grupoAcesso.setId(id);

        Assert.notEmpty(grupoAcesso.getGruposAcessoPermissoes(), "Defina permissões para esse Grupo de Acesso");

        /*
         * Lista auxiliar com os grupos acesso permissao que serão persistidos a posteriore
         */
        final Set<GrupoAcessoPermissao> grupoAcessoPermissaoList = grupoAcesso.getGruposAcessoPermissoes();

        /*
         * Seto null nos grupos acesso permissoes, dessa forma o entityManager remove todos via cascade
         */
        grupoAcesso.setGruposAcessoPermissoes(new HashSet<>());

        /*
         * Atualizo o grupo de acesso
         */
        this.grupoAcessoRepository.save(grupoAcesso);

        /*
         * Insiro todos os grupo acesso permissao
         */
        grupoAcessoPermissaoList.forEach(grupoAcessoPermissao -> {
            if (grupoAcessoPermissao.getId() == null)
                grupoAcessoPermissao.setId(null);
            this.grupoAcessoPermissaoRepository.save(grupoAcessoPermissao);
        });

        return grupoAcesso;
    }

    /**
     * @param id Long
     * @return Boolean
     */
    public Boolean delete(final long id) {
        this.grupoAcessoRepository.deleteById(id);
        return true;
    }

}
