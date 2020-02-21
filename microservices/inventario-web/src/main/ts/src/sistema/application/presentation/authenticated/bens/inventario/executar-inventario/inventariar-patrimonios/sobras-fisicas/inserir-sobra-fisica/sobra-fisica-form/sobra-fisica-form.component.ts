import {Component, ElementRef, Inject, OnInit, Renderer} from '@angular/core';
import {MAT_FORM_FIELD_DEFAULT_OPTIONS, MatFormFieldDefaultOptions, MatSnackBar} from "@angular/material";
import {ActivatedRoute, Router} from "@angular/router";
import {AbstractControl, FormBuilder, FormControl, ValidatorFn} from "@angular/forms";
import {CrudViewComponent} from "../../../../../../../../../controls/crud/crud-view.component";
import {PatrimonioRepository} from "../../../../../../../../../../domain/repository/patrimonio.repository";
import {MessageService} from "../../../../../../../../../../domain/services/message.service";
import {obrigatorio} from "../../../../../../../../../controls/validators/validators";
import {PatrimonioFormComponent} from "../../../inserir-patrimonio/patrimonio-form/patrimonio-form.component";
import {CentroCustoRepository} from "../../../../../../../../../../domain/repository/centro-custo.repository";
import {CentroCustoInventario} from "../../../../../../../../../../domain/entity/patrimonio/inventario/centro-custo-inventario.model";

const appearance: MatFormFieldDefaultOptions = {
  appearance: 'outline'
};

// @ts-ignore
@Component({
  selector: 'sobra-fisica-form',
  templateUrl: './sobra-fisica-form.component.html',
  styleUrls: ['../../../../../inventarios.scss'],
  providers: [
    {
      provide: MAT_FORM_FIELD_DEFAULT_OPTIONS,
      useValue: appearance
    }
  ]
})
export class SobraFisicaFormComponent extends CrudViewComponent implements OnInit {

  /**
   *
   */
  patrimonios: any = [];

  /**
   *
   */
  localizacoes: any = [];

  /**
   *
   */
  private centroCustoInventario: CentroCustoInventario;

  /**
   *
   * @param router
   * @param snackBar
   * @param activatedRoute
   * @param messageService
   * @param element
   * @param fb
   * @param patrimonioRepository
   * @param renderer
   * @param centroCustoRepository
   */
  constructor(public activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              @Inject(ElementRef) public element: ElementRef,
              public fb: FormBuilder, public renderer: Renderer,
              private patrimonioRepository: PatrimonioRepository,
              private router: Router, public snackBar: MatSnackBar,
              private centroCustoRepository: CentroCustoRepository) {
    super(snackBar, element, fb, renderer, activatedRoute)
  }

  /**
   *
   */
  ngOnInit() {
    this.form = this.fb.group({
      plaqueta: new FormControl(
        {
          value: this.entity.plaqueta
        },
        [obrigatorio()]
      ),
      localizacao: [{value: ''}, [this.selecaoObrigatoria('Nenhuma localização selecionada')]]
    });

    this.centroCustoInventario = this.entity.centroCustoInventario;

  }

  /**
   *
   */
  public listPatrimoniosByFilters() {

    let patrimonio = this.entity.plaqueta || null;
    if (PatrimonioFormComponent.isString(patrimonio) && !(patrimonio as any).length) {
      this.patrimonios = [];
      return
    }

    if (this.entity && this.entity.plaqueta && this.entity.plaqueta.length >= 3)
      this.patrimonioRepository.findByNumeroPlaqueta(this.entity.plaqueta as any).subscribe(result => {
        this.patrimonios = (result as any).content;
        this.entity.sobraFisica = !(result as any).content.length || (result as any).content.length === 0
      })
  }

  /**
   *
   * @param exception
   */
  selecaoObrigatoria(exception?: string): ValidatorFn {
    return (c: AbstractControl): { [key: string]: any } => {
      if (c.value instanceof Object)
        return null;
      else return {
        exception: exception ? exception : 'Campo obrigatório'
      }
    }
  }

  /**
   *
   */
  public async listByLocalizacoesFilters() {

    let localizacao = this.entity.localizacao || null;
    if (PatrimonioFormComponent.isString(localizacao) && !(localizacao as any).length) {
      this.localizacoes = [];
      return
    }

    if (this.entity.localizacao && this.entity.localizacao.length >= 3) {

      const id: number = (this.centroCustoInventario.centroCusto.id ? this.centroCustoInventario.centroCusto.id : this.centroCustoInventario.centroCusto) as any;
      const centroCustoCodigo = (await this.centroCustoRepository.findById(id).toPromise()).codigo;

      await this.patrimonioRepository.listByLocalizacoesFilters({
        descricaoLocalizacaoFilter: this.entity.localizacao,
        centroCustoCodigo: centroCustoCodigo
      }).subscribe(result => {
        this.localizacoes = result;
        if (this.localizacoes.length && this.entity.localizacao)
          if (this.localizacoes[0].codigo.toLocaleLowerCase() === ((this.entity.localizacao.constructor === String) && (this.entity.localizacao as any).toLowerCase()))
            this.entity.localizacao = this.localizacoes[0]
      });
    }
  }

  /**
   *
   * @param object
   */
  public displayNome(object) {
    return object && object.codigo && object.descricao ? object.codigo + ' - ' + object.descricao : null
  }

}
