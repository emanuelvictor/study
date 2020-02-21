package br.org.pti.integrador.domain.services;

import br.org.pti.integrador.domain.entities.oracamento.Contrato;
import br.org.pti.integrador.domain.entities.oracamento.ItemContrato;
import br.org.pti.integrador.domain.repositories.ContratoRepository;
import br.org.pti.integrador.domain.repositories.ItemContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 18/01/2019
 */
@Service
@Transactional
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;
    @Autowired
    private ItemContratoRepository itemContratoRepository;

    /**
     *
     * @param codigo
     * @return
     */
    @Transactional(readOnly = true)
    public Contrato buscaContrato(String codigo) {

        List<Contrato> contratos = this.contratoRepository.findByCodigo(codigo);

        if (contratos.size() > 0) {
            Contrato contrato = new Contrato();
            contrato = contratos.get(contratos.size() - 1);

            List<ItemContrato> items = this.itemContratoRepository
                    .findByContrato(contrato);
            contrato.setItensContrato(items);
            return contrato;

        } else {
            return null;
        }
    }
}
