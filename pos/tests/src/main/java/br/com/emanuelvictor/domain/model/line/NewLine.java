package br.com.emanuelvictor.domain.model.line;

import java.util.Objects;

public class NewLine {

    static final String NULL_STRING_MESSAGE_ERROR = "Null string!";
    static final String EMPTY_STRING_MESSAGE_ERROR = "Empty string!";

    /**
     * @param argStr String
     * @return String
     */
    public String collapseNewlines(final String argStr) {
        validateString(argStr);
        char last = argStr.charAt(0);
        final StringBuilder sBuf = new StringBuilder();

        for (int i = 0; i < argStr.length(); i++) {
            final char ch = argStr.charAt(i);
            if (ch != '\n' || last != '\n') {
                sBuf.append(ch);
                last = ch;
            }
        }

        return sBuf.toString();
    }

    void validateString(final String string) {
        if (Objects.isNull(string))
            throw new RuntimeException(NULL_STRING_MESSAGE_ERROR);
        if(string.isEmpty())
            throw new RuntimeException(EMPTY_STRING_MESSAGE_ERROR);
    }
}

