package br.org.pti.compras.domain.service;

import br.org.pti.compras.domain.entity.configuracao.GrupoAcesso;
import br.org.pti.compras.domain.entity.configuracao.GrupoAcessoPermissao;
import br.org.pti.compras.domain.repository.IGrupoAcessoPermissaoRepository;
import br.org.pti.compras.domain.repository.IGrupoAcessoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GrupoAcessoService {

    private final IGrupoAcessoRepository grupoAcessoRepository;

    private final IGrupoAcessoPermissaoRepository grupoAcessoPermissaoRepository;

    public Page<GrupoAcesso> listByFilters(final String defaultFilter, final Pageable pageable) {
        return this.grupoAcessoRepository.listByFilters(defaultFilter, pageable);
    }

    public Optional<GrupoAcesso> findById(final long id) {
        final GrupoAcesso grupoAcesso = this.grupoAcessoRepository.findById(id).get();

//        grupoAcesso.getGruposAcessoPermissoes().forEach(grupoAcessoPermissao -> {
//            grupoAcessoPermissao.getPermissao().setPermissaoSuperior(null);
//        });

        return Optional.of(grupoAcesso);
    }

    @Transactional
    public GrupoAcesso save(final GrupoAcesso grupoAcesso) {
        Assert.notEmpty(grupoAcesso.getGruposAcessoPermissoes(), "Defina permissões para esse Grupo de Acesso");

        return grupoAcessoRepository.save(grupoAcesso);
    }

    //    @Transactional
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

    public Boolean delete(final long id) {
        this.grupoAcessoRepository.deleteById(id);
        return true;
    }

}
