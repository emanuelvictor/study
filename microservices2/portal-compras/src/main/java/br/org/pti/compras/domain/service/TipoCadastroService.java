package br.org.pti.compras.domain.service;

import br.org.pti.compras.domain.entity.cadastros.TipoCadastro;
import br.org.pti.compras.domain.repository.ITipoCadastroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class TipoCadastroService {

    private final ITipoCadastroRepository tipoCadastroRepository;

    public TipoCadastro save(final TipoCadastro tipoCadastro) {
        Assert.notEmpty(tipoCadastro.getDocumentos(), "O Tipo de Cadastro deve estar vinculado ao menos á um Tipo de Documento");
        return tipoCadastroRepository.save(tipoCadastro);
    }

    public TipoCadastro save(final long id, final TipoCadastro tipoCadastro) {
        Assert.notEmpty(tipoCadastro.getDocumentos(), "O Tipo de Cadastro deve estar vinculado ao menos á um Tipo de Documento");
        tipoCadastro.setId(id);
        return this.tipoCadastroRepository.save(tipoCadastro);
    }

}
