package br.org.pti.compras.domain.repository;

import br.org.pti.compras.domain.entity.fornecedor.DadosRecebimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IDadosRecebimentoRepository extends JpaRepository<DadosRecebimento, Long> {

//    DadosRecebimento findDadosRecebimentoByContaBancariaId(final long contaBancariaId);

    @Query("FROM DadosRecebimento dadosRecebimento WHERE" +
            "   (" +
            "       dadosRecebimento.contaBancaria.agencia = :agencia AND " +
            "       dadosRecebimento.contaBancaria.banco.id = :bancoId AND " +
            "       dadosRecebimento.contaBancaria.numero = :numero AND " +
            "       dadosRecebimento.contaBancaria.digito = :digito " +
            "   )"
    )
    DadosRecebimento findDadosRecebimentoByAgenciaAndBancoIdAndNumeroAndDigito(
            @Param("agencia") final String agencia,
            @Param("bancoId") final long bancoId,
            @Param("numero") final String numero,
            @Param("digito") final String digito);

}
