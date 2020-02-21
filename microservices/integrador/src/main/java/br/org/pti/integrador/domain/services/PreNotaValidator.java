package br.org.pti.integrador.domain.services;

import br.org.pti.integrador.domain.entities.compras.Cliente;
import br.org.pti.integrador.domain.entities.compras.Produto;
import br.org.pti.integrador.domain.entities.contabilidade.ItemPreNota;
import br.org.pti.integrador.domain.entities.contabilidade.PreNota;
import br.org.pti.integrador.domain.repositories.ClienteRepository;
import br.org.pti.integrador.domain.repositories.PreNotaRepository;
import br.org.pti.integrador.domain.repositories.ProdutoRepository;
import br.org.pti.integrador.infrastructure.utils.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 01/11/2017
 */
@Component
public class PreNotaValidator {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private PreNotaRepository notaFiscalRepository;

    /**
     * Metodo responsavel por validas os dados da nota fiscal
     *
     * @param preNota pre nota a ser validada
     * @return NotaFiscal
     */
    public PreNota validaNota(PreNota preNota) {

        final long existeNotaFiscal = this.notaFiscalRepository.existeNotaFiscal(
                preNota.getNumero(), preNota.getDocumento());

        if (existeNotaFiscal != 0) {
            throw new ValidationException("pre-nota.nota-existente",
                    preNota.getNumero(), preNota.getDocumento());
        }
        
        preNota.setCliente(this.validaCliente(preNota.getCliente()));
        preNota.setItensPreNota(this.validaItens(preNota.getItensPreNota()));
        
        return preNota;
    }

    /**
     * Metodo responsavel por validas o cliente da nota fiscal
     *
     * @param cliente cliente a ser validado
     * @return Cliente
     */
    private Cliente validaCliente(Cliente cliente) {
        return this.clienteRepository
                .findOptionalByCodigoAndLoja(cliente.getCodigo(), cliente.getLoja())
                .orElseThrow(() -> new ValidationException(
                        "item-pre-nota.cliente-invalido", cliente.getCodigo()));
    }

    /**
     * Metodo para avaliar os dados informados nos itens da nota fiscal
     * 
     * @param itens lista de itens a serem validados
     * @return itens lista de itens validados 
     */
    public List<ItemPreNota> validaItens(List<ItemPreNota> itens){
        
        final List<ItemPreNota> itensValidos = new ArrayList<>();
        
        itens.stream().forEach(item -> {
            
            final String codigoProduto = item.getProduto().getCodigo();
            
            final Produto produto = this.produtoRepository.findOptionalByCodigo(codigoProduto)
                    .orElseThrow(() -> new ValidationException("item-pre-nota.producao-invalido"));
            
            item.setProduto(produto);
            itensValidos.add(item);
        });
        
        return itensValidos;
    }
}
