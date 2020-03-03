package br.org.pti.api.functional.inventario.domain.repository;

import br.org.pti.api.functional.inventario.domain.entity.configuracao.GrupoAcessoPermissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Repository
public interface IGrupoAcessoPermissaoRepository extends JpaRepository<GrupoAcessoPermissao, Long> {

    /**
     * @param grupoAcessoId long
     * @return List<GrupoAcessoPermissao>
     */
    List<GrupoAcessoPermissao> findByGrupoAcessoId(final long grupoAcessoId);

}
