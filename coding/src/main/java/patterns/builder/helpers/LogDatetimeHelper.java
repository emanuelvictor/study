package patterns.builder.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 */
public class LogDatetimeHelper {

    private static final String DATETIME_PATTERN = "yyyy/MM/dd HH:mm:ss";

    /**
     * TODO need test
     * @return String
     */
    static String getFormattedDateTime() {
        return getFormattedDateTime(DATETIME_PATTERN);
    }

    /**
     * TODO need test
     * @param localDateTime LocalDateTime
     * @return String
     */
    static String getFormattedDateTime(final LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(DATETIME_PATTERN));
    }

    /**
     * TODO need test
     * @param pattern String
     * @return String
     */
    static String getFormattedDateTime(final String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * TODO need test
     * @param localDateTime LocalDateTime
     * @return String
     */
    static String getFormattedDateTime(final LocalDateTime localDateTime, final String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
