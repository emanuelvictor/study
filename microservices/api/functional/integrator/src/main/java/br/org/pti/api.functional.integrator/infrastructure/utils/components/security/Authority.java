package br.org.pti.api.functional.integrator.infrastructure.utils.components.security;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Definicao das authorities disponiveis para autorizacao
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 14/09/2017
 */
public final class Authority {

    public final static String COMPRAS_R = "COMPRAS_R"; 
    public final static String COMPRAS_W = "COMPRAS_W"; 
    public final static String CONTABILIDADE_W = "CONTABILIDADE_W";
    public final static String CONTABILIDADE_R = "CONTABILIDADE_R";
    public final static String RECURSOS_HUMANOS_R = "RECURSOS_HUMANOS_R";
    public final static String RECURSOS_HUMANOS_W = "RECURSOS_HUMANOS_W";
    public final static String PONTO_ELETRONICO_W = "PONTO_ELETRONICO_W";
    public final static String PONTO_ELETRONICO_R = "PONTO_ELETRONICO_R";
    public final static String ATIVO_FIXO_W = "ATIVO_FIXO_W";
    public final static String ATIVO_FIXO_R = "ATIVO_FIXO_R";
    public final static String ORCAMENTO_W = "ORCAMENTO_W";
    public final static String ORCAMENTO_R = "ORCAMENTO_R";
    public final static String FINANCEIRO_W = "FINANCEIRO_W";
    public final static String FINANCEIRO_R = "FINANCEIRO_R";

    public final static String MANAGEMENT = "MANAGEMENT"; 
    
    /**
     * 
     * @return 
     */
    public static String[] toArray() {
        
        final List<String> authorities = new ArrayList<>();
        
        final Field fields[] = Authority.class.getDeclaredFields();
        
        for (Field f : fields) {
            if (Modifier.isStatic(f.getModifiers())) {
                authorities.add(f.getName());
            }
        }
        
        return authorities.toArray(new String[]{});
    }
}
