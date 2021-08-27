package modelo;

import javax.persistence.*;

@Entity
public class Produto {
    @Id @GeneratedValue
    private Long id;
    private String nome;
    private Double preco;

    @PrePersist
    public void prePersist(){
        System.out.println("Persistindo....");
    }
    @PostPersist
    public void postPersist(){
        System.out.println("Já persistiu....");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
