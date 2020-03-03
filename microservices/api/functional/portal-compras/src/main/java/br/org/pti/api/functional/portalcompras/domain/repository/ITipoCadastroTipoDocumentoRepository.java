package br.org.pti.api.functional.portalcompras.domain.repository;

import br.org.pti.api.functional.portalcompras.domain.entity.cadastros.TipoCadastroTipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoCadastroTipoDocumentoRepository extends JpaRepository<TipoCadastroTipoDocumento, Long> {

}
