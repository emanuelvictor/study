
package others.functional.address;

import java.util.List;

public class Estado {

    private String nome;

    private String uf;

    private Pais pais;

    private List<Cidade> cidades;

    public Estado() {
    }

    public Estado(String nome, String uf, Pais pais) {
        this.nome = nome;
        this.uf = uf;
        this.pais = pais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
}
