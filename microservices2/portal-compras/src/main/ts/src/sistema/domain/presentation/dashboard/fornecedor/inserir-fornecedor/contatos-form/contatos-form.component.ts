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
import {MatChipInputEvent, MatSnackBar} from '@angular/material';
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
import {max, obrigatorio} from "../../../../../../application/controls/validators/validators";
import {StatusFornecedor} from "../../../../../entity/fornecedor/status-fornecedor.enum";
import {Endereco} from "../../../../../entity/endereco/endereco.model";
import {MatAutocompleteSelectedEvent} from "@angular/material/typings/autocomplete";
import {Subject} from "rxjs";

@Component({
  selector: 'contatos-form',
  templateUrl: './contatos-form.component.html',
  styleUrls: ['../../fornecedor.component.scss']
})
export class ContatosFormComponent implements OnInit {

  @Output()
  entityChange = new EventEmitter();

  @Input()
  entity: Fornecedor;

  @Output()
  save = new EventEmitter();

  @Input()
  form: FormGroup;

  @Output()
  formChange: EventEmitter<FormGroup> = new EventEmitter();

  paises: Pais[] = [];

  estados: Estado[] = [];

  cidades: Cidade[] = [];

  masks = textMasks;

  @ViewChild('chipListTelefones') chipListTelefones;

  @ViewChild('chipListEmails') chipListEmails;

  iterableDiffer: any;

  /**
   *
   * @type {Subject<string>}
   */
  public paisModelChanged: Subject<string> = new Subject<string>();

  /**
   *
   * @type {Subject<string>}
   */
  public paisVerifyChanged: Subject<string> = new Subject<string>();

  /**
   *
   * @type {Subject<string>}
   */
  public estadoModelChanged: Subject<string> = new Subject<string>();

  /**
   *
   * @type {Subject<string>}
   */
  public estadoVerifyChanged: Subject<string> = new Subject<string>();

  /**
   *
   * @type {Subject<string>}
   */
  public cidadeModelChanged: Subject<string> = new Subject<string>();

  /**
   *
   * @type {Subject<string>}
   */
  public cidadeVerifyChanged: Subject<string> = new Subject<string>();

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
  ngOnInit() {
    if (this.entity.id != null) {
      this.entity.emails.push(this.entity.emails[this.entity.emails.length - 1]);
      for (let i = this.entity.emails.length - 1; i >= 0; i--) {
        this.entity.emails[i] = this.entity.emails[i - 1];
      }
      this.entity.emails[0] = this.entity.usuario.email;
    }

    if (!this.entity.endereco || (this.entity.endereco && !this.entity.endereco.cidade)) {
      this.entity.endereco = new Endereco();
      this.entity.endereco.cidade = new Cidade();
    }

    this.paisModelChanged.debounceTime(300).subscribe(() => this.listPaises());

    this.paisVerifyChanged.debounceTime(300).subscribe(() => this.paisChange());

    this.estadoModelChanged.debounceTime(300).subscribe(() => this.listEstados());

    this.estadoVerifyChanged.debounceTime(300).subscribe(() => this.estadoChange());

    this.cidadeModelChanged.debounceTime(300).subscribe(() => this.listCidades());

    this.cidadeVerifyChanged.debounceTime(300).subscribe(() => this.cidadeChange());

    this.populeForm()
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
      logradouro: new FormControl('', obrigatorio('O logradouro é obrigatório')),
      numero: new FormControl('', [obrigatorio('O número é obrigatório'), max(6)]),
      caixaPostal: new FormControl(''),
      pais: new FormControl('', this.paisObrigatorio('O país é obrigatório')),
      estado: new FormControl({
          value: this.entity.endereco.cidade.estado.nome,
          disabled: false
        }, this.estadoObrigatorio('O estado é obrigatório')
      ),
      cidade: new FormControl({
          value: this.entity.endereco.cidade.nome,
          disabled: false
        }, this.cidadeObrigatoria('A cidade é obrigatória')
      ),
      site: new FormControl(''),
      complemento: new FormControl(''),
      telefones: this.fb.array(this.entity.telefones, [obrigatorio('Insira ao menos um Telefone')]),
      emails: this.fb.array(this.entity.emails, [this.umEmailObrigatorio('Insira ao menos um E-mail')]),
    });

    if (StatusFornecedor[this.entity.status] === (StatusFornecedor.APROVADO as any))
      formGroup.disable();

    this.form.addControl('contatos', formGroup);

    this.form.controls['contatos'].get('telefones').statusChanges.subscribe(status =>
      this.chipListTelefones.errorState = status === 'INVALID');

    this.form.controls['contatos'].get('emails').statusChanges.subscribe(status =>
      this.chipListEmails.errorState = status === 'INVALID');

    this.entity.aprovado = StatusFornecedor[this.entity.status] === (StatusFornecedor.APROVADO as any);
  }

  /**
   *
   * @param exception
   * @param validatorFn
   */
  public umEmailObrigatorio(exception?: string, validatorFn?: ValidatorFn): ValidatorFn {
    if (validatorFn)
      return validatorFn;
    return (c: AbstractControl): { [key: string]: any } => {

      if (!c.value || !c.value.length) return {
        exception: exception ? exception : 'Campo obrigatório'
      };

      for (let i = 0; i < c.value; i++) {
        if (!c.value[i].match('[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$')) {
          return {
            exception: exception ? exception : "E-mail inválido, utilize o formato 'email@email.com'"
          }
        }
      }

      return null
    }
  }

  /**
   *
   * @param exception
   */
  paisObrigatorio(exception?: string): ValidatorFn {
    return (c: AbstractControl): { [key: string]: any } => {
      if (c.value && c.value.id) {
        return null;
      }

      if (this.paises.filter(a => a.nome === c.value).length) {
        return null;
      } else if (this.paises.length) {
        return {
          exception: exception ? exception : 'Campo obrigatório'
        }
      }

      if (this.entity.endereco && this.entity.endereco.cidade && this.entity.endereco.cidade.estado.pais.id)
        return null;

      return {
        exception: exception ? exception : 'Campo obrigatório'
      }
    }
  }

  /**
   *
   */
  async paisChange() {

    this.paises = (await this.enderecoRepository.listPaisesByFilters({defaultFilter: this.entity.endereco.cidade.estado.pais.nome}).toPromise()).content;

    if (this.paises.length && this.entity.endereco.cidade.estado.pais.nome)
      for (let i = 0; i < this.paises.length; i++) {
        if (this.paises[i].nome.toLocaleLowerCase() === (this.entity.endereco.cidade.estado.pais.nome as any).toLowerCase()) {
          return
        }
      }

    this.entity.endereco.cidade.estado.pais.id = null
  }

  /**
   *
   * @param exception
   */
  estadoObrigatorio(exception?: string): ValidatorFn {
    return (c: AbstractControl): { [key: string]: any } => {
      if (c.value && c.value.id) {
        return null;
      }

      if (this.estados.filter(a => a.nome === c.value).length) {
        return null;
      } else if (this.estados.length) {
        return {
          exception: exception ? exception : 'Campo obrigatório'
        }
      }

      if (this.entity.endereco && this.entity.endereco.cidade && this.entity.endereco.cidade.estado.id)
        return null;

      return {
        exception: exception ? exception : 'Campo obrigatório'
      };
    }
  }

  /**
   *
   */
  async estadoChange() {

    this.estados = (await this.enderecoRepository.listEstadosByFilters({
      size: 1000,
      defaultFilter: this.entity.endereco.cidade.estado.nome,
      paisId: this.entity.endereco.cidade.estado.pais.id
    }).toPromise()).content;

    if (this.estados.length && this.entity.endereco.cidade.estado.nome)
      for (let i = 0; i < this.estados.length; i++) {
        if (this.estados[i].nome.toLocaleLowerCase() === (this.entity.endereco.cidade.estado.nome as any).toLowerCase()) {
          return
        }
      }

    this.entity.endereco.cidade.estado.id = null
  }

  /**
   *
   * @param exception
   */
  cidadeObrigatoria(exception?: string): ValidatorFn {
    return (c: AbstractControl): { [key: string]: any } => {
      if (c.value && c.value.id) {
        return null;
      }

      if (this.cidades.filter(a => a.nome === c.value).length) {
        return null;
      }

      if (this.entity.endereco && this.entity.endereco.cidade && this.entity.endereco.cidade.id)
        return null;

      return {
        exception: exception ? exception : 'Campo obrigatório'
      };
    }
  }

  /**
   *
   */
  async cidadeChange() {

    this.cidades = (await this.enderecoRepository.listCidadesByFilters({
      size: 1000,
      defaultFilter: this.entity.endereco.cidade.nome,
      estadoId: this.entity.endereco.cidade.estado.id
    }).toPromise()).content;

    if (this.cidades.length && this.entity.endereco.cidade.nome)
      for (let i = 0; i < this.cidades.length; i++) {
        if (this.cidades[i].nome.toLocaleLowerCase() === (this.entity.endereco.cidade.nome as any).toLowerCase()) {
          return
        }
      }

    this.entity.endereco.cidade.id = null
  }

  /**
   *
   * @param model
   * @param $event
   */
  public parseOptionSelected(model, $event: MatAutocompleteSelectedEvent) {
    switch (model) {
      case 'pais':
        this.paises.forEach(pais => {
          if (pais.id === this.entity.endereco.cidade.estado.pais.id) {
            this.entity.endereco.cidade.estado.pais = pais;
            this.entity.endereco.cidade.estado.pais.nome = $event.option.viewValue;
            pais.nome = $event.option.viewValue;
            $event.option.value = $event.option.viewValue
          }
        });
        if (!ContatosFormComponent.isString(this.entity.endereco.cidade.estado.pais.nome))
          this.entity.endereco.cidade.estado.pais = <any>this.entity.endereco.cidade.estado.pais.nome;
        this.form.controls['contatos'].get('estado').enable();
        break;
      case 'estado':
        this.estados.forEach(estado => {
          if (estado.id === this.entity.endereco.cidade.estado.id) {
            this.entity.endereco.cidade.estado = estado;
            this.entity.endereco.cidade.estado.nome = $event.option.viewValue;
            estado.nome = $event.option.viewValue;
            $event.option.value = $event.option.viewValue
          }
        });
        if (!ContatosFormComponent.isString(this.entity.endereco.cidade.estado.nome))
          this.entity.endereco.cidade.estado = <any>this.entity.endereco.cidade.estado.nome;
        this.form.controls['contatos'].get('estado').enable();
        this.form.controls['contatos'].get('cidade').enable();
        break;
      case 'cidade':
        this.cidades.forEach(cidade => {
          if (cidade.id === this.entity.endereco.cidade.id) {
            this.entity.endereco.cidade = cidade;
            this.entity.endereco.cidade.nome = $event.option.viewValue;
            cidade.nome = $event.option.viewValue;
            $event.option.value = $event.option.viewValue
          }
        });
        if (!ContatosFormComponent.isString(this.entity.endereco.cidade.nome))
          this.entity.endereco.cidade = <any>this.entity.endereco.cidade.nome;
        break;
    }
  }

  /**
   *
   */
  public listPaises() {

    this.paisHandler();

    this.enderecoRepository.listPaisesByFilters({defaultFilter: this.entity.endereco.cidade.estado.pais.nome})
      .subscribe((result) => {
        this.paises = result.content;
        if (this.paises.length && this.entity.endereco.cidade.estado.pais.nome)
          if (this.paises[0].nome.toLocaleLowerCase() === (this.entity.endereco.cidade.estado.pais.nome as any).toLowerCase()) {
            this.entity.endereco.cidade.estado.pais = this.paises[0];
            this.form.controls['contatos'].get('estado').enable();
            this.form.controls['contatos'].get('cidade').disable()
          }
      })
  }

  /**
   *
   */
  public paisHandler() {
    const pais = this.entity.endereco.cidade.estado.pais;
    this.entity.endereco.cidade = new Cidade();
    this.entity.endereco.cidade.estado.pais = pais;
    this.form.controls['contatos'].get('estado').disable();
    this.form.controls['contatos'].get('cidade').disable()
  }

  /**
   *
   */
  public listEstados() {

    this.estadoHandler();

    if (this.entity.endereco.cidade.estado.pais.nome != null) {
      this.enderecoRepository.listEstadosByFilters({
        size: 1000,
        defaultFilter: this.entity.endereco.cidade.estado.nome,
        paisId: this.entity.endereco.cidade.estado.pais.id
      })
        .subscribe((result) => {
          this.estados = result.content;
          if (this.estados.length && this.entity.endereco.cidade.estado.nome)
            if (this.estados[0].nome.toLocaleLowerCase() === (this.entity.endereco.cidade.estado.nome as any).toLowerCase()) {
              this.entity.endereco.cidade.estado = this.estados[0];
              this.form.controls['contatos'].get('cidade').enable()
            }
        })
    }
  }

  /**
   *
   */
  public estadoHandler() {
    const estado = this.entity.endereco.cidade.estado;
    this.entity.endereco.cidade = new Cidade();
    this.entity.endereco.cidade.estado = estado;
    this.form.controls['contatos'].get('cidade').disable()
  }

  /**
   *
   */
  public listCidades() {

    if (this.entity.endereco.cidade.estado.nome != null) {
      this.enderecoRepository.listCidadesByFilters({
        size: 1000,
        defaultFilter: this.entity.endereco.cidade.nome,
        estadoId: this.entity.endereco.cidade.estado.id
      })
        .subscribe((result) => {
          this.cidades = result.content;
          if (this.cidades.length && this.entity.endereco.cidade.nome)
            if (this.cidades[0].nome.toLocaleLowerCase() === (this.entity.endereco.cidade.nome as any).toLowerCase())
              this.entity.endereco.cidade = this.cidades[0];
        });
    }
  }

  /**
   *
   * @param object
   */
  public displayNome(object) {
    // console.log(object);
    if (object)
      if (ContatosFormComponent.isString(object))
        return object;

    // console.log(object);
    if (object && object.nome)
      return object.nome;
    else return null;
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
    const numbers = rawValue.match(/\d/g);
    let numberLength = 0;
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
            this.entity.endereco.cidade.estado = result.content[0].estado;
            this.entity.endereco.cidade.estado.pais = result.content[0].estado.pais;
          });
      });
  }

  /**
   *
   * @param message
   */
  public error(message: string) {
    this.openSnackBar(message)
  }

  /**
   *
   * @param message
   */
  public openSnackBar(message: string) {
    this.snackBar.open(message, 'Fechar', {
      duration: 5000
    })
  }
}
