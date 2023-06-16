package br.com.emanuelvictor.domain.model.mortgage;

import br.com.emanuelvictor.domain.exceptions.BusinessLogicException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonTests {

    private final BigDecimal salary = BigDecimal.valueOf(10000);

    @Test
    void mustCreateAMan() {
        final int age = 19;
        final Boolean male = true;

        final Person person = new Person(male, age, salary);

        assertThat(person.isMale()).isTrue();
        assertThat(person.isFemale()).isFalse();
    }

    @Test
    void mustCreateAWoman() {
        final int age = 19;
        final Boolean male = false;

        final Person person = new Person(male, age, salary);

        assertThat(person.isMale()).isFalse();
        assertThat(person.isFemale()).isTrue();
    }

    @Test
    void mustCreateAYoungMan() {
        final int age = 18;
        final Boolean male = true;

        final Person person = new Person(male, age, salary);

        assertThat(person.isYoungMan()).isTrue();
        assertThat(person.isMediumMan()).isFalse();
        assertThat(person.isOldMan()).isFalse();
        assertThat(person.isYoungWoman()).isFalse();
        assertThat(person.isMediumWoman()).isFalse();
        assertThat(person.isOldWoman()).isFalse();
    }

    @Test
    void mustCreateAMediumMan() {
        final int age = 36;
        final Boolean male = true;

        final Person person = new Person(male, age, salary);

        assertThat(person.isYoungMan()).isFalse();
        assertThat(person.isMediumMan()).isTrue();
        assertThat(person.isOldMan()).isFalse();
        assertThat(person.isYoungWoman()).isFalse();
        assertThat(person.isMediumWoman()).isFalse();
        assertThat(person.isOldWoman()).isFalse();
    }

    @Test
    void mustCreateAOldMan() {
        final int age = 46;
        final Boolean male = true;

        final Person person = new Person(male, age, salary);

        assertThat(person.isYoungMan()).isFalse();
        assertThat(person.isMediumMan()).isFalse();
        assertThat(person.isOldMan()).isTrue();
        assertThat(person.isYoungWoman()).isFalse();
        assertThat(person.isMediumWoman()).isFalse();
        assertThat(person.isOldWoman()).isFalse();
    }

    @Test
    void mustCreateAYoungWoman() {
        final int age = 18;
        final Boolean male = false;

        final Person person = new Person(male, age, salary);

        assertThat(person.isYoungMan()).isFalse();
        assertThat(person.isMediumMan()).isFalse();
        assertThat(person.isOldMan()).isFalse();
        assertThat(person.isYoungWoman()).isTrue();
        assertThat(person.isMediumWoman()).isFalse();
        assertThat(person.isOldWoman()).isFalse();
    }


    @Test
    void mustCreateAMediumWoman() {
        final int age = 31;
        final Boolean male = false;

        final Person person = new Person(male, age, salary);

        assertThat(person.isYoungMan()).isFalse();
        assertThat(person.isMediumMan()).isFalse();
        assertThat(person.isOldMan()).isFalse();
        assertThat(person.isYoungWoman()).isFalse();
        assertThat(person.isMediumWoman()).isTrue();
        assertThat(person.isOldWoman()).isFalse();
    }

    @Test
    void mustCreateAOldWoman() {
        final int age = 41;
        final Boolean male = false;

        final Person person = new Person(male, age, salary);

        assertThat(person.isYoungMan()).isFalse();
        assertThat(person.isMediumMan()).isFalse();
        assertThat(person.isOldMan()).isFalse();
        assertThat(person.isYoungWoman()).isFalse();
        assertThat(person.isMediumWoman()).isFalse();
        assertThat(person.isOldWoman()).isTrue();
    }

    @Test
    void cannotAllowMortgage() {
        final int age = 55;
        final Boolean male = false;
        final Person person = new Person(male, age, salary);

        final Exception businessLogicException = assertThrows(BusinessLogicException.class, person::calculateAllowedMortgage);

        Assertions.assertThat(businessLogicException).isInstanceOf(BusinessLogicException.class).hasMessageContaining("Mortgage is not allowed");
    }

    @ParameterizedTest
    @MethodSource("getValuesAndMessageErrorsFromPeopleEntity")
    void cannotCreateInstanceOfPersonWithInvalidInputs(final Boolean gender, final Integer age, final BigDecimal salary, final String message) {

        final Exception businessLogicException = assertThrows(BusinessLogicException.class, () -> new Person(gender, age, salary));

        Assertions.assertThat(businessLogicException).isInstanceOf(BusinessLogicException.class).hasMessageContaining(message);

    }

    @ParameterizedTest
    @MethodSource("getAgesAndMortgageFactors")
    void mustCalculateMortgageFactor(final Boolean male, final Integer age, final BigDecimal mortgageFactor) {
        final Person person = new Person(male, age, BigDecimal.valueOf(10000));

        assertThat(person.getMortgageFactor()).isEqualTo(mortgageFactor);
    }

    @ParameterizedTest
    @MethodSource("getAgesAndMortgageFactors")
    void mustCalculateMaxMortgage(final Boolean male, final Integer age, final BigDecimal mortgageFactor) {
        final Person person = new Person(male, age, BigDecimal.valueOf(10000));
        final BigDecimal mortgageAllowedExpected = person.getSalary().multiply(mortgageFactor);

        final BigDecimal mortgageAllowed = person.calculateAllowedMortgage();

        assertThat(mortgageAllowed).isEqualTo(mortgageAllowedExpected);
    }

    private static Stream<Arguments> getValuesAndMessageErrorsFromPeopleEntity() {
        return Stream.of(
                Arguments.arguments(true, 17, BigDecimal.valueOf(10000), Person.MIN_AGE_ERROR_MESSAGE),
                Arguments.arguments(true, 56, BigDecimal.valueOf(10000), Person.MAX_AGE_ERROR_MESSAGE),
                Arguments.arguments(true, null, BigDecimal.valueOf(10000), Person.NULL_AGE_ERROR_MESSAGE),
                Arguments.arguments(true, 32, BigDecimal.valueOf(-1), Person.MIN_SALARY_ERROR_MESSAGE),
                Arguments.arguments(true, 32, BigDecimal.valueOf(10001), Person.MAX_SALARY_ERROR_MESSAGE),
                Arguments.arguments(true, 32, null, Person.NULL_SALARY_ERROR_MESSAGE),
                Arguments.arguments(null, 32, BigDecimal.valueOf(10000), Person.NULL_GENDER_ERROR_MESSAGE)
        );
    }

    private static Stream<Arguments> getAgesAndMortgageFactors() {
        return Stream.of(
                Arguments.arguments(true, 18, Person.MORTGAGE_FACTOR_TO_YOUNG_MAN),
                Arguments.arguments(true, 36, Person.MORTGAGE_FACTOR_TO_MEDIUM_MAN),
                Arguments.arguments(true, 46, Person.MORTGAGE_FACTOR_TO_OLD_MAN),
                Arguments.arguments(false, 18, Person.MORTGAGE_FACTOR_TO_YOUNG_WOMAN),
                Arguments.arguments(false, 31, Person.MORTGAGE_FACTOR_TO_MEDIUM_WOMAN),
                Arguments.arguments(false, 41, Person.MORTGAGE_FACTOR_TO_OLD_WOMAN)
        );
    }
}
