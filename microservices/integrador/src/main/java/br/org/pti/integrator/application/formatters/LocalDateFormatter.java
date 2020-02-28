package br.org.pti.integrator.application.formatters;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 2.0.0, 08/01/2020
 */
@Component
public class LocalDateFormatter implements Formatter<LocalDate> {

    private static final String PATTERN = "yyyy-MM-dd";

    /**
     * {@inheritDoc}
     *
     * @param text
     * @param locale
     * @return
     * @throws ParseException
     */
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return text != null ? LocalDate.parse(text, DateTimeFormatter.ofPattern(PATTERN)) : null;
    }

    /**
     * {@inheritDoc}
     *
     * @param localDate
     * @param locale
     * @return
     */
    @Override
    public String print(LocalDate localDate, Locale locale) {
        return localDate.format(DateTimeFormatter.ofPattern(PATTERN));
    }
}
