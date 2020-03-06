package br.org.pti.api.functional.integrator.domain.repositories;

import br.org.pti.api.functional.integrator.domain.entities.ativofixo.Patrimonio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, Long> {

    /**
     * @param matricula
     * @param pageable
     * @return
     */
    @Query("FROM Patrimonio pa "
            + "WHERE pa.responsavel.matricula = :matricula")
    Page<Patrimonio> findByResponsavel(String matricula, Pageable pageable);

    /**
     * @param codigo
     * @param pageable
     * @return
     */
    @Query("FROM Patrimonio pa "
            + "WHERE pa.localizacao.codigo = :codigo")
    Page<Patrimonio> findByLocalizacao(String codigo, Pageable pageable);

    /**
     * @param plaqueta
     * @return
     */
    @Query("FROM Patrimonio pa "
            + "WHERE TRIM(pa.plaqueta) = :plaqueta")
    Page<Patrimonio> findByPlaqueta(String plaqueta, Pageable pageable);

    /**
     * @param centroCusto
     * @param pageable
     * @return
     */
    @Query("FROM Patrimonio pa "
            + "WHERE TRIM(pa.centroCusto.codigo) = :centroCusto")
    Page<Patrimonio> findByCentroCusto(String centroCusto, Pageable pageable);

    /**
     * @param centroCusto
     * @param descricaoLocalizacao
     * @param pageable
     * @return
     */
    @Query("FROM Patrimonio pa "
            + "WHERE TRIM(pa.centroCusto.codigo) = :centroCusto "
            + "AND upper(pa.localizacao.descricao) LIKE "
            + "'%'||upper(:descricaoLocalizacao)||'%'")
    Page<Patrimonio> findByCentroCustoAndLocalizacaoLike(String centroCusto,
                                                         String descricaoLocalizacao, Pageable pageable);

    /**
     * @param centroCusto
     * @param descricaoPatrimonio
     * @param pageable
     * @return
     */
    @Query("FROM Patrimonio pa "
            + "WHERE TRIM(pa.centroCusto.codigo) = :centroCusto "
            + "AND upper(pa.descricao) LIKE "
            + "'%'||upper(:descricaoPatrimonio)||'%'")
    Page<Patrimonio> findByCentroCustoAndPatrimonioLike(String centroCusto,
                                                        String descricaoPatrimonio, Pageable pageable);

    /**
     * @param centroCusto
     * @param descricaoLocalizacao
     * @param descricaoPatrimonio
     * @param pageable
     * @return
     */
    @Query("FROM Patrimonio pa "
            + " WHERE TRIM(pa.centroCusto.codigo) = :centroCusto "
            + " AND upper(pa.descricao) LIKE "
            + " '%'||upper(:descricaoPatrimonio)||'%'"
            + " AND upper(pa.localizacao.descricao) LIKE "
            + " '%'||upper(:descricaoLocalizacao)||'%'")
    Page<Patrimonio> findByCentroCustoAndLocalizacaoLikeAndPatrimonioLike(String centroCusto,
                                                                          String descricaoLocalizacao, String descricaoPatrimonio, Pageable pageable);
}
