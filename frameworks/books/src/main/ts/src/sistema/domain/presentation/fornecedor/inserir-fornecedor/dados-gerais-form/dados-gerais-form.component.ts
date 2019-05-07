import {Component, ElementRef, EventEmitter, Inject, Input, OnInit, Output, Renderer, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatAutocomplete, MatAutocompleteSelectedEvent, MatSnackBar} from '@angular/material';
import {AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, ValidatorFn} from "@angular/forms"
import {MessageService} from "../../../../services/message.service";
import {DashboardViewComponent} from "../../../dashboard-view.component";
import {UsuarioRepository} from "../../../../repository/usuario.repository";
import {CategoriaRepository} from "../../../../repository/categoria.repository";
import {CategoriaFornecedor} from "../../../../entity/fornecedor/categoria-fornecedor.model";
import {AtividadeEconomicaRepository} from "../../../../repository/atividade-economica.repository";
import 'rxjs/add/operator/debounceTime';
import {Subject} from 'rxjs/Subject';
import {Fornecedor} from "../../../../entity/fornecedor/fornecedor.model";
import {AtividadeEconomica} from "../../../../entity/fornecedor/atividade-economica.model";
import {obrigatorio} from "../../../../../application/controls/validators/validators";

@Component({
  selector: 'dados-gerais-form',
  templateUrl: './dados-gerais-form.component.html',
  styleUrls: ['../../fornecedor.component.scss']
})
export class DadosGeraisFormComponent implements OnInit {

  @Input()
  entity: Fornecedor;

  @Output()
  save: EventEmitter<Fornecedor> = new EventEmitter();

  @Input()
  form: FormGroup;

  @Output()
  formChange: EventEmitter<FormGroup> = new EventEmitter();

  /**
   *
   */
  categoriasList: CategoriaFornecedor[] = [];

  /**
   *
   */
  atividadesEconomicas = [];

  /**
   *
   * @type {Subject<string>}
   */
  private modelChanged: Subject<string> = new Subject<string>();

  @ViewChild('atividadesEconomicasPrimarias') atividadesEconomicasPrimarias: ElementRef<HTMLInputElement>;
  @ViewChild('atividadesEconomicasSecundarias') atividadesEconomicasSecundarias: ElementRef<HTMLInputElement>;

  @ViewChild('auto') matAutocomplete: MatAutocomplete;
  @ViewChild('chipList') chipList;
  @ViewChild('chipListSecundaria') chipListSecundaria;

  /**
   *
   * @param router
   * @param snackBar
   * @param activatedRoute
   * @param messageService
   * @param homeView
   * @param usuarioRepository
   * @param element
   * @param categoriaRepository
   * @param fb
   * @param renderer
   * @param atividadeEconomicaRepository
   */
  constructor(private homeView: DashboardViewComponent,
              private usuarioRepository: UsuarioRepository,
              @Inject(ElementRef) public element: ElementRef,
              private categoriaRepository: CategoriaRepository,
              public fb: FormBuilder, public renderer: Renderer,
              private messageService: MessageService, private router: Router,
              private atividadeEconomicaRepository: AtividadeEconomicaRepository,
              public activatedRoute: ActivatedRoute, public snackBar: MatSnackBar) {

  }

  /**
   *
   */
  populeForm(): void {
    if (!this.form) {
      this.form = this.fb.group({});
    }

    const formGroup: FormGroup = new FormGroup({
      nome: new FormControl('', obrigatorio('O Nome é obrigatório')),
      atividadesEconomicas: new FormControl('', [obrigatorio('Quais são suas atividades economicas?')]),
      categoriasFornecedor: new FormControl('', [obrigatorio('Defina ao menos uma categoria')]),
      descricaoProdutosServicos: new FormControl('', [obrigatorio('Definia uma descrição dos produtos')]),
      atividadesEconomicasPrimarias: this.fb.array(this.entity.atividadesEconomicasPrimarias, [obrigatorio('Ao menos uma Atividade Econômica Primária deve ser definida')]),
      atividadesEconomicasSecundarias: this.fb.array(this.entity.atividadesEconomicasSecundarias, [])
    });

    this.form.addControl('dadosGerais', formGroup);

    this.form.get('dadosGerais').get('atividadesEconomicasPrimarias')
      .statusChanges.subscribe(status => this.chipList.errorState = status === 'INVALID');
    //
    this.form.get('dadosGerais').get('atividadesEconomicasSecundarias')
      .statusChanges.subscribe(status => this.chipListSecundaria.errorState = status === 'INVALID');
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
      };
    }
  }

  /**
   *
   */
  ngOnInit() {

    this.atividadeEconomicaRepository.listByFilters({size:100000})
      .subscribe((result) => {
        this.atividadesEconomicas = result.content
      });

    this.modelChanged.debounceTime(300).subscribe(model => {
      this.atividadeEconomicaRepository.listByFilters({defaultFilter: model})
        .subscribe((result) => {
          this.atividadesEconomicas = result.content
        })
    });

    if (!this.entity.atividadesEconomicasPrimarias)
      this.entity.atividadesEconomicasPrimarias = [];
    if (!this.entity.atividadesEconomicasSecundarias)
      this.entity.atividadesEconomicasSecundarias = [];

    this.populeForm();

    this.categoriaRepository.listByFilters({size: 10000, sort: {properties: ['nome'], direction: 'ASC'}})
      .subscribe(result => {
        result.content.forEach(a => {

          if (a.ativo) {
            const categoriaFornecedor = new CategoriaFornecedor();
            categoriaFornecedor.fornecedor = this.entity;
            categoriaFornecedor.categoria = a;
            this.categoriasList.push(categoriaFornecedor);
          } else {
            if (this.entity.categoriasFornecedor)
              this.entity.categoriasFornecedor.forEach(inner => {
                if (a.id === inner.categoria.id) {
                  const categoriaFornecedor = new CategoriaFornecedor();
                  categoriaFornecedor.fornecedor = this.entity;
                  categoriaFornecedor.categoria = a;
                  this.categoriasList.push(categoriaFornecedor);
                }
              })
          }

          if (this.entity.categoriasFornecedor)
            this.entity.categoriasFornecedor.forEach(a => {
              this.categoriasList.forEach(b => {
                if (b.categoria.id === a.categoria.id)
                  b.id = a.id;
              })
            })
        })
      });
  }

  /**
   *
   * @param o1
   * @param o2
   */
  compareAtividadesEconomicas(o1: any, o2: any): boolean {
    return o1 === o2;
  }

  /**
   *
   * @param o1
   * @param o2
   */
  compareCategorias(o1: any, o2: any): boolean {
    return o1 && o2 && o1.categoria && o2.categoria && o1.categoria.id === o2.categoria.id;
  }

  /**
   *
   * @param {string} model
   */
  atividadesEconomicasPrimariasChanged(model: string): void {
    console.log('atividadesEconomicasPrimariasChanged');
    if (this.entity.atividadesEconomicasPrimarias.length < 1) {
      if (model && model.length)
        this.modelChanged.next(model);
    } else this.atividadesEconomicasPrimarias.nativeElement.value = '';
  }

  /**
   *
   * @param {string} model
   */
  atividadesEconomicasSecundariasChanged(model: string): void {
    this.modelChanged.next(model);
  }

  /**
   *
   * @param {AtividadeEconomica} atividadeEconomica
   */
  remove(atividadeEconomica: AtividadeEconomica): void {
    const index = this.entity.atividadesEconomicasPrimarias.indexOf(atividadeEconomica);
    if (index >= 0) {
      this.entity.atividadesEconomicasPrimarias.splice(index, 1);
    }
    this.form.controls['dadosGerais'].get('atividadesEconomicasPrimarias').enable();
    const control = this.form.controls['dadosGerais'].get('atividadesEconomicasPrimarias') as FormArray;
    control.removeAt(index);
  }

  /**
   *
   * @param {AtividadeEconomica} atividadeEconomica
   */
  removeAtividadeSecundaria(atividadeEconomica: AtividadeEconomica): void {
    const index = this.entity.atividadesEconomicasSecundarias.indexOf(atividadeEconomica);
    if (index >= 0) {
      this.entity.atividadesEconomicasSecundarias.splice(index, 1);
    }
    this.form.controls['dadosGerais'].get('atividadesEconomicasSecundarias').enable();
    const control = this.form.controls['dadosGerais'].get('atividadesEconomicasSecundarias') as FormArray;
    control.removeAt(index);
  }

  /**
   *
   * @param {MatAutocompleteSelectedEvent} event
   */
  add(event: MatAutocompleteSelectedEvent): void {
    this.entity.atividadesEconomicasPrimarias.push(event.option.value);
    this.atividadesEconomicasPrimarias.nativeElement.value = '';
    this.form.controls['dadosGerais'].get('atividadesEconomicasPrimarias').disable();

    const control = this.form.controls['dadosGerais'].get('atividadesEconomicasPrimarias') as FormArray;
    control.push(this.initName(event.option.value._code));
  }

  /**
   *
   * @param {MatAutocompleteSelectedEvent} event
   */
  addAtividadeSecundaria(event: MatAutocompleteSelectedEvent): void {
    this.entity.atividadesEconomicasSecundarias.push(event.option.value);
    this.atividadesEconomicasSecundarias.nativeElement.value = '';

    this.form.controls['dadosGerais'].get('atividadesEconomicasSecundarias').disable();

    const control = this.form.controls['dadosGerais'].get('atividadesEconomicasSecundarias') as FormArray;
    control.push(this.initName(event.option.value._code));
  }

  /**
   *
   * @param name
   */
  initName(name: string): FormControl {
    return this.fb.control(name);
  }
}
