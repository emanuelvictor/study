package br.org.pti.api.functional.integrator.infrastructure.utils.components.datetime;

import lombok.Getter;

import java.time.LocalDate;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 23/08/2017
 */
public final class DatePeriod {

    @Getter
    private final LocalDate inicio;
    @Getter
    private final LocalDate fim;

    /**
     * 
     */
    private DatePeriod() {
        this.inicio = null;
        this.fim = null;
    }
    
    /**
     * 
     * @param start
     * @param end 
     */
    private DatePeriod(LocalDate start, LocalDate end) {
        this.inicio = start;
        this.fim = end;
    }
    
    /**
     * 
     * @param inicio
     * @param fim
     * @return 
     */
    public static DatePeriod of(LocalDate inicio, LocalDate fim) {
        return new DatePeriod(inicio, fim);
    }
}
