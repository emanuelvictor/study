package br.org.pti.compras.application.resource;

import br.org.pti.compras.domain.entity.endereco.Cidade;
import br.org.pti.compras.domain.entity.endereco.Estado;
import br.org.pti.compras.domain.entity.endereco.Pais;
import br.org.pti.compras.domain.repository.ICidadeRepository;
import br.org.pti.compras.domain.repository.IEstadoRepository;
import br.org.pti.compras.domain.repository.IPaisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.org.pti.compras.application.resource.Roles.ENDERECO_MAPPING_RESOURCE;
import static br.org.pti.compras.infrastructure.aid.Utils.getListFromArray;

@RestController
@RequiredArgsConstructor
@RequestMapping({ENDERECO_MAPPING_RESOURCE, "/sistema/" + ENDERECO_MAPPING_RESOURCE})
public class EnderecoResource {

    /**
     *
     */
    private final IPaisRepository paisRepository;

    /**
     *
     */
    private final IEstadoRepository estadoRepository;

    /**
     *
     */
    private final ICidadeRepository cidadeRepository;

    /**
     * @param defaultFilter String
     * @param pageable      Pageable
     * @return Page<Pais>
     */
    @GetMapping(value = "/paises")
    public Page<Pais> listPaisesByFilters(final String defaultFilter, final Pageable pageable) {
        return this.paisRepository.listByFilters(defaultFilter, pageable);
    }

    /**
     * @param defaultFilter String
     * @param paisId        Long
     * @param pageable      Pageable
     * @return Page<Estado>
     */
    @GetMapping(value = "/estados")
    public Page<Estado> listEstadosByFilters(final String defaultFilter, final Long paisId, final Pageable pageable) {
        return this.estadoRepository.listByFilters(defaultFilter, paisId, pageable);
    }

    /**
     * @param defaultFilter String
     * @param estadoId      Long
     * @param pageable      Pageable
     * @return Page<Cidade>
     */
    @GetMapping(value = "/cidades")
    public Page<Cidade> listCidadesByFilters(final String defaultFilter, final Long[] estadosFilter, final Long estadoId, final String uf, final Pageable pageable) {
        return this.cidadeRepository.listByFilters(defaultFilter, getListFromArray(estadosFilter), estadoId, uf, pageable);
    }

}
