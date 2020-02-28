package br.org.pti.integrator.application.formatters;


import br.org.pti.integrator.infrastructure.utils.components.datetime.DatePeriod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 23/08/2017
 */
public class PonMesFormatter implements ParametroFormatter<DatePeriod> {

    /**
     * 
     */
    public PonMesFormatter() { }
    
    /**
     * 
     * @param conteudo
     * @return 
     */
    @Override
    public DatePeriod format(String conteudo) {
        
        // faz split dos valores do parametro
        final String[] arrayConteudo = conteudo.split("/");
        
        // define o formato 
        final DateTimeFormatter formato = 
                DateTimeFormatter.ofPattern("yyyyMMdd");
        
        final LocalDate dataInicial = LocalDate.parse(arrayConteudo[0], formato)
                .minus(1, ChronoUnit.MONTHS);
        final LocalDate dataFinal = LocalDate.parse(arrayConteudo[1], formato)
                .minus(1, ChronoUnit.MONTHS);
        
        return DatePeriod.of(dataInicial, dataFinal);
    }
}
