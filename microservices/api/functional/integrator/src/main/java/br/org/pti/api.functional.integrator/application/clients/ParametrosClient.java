package br.org.pti.api.functional.integrator.application.clients;

import br.org.pti.api.functional.integrator.application.formatters.ParametroFormatter;
import br.org.pti.api.functional.integrator.domain.entities.dto.ParametroDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 1.0.0, 18/08/2017
 */
@Component
public class ParametrosClient {

    @Value("${protheus.api.rest}")
    private String protheusApiUrl;

//    private final Client client;

    /**
     *
     */
    public ParametrosClient() {

//        this.client = ClientBuilder.newClient();
    }

    /**
     * @param nomeParametro
     * @return
     */
    public ParametroDTO getParametro(String nomeParametro) {
        return this.callEndpoint(nomeParametro);
    }

    /**
     * @param <T>
     * @param nomeParametro
     * @param formatter
     * @return
     */
    public <T> ParametroDTO getParametro(String nomeParametro, ParametroFormatter<T> formatter) {
        final ParametroDTO<T> parametro = this.callEndpoint(nomeParametro);
        parametro.setFormatter(formatter);
        return parametro;
    }

    /**
     * @param nomeParametro
     * @return
     */
    private ParametroDTO callEndpoint(String nomeParametro) {
        return null;
//        final WebTarget target = this.client
//                .target(this.protheusApiUrl)
//                .path("parametros")
//                .path(nomeParametro);
//
//        try {
//            return target
//                    .request(MediaType.APPLICATION_JSON)
//                    .get(ParametroDTO.class);
//        } catch (ProcessingException ex) {
//            throw new ServiceException("webservice.cant-connect", ex, target.getUri());
//        }
    }
}
