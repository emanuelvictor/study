package br.com.emanuelvictor.domain.model.triangle;

import br.com.emanuelvictor.domain.model.triangle.exceptions.InvalidTriangle;

import java.util.Objects;

public class Triangle extends GeometricForm {

    public Triangle(final Integer... edges) {
        super(edges);
        validateTriangleEdges(edges);
    }

    private void validateTriangleEdges(final Integer... edges) {
        if (edges.length < 3)
            throw new InvalidTriangle("Cannot create a triangle less than 3 edges");
        if (edges.length > 3)
            throw new InvalidTriangle("Cannot create a triangle greater than 3 edges");

        for (int i = 0; i < edges.length; i++) {
            Integer sum = 0;
            for (int j = 0; j < edges.length; j++) {
                if (j != i)
                    sum = sum + edges[j];
            }
            if (sum < edges[i])
                throw new InvalidTriangle("The sum from other edges is less than one edge");
            if (sum.equals(edges[i]))
                throw new InvalidTriangle("The sum from other edges is equals to one edge");
        }
    }

    public boolean isScalene() {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length; j++) {
                if (i != j)
                    if (Objects.equals(edges[j], edges[i]))
                        return false;
            }
        }
        return true;
    }

    public boolean isEquilateral() {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length; j++) {
                if (i != j)
                    if (!Objects.equals(edges[j], edges[i]))
                        return false;
            }
        }
        return true;
    }

    public boolean isRectangle() {
        final int hypotenuse = getIndexOfHypotenuse();
        final int hypotenuseSquared = square(edges[hypotenuse]);
        int sidesSquared = 0;
        for (int i = 0; i < edges.length; i++) {
            if (i != hypotenuse) {
                sidesSquared = sidesSquared + square(edges[i]);
            }
        }
        return hypotenuseSquared == sidesSquared;
    }

    private int square(final int value) {
        return value * value;
    }

    private int getIndexOfHypotenuse() {
        int index = 0;
        for (int i = 0; i < edges.length; i++) {
            if (edges[i] > edges[index]) {
                index = i;
            }
        }
        return index;
    }
}
