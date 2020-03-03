package br.org.pti.inventario.domain.repository;

import br.org.pti.inventario.domain.entity.patrimonio.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Repository
public interface ILocalizacaoRepository extends JpaRepository<Localizacao, Long> {

    Optional<Localizacao> findByCodigo(final String codigo);

}
