package patterns.builder.helpers;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;

import static patterns.builder.helpers.LogIdentifierHelper.getIdentifier;

public class LogIdentifiersHelperTests {

    /**
     *
     */
    private static final String REGEX = "^[0-9]*$";

    /**
     *
     */
    @Test
    public void getRisingColumn() {
        final String identifier = getIdentifier();
        Assertions.assertThat(identifier).isNotNull();
        Assertions.assertThat(identifier.matches(REGEX)).isTrue();

        final String wrongIdentifier = identifier + "j";
        Assertions.assertThat(wrongIdentifier.matches(REGEX)).isFalse();
    }

    /**
     *
     */
    @Test
    public void getOverloadRisingColumn() {
        final LocalDateTime localDateTime = LocalDateTime.now();
        final String identifier = getIdentifier(localDateTime);
        Assertions.assertThat(identifier).isNotNull();
        Assertions.assertThat(identifier.matches(REGEX)).isTrue();
    }

}
