package br.org.pti.integrator.infrastructure.utils.protheus;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.4.0, 21/08/2018
 */
public class ProtheusUtils {

    /**
     * Compatibiliza a matricula de um funcionario do protheus para que caso 
     * informem fora do padrao do sistema a busca ainda possa ser realizada
     * 
     * Exemplos:
     * 
     * - Informado 570 muda para 000570
     * - Informado 0570 muda para 000570
     * 
     * @deprecated usar o metodo {@link #preencheComZeros(String, int) }
     * 
     * @param matricula a matricula a ser compatibilizada
     * @return a matricula compatibilizada
     */
    @Deprecated
    public static String arrumaMatricula(String matricula) {
        return ProtheusUtils.preencheComZeros(matricula, 6);
    }
    
    /**
     * Preenche um valor passado como parametro com zeros a esquerda de acordo 
     * com o tamanho total do campo
     * 
     * @param valor o valor a ser preenchido com zeros
     * @param tamanhoCampo o tamanho total do campo
     * @return o valor preenchido ate o talo com zeros
     */
    public static String preencheComZeros(String valor, int tamanhoCampo) {
        return valor.length() < tamanhoCampo 
                ? StringUtils.leftPad(valor, tamanhoCampo, "0") : valor;
    }
}
