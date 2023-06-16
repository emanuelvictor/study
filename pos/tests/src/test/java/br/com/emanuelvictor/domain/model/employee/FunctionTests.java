package br.com.emanuelvictor.domain.model.employee;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static br.com.emanuelvictor.domain.model.employee.Function.*;

public class FunctionTests {

    @ParameterizedTest
    @MethodSource("getAverageSalariesAndFunctions")
    void mustExtractAverageSalaryFromFunction(final int averageSalary, final Function function) {
        Assertions.assertThat(BigDecimal.valueOf(averageSalary)).isEqualTo(function.getAverageSalary());
    }

    private static Stream<Arguments> getAverageSalariesAndFunctions() {
        return Stream.of(
                Arguments.arguments(3000, DEVELOPER),
                Arguments.arguments(2000, DBA),
                Arguments.arguments(2000, TESTER)
        );
    }
}
