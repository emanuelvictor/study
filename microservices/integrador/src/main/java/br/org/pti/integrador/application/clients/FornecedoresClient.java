package br.org.pti.integrador.application.clients;

import br.org.pti.integrador.domain.entities.dto.FornecedorDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @author Willian Brecher
 * @version 1.0.0
 * @since 1.0.0, 09/05/2019
 */
@Component
public class FornecedoresClient {

    @Value("${protheus.api.rest}")
    private String protheusApiUrl;

//    private final Client client;

    /**
     *
     */
    public FornecedoresClient() {

//        this.client = ClientBuilder.newClient();
    }

    /**
     * @param fornecedorDTO
     * @return
     */
    public FornecedorDTO cadastrarNoProtheus(FornecedorDTO fornecedorDTO) {
        return null;
//        final WebTarget target = this.client
//                .target(this.protheusApiUrl)
//                .path("fornecedores");
//
//        final Entity<FornecedorDTO> entity = Entity.entity(
//                fornecedorDTO, MediaType.APPLICATION_JSON);
//
//        try {
//            return target
//                    .request(MediaType.APPLICATION_JSON)
//                    .post(entity, FornecedorDTO.class);
//        } catch (ProcessingException ex) {
//            throw new ServiceException("webservice.cant-connect", ex, target.getUri());
//        }
    }

    /**
     * @param fornecedorDTO
     * @return
     */
    public FornecedorDTO atualizarNoProtheus(FornecedorDTO fornecedorDTO) {
        return null;
//        final WebTarget target = this.client
//                .target(this.protheusApiUrl)
//                .path("fornecedores");
//
//        final Entity<FornecedorDTO> entity = Entity.entity(
//                fornecedorDTO, MediaType.APPLICATION_JSON);
//
//        try {
//            return target
//                    .request(MediaType.APPLICATION_JSON)
//                    .put(entity, FornecedorDTO.class);
//        } catch (ProcessingException ex) {
//            throw new ServiceException("webservice.cant-connect", ex, target.getUri());
//        }
    }
}
