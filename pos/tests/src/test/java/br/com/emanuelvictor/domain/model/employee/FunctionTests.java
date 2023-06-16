package br.com.emanuelvictor.domain.model.employee;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class FunctionTests {

    @Test
    void mustCreateInstanceOfFunction() {
        final String name = "nameOfFunction";
        final BigDecimal averageSalary = BigDecimal.ZERO;
        final BigDecimal percentageOfDiscountFromSalaryLessThanAverageSalary = BigDecimal.ZERO;
        final BigDecimal percentageOfDiscountFromSalaryGreaterThanAverageSalary = BigDecimal.ZERO;

        final Function function = new Function(name, averageSalary, percentageOfDiscountFromSalaryLessThanAverageSalary, percentageOfDiscountFromSalaryGreaterThanAverageSalary);

        Assertions.assertThat(function).isNotNull().satisfies(it -> {
            Assertions.assertThat(it.getName()).isEqualTo(name);
            Assertions.assertThat(it.getAverageSalary()).isEqualTo(averageSalary);
            Assertions.assertThat(it.getPercentageOfDiscountFromSalaryLessThanAverageSalary()).isEqualTo(percentageOfDiscountFromSalaryLessThanAverageSalary);
            Assertions.assertThat(it.getPercentageOfDiscountFromSalaryGreaterThanAverageSalary()).isEqualTo(percentageOfDiscountFromSalaryGreaterThanAverageSalary);
        });
    }

    @ParameterizedTest
    @MethodSource("getFunctionsWithAnySalaries")
    void mustVerifyIfTheSalaryIsGreaterOrEqualsToAverageSalary(final boolean isGreaterOrEquals,
                                                               final BigDecimal salary,
                                                               final Function function) {
        Assertions.assertThat(isGreaterOrEquals).isEqualTo(function.salaryIsGreaterOrEqualsToAverageSalary(salary));
    }

    private static Stream<Arguments> getFunctionsWithAnySalaries() {
        final Function function = new Function("nameOfFunction", BigDecimal.valueOf(2000), BigDecimal.ZERO, BigDecimal.ZERO);
        return Stream.of(
                Arguments.arguments(true, BigDecimal.valueOf(3000), function),
                Arguments.arguments(false, BigDecimal.valueOf(1000), function),
                Arguments.arguments(true, BigDecimal.valueOf(2000), function)
        );
    }

    @ParameterizedTest
    @MethodSource("getPercentagesOfDiscounts")
    void mustObtainPercentageOfDiscount(final BigDecimal percentageOfDiscount,
                                        final BigDecimal salary,
                                        final Function function) {
        Assertions.assertThat(percentageOfDiscount).isEqualTo(function.getPercentageOfDiscount(salary));
    }

    private static Stream<Arguments> getPercentagesOfDiscounts() {
        final BigDecimal averageSalary = BigDecimal.valueOf(2000);
        final BigDecimal percentageOfDiscountToSalaryLessThenAverageSalary = BigDecimal.valueOf(0.20);
        final BigDecimal percentageOfDiscountToSalaryGreaterOrEqualsToAverageSalary = BigDecimal.valueOf(0.30);
        final Function function = new Function("nameOfFunction", averageSalary, percentageOfDiscountToSalaryLessThenAverageSalary, percentageOfDiscountToSalaryGreaterOrEqualsToAverageSalary);
        return Stream.of(
                Arguments.arguments(percentageOfDiscountToSalaryLessThenAverageSalary, BigDecimal.valueOf(1000), function),
                Arguments.arguments(percentageOfDiscountToSalaryGreaterOrEqualsToAverageSalary, BigDecimal.valueOf(3000), function),
                Arguments.arguments(percentageOfDiscountToSalaryGreaterOrEqualsToAverageSalary, averageSalary, function)
        );
    }

    @ParameterizedTest
    @MethodSource("getFunctions")
    void mustCalculateTheGrossSalaryToTheFunction(final String nameOfFunction,
                                                  final BigDecimal grossSalary,
                                                  final BigDecimal salary,
                                                  final Function function) {
        Assertions.assertThat(function.getName()).isEqualTo(nameOfFunction);
        Assertions.assertThat(grossSalary).isEqualTo(function.calculateSalary(salary));
    }

    private static Stream<Arguments> getFunctions() {
        final BigDecimal averageSalaryToDeveloper = BigDecimal.valueOf(3000);
        final BigDecimal percentageOfDiscountToSalaryLessThenAverageSalary = BigDecimal.valueOf(0.10);
        final BigDecimal percentageOfDiscountToSalaryGreaterOrEqualsToAverageSalary = BigDecimal.valueOf(0.20);

        final BigDecimal grossSalaryToSeniorDeveloper = BigDecimal.valueOf(5000);
        final BigDecimal salaryToSeniorDeveloper = grossSalaryToSeniorDeveloper.subtract(grossSalaryToSeniorDeveloper.multiply(percentageOfDiscountToSalaryGreaterOrEqualsToAverageSalary));
        final String seniorDeveloperNameFunction = "SENIOR DEVELOPER";
        final Function seniorDeveloperFunction = new Function(seniorDeveloperNameFunction, averageSalaryToDeveloper, percentageOfDiscountToSalaryLessThenAverageSalary, percentageOfDiscountToSalaryGreaterOrEqualsToAverageSalary);

        final BigDecimal grossSalaryToFullDeveloper = BigDecimal.valueOf(3000);
        final BigDecimal salaryToFullDeveloper = grossSalaryToFullDeveloper.subtract(grossSalaryToFullDeveloper.multiply(percentageOfDiscountToSalaryGreaterOrEqualsToAverageSalary));
        final String fullDeveloperNameFunction = "FULL DEVELOPER";
        final Function fullDeveloperFunction = new Function(fullDeveloperNameFunction, averageSalaryToDeveloper, percentageOfDiscountToSalaryLessThenAverageSalary, percentageOfDiscountToSalaryGreaterOrEqualsToAverageSalary);

        final BigDecimal grossSalaryToJuniorDeveloper = BigDecimal.valueOf(3000);
        final BigDecimal salaryToJuniorDeveloper = grossSalaryToJuniorDeveloper.subtract(grossSalaryToJuniorDeveloper.multiply(percentageOfDiscountToSalaryGreaterOrEqualsToAverageSalary));
        final String juniorDeveloperNameFunction = "JUNIOR DEVELOPER";
        final Function juniorDeveloperFunction = new Function(juniorDeveloperNameFunction, averageSalaryToDeveloper, percentageOfDiscountToSalaryLessThenAverageSalary, percentageOfDiscountToSalaryGreaterOrEqualsToAverageSalary);

        return Stream.of(
                Arguments.of(seniorDeveloperNameFunction, salaryToSeniorDeveloper, grossSalaryToSeniorDeveloper, seniorDeveloperFunction),
                Arguments.of(fullDeveloperNameFunction, salaryToFullDeveloper, grossSalaryToFullDeveloper, fullDeveloperFunction),
                Arguments.of(juniorDeveloperNameFunction, salaryToJuniorDeveloper, grossSalaryToJuniorDeveloper, juniorDeveloperFunction)
        );
    }
}
