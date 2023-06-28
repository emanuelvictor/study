package br.com.emanuelvictor.application.resource;

import jakarta.json.JsonObject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("rest")
public class CalculatorResource {

    @POST
    @Path("sum")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer sum(final JsonObject jsonData) {
        return jsonData.getInt("firstValue") + jsonData.getInt("secondValue");
    }

}
