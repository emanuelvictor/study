package others.functional.address;

import java.util.List;

public class Pais {

    public Pais() {
    }

    public Pais(String nome) {
        this.nome = nome;
    }

    private List<Estado> estados;

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }
}