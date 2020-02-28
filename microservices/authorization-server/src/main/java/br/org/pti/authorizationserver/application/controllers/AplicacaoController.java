package br.org.pti.authorizationserver.application.controllers;

import br.org.pti.authorizationserver.domain.entities.configuration.Application;
import br.org.pti.authorizationserver.domain.repositories.security.PermissaoRepository;
import br.org.pti.authorizationserver.domain.services.AplicacaoService;
import br.org.pti.authorizationserver.infrastructure.misc.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 2.0.0, 04/01/2020
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/seguro/aplicacoes")
public class AplicacaoController {

    private final AplicacaoService aplicacaoService;

    private final PermissaoRepository permissaoRepository;

    /**
     * @param binder WebDataBinder
     */
    @InitBinder
    protected void initBinder(final WebDataBinder binder) {

        final CustomCollectionEditor permissoesCollector = new CustomCollectionEditor(Set.class) {
            @Override
            protected Object convertElement(final Object objectId) {
                final String stringId = ((String) objectId).split(",")[((String) objectId).split(",").length - 1];
                final long longId = Long.parseLong(stringId);
                return permissaoRepository.findById(longId).orElseThrow();

            }
        };

        binder.registerCustomEditor(Set.class, "permissoes", permissoesCollector);
    }

    /**
     * @param model Model
     * @return String
     */
    @GetMapping
    public String acaoListar(final Model model) {
        model.addAttribute("aplicacoes", this.aplicacaoService.findAll());
        model.addAttribute("filtro", "");
        return "seguro/aplicacoes/aplicacoesListar";
    }

    /**
     * @param model Model
     * @return String
     */
    @GetMapping("/cadastrar")
    public String acaoCadastrar(final Model model) {

        final Application aplicacao = new Application();

        aplicacao.setSenha(PasswordGenerator.generate());

        model.addAttribute("aplicacao", aplicacao);
        model.addAttribute("allPermissoes", this.aplicacaoService.findAllPermissions().getContent());

        return "seguro/aplicacoes/aplicacaoFormulario";
    }

    /**
     * @param id    Long
     * @param model Model
     * @return String
     */
    @GetMapping("/editar/{id}")
    public String acaoEditar(@PathVariable @NotNull final Long id, final Model model) {

        model.addAttribute("aplicacao", this.aplicacaoService.findById(id).orElseThrow(IllegalStateException::new));
        model.addAttribute("allPermissoes", this.aplicacaoService.findAllPermissions().getContent());

        return "seguro/aplicacoes/aplicacaoAtualizar";
    }

    /**
     * @param id    Long
     * @param model Model
     * @return String
     */
    @GetMapping("/detalhar/{id}")
    public String acaoDetalhar(@PathVariable @NotNull final Long id, final Model model) {
        model.addAttribute("aplicacao", this.aplicacaoService.findById(id).orElseThrow(IllegalStateException::new));
        return "seguro/aplicacoes/aplicacaoDetalhar";
    }

    /**
     * @param filtro String
     * @param model  Model
     * @return String
     */
    @ResponseStatus(OK)
    @GetMapping("/buscar")
    public String acaoBusca(@RequestParam final String filtro, final Model model) {

        final List<Application> aplicacoes;

        if (isBlank(filtro)) {
            aplicacoes = this.aplicacaoService.findAll();
        } else {
            aplicacoes = this.aplicacaoService.findByFiltro(filtro, null).getContent();
        }

        model.addAttribute("aplicacoes", aplicacoes);

        return "seguro/aplicacoes/aplicacoesListar :: #listaAplicacoes";
    }

    /**
     * @param aplicacao     Aplicacao
     * @param bindingResult BindingResult
     * @param model         Model
     * @return String
     */
    @PostMapping("/cadastrar")
    public String salvar(@ModelAttribute @Valid final Application aplicacao, final BindingResult bindingResult, final Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("aplicacao", aplicacao);

            return "seguro/aplicacoes/aplicacaoFormulario";
        }

        if (aplicacao.getId() != null)
            this.aplicacaoService.save(aplicacao.getId(), aplicacao);
        else
            this.aplicacaoService.save(aplicacao);

        return "redirect:/seguro/aplicacoes";
    }

    /**
     * @param id Long
     */
    @ResponseStatus(OK)
    @DeleteMapping("{id}")
    public void deletar(@PathVariable @NotNull final Long id/*, final Model model*/) {
        this.aplicacaoService.delete(id);
    }

    /**
     * @param id    Long
     * @param model Model
     * @return String
     */
    @ResponseStatus(OK)
    @PutMapping("/mudar-senha")
    public String mudarSenha(@RequestBody @NotNull final Long id, final Model model) {
        model.addAttribute("novaSenha", this.aplicacaoService.changePassword(id));
        return "seguro/aplicacoes/aplicacaoDetalhar :: #inNovaSenha";
    }
}
