package br.org.pti.api.functional.inventario.domain.entity.configuracao;

import br.org.pti.api.functional.inventario.domain.entity.patrimonio.inventario.Executor;
import br.org.pti.api.functional.inventario.domain.entity.pessoal.CentroCusto;
import com.fasterxml.jackson.annotation.JsonAlias;
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
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static br.org.pti.api.functional.inventario.InventarioApplication.AUDIT_SUFFIX;
import static br.org.pti.api.functional.inventario.InventarioApplication.CONFIGURACAO;
import static br.org.pti.api.functional.inventario.application.resource.Roles.*;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@Entity
@Audited
@Table(schema = CONFIGURACAO)
@EqualsAndHashCode(callSuper = true)
@AuditTable(value = "usuario" + AUDIT_SUFFIX, schema = CONFIGURACAO + AUDIT_SUFFIX)
public class Usuario extends Pessoa implements UserDetails {

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
//    @Transient
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(targetEntity = CentroCusto.class, mappedBy = "gestor", fetch = FetchType.EAGER/*, orphanRemoval = true*/)
    private Set<CentroCusto> centrosCusto;

    /**
     *
     */
//    @Transient
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(targetEntity = Executor.class, mappedBy = "usuario", fetch = FetchType.EAGER/*, orphanRemoval = true*/)
    private Set<Executor> executores;

    /**
     *
     */
    public Usuario() {
    }

    /**
     * @param email String
     */
    public Usuario(final String email) {
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
    public Usuario(final Long id, final String nome, final String documento,
                   final String email, final Boolean ativo, final boolean root,
                   final String senha, final String username, final Integer tentativasLogin,
                   final LocalDateTime ultimaTentativaLogin, final GrupoAcesso grupoAcesso,
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
    private static Set<Permissao> populePermissoes(final Set<Permissao> permissoes) {

        final Set<Permissao> permissoesLocais = new HashSet<>();

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
            return new HashSet<>(Collections.singletonList((GrantedAuthority) () -> ROOT));
        }

        final Set<Permissao> permissoes = new HashSet<>();

        if (this.grupoAcesso != null && this.grupoAcesso.getGruposAcessoPermissoes() != null)
            for (GrupoAcessoPermissao grupoAcessoPermissao : this.grupoAcesso.getGruposAcessoPermissoes()) {
                permissoes.add(grupoAcessoPermissao.getPermissao());

                if (!grupoAcessoPermissao.getPermissao().getPermissoesInferiores().isEmpty())
                    permissoes.addAll(populePermissoes(grupoAcessoPermissao.getPermissao().getPermissoesInferiores()));
            }

        if (this.getIsGestor()) {
            final Set<Permissao> gestorPermissoes = new HashSet<>(Collections.singletonList(new Permissao(INVENTARIO_PUT_ROLE)));
            gestorPermissoes.add(new Permissao(INVENTARIO_GET_ROLE));
            gestorPermissoes.add(new Permissao(INVENTARIO_EXECUTE_ROLE));
            gestorPermissoes.add(new Permissao(CENTRO_CUSTO_GET_ROLE));
            permissoes.addAll(gestorPermissoes);
        }

        if (this.getIsExecutor()) {
            final Set<Permissao> executorPermissoes = new HashSet<>(Collections.singletonList(new Permissao(INVENTARIO_GET_ROLE)));
            executorPermissoes.add(new Permissao(INVENTARIO_EXECUTE_ROLE));
            executorPermissoes.add(new Permissao(CENTRO_CUSTO_GET_ROLE));
            permissoes.addAll(executorPermissoes);
        }

        if (getIsPatrimonio()) {
            final Set<Permissao> patrimonioPermissoes = new HashSet<>(Collections.singletonList(new Permissao(INVENTARIO_EXECUTE_ROLE)));
            patrimonioPermissoes.add(new Permissao(CENTRO_CUSTO_GET_ROLE));
            permissoes.addAll(patrimonioPermissoes);
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

    /**
     * @return boolean
     */
    public boolean getIsGestor() {
        return this.centrosCusto != null && !this.centrosCusto.isEmpty() && this.centrosCusto.stream().anyMatch(centroCusto -> centroCusto.getGestor().getEmail().equals(this.email));
    }

    /**
     * @return boolean
     */
    public boolean getIsExecutor() {
        return this.executores != null && !this.executores.isEmpty();
    }

    /**
     * Se o usuário tem autorização para interir um inventário, então é um usuário do patrimônio.
     *
     * @return boolean
     */
    public boolean getIsPatrimonio() {
        return this.grupoAcesso != null && this.grupoAcesso.getGruposAcessoPermissoes() != null && this.grupoAcesso.getGruposAcessoPermissoes().stream().anyMatch(grantedAuthority -> grantedAuthority.getPermissao().getIdentificador().equals(INVENTARIO_POST_ROLE) || grantedAuthority.getPermissao().getIdentificador().equals(INVENTARIO_MAPPING));
    }
}
