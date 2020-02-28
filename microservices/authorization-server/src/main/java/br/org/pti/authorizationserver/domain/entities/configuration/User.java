package br.org.pti.authorizationserver.domain.entities.configuration;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@Entity
@Audited
@EqualsAndHashCode(callSuper = true)
public class User extends Person implements UserDetails {

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
    @JsonAlias("isRoot")
    private boolean root;

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
    private AccessGroup grupoAcesso;

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
    public User() {
    }

    /**
     * @param email String
     */
    public User(final String email) {
        this.email = email;
    }

    /**
     * @param id                   long
     * @param nome                 String
     * @param documento            String
     * @param email                String
     * @param ativo                boolean
     * @param root                 boolean
     * @param senha                String
     * @param username             String
     * @param tentativasLogin      Integer
     * @param ultimaTentativaLogin LocalDateTime
     * @param grupoAcesso          GrupoAcesso
     * @param ultimoAcesso         LocalDateTime
     * @param codigo               String
     * @param interno              boolean
     */
    public User(final Long id, final String nome, final String documento,
                final String email, final Boolean ativo, final boolean root,
                final String senha, final String username, final Integer tentativasLogin,
                final LocalDateTime ultimaTentativaLogin, final AccessGroup grupoAcesso,
                final LocalDateTime ultimoAcesso, final String codigo, final boolean interno) {

        this.setId(id);
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.username = username;
        this.ativo = ativo;
        this.root = root;
        this.senha = senha;
        this.tentativasLogin = tentativasLogin;
        this.ultimaTentativaLogin = ultimaTentativaLogin;
        this.grupoAcesso = grupoAcesso;
        this.ultimoAcesso = ultimoAcesso;
        this.codigo = codigo;
        this.interno = interno;
    }

    /**
     * Percorre recursivamente as permissões e retorna elas lineares.
     *
     * @param permissoes Set<Permissao>
     * @return Set<Permissao>
     */
    private static Set<Permission> populePermissoes(final Set<Permission> permissoes) {

        final Set<Permission> permissoesLocais = new HashSet<>();

        permissoes.forEach(permissao -> {
            permissoesLocais.add(permissao);
            if (!permissao.getPermissoesInferiores().isEmpty())
                permissoesLocais.addAll(populePermissoes(permissao.getPermissoesInferiores()));
        });

        return permissoesLocais;
    }

    /**
     * @param email String
     * @return String
     */
    public static String extractUsernameFromEmail(final String email) {
        return email != null ? email.replaceAll("@pti.org.br", "") : null;
    }

    /**
     * Registra a quantidade de tentativas de login do usuário para um possível bloqueio de acesso
     */
    public void registerFailedLogin() {
        this.tentativasLogin = this.tentativasLogin != null ? this.tentativasLogin + 1 : 1;
        this.ultimaTentativaLogin = LocalDateTime.now();
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

        if (root) {
            return new HashSet<>(Collections.singletonList((GrantedAuthority) () -> "root")); // TODO separar em uma variável global
        }

        final Set<Permission> permissoes = new HashSet<>();

        if (this.grupoAcesso != null && this.grupoAcesso.getGruposAcessoPermissoes() != null)
            for (AccessGroupPermission grupoAcessoPermissao : this.grupoAcesso.getGruposAcessoPermissoes()) {
                permissoes.add(grupoAcessoPermissao.getPermissao());

                if (!grupoAcessoPermissao.getPermissao().getPermissoesInferiores().isEmpty())
                    permissoes.addAll(populePermissoes(grupoAcessoPermissao.getPermissao().getPermissoesInferiores()));
            }
        return permissoes.isEmpty() ? null : new HashSet<>(permissoes);
    }

    /**
     * @return String
     */
    @Override
    @JsonIgnore
    @Transient
    public String getPassword() {
        return this.senha;
    }

    /**
     * @return String
     */
    @Override
    public String getUsername() {
        return extractUsernameFromEmail(this.email);
    }

    /**
     * @param username String
     */
    public void setUsername(final String username) {
        this.username = extractUsernameFromEmail(username);
    }

    /**
     * @return boolean
     */
    @Override
    @JsonIgnore
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return boolean
     */
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

    /**
     * @return boolean
     */
    @Override
    @JsonIgnore
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return boolean
     */
    @Override
    @JsonIgnore
    @Transient
    public boolean isEnabled() {
        return this.ativo;
    }

    /**
     * @return String
     */
    public String getEmail() {
        return email != null ? email.toLowerCase() : null;
    }

    /**
     * @param email String
     */
    public void setEmail(final String email) {
        this.email = email != null ? email.toLowerCase() : null;
    }

}
