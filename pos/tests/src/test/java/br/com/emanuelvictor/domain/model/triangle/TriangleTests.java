package br.com.emanuelvictor.domain.model.triangle;

import br.com.emanuelvictor.domain.model.triangle.exceptions.InvalidTriangle;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleTests {

    @ParameterizedTest
    @MethodSource("getInvalidValuesFromCreateATriangle")
    void cannotCreateInstanceOfTriangleWithInvalidEdges(final Integer[] edges, final String message) {

        final Exception exception = assertThrows(InvalidTriangle.class, () -> new Triangle(edges));

        Assertions.assertThat(exception).isInstanceOf(InvalidTriangle.class).hasMessageContaining(message);
    }

    private static Stream<Arguments> getInvalidValuesFromCreateATriangle() {
        return Stream.of(
                Arguments.arguments(new Integer[]{1, 2, 1, 2}, "Cannot create a triangle greater than 3 edges"),
                Arguments.arguments(new Integer[]{1, 2}, "Cannot create a triangle less than 3 edges"),
                Arguments.arguments(new Integer[]{1, 2, 3}, "The sum from other edges is equals to one edge"),
                Arguments.arguments(new Integer[]{2, 3, 1}, "The sum from other edges is equals to one edge"),
                Arguments.arguments(new Integer[]{3, 1, 2}, "The sum from other edges is equals to one edge"),
                Arguments.arguments(new Integer[]{1, 1, 3}, "The sum from other edges is less than one edge"),
                Arguments.arguments(new Integer[]{1, 3, 1}, "The sum from other edges is less than one edge"),
                Arguments.arguments(new Integer[]{3, 1, 1}, "The sum from other edges is less than one edge")
        );
    }

    @ParameterizedTest
    @MethodSource("getValuesToCreateScaleneTriangles")
    void createScaleneTriangles(final Integer firstEdge, final Integer secondEdge, final Integer thirdEdge, final boolean isScalene) {

        final Triangle triangle = new Triangle(firstEdge, secondEdge, thirdEdge);

        Assertions.assertThat(triangle.isScalene()).isEqualTo(isScalene);
    }

    private static Stream<Arguments> getValuesToCreateScaleneTriangles() {
        return Stream.of(
                Arguments.arguments(5, 7, 8, true),
                Arguments.arguments(7, 8, 5, true),
                Arguments.arguments(8, 5, 7, true),
                Arguments.arguments(2, 2, 3, false),
                Arguments.arguments(3, 2, 2, false),
                Arguments.arguments(2, 3, 2, false)
        );
    }

    @ParameterizedTest
    @MethodSource("getValuesToCreateEquilateralTriangles")
    void createEquilateralTriangles(final Integer firstEdge, final Integer secondEdge, final Integer thirdEdge, final boolean isEquilateral) {

        final Triangle triangle = new Triangle(firstEdge, secondEdge, thirdEdge);

        Assertions.assertThat(triangle.isEquilateral()).isEqualTo(isEquilateral);
    }

    private static Stream<Arguments> getValuesToCreateEquilateralTriangles() {
        return Stream.of(
                Arguments.arguments(5, 5, 5, true),
                Arguments.arguments(11, 11, 11, true),
                Arguments.arguments(7, 7, 7, true),
                Arguments.arguments(2, 2, 3, false),
                Arguments.arguments(3, 2, 2, false),
                Arguments.arguments(2, 3, 2, false)
        );
    }

    @ParameterizedTest
    @MethodSource("getValuesToCreateRectanglesTriangles")
    void createRectanglesTriangles(final Integer firstEdge, final Integer secondEdge, final Integer thirdEdge, final boolean isRectangle) {

        final Triangle triangle = new Triangle(firstEdge, secondEdge, thirdEdge);

        Assertions.assertThat(triangle.isRectangle()).isEqualTo(isRectangle);
    }

    private static Stream<Arguments> getValuesToCreateRectanglesTriangles() {
        return Stream.of(
                Arguments.arguments(3, 5, 4, true),
                Arguments.arguments(4, 5, 3, true),
                Arguments.arguments(5, 4, 3, true),
                Arguments.arguments(15, 12, 9, true),
                Arguments.arguments(9, 15, 12, true),
                Arguments.arguments(12, 9, 15, true),
                Arguments.arguments(7, 1, 7, false),
                Arguments.arguments(4, 2, 3, false),
                Arguments.arguments(3, 2, 2, false),
                Arguments.arguments(2, 3, 2, false)
        );
    }

}
