package br.org.pti.integrador.domain.services;

import br.org.pti.integrador.application.clients.ParametrosClient;
import br.org.pti.integrador.application.formatters.PonMesFormatter;
import br.org.pti.integrador.domain.entities.dto.ParametroDTO;
import br.org.pti.integrador.domain.entities.pontoeletronico.BancoHora;
import br.org.pti.integrador.domain.repositories.BancoHoraRepository;
import br.org.pti.integrador.infrastructure.utils.components.datetime.DatePeriod;
import br.org.pti.integrador.infrastructure.utils.exceptions.ResourceNotFoundException;
import br.org.pti.integrador.infrastructure.utils.protheus.ProtheusUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 01/08/2017
 */
@Service
@Transactional
public class BancoHoraService {
    
    @Autowired
    private ParametrosClient parametrosClient;
    
    @Autowired
    private BancoHoraRepository bancoHorasRepositorio;

    /**
     *
     * @param matricula
     * @return
     */
    @Transactional(readOnly = true)
    public List<BancoHora> listarBancoHorasPorMatricula(String matricula) {

        final ParametroDTO<DatePeriod> parametro = this.parametrosClient.getParametro(
                "ponmes", new PonMesFormatter());
        
        final DatePeriod periodo = parametro.applyFormat();
        
        final String ra_mat = ProtheusUtils.arrumaMatricula(matricula);
        
        final List<BancoHora> bancoHoras = this.bancoHorasRepositorio
                .listarPorMatriculaNoPeriodo(ra_mat, periodo.getInicio(), periodo.getFim());
        
        // se nao tem dados, diz que nao tem dados...
        if (bancoHoras == null || bancoHoras.isEmpty()) {
            throw new ResourceNotFoundException("repository.empty-result", matricula);
        }
        
        return bancoHoras;
    }
}
