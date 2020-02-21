package br.org.pti.compras.domain.repository;

import br.org.pti.compras.domain.entity.configuracao.GrupoAcessoPermissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGrupoAcessoPermissaoRepository extends JpaRepository<GrupoAcessoPermissao, Long> {

    List<GrupoAcessoPermissao> findByGrupoAcessoId(final long grupoAcessoId);

}
