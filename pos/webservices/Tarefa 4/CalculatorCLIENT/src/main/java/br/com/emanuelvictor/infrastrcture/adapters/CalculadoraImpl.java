/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package br.com.emanuelvictor.infrastrcture.adapters;

import br.com.emanuelvictor.domain.ports.Calculadora;
import jakarta.ejb.Stateless;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * @author Emanuel Victor
 */
@Stateless
public class CalculadoraImpl implements Calculadora {

    /**
     * @param valores {@link int.. }
     * @return {@link int}
     */
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public int somar(final int... valores) {
        final JsonObject value = Json.createObjectBuilder()
                .add("firstValue", valores[0])
                .add("secondValue", valores[1])
                .build();
        final Integer response = new RestClient()
                .getPath("sum")
                .request()
                .post(Entity.entity(value, MediaType.APPLICATION_JSON), Integer.class);
        return response;
    }
}
