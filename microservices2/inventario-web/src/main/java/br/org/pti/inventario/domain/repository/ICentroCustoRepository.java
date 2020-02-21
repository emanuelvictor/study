package br.org.pti.inventario.domain.repository;

import br.org.pti.inventario.domain.entity.pessoal.CentroCusto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Repository
public interface ICentroCustoRepository extends JpaRepository<CentroCusto, Long> {

    /**
     * @param codigo String
     * @return CentroCusto
     */
    Optional<CentroCusto> findByCodigo(final String codigo);

    /**
     *
     * @param gestorId
     * @return
     */
    Set<CentroCusto> findByGestorId(final long gestorId);

    /**
     *
     * @param email
     * @return
     */
    @Query(" FROM CentroCusto centroCusto WHERE ( LOWER(centroCusto.gestor.email) = LOWER(:email) OR centroCusto.gestor.email = :email )")
    Set<CentroCusto> findByGestorEmail(String email);
}
