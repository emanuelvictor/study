package br.com.emanuelvictor.domain.entities;

import lombok.Getter;
import lombok.Setter;

public class Resposta {

    @Setter
    @Getter
    private Usuario usuario = new Usuario();

    @Setter
    @Getter
    private Questao questao;

    @Setter
    @Getter
    private int resultadoInformadoPeloUsuario;

    public boolean verificarValorInformadoPeloUsuario(){
        return resultadoInformadoPeloUsuario == questao.somar();
    }

}
