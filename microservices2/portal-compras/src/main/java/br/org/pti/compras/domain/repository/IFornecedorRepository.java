package br.org.pti.compras.domain.repository;

import br.org.pti.compras.domain.entity.fornecedor.Fornecedor;
import br.org.pti.compras.domain.entity.fornecedor.StatusFornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IFornecedorRepository extends JpaRepository<Fornecedor, Long> {

    /**
     *
     */
    @Query("SELECT new Fornecedor( " +
            "   fornecedor.id, " +
            "   fornecedor.usuario, " +
            "   fornecedor.razaoSocial," +
            "   fornecedor.souEmpresa," +
            "   fornecedor.possuiInscricaoEstadual, " +
            "   fornecedor.simplesNacional, " +
            "   fornecedor.cooperativa, " +
            "   fornecedor.status, " +
            "   fornecedor.tipoPessoaJuridica," +
            "   fornecedor.inscricaoEstadual, " +
            "   fornecedor.inscricaoMunicipal, " +
            "   fornecedor.descricaoProdutosServicos, " +
            "   endereco, fornecedor.site, " +
            "   fornecedor.feedback, tipoCadastro, " +
            "   fornecedor.dadosRecebimento, fornecedor.dataAprovacao," +
            "   fornecedor.created" +
            ") FROM Fornecedor fornecedor " +
            "       LEFT OUTER JOIN Endereco endereco ON endereco.id = fornecedor.endereco.id" +
            "       LEFT OUTER JOIN Cidade cidade ON cidade.id = endereco.cidade.id" +
            "       LEFT OUTER JOIN Estado estado ON estado.id = cidade.estado.id" +
            "       LEFT OUTER JOIN TipoCadastro tipoCadastro ON tipoCadastro.id = fornecedor.tipoCadastro.id " +
            "       LEFT OUTER JOIN CategoriaFornecedor categoriaFornecedor ON categoriaFornecedor.fornecedor.id = fornecedor.id " +
            "       LEFT OUTER JOIN AtividadeEconomicaPrimaria atividadeEconomicaPrimaria ON atividadeEconomicaPrimaria.fornecedor.id = fornecedor.id " +
            "       LEFT OUTER JOIN AtividadeEconomicaSecundaria atividadeEconomicaSecundaria ON atividadeEconomicaSecundaria.fornecedor.id = fornecedor.id " +
            "   WHERE " +
            "   ( " +
            "       ((" +
            "           (" +
            "               (cast(:umAnoAtras AS date)) IS NOT NULL AND fornecedor.dataAprovacao IS NOT NULL AND fornecedor.dataAprovacao < :umAnoAtras" +
            "           ) OR ((cast(:umAnoAtras AS date)) IS NULL AND (:status IS NULL))" +
            "       )" +
            "       OR " +
            "       ( " +
            "           (" +
            "               (:status IS NOT NULL AND fornecedor.status = :status AND :status != " + StatusFornecedor.APROVADO_VALUE + ")" +
            "               OR " +
            "               (" +
            "                   (" +
            "                           :status IS NOT NULL AND fornecedor.status = :status AND :status = " + StatusFornecedor.APROVADO_VALUE +
            "                       AND ((cast(:umAnoAtras AS date)) IS NOT NULL AND fornecedor.dataAprovacao >= :umAnoAtras)" +
            "                   )" +
            "               ) " +
//            "               OR :status IS NULL" +
            "           ) " +
            "       ))" +
            "       AND ( FILTER(:filter, fornecedor.razaoSocial, fornecedor.usuario.nome, fornecedor.usuario.documento, fornecedor.usuario.email ) = TRUE ) " +
            "       AND " +
            "       (" +
            "           (" +
            "               cidade.id IN :cidadesFilter" +
            "           ) OR :cidadesFilter IS NULL" +
            "       )" +
            "       AND " +
            "       (" +
            "           (" +
            "               estado.id IN :estadosFilter" +
            "           ) OR :estadosFilter IS NULL" +
            "       )" +
            "       AND " +
            "       (" +
            "           (" +
            "               categoriaFornecedor.categoria.id IN :categoriasFilter" +
            "           ) OR :categoriasFilter IS NULL " +
            "       )" +
            "       AND " +
            "       (" +
            "           (" +
            "               atividadeEconomicaPrimaria.atividadeEconomica.id IN :atividadesEconomicasFilter OR atividadeEconomicaSecundaria.atividadeEconomica.id IN :atividadesEconomicasFilter" +
            "           ) OR :atividadesEconomicasFilter IS NULL" +
            "       )" +
            "   )" +
            "   GROUP BY    fornecedor.id, fornecedor.usuario.id, fornecedor.usuario.nome, fornecedor.usuario.documento, fornecedor.usuario.email, fornecedor.usuario.ativo, " +
            "               fornecedor.usuario.administrador, fornecedor.usuario.senha, fornecedor.usuario.tentativasLogin, fornecedor.usuario.ultimaTentativaLogin," +
            "               fornecedor.usuario.ultimoAcesso, fornecedor.usuario.codigo, atividadeEconomicaPrimaria.fornecedor.id, atividadeEconomicaPrimaria.atividadeEconomica.id," +
            "               tipoCadastro.id, endereco.id, fornecedor.dataAprovacao"
    )
    Page<Fornecedor> listByFilters(@Param("filter") final String filter,
                                   @Param("status") final StatusFornecedor status,
                                   @Param("umAnoAtras") final LocalDateTime umAnoAtras,
                                   @Param("atividadesEconomicasFilter") final List<String> atividadesEconomicasFilter,
                                   @Param("categoriasFilter") final List<Long> categoriasFilter,
                                   @Param("estadosFilter") final List<Long> estadosFilter,
                                   @Param("cidadesFilter") final List<Long> cidadesFilter,
                                   final Pageable pageable);

    /**
     *
     */
    @Query("SELECT COUNT(fornecedor) FROM Fornecedor fornecedor " +
            "   WHERE " +
            "   ( " +
            "       fornecedor.status = " + StatusFornecedor.EM_CRIACAO_VALUE +
            "   )"
    )
    int getCountFornecedoresEmCriacao();

    /**
     *
     */
    @Query("SELECT COUNT(fornecedor) FROM Fornecedor fornecedor " +
            "   WHERE " +
            "   ( " +
            "       fornecedor.status = " + StatusFornecedor.EM_ANALISE_VALUE +
            "   )"
    )
    int getCountFornecedoresEmAnalise();

    /**
     *
     */
    @Query("SELECT COUNT(fornecedor) FROM Fornecedor fornecedor " +
            "   WHERE " +
            "   ( " +
            "       fornecedor.status = " + StatusFornecedor.APROVADO_VALUE +
            "   )"
    )
    int getCountFornecedoresAprovados();

    /**
     *
     */
    @Query("SELECT COUNT(fornecedor) FROM Fornecedor fornecedor " +
            "   WHERE " +
            "   ( " +
            "       fornecedor.status = " + StatusFornecedor.RECUSADO_VALUE +
            "   )"
    )
    int getCountFornecedoresRecusados();

    /**
     *
     */
    @Query("SELECT COUNT(fornecedor) FROM Fornecedor fornecedor " +
            "   WHERE " +
            "   ( " +
            "       fornecedor.dataAprovacao < :umAnoAtras" +
            "   )"
    )
    int getCountFornecedoresVencidos(@Param("umAnoAtras") final LocalDateTime umAnoAtras);

    /**
     *
     */
    @Query("FROM Fornecedor fornecedor WHERE " +
            "   (" +
            "       (" +
            "           (fornecedor.emailVencimentoEnviado = FALSE OR fornecedor.emailVencimentoEnviado IS NULL) AND (" +
            "               fornecedor.dataAprovacao IS NOT NULL AND (fornecedor.dataAprovacao > :umAnoAtras AND fornecedor.dataAprovacao < :onzeMesesAtras)" +
            "           )" +
            "       )" +
            "   )"
    )
    List<Fornecedor> listFornecedoresAVencer(@Param("umAnoAtras") final LocalDateTime umAnoAtras, @Param("onzeMesesAtras") final LocalDateTime onzeMesesAtras);

    /**
     * @param id Long
     */
    @Modifying
    @Transactional
    @Query("UPDATE Fornecedor" +
            "    SET emailVencimentoEnviado = TRUE" +
            "    WHERE id = :id")
    void updateSentEmailVencido(@Param("id") final long id);

    @Query("FROM Fornecedor fornecedor WHERE fornecedor.usuario.id = :id")
    Fornecedor findByUsuarioId(@Param("id") Long id);
}
