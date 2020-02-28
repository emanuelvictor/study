package br.org.pti.compras.domain.entity.configuracao;

import br.org.pti.compras.domain.entity.fornecedor.Fornecedor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static br.org.pti.compras.ComprasApplication.AUDIT_SUFFIX;
import static br.org.pti.compras.ComprasApplication.CONFIGURACAO;
import static br.org.pti.compras.application.resource.Roles.*;

@Data
@Entity
@Audited
@Table(schema = CONFIGURACAO)
@EqualsAndHashCode(callSuper = true)
@AuditTable(value = "usuario" + AUDIT_SUFFIX, schema = CONFIGURACAO + AUDIT_SUFFIX)
public class Usuario extends Pessoa implements UserDetails, Serializable {

    /**
     *
     */
    public static final String DEFAULT_PASSWORD = "pti#1234";
    /**
     * Máximo de tentativas frustradas de login para bloqueio do usuário
     */
    private static final int MAX_ATTEMPTS = 5;
    /**
     *
     */
    @Column(nullable = false, length = 150, unique = true)
    private String email;

    /**
     *
     */
    @Transient
    private String username;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    private Boolean ativo;

    /**
     *
     */
    private boolean administrador;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    private boolean interno;

    /**
     *
     */
    @NotBlank
    @Column(nullable = false, length = 100)
    @JsonProperty(access = Access.WRITE_ONLY)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@!%*#?&+,./])[A-Za-z\\d$@!%*#?&+,./]{8,}$", flags = Flag.UNICODE_CASE, message = "A senha deve conter ao menos 8 caracteres com letras, números e um caractere especial.")
    private String senha;

    /**
     *
     */
    @Column(columnDefinition = "int2")
    private Integer tentativasLogin;

    /**
     *
     */
    @Column
    private LocalDateTime ultimaTentativaLogin;

    /**
     *
     */
    @ManyToOne
    private GrupoAcesso grupoAcesso;

    /**
     *
     */
    @Column
    private LocalDateTime ultimoAcesso;

    /**
     *
     */
    @Column
    private String codigo;

    /**
     *
     */
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "usuario")
    private Fornecedor fornecedor;

    /**
     *
     */
    public Usuario() {
    }

    /**
     * @param id                   long
     * @param nome                 String
     * @param documento            String
     * @param email                String
     * @param ativo                boolean
     * @param administrador        boolean
     * @param senha                String
     * @param username             String
     * @param tentativasLogin      Integer
     * @param ultimaTentativaLogin LocalDateTime
     * @param grupoAcesso          GrupoAcesso
     * @param ultimoAcesso         LocalDateTime
     * @param codigo               String
     * @param interno              boolean
     */
    public Usuario(final Long id, final String nome, final String documento,
                   final String email, final Boolean ativo, final boolean administrador,
                   final String senha, final String username, final Integer tentativasLogin,
                   final LocalDateTime ultimaTentativaLogin, final GrupoAcesso grupoAcesso,
                   final LocalDateTime ultimoAcesso, final String codigo, final boolean interno) {

        this.setId(id);
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.username = username;
        this.ativo = ativo;
        this.administrador = administrador;
        this.senha = senha;
        this.tentativasLogin = tentativasLogin;
        this.ultimaTentativaLogin = ultimaTentativaLogin;
        this.grupoAcesso = grupoAcesso;
        this.ultimoAcesso = ultimoAcesso;
        this.codigo = codigo;
        this.interno = interno;
    }


    /**
     * @param id                   long
     * @param nome                 String
     * @param documento            String
     * @param email                String
     * @param ativo                boolean
     * @param administrador        boolean
     * @param senha                String
     * @param username             String
     * @param tentativasLogin      Integer
     * @param ultimaTentativaLogin LocalDateTime
     * @param grupoAcesso          GrupoAcesso
     * @param ultimoAcesso         LocalDateTime
     * @param codigo               String
     * @param interno              boolean
     * @param fornecedor           {@link Fornecedor}
     */
    public Usuario(final Long id, final String nome, final String documento,
                   final String email, final Boolean ativo, final boolean administrador,
                   final String senha, final String username, final Integer tentativasLogin,
                   final LocalDateTime ultimaTentativaLogin, final GrupoAcesso grupoAcesso,
                   final LocalDateTime ultimoAcesso, final String codigo, final boolean interno, final Fornecedor fornecedor) {

        this.setId(id);
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.username = username;
        this.ativo = ativo;
        this.administrador = administrador;
        this.senha = senha;
        this.tentativasLogin = tentativasLogin;
        this.ultimaTentativaLogin = ultimaTentativaLogin;
        this.grupoAcesso = grupoAcesso;
        this.ultimoAcesso = ultimoAcesso;
        this.codigo = codigo;
        this.interno = interno;
        this.fornecedor = fornecedor;
    }

    /**
     * Percorre recursivamente as permissões e retorna elas lineares.
     *
     * @param permissoes Set<Permissao>
     * @return Set<Permissao>
     */
    private static Set<Permissao> populePermissoes(final Set<Permissao> permissoes) {

        final Set<Permissao> permissoesLocais = new HashSet<>();

        permissoes.forEach(permissao -> {
            permissoesLocais.add(permissao);
            if (!permissao.getPermissoesInferiores().isEmpty())
                permissoesLocais.addAll(populePermissoes(permissao.getPermissoesInferiores()));
        });

        return permissoesLocais;
    }

    public static String extractUsernameFromEmail(final String email) {
        return email != null ? email.replaceAll("@pti.org.br", "") : null;
//        return email != null ? email.substring(0, email.indexOf("@")) : null;
    }

    /**
     * Registra a quantidade de tentativas de login do usuário para um possível bloqueio de acesso
     */
    public void registerFailedLogin() {
        this.tentativasLogin = this.tentativasLogin != null ? this.tentativasLogin + 1 : 1;
        this.ultimaTentativaLogin = LocalDateTime.now();
    }

    /**
     * @return {Boolean}
     */
    public Boolean getIsAdministrador() {
        return administrador;
    }

    /**
     * Retorna as authorities do usuário.
     *
     * @return Set<GrantedAuthority>
     */
    @Override
    @Transient
    @JsonIgnore
    public Set<GrantedAuthority> getAuthorities() {

        if (administrador)
            return new HashSet<>(Collections.singletonList((GrantedAuthority) () -> ADMINISTRADOR));

        final Set<Permissao> permissoes = new HashSet<>();

        if (!getIsFornecedor()) {
            if (this.grupoAcesso != null && this.grupoAcesso.getGruposAcessoPermissoes() != null) {
                for (final GrupoAcessoPermissao grupoAcessoPermissao : this.grupoAcesso.getGruposAcessoPermissoes()) {
                    permissoes.add(grupoAcessoPermissao.getPermissao());

                    if (!grupoAcessoPermissao.getPermissao().getPermissoesInferiores().isEmpty())
                        permissoes.addAll(populePermissoes(grupoAcessoPermissao.getPermissao().getPermissoesInferiores()));
                }
            }
        } else
            return new HashSet<>(Arrays.asList((GrantedAuthority) () -> FORNECEDOR_GET_ROLE, (GrantedAuthority) () -> FORNECEDOR_PUT_ROLE));

        return permissoes.isEmpty() ? null : new HashSet<>(permissoes);
    }

    /**
     * @return boolean
     */
    public boolean getIsFornecedor() {
        return this.fornecedor != null;
    }

    @Override
    @JsonIgnore
    @Transient
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return extractUsernameFromEmail(this.email);
    }

    /**
     * @param username {String}
     */
    public void setUsername(final String username) {
        this.username = extractUsernameFromEmail(username);
        if (this.getIsFornecedor()) {
            this.username = this.username != null ? this.username.toUpperCase() : null;
        }
    }

    @Override
    @JsonIgnore
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    @Transient
    public boolean isAccountNonLocked() {
        if (this.ultimaTentativaLogin == null || this.tentativasLogin == null) {
            return true;
        }

        if (this.tentativasLogin >= MAX_ATTEMPTS) {
            return !LocalDateTime.now().isBefore(this.ultimaTentativaLogin.plusHours(3));
        }

        return true;
    }

    @Override
    @JsonIgnore
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    @Transient
    public boolean isEnabled() {
        return this.ativo;
    }

    /**
     * @return {String}
     */
    public String getEmail() {
        if (this.getIsFornecedor()) {
            return this.email != null ? this.email.toUpperCase() : null;
        }
        return email != null ? email.toLowerCase() : null;
    }

    /**
     * @param email {String}
     */
    public void setEmail(final String email) {
        this.email = email != null ? email.toLowerCase() : null;
        if (this.getIsFornecedor()) {
            this.email = this.email != null ? this.email.toUpperCase() : null;
        }
    }

    /**
     * @return {String}
     */
    public String getNome() {
        if (this.getIsFornecedor()) {
            return this.nome != null ? this.nome.toUpperCase() : null;
        }
        return nome != null ? nome.toLowerCase() : null;
    }

    /**
     * @param nome {String}
     */
    public void setNome(final String nome) {
        this.nome = nome;
        if (this.getIsFornecedor()) {
            this.nome = this.nome != null ? this.nome.toUpperCase() : null;
        }
    }

}
