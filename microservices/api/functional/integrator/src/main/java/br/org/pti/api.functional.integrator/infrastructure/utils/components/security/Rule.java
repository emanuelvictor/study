package br.org.pti.integrator.infrastructure.utils.components.security;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 01/02/2018
 */
public final class Rule {
    
    public final static String RH_READ = "hasAuthority('" + Authority.RECURSOS_HUMANOS_R + "')";
    public final static String RH_WRITE = "hasAuthority('" + Authority.RECURSOS_HUMANOS_W + "')";
    
    public final static String CONTABILIDADE_READ = "hasAuthority('" + Authority.CONTABILIDADE_R + "')";
    public final static String CONTABILIDADE_WRITE = "hasAuthority('" + Authority.CONTABILIDADE_W + "')";
    
    public final static String COMPRAS_READ = "hasAuthority('" + Authority.COMPRAS_R + "')";
    public final static String COMPRAS_WRITE = "hasAuthority('" + Authority.COMPRAS_W + "')";
    
    public final static String PONTO_ELETRONICO_READ = "hasAuthority('" + Authority.PONTO_ELETRONICO_R + "')";
    public final static String PONTO_ELETRONICO_WRITE = "hasAuthority('" + Authority.PONTO_ELETRONICO_W + "')";
    
    public final static String ATIVO_FIXO_READ = "hasAuthority('" + Authority.ATIVO_FIXO_R + "')";
    public final static String ATIVO_FIXO_WRITE = "hasAuthority('" + Authority.ATIVO_FIXO_W + "')";

    public final static String ORCAMENTO_READ = "hasAuthority('" + Authority.ORCAMENTO_R + "')";
    public final static String ORCAMENTO_WRITE = "hasAuthority('" + Authority.ORCAMENTO_W + "')";
    
    public final static String FINANCEIRO_READ = "hasAuthority('" + Authority.FINANCEIRO_R + "')";
    public final static String FINANCEIRO_WRITE = "hasAuthority('" + Authority.FINANCEIRO_W + "')";
}
