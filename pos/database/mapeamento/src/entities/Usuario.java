package entities;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    private Calendar dataDeCadastro;

    @Lob
    private byte[] foto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(Calendar dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
