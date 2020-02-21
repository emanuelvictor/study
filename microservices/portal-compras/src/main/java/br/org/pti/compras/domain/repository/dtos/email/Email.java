package br.org.pti.compras.domain.repository.dtos.email;

import br.org.pti.compras.domain.entity.cadastros.Categoria;
import br.org.pti.compras.domain.entity.fornecedor.Fornecedor;
import br.org.pti.compras.domain.entity.fornecedor.StatusFornecedor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Email {

    /**
     *
     */
    private String url;

    /**
     *
     */
    private String assunto;

    /**
     *
     */
    private String conteudo;

    /**
     *
     */
    private List<AnexoEmail> anexosEmail;

    /**
     *
     */
    private StatusFornecedor status = StatusFornecedor.APROVADO;

    /**
     *
     */
    private List<Categoria> categorias;

    /**
     *
     */
    private List<Fornecedor> fornecedores;

    /**
     *
     */
    public Email() {
        this.anexosEmail = new ArrayList<>();
    }
}
