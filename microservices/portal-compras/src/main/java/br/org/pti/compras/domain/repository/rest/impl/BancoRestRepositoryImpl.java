package br.org.pti.compras.domain.repository.rest.impl;

import br.org.pti.compras.domain.entity.cadastros.Banco;
import br.org.pti.compras.domain.repository.rest.IBancoRestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BancoRestRepositoryImpl implements IBancoRestRepository {

    private final OAuth2RestOperations restTemplate;

    @Value("${oauth.endpoints.bancos}")
    private String bancosUrl;

    @Override
    @SuppressWarnings("unchecked")
    public List<Banco> listByFilters(final String defaultFilter) {
        return (List<Banco>) Objects.requireNonNull(restTemplate.getForEntity(bancosUrl, ArrayList.class).getBody())
                .parallelStream()
                .map(map -> new Banco(((HashMap<String, String>) map).get("codigo"), ((HashMap<String, String>) map).get("descricao")))
                .filter(banco -> defaultFilter == null || (((Banco) banco).getNome().contains(defaultFilter) || ((Banco) banco).getCodigo().contains(defaultFilter)))
                .collect(Collectors.toList());
    }

}

//    {
//            "nome": "FORNECEDOR TESTE",
//            "nomeFantasia": "CADFOR",
//            "documento": "09355053000108",
//            "inscricaoEstadual": "9644259495",
//            "inscricaoMunicipal": "",
//            "tipo": "J",
//            "tipoPessoa": "OS",
//            "tipoPessoaJuridica": "1",
//            "email": "sysdsevel3@pti.org.br",
//            "municipio": "08304",
//            "pais": "1058",
//            "uf": "PR",
//            "rua": "R ERNESTO GAYER",
//            "numero": "395",
//            "bairro": "SEM BAIRRO",
//            "complemento": "",
//            "natureza": "0103002",
//            "cep": "85862590",
//            "ddd": "045",
//            "telefone": "12344321",
//            "banco": "341",
//            "agencia": "3839",
//            "numeroConta": "64444",
//            "tipoConta": "C",
//            "formaPagamento": "B",
//            "simplesNacional": "S",
//            "digitoConta": "4",
//            "recolhimentoPIS": "S",
//            "recolhimentoCOFINS": "S",
//            "recolhimentoCSLL": "S",
//            "recolhimentoISS": "N",
//            "calculaIRRF": "S",
//            "calculaINSS": "N",
//            "cooperativa": "N"
//            }


//{
//        "nome":"123",
//        "nomeFantasia":"123",
//        "documento":"73469758000147",
//        "inscricaoEstadual":"9644259495",
//        "inscricaoMunicipal":"",
//        "tipo":"J",
//        "tipoPessoa":"OS",
//        "tipoPessoaJuridica":"3",
//        "email":"testes@testes.com",
//        "municipio":"08304",
//        "pais":"1058",
//        "uf":"PR",
//        "rua":"Rua Ernesto Gayer",
//        "numero":"1123",
//        "bairro":"SEM BAIRRO",
//        "complemento":"123",
//        "natureza":"0103002",
//        "cep":"85862-590",
//        "telefone":"(12)  3443-21",
//        "banco":"341",
//        "agencia":"3839",
//        "numeroConta":"64444",
//        "tipoConta":"C",
//        "formaPagamento":"D",
//        "simplesNacional":"S",
//        "digitoConta":"4",
//        "recolhimentoPIS":"S",
//        "recolhimentoCOFINS":"S",
//        "recolhimentoCSLL":"S",
//        "recolhimentoISS":"N",
//        "calculaIRRF":"S",
//        "calculaINSS":"N",
//        "cooperativa":"N"
//        }
