package br.org.pti.integrador.application.clients;

import br.org.pti.integrador.domain.entities.dto.ClienteDTO;
import br.org.pti.integrador.infrastructure.utils.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 04/10/2017
 */
@Component
public class ClientesClient {

    private final Client client;

    @Value("${protheus.api.rest}")
    private String protheusApiUrl;

    /**
     *
     */
    public ClientesClient() {
        this.client = ClientBuilder.newClient();
    }
    
    /**
     * 
     * @param clienteDTO
     * @return 
     */
    public ClienteDTO cadastrarNoProtheus(final ClienteDTO clienteDTO) {
       
        final WebTarget target = this.client
                .target(this.protheusApiUrl)
                .path("clientes");

        final Entity<ClienteDTO> entity = Entity.entity(
                clienteDTO, MediaType.APPLICATION_JSON);

        try {
            return target
                    .request(MediaType.APPLICATION_JSON)
                    .post(entity, ClienteDTO.class);
        } catch (ProcessingException ex) {
            throw new ServiceException("webservice.cant-connect", ex, target.getUri());
        }
    }

    /**
     * 
     * @param clienteDTO
     * @return 
     */
    public ClienteDTO atualizarNoProtheus(final ClienteDTO clienteDTO) {
        final WebTarget target = this.client
                .target(this.protheusApiUrl)
                .path("clientes");

        final Entity<ClienteDTO> entity = Entity.entity(
                clienteDTO, MediaType.APPLICATION_JSON);

        try {
            return target
                    .request(MediaType.APPLICATION_JSON)
                    .put(entity, ClienteDTO.class);
        } catch (ProcessingException ex) {
            throw new ServiceException("webservice.cant-connect", ex, target.getUri());
        }
    }
}
