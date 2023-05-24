package domain.entities;

import domain.exceptions.BusinessLogicException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static domain.entities.Person.*;
import static domain.entities.Person.MORTGAGE_FACTOR_TO_YOUNG_MAN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonTests {

    @ParameterizedTest
    @MethodSource("getValuesAndMessageErrorsFromPeopleEntity")
    void cannotCreateInstanceOfPersonWithInvalidInputs(final Boolean gender, final Integer age, final BigDecimal salary, final String message) {

        final Exception businessLogicException = assertThrows(BusinessLogicException.class, () -> new Person(gender, age, salary));

        Assertions.assertThat(businessLogicException).isInstanceOf(BusinessLogicException.class).hasMessageContaining(message);

    }

    private static Stream<Arguments> getValuesAndMessageErrorsFromPeopleEntity() {
        return Stream.of(
                Arguments.arguments(true, 17, BigDecimal.valueOf(10000), MIN_AGE_ERROR_MESSAGE),
                Arguments.arguments(true, 56, BigDecimal.valueOf(10000), MAX_AGE_ERROR_MESSAGE),
                Arguments.arguments(true, null, BigDecimal.valueOf(10000), NULL_AGE_ERROR_MESSAGE),
                Arguments.arguments(true, 32, BigDecimal.valueOf(-1), MIN_SALARY_ERROR_MESSAGE),
                Arguments.arguments(true, 32, BigDecimal.valueOf(10001), MAX_SALARY_ERROR_MESSAGE),
                Arguments.arguments(true, 32, null, NULL_SALARY_ERROR_MESSAGE),
                Arguments.arguments(null, 32, BigDecimal.valueOf(10000), NULL_GENDER_ERROR_MESSAGE)
        );
    }

    @Nested
    class ManTests {
        private final Boolean male = true;
        private final BigDecimal salary = BigDecimal.valueOf(10000);

        @Nested
        class YoungAgeTests {
            private final int age = 18;
            private final BigDecimal mortgageFactor = MORTGAGE_FACTOR_TO_YOUNG_MAN;

            @Test
            void mustCreateAYoungMan() {
                final Person person = new Person(male, age, salary);

                assertThat(person.isYoungMan()).isTrue();
                assertThat(person.isMediumMan()).isFalse();
                assertThat(person.isOldMan()).isFalse();
                assertThat(person.isYoungWoman()).isFalse();
                assertThat(person.isMediumWoman()).isFalse();
                assertThat(person.isOldWoman()).isFalse();
            }

            @Test
            void mustCalculateMortgageFactor() {
                final Person person = new Person(male, age, salary);

                assertThat(person.getMortgageFactor()).isEqualTo(mortgageFactor);
            }

            @Test
            void mustCalculateMaxMortgage() {
                final Person person = new Person(male, age, salary);
                final BigDecimal mortgageAllowedExpected = person.getSalary().multiply(mortgageFactor);

                final BigDecimal mortgageAllowed = person.calculateAllowedMortgage();

                assertThat(mortgageAllowed).isEqualTo(mortgageAllowedExpected);
            }
        }

        @Nested
        class MediumAgeTests {
            private final int age = 36;
            private final BigDecimal mortgageFactor = MORTGAGE_FACTOR_TO_MEDIUM_MAN;

            @Test
            void mustCreateAMediumMan() {

                final Person person = new Person(male, age, salary);

                assertThat(person.isYoungMan()).isFalse();
                assertThat(person.isMediumMan()).isTrue();
                assertThat(person.isOldMan()).isFalse();
                assertThat(person.isYoungWoman()).isFalse();
                assertThat(person.isMediumWoman()).isFalse();
                assertThat(person.isOldWoman()).isFalse();
            }

            @Test
            void mustCalculateMortgageFactor() {
                final Person person = new Person(male, age, salary);

                assertThat(person.getMortgageFactor()).isEqualTo(mortgageFactor);
            }

            @Test
            void mustCalculateMaxMortgage() {
                final Person person = new Person(male, age, salary);
                final BigDecimal mortgageAllowedExpected = person.getSalary().multiply(mortgageFactor);

                final BigDecimal mortgageAllowed = person.calculateAllowedMortgage();

                assertThat(mortgageAllowed).isEqualTo(mortgageAllowedExpected);
            }

        }

        @Nested
        class OldAgeTests {
            private final int age = 46;
            private final BigDecimal mortgageFactor = MORTGAGE_FACTOR_TO_OLD_MAN;
            @Test
            void mustCreateAOldMan() {

                final Person person = new Person(male, age, salary);

                assertThat(person.isYoungMan()).isFalse();
                assertThat(person.isMediumMan()).isFalse();
                assertThat(person.isOldMan()).isTrue();
                assertThat(person.isYoungWoman()).isFalse();
                assertThat(person.isMediumWoman()).isFalse();
                assertThat(person.isOldWoman()).isFalse();
            }

            @Test
            void mustCalculateMortgageFactor() {
                final Person person = new Person(male, age, salary);

                assertThat(person.getMortgageFactor()).isEqualTo(mortgageFactor);
            }

            @Test
            void mustCalculateMaxMortgage() {
                final Person person = new Person(male, age, salary);
                final BigDecimal mortgageAllowedExpected = person.getSalary().multiply(mortgageFactor);

                final BigDecimal mortgageAllowed = person.calculateAllowedMortgage();

                assertThat(mortgageAllowed).isEqualTo(mortgageAllowedExpected);
            }
        }
    }

    @Nested
    class WomanTests {
        private final Boolean male = false;
        private final BigDecimal salary = BigDecimal.valueOf(10000);

        @Nested
        class YoungAgeTests {
            private final int age = 18;
            private final BigDecimal mortgageFactor = MORTGAGE_FACTOR_TO_YOUNG_WOMAN;
            @Test
            void mustCreateAYoungMan() {

                final Person person = new Person(male, age, salary);

                assertThat(person.isYoungMan()).isFalse();
                assertThat(person.isMediumMan()).isFalse();
                assertThat(person.isOldMan()).isFalse();
                assertThat(person.isYoungWoman()).isTrue();
                assertThat(person.isMediumWoman()).isFalse();
                assertThat(person.isOldWoman()).isFalse();
            }

            @Test
            void mustCalculateMortgageFactor() {
                final Person person = new Person(male, age, salary);

                assertThat(person.getMortgageFactor()).isEqualTo(mortgageFactor);
            }

            @Test
            void mustCalculateMaxMortgage() {
                final Person person = new Person(male, age, salary);
                final BigDecimal mortgageAllowedExpected = person.getSalary().multiply(mortgageFactor);

                final BigDecimal mortgageAllowed = person.calculateAllowedMortgage();

                assertThat(mortgageAllowed).isEqualTo(mortgageAllowedExpected);
            }
        }

        @Nested
        class MediumAgeTests {
            private final int age = 31;
            private final BigDecimal mortgageFactor = MORTGAGE_FACTOR_TO_MEDIUM_WOMAN;
            @Test
            void mustCreateAMediumMan() {

                final Person person = new Person(male, age, salary);

                assertThat(person.isYoungMan()).isFalse();
                assertThat(person.isMediumMan()).isFalse();
                assertThat(person.isOldMan()).isFalse();
                assertThat(person.isYoungWoman()).isFalse();
                assertThat(person.isMediumWoman()).isTrue();
                assertThat(person.isOldWoman()).isFalse();
            }

            @Test
            void mustCalculateMortgageFactor() {
                final Person person = new Person(male, age, salary);

                assertThat(person.getMortgageFactor()).isEqualTo(mortgageFactor);
            }

            @Test
            void mustCalculateMaxMortgage() {
                final Person person = new Person(male, age, salary);
                final BigDecimal mortgageAllowedExpected = person.getSalary().multiply(mortgageFactor);

                final BigDecimal mortgageAllowed = person.calculateAllowedMortgage();

                assertThat(mortgageAllowed).isEqualTo(mortgageAllowedExpected);
            }
        }

        @Nested
        class OldAgeTests {
            private final int age = 41;
            private final BigDecimal mortgageFactor = MORTGAGE_FACTOR_TO_OLD_WOMAN;
            @Test
            void mustCreateAOldMan() {

                final Person person = new Person(male, age, salary);

                assertThat(person.isYoungMan()).isFalse();
                assertThat(person.isMediumMan()).isFalse();
                assertThat(person.isOldMan()).isFalse();
                assertThat(person.isYoungWoman()).isFalse();
                assertThat(person.isMediumWoman()).isFalse();
                assertThat(person.isOldWoman()).isTrue();
            }

            @Test
            void mustCalculateMortgageFactor() {
                final Person person = new Person(male, age, salary);

                assertThat(person.getMortgageFactor()).isEqualTo(mortgageFactor);
            }

            @Test
            void mustCalculateMaxMortgage() {
                final Person person = new Person(male, age, salary);
                final BigDecimal mortgageAllowedExpected = person.getSalary().multiply(mortgageFactor);

                final BigDecimal mortgageAllowed = person.calculateAllowedMortgage();

                assertThat(mortgageAllowed).isEqualTo(mortgageAllowedExpected);
            }

            @Test
            void cannotAllowMortgage() {
                final int age = 55;
                final Person person = new Person(male, age, salary);

                final Exception businessLogicException = assertThrows(BusinessLogicException.class, person::calculateAllowedMortgage);

                Assertions.assertThat(businessLogicException).isInstanceOf(BusinessLogicException.class).hasMessageContaining("Mortgage is not allowed");
            }
        }
    }
}
