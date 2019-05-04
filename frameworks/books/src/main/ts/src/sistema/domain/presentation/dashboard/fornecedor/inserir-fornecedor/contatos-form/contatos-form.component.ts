import {
  Component,
  ElementRef,
  EventEmitter,
  Inject,
  Input,
  IterableDiffers,
  OnInit,
  Output,
  Renderer,
  ViewChild
} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatAutocompleteSelectedEvent, MatChipInputEvent, MatSnackBar} from '@angular/material';
import {AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms"
import 'rxjs/add/operator/debounceTime';
import {MessageService} from "../../../../../services/message.service";
import {DashboardViewComponent} from "../../../dashboard-view.component";
import {Pais} from 'sistema/domain/entity/endereco/pais.model';
import {EnderecoRepository} from 'sistema/domain/repository/endereco.repository';
import {Estado} from 'sistema/domain/entity/endereco/estado.model';
import {Cidade} from 'sistema/domain/entity/endereco/cidade.model';
import {Fornecedor} from 'sistema/domain/entity/fornecedor/fornecedor.model';
import {textMasks} from 'sistema/application/controls/text-masks/text-masks';
import {HttpClient} from '@angular/common/http';
import {obrigatorio} from "../../../../../../application/controls/validators/validators";
import {AtividadeEconomica} from "../../../../../entity/fornecedor/atividade-economica.model";

@Component({
  selector: 'contatos-form',
  templateUrl: './contatos-form.component.html',
  styleUrls: ['../../fornecedor.component.scss']
})
export class ContatosFormComponent implements OnInit {

  @Input()
  entity: Fornecedor;

  @Output()
  save = new EventEmitter();

  @Input()
  form: FormGroup;

  @Output()
  formChange: EventEmitter<FormGroup> = new EventEmitter();

  paises: Pais[] = [];

  pais: Pais;

  estados: Estado[] = [];

  estado: Estado;

  cidades: Cidade[] = [];

  masks = textMasks;

  @ViewChild('chipListTelefones') chipListTelefones;

  @ViewChild('chipListEmails') chipListEmails;

  iterableDiffer: any;

  /**
   *
   * @param router
   * @param snackBar
   * @param activatedRoute
   * @param messageService
   * @param homeView
   * @param enderecoRepository
   * @param element
   * @param fb
   * @param renderer
   * @param http
   * @param _iterableDiffers
   */
  constructor(private router: Router,
              public snackBar: MatSnackBar,
              public activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: DashboardViewComponent,
              private enderecoRepository: EnderecoRepository,
              @Inject(ElementRef) public element: ElementRef,
              public fb: FormBuilder, public renderer: Renderer,
              private http: HttpClient, private _iterableDiffers: IterableDiffers) {

    this.iterableDiffer = this._iterableDiffers.find([]).create(null);

  }

  /**
   *
   */
  populeForm(): void {
    if (!this.form) {
      this.form = this.fb.group({});
    }

    const formGroup: FormGroup = new FormGroup({
      cep: new FormControl('', obrigatorio('O CEP é obrigatório')),
      logradouro: new FormControl('', obrigatorio('O Logradouro é obrigatório')),
      numero: new FormControl('', obrigatorio('O Número do endereço é obrigatório')),
      caixaPostal: new FormControl(''),
      pais: new FormControl('', obrigatorio(null, this.selecaoObrigatoria('Nenhum País selecionado'))),
      estado: new FormControl('', obrigatorio(null, this.selecaoObrigatoria('Nenhum Estado selecionado'))),
      cidade: new FormControl('', obrigatorio(null, this.selecaoObrigatoria('Nenhum Cidade selecionado'))),
      site: new FormControl(''),
      telefones: this.fb.array(this.entity.telefones, [obrigatorio('Insira ao menos um Telefone')]),
      emails: this.fb.array(this.entity.emails, [obrigatorio('Insira ao menos um E-mail'), Validators.pattern('[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$')]),
    });

    this.form.addControl('contatos', formGroup);

    this.form.controls['contatos'].get('telefones').statusChanges.subscribe(status =>
      this.chipListTelefones.errorState = status === 'INVALID');

    this.form.controls['contatos'].get('emails').statusChanges.subscribe(status =>
      this.chipListEmails.errorState = status === 'INVALID');
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
    if (this.entity.id != null) {
      if (this.entity.endereco.cidade) {
        // this.cidade = this.entity.endereco.cidade;
        this.estado = this.entity.endereco.cidade.estado;
        this.pais = this.entity.endereco.cidade.estado.pais;
      }

      this.entity.emails.push(this.entity.emails[this.entity.emails.length - 1]);
      for (let i = this.entity.emails.length - 1; i >= 0; i--) {
        this.entity.emails[i] = this.entity.emails[i - 1];
      }
      this.entity.emails[0] = this.entity.usuario.email;
    }

    this.populeForm()
  }

  /**
   *
   */
  public listPaises() {
    if (ContatosFormComponent.isString(this.pais)) {
      this.cidades = null;
      this.estados = null;
      this.entity.endereco.cidade = null;
      this.estado = null;
    }

    let nomePais = this.pais || null;
    if (ContatosFormComponent.isString(nomePais) && !(nomePais as any).length) {
      this.paises = [];
      return;
    }

    this.enderecoRepository.listPaisesByFilters({defaultFilter: this.pais})
      .subscribe((result) => {
        this.paises = result.content;
        if (this.paises.length && this.pais)
          if (this.paises[0].nome.toLocaleLowerCase() === ((this.pais.constructor === String) && (this.pais as any).toLowerCase()))
            this.pais = this.paises[0];
      });
  }

  /**
   *
   */
  public listEstados() {
    if (ContatosFormComponent.isString(this.estado)) {
      this.cidades = null;
      this.entity.endereco.cidade = null;
    }

    let nomeEstado = this.estado || null;
    if (ContatosFormComponent.isString(nomeEstado) && !(nomeEstado as any).length) {
      this.estados = [];
      return;
    }

    if (this.pais != null) {
      this.enderecoRepository.listEstadosByFilters({size: 1000, defaultFilter: this.estado, paisId: this.pais.id})
        .subscribe((result) => {
          this.estados = result.content;
          if (this.estados.length && this.estado)
            if (this.estados[0].nome.toLocaleLowerCase() === ((this.estado.constructor === String) && (this.estado as any).toLowerCase()))
              this.estado = this.estados[0];
        });
    }
  }

  /**
   *
   */
  public listCidades() {
    let nomeCidade = this.entity.endereco.cidade || null;
    if (ContatosFormComponent.isString(nomeCidade) && !(nomeCidade as any).length) {
      this.cidades = [];
      return;
    }

    if (this.estado != null) {
      this.enderecoRepository.listCidadesByFilters({
        size: 1000,
        defaultFilter: this.entity.endereco.cidade,
        estadoId: this.estado.id
      })
        .subscribe((result) => {
          this.cidades = result.content;
          if (this.cidades.length && this.entity.endereco.cidade)
            if (this.cidades[0].nome.toLocaleLowerCase() === ((this.entity.endereco.cidade.constructor === String) && (this.entity.endereco.cidade as any).toLowerCase()))
              this.entity.endereco.cidade = this.cidades[0];
        });
    }
  }

  /**
   *
   */
  public normalizeCidade() {
    if (ContatosFormComponent.isString(this.entity.endereco.cidade)) {
      this.entity.endereco.cidade = null;
    }
  }

  /**
   *
   * @param object
   */
  public displayNome(object) {
    return object && object.nome ? object.nome : null;
  }

  /**
   *
   * @param value
   */
  public static isString(value): boolean {
    return typeof value === 'string';
  }

  /**
   *
   * @param name
   */
  initName(name: string): FormControl {
    return this.fb.control(name);
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
   * @param event
   */
  addTelefone(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      this.entity.telefones.push(value);
    }

    if (input) {
      input.value = '';
    }

    this.form.controls['contatos'].get('telefones').disable();
    const control = this.form.controls['contatos'].get('telefones') as FormArray;
    control.push(this.initName(event.value));
  }

  /**
   *
   * @param telefone
   */
  removeTelefone(telefone: string): void {
    const index = this.entity.telefones.indexOf(telefone);
    if (index >= 0) {
      this.entity.telefones.splice(index, 1);
    }

    this.form.controls['contatos'].get('telefones').enable();
    const control = this.form.controls['contatos'].get('telefones') as FormArray;
    control.removeAt(index);
  }

  /**
   *
   * @param event
   */
  addEmail(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      this.entity.emails.push(value);
      const index = this.entity.emails.indexOf(value);
      if (index == 0) {
        this.entity.usuario.email = value;
      }

    }

    if (input) {
      input.value = '';
    }

    this.form.controls['contatos'].get('emails').disable();
    const control = this.form.controls['contatos'].get('emails') as FormArray;
    control.push(this.initName(event.value));
  }

  /**
   *
   * @param email
   */
  removeEmail(email: string): void {

    const index = this.entity.emails.indexOf(email);

    if (index >= 0) {
      this.entity.emails.splice(index, 1);
    }

    if (email === this.entity.usuario.email) {
      delete this.entity.usuario.email;
    }

    if (this.entity.emails.length)
      this.entity.usuario.email = this.entity.emails[0];

    this.form.controls['contatos'].get('emails').enable();
    const control = this.form.controls['contatos'].get('emails') as FormArray;
    control.removeAt(index);
  }

  /**
   *
   * @param rawValue
   */
  public phoneMask = function (rawValue) {
    var numbers = rawValue.match(/\d/g);
    var numberLength = 0;
    if (numbers) {
      numberLength = numbers.join('').length;
    }
    if (numberLength <= 10) {
      return textMasks.phone8;
    } else {
      return textMasks.phone9;
    }
  };

  /**
   *
   * @param cep
   */
  findByCep(cep: string) {
    return this.http.jsonp(`https://viacep.com.br/ws/${cep}/json/`, 'callback')
      .subscribe((data: any) => {
        this.entity.endereco.logradouro = data.logradouro;

        this.enderecoRepository.listCidadesByFilters({
          size: 4,
          defaultFilter: data.localidade,
          estadoId: null,
          uf: data.uf
        })
          .subscribe((result) => {
            this.entity.endereco.cidade = result.content[0];
            this.estado = result.content[0].estado;
            this.pais = result.content[0].estado.pais;
            this.entity.endereco.cidade = result.content[0];
          });
      });
  }

  /**
   *
   * @param message
   */
  public error(message: string) {
    this.openSnackBar(message);
  }

  /**
   *
   * @param message
   */
  public openSnackBar(message: string) {
    this.snackBar.open(message, 'Fechar', {
      duration: 5000
    });
  }

  /**
   *
   */
  clearFormArray = (formArray: FormArray) => {
    if (formArray != null) {
      while (formArray.length !== 0) {
        formArray.removeAt(0)
      }
    }
  }

}

