//package patterns.builder.helpers;
//
//import java.time.LocalDateTime;
//
//import static br.com.firstdatacorp.portalops.util.log.LogDatetimeHelper.getFormattedDateTime;
//
///**
// *
// */
//public class LogIdentifierHelper {
//
//    private static final String IDENTIFIER_PATTERN = "yyyyMMddHHmmss";
//
//    /**
//     * @return String
//     */
//    public static String getIdentifier() {
//        return getIdentifier(LocalDateTime.now());
//    }
//
//    /**
//     * @param localDateTime LocalDateTime
//     * @return String
//     */
//    static String getIdentifier(final LocalDateTime localDateTime) {
//        return getFormattedDateTime(localDateTime, IDENTIFIER_PATTERN);
//    }
//
//}
