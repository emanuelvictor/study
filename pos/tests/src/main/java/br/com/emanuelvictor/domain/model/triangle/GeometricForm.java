package br.com.emanuelvictor.domain.model.triangle;

import br.com.emanuelvictor.domain.model.triangle.exceptions.InvalidGeometricForm;

public abstract class GeometricForm {

    protected Integer[] edges;

    public GeometricForm(final Integer... edges) {
        validateEdges(edges);
        this.edges = edges;
    }

    private static void validateEdges(final Integer... edges) {
        if (edges.length < 1)
            throw new InvalidGeometricForm("Cannot create a geometric form without edges") {
            };
        for (final Integer edge : edges){
            if(edge == null)
                throw new InvalidGeometricForm("One or more edges is null") {
                };
            if (edge <= 0)
                throw new InvalidGeometricForm("One or more edges less than 0") {
                };
        }

    }
}
