package br.com.emanuelvictor.domain.model.triangle;

import br.com.emanuelvictor.domain.model.triangle.exceptions.InvalidGeometricForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeometricFormTests {

    @Test
    void cannotCreateInstanceOfGeometricFormWithoutEdges() {

        final Exception exception = assertThrows(InvalidGeometricForm.class, () -> new GeometricForm() {
        });

        Assertions.assertThat(exception).isInstanceOf(InvalidGeometricForm.class).hasMessageContaining("Cannot create a geometric form without edges");
    }

    @ParameterizedTest
    @MethodSource("getInvalidValuesToCreateAInvalidGeometricForm")
    void cannotCreateInstanceOfGeometricFormWithInvalidEdges(final Integer[] edges, final String message) {

        final Exception exception = assertThrows(InvalidGeometricForm.class, () -> new GeometricForm(edges) {
        });

        Assertions.assertThat(exception).isInstanceOf(InvalidGeometricForm.class).hasMessageContaining(message);
    }

    private static Stream<Arguments> getInvalidValuesToCreateAInvalidGeometricForm() {
        return Stream.of(
                Arguments.arguments(new Integer[]{}, "Cannot create a geometric form without edges"),
                Arguments.arguments(new Integer[]{1, 1, null}, "One or more edges is null"),
                Arguments.arguments(new Integer[]{1, null, null}, "One or more edges is null"),
                Arguments.arguments(new Integer[]{1, 1, 0}, "One or more edges less than 0"),
                Arguments.arguments(new Integer[]{0}, "One or more edges less than 0"),
                Arguments.arguments(new Integer[]{0, 1, 2}, "One or more edges less than 0"),
                Arguments.arguments(new Integer[]{1, 0, 2}, "One or more edges less than 0"),
                Arguments.arguments(new Integer[]{-1, 0, 2}, "One or more edges less than 0"),
                Arguments.arguments(new Integer[]{1, 0, -2}, "One or more edges less than 0"),
                Arguments.arguments(new Integer[]{0, 0, 0}, "One or more edges less than 0")
        );
    }
}
