package br.org.pti.api.functional.portalcompras.domain.entity.fornecedor;

import br.org.pti.api.functional.portalcompras.ComprasApplication;
import br.org.pti.api.functional.portalcompras.domain.entity.cadastros.TipoCadastro;
import br.org.pti.api.functional.portalcompras.domain.entity.configuracao.Usuario;
import br.org.pti.api.functional.portalcompras.domain.entity.endereco.Endereco;
import br.org.pti.api.functional.portalcompras.domain.entity.generic.AbstractEntity;
import br.org.pti.api.functional.portalcompras.domain.repository.dtos.FornecedorProtheus;
import br.org.pti.api.functional.portalcompras.domain.entity.generic.EntityIdResolver;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Audited
@Table(schema = ComprasApplication.FORNECEDOR)
@lombok.EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Fornecedor.class,
        resolver = EntityIdResolver.class)
@AuditTable(value = "fornecedor" + ComprasApplication.AUDIT_SUFFIX, schema = ComprasApplication.FORNECEDOR + ComprasApplication.AUDIT_SUFFIX)
public class Fornecedor extends AbstractEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1016827186516472876L;

    /**
     *
     */
    @NotNull
    @OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Usuario usuario;

    /**
     *
     */
    @Column(unique = true)
    private Long codigoProtheus;

    /**
     *
     */
    @EqualsAndHashCode.Exclude
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(schema = ComprasApplication.FORNECEDOR)
    @AuditJoinTable(schema = ComprasApplication.FORNECEDOR + ComprasApplication.AUDIT_SUFFIX)
    private Set<String> telefones;

    /**
     *
     */
    @EqualsAndHashCode.Exclude
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(schema = ComprasApplication.FORNECEDOR)
    @AuditJoinTable(schema = ComprasApplication.FORNECEDOR + ComprasApplication.AUDIT_SUFFIX)
    private Set<String> emails;

    /**
     *
     */
    @Column
    private Boolean emailVencimentoEnviado;

    /**
     *
     */
    @Column(length = 150)
    private String razaoSocial;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    private Boolean souEmpresa = true;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    private Boolean possuiInscricaoEstadual = true;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    private Boolean simplesNacional = true;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    private Boolean cooperativa = true;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private StatusFornecedor status;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TipoPessoaJuridica tipoPessoaJuridica;

    /**
     *
     */
    @Column(length = 18)
    private String inscricaoEstadual;

    /**
     *
     */
    @Column(length = 18)
    private String inscricaoMunicipal;

    /**
     *
     */
    @Column(length = 250)
    private String descricaoProdutosServicos;

    /**
     *
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    /**
     *
     */
    @Column(length = 300)
    private String site;

    /**
     *
     */
    @Column(length = 250)
    private String feedback;

    /**
     *
     */
    @ManyToOne(targetEntity = TipoCadastro.class/*, fetch = FetchType.EAGER, cascade = CascadeType.ALL*/)
    private TipoCadastro tipoCadastro;

    /**
     *
     */
    @OneToOne(targetEntity = DadosRecebimento.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "dados_recebimento_id")
    private DadosRecebimento dadosRecebimento;

    /**
     *
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(schema = ComprasApplication.FORNECEDOR)
    @AuditJoinTable(schema = ComprasApplication.FORNECEDOR + ComprasApplication.AUDIT_SUFFIX)
    private Set<TipoAtividadeEconomica> atividadesEconomicas;

    /**
     *
     */
    @EqualsAndHashCode.Exclude
    @OneToMany(targetEntity = CategoriaFornecedor.class, mappedBy = "fornecedor", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CategoriaFornecedor> categoriasFornecedor;

//    @EqualsAndHashCode.Exclude
//    @OneToMany(targetEntity = Documento.class, mappedBy = "fornecedor", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Documento> documentos;

    /**
     *
     */
//    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            schema = ComprasApplication.FORNECEDOR, name = "atividade_economica_primaria",
            joinColumns = {@JoinColumn(name = "fornecedor_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "atividade_economica_id", nullable = false)},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"fornecedor_id", "atividade_economica_id"})}
    )
//    @NotAudited
    @AuditJoinTable(
            schema = ComprasApplication.FORNECEDOR + ComprasApplication.AUDIT_SUFFIX, name = "atividade_economica_primaria" + ComprasApplication.AUDIT_SUFFIX
//            ,
//            inverseJoinColumns = {@JoinColumn(name = "atividade_economica_id", nullable = false)}
    )
    private Set<AtividadeEconomica> atividadesEconomicasPrimarias;

    /**
     *
     */
    //    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(schema = ComprasApplication.FORNECEDOR, name = "atividade_economica_secundaria",
            joinColumns = {@JoinColumn(name = "fornecedor_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "atividade_economica_id", nullable = false)},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"fornecedor_id", "atividade_economica_id"})}
    )
    //    @NotAudited
    @AuditJoinTable(schema = ComprasApplication.FORNECEDOR + ComprasApplication.AUDIT_SUFFIX, name = "atividade_economica_secundaria" + ComprasApplication.AUDIT_SUFFIX
//            ,
//            inverseJoinColumns = {@JoinColumn(name = "atividade_economica_id", nullable = false)}
    )
    private Set<AtividadeEconomica> atividadesEconomicasSecundarias;

    /**
     *
     */
    private LocalDateTime dataAprovacao;

    /**
     *
     */
    public Fornecedor() {
    }

    /**
     *
     * @param id
     */
    public Fornecedor(final long id) {
        this.setId(id);
    }

    /**
     *
     * @param id
     * @param usuario
     * @param razaoSocial
     * @param souEmpresa
     * @param possuiInscricaoEstadual
     * @param simplesNacional
     * @param cooperativa
     * @param status
     * @param tipoPessoaJuridica
     * @param inscricaoEstadual
     * @param inscricaoMunicipal
     * @param descricaoProdutosServicos
     * @param endereco
     * @param site
     * @param feedback
     * @param tipoCadastro
     * @param dadosRecebimento
     * @param dataAprovacao
     * @param created
     */
    public Fornecedor(final long id, final Usuario usuario,
                      final String razaoSocial, final Boolean souEmpresa,
                      final Boolean possuiInscricaoEstadual, final Boolean simplesNacional,
                      final Boolean cooperativa, final StatusFornecedor status, final TipoPessoaJuridica tipoPessoaJuridica,
                      final String inscricaoEstadual, final String inscricaoMunicipal, final String descricaoProdutosServicos,
                      final Endereco endereco, final String site, final String feedback, final TipoCadastro tipoCadastro,
                      final DadosRecebimento dadosRecebimento, final LocalDateTime dataAprovacao, final LocalDateTime created) {
        this.setId(id);
        this.usuario = usuario;
        this.razaoSocial = razaoSocial;
        this.souEmpresa = souEmpresa;
        this.possuiInscricaoEstadual = possuiInscricaoEstadual;
        this.simplesNacional = simplesNacional;
        this.cooperativa = cooperativa;
        this.status = status;
        this.tipoPessoaJuridica = tipoPessoaJuridica;
        this.inscricaoEstadual = inscricaoEstadual;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.descricaoProdutosServicos = descricaoProdutosServicos;
        this.endereco = endereco;
        this.site = site;
        this.feedback = feedback;
        this.tipoCadastro = tipoCadastro;
        this.dadosRecebimento = dadosRecebimento;
        this.dataAprovacao = dataAprovacao;
        this.created = created;
    }

    @PreUpdate
    private void preUpdate() {
        // Se o fornecedor for atualizado com o perfil de aprovado, ou seja, se ele for aprovado então. ..
        // Deve atualizar a variável {emailVencimentoEnviado} para que o sistema envie email antes de vencher.
        if (this.status.equals(StatusFornecedor.APROVADO)) {
            this.emailVencimentoEnviado = false;
            dataAprovacao = LocalDateTime.now();
        }
    }

    @PrePersist
    private void prePersist() {
        this.setStatus(StatusFornecedor.EM_CRIACAO);
    }

    public LocalDateTime getDataExpiracao() {
        return dataAprovacao != null ? dataAprovacao.plusYears(1) : null;
    }

    public boolean isVencido() {
        return this.getDataExpiracao() != null && this.getDataExpiracao().isBefore(LocalDateTime.now());
    }

    public boolean getVencido() {
        return isVencido();
    }

    public Set<String> getTelefones() {
        if (telefones != null && !telefones.isEmpty())
            return telefones.stream().map(s -> s = s.toUpperCase()).collect(Collectors.toSet());
        else
            return telefones;
    }

    public void setTelefones(final Set<String> telefones) {
        if (!telefones.isEmpty())
            this.telefones = telefones.stream().map(s -> s = s.toUpperCase()).collect(Collectors.toSet());
        else
            this.telefones = telefones;
    }

    public Set<String> getEmails() {
        if (emails != null && !emails.isEmpty()) {
            emails = emails.stream().map(s -> s = s.toUpperCase()).collect(Collectors.toSet());
            return emails;
        } else
            return emails;
    }

    public void setEmails(final Set<String> emails) {
        if (!emails.isEmpty())
            this.emails = emails.stream().map(s -> s = s.toUpperCase()).collect(Collectors.toSet());
        else
            this.emails = emails;
    }

    public String getRazaoSocial() {
        return razaoSocial != null ? razaoSocial.toUpperCase() : null;
    }

    public void setRazaoSocial(final String razaoSocial) {
        this.razaoSocial = razaoSocial != null ? razaoSocial.replaceAll(FornecedorProtheus.REGEX_TO_REMOVE_ESPECIAL_CHARACTERS_TO_PROTHEUS, "").toUpperCase().trim() : null;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual != null ? inscricaoEstadual.toUpperCase() : null;
    }

    public void setInscricaoEstadual(final String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual != null ? inscricaoEstadual.toUpperCase() : null;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal != null ? inscricaoMunicipal.toUpperCase() : null;
    }

    public void setInscricaoMunicipal(final String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal != null ? inscricaoMunicipal.toUpperCase() : null;
    }

    public String getDescricaoProdutosServicos() {
        return descricaoProdutosServicos != null ? descricaoProdutosServicos.toUpperCase() : null;
    }

    public void setDescricaoProdutosServicos(final String descricaoProdutosServicos) {
        this.descricaoProdutosServicos = descricaoProdutosServicos != null ? descricaoProdutosServicos.toUpperCase() : null;
    }

    public String getSite() {
        return site != null ? site.toUpperCase() : null;
    }

    public void setSite(final String site) {
        this.site = site != null ? site.toUpperCase() : null;
    }

    public Usuario getUsuario() {
        if (usuario != null) {
            if (usuario.getNome() != null)
                usuario.setNome(usuario.getNome().toUpperCase());
            if (usuario.getEmail() != null)
                usuario.setEmail(usuario.getEmail().toUpperCase());
            if (usuario.getUsername() != null)
                usuario.setUsername(usuario.getUsername().toUpperCase());
            return usuario;
        } else return usuario;
    }

    public void setUsuario(final Usuario usuario) {
        if (usuario != null) {
            if (usuario.getNome() != null)
                usuario.setNome(usuario.getNome().toUpperCase());
            if (usuario.getEmail() != null)
                usuario.setEmail(usuario.getEmail().toUpperCase());
            if (usuario.getUsername() != null)
                usuario.setUsername(usuario.getUsername().toUpperCase());
            this.usuario = usuario;
        } else this.usuario = null;
    }
}
