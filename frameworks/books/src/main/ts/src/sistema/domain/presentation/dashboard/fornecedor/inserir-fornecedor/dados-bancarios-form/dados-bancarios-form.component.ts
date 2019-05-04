import {Component, ElementRef, EventEmitter, Inject, Input, OnInit, Output, Renderer} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material';
import {AbstractControl, FormBuilder, FormGroup, ValidatorFn} from "@angular/forms"
import 'rxjs/add/operator/debounceTime';
import {CrudViewComponent} from "../../../../../../application/controls/crud/crud-view.component";
import {MessageService} from "../../../../../services/message.service";
import {DashboardViewComponent} from "../../../dashboard-view.component";
import {UsuarioRepository} from "../../../../../repository/usuario.repository";
import {Fornecedor} from 'sistema/domain/entity/fornecedor/fornecedor.model';
import {enumToArrayString} from 'sistema/application/utils/utils';
import {FormaPagamento} from 'sistema/domain/entity/fornecedor/forma-pagamento.enum';
import {TipoConta} from 'sistema/domain/entity/fornecedor/tipo-conta.enum';
import {Banco} from 'sistema/domain/entity/fornecedor/banco.model';
import {BancoRepository} from 'sistema/domain/repository/banco.repository';
import {textMasks} from 'sistema/application/controls/text-masks/text-masks';
import {obrigatorio} from "../../../../../../application/controls/validators/validators";
import {DadosRecebimento} from "../../../../../entity/fornecedor/dados-recebimento.model";

@Component({
  selector: 'dados-bancarios-form',
  templateUrl: './dados-bancarios-form.component.html',
  styleUrls: ['../../fornecedor.component.scss']
})
export class DadosBancariosFormComponent extends CrudViewComponent implements OnInit {

  /**
   *
   */
  masks = textMasks;

  /**
   *
   */
  @Input()
  entity: Fornecedor = new Fornecedor();

  /**
   *
   */
  @Input()
  form: FormGroup;

  /**
   *
   */
  @Output()
  formChange: EventEmitter<FormGroup> = new EventEmitter();

  /**
   *
   */
  formasPagamento: FormaPagamento[] = enumToArrayString(FormaPagamento);

  /**
   *
   */
  tiposContas: TipoConta[] = enumToArrayString(TipoConta);

  /**
   *
   */
  bancos: Banco[];

  /**
   *
   * @param router
   * @param snackBar
   * @param activatedRoute
   * @param messageService
   * @param homeView
   * @param bancoRepository
   * @param usuarioRepository
   * @param element
   * @param fb
   * @param renderer
   */
  constructor(public activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: DashboardViewComponent,
              private bancoRepository: BancoRepository,
              private usuarioRepository: UsuarioRepository,
              @Inject(ElementRef) public element: ElementRef,
              public fb: FormBuilder, public renderer: Renderer,
              private router: Router, public snackBar: MatSnackBar) {
    super(snackBar, element, fb, renderer, activatedRoute);
  }

  /**
   *
   */
  populeForm(): void {
    if (!this.form) {
      this.form = this.fb.group({});
    }

    const formGroup = this.fb.group({
      formaPagamento: [{value: ''}, [obrigatorio('Este campo é obrigatório')]],
      tipoConta: [{value: ''}, [obrigatorio('Este campo é obrigatório')]],
      identificacao: [{value: ''}, [obrigatorio('Este campo é obrigatório')]],
      banco: [{value: ''}, [obrigatorio(null, this.selecaoObrigatoria('Nenhum Banco selecionado'))]],
      agencia: [{value: ''}, [obrigatorio('Este campo é obrigatório')]],
      numero: [{value: ''}, [obrigatorio('Este campo é obrigatório')]],
      digito: [{value: ''}, [obrigatorio('Este campo é obrigatório')]]
    });

    this.form.addControl('dadosBancarios', formGroup)
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
    if (!this.entity.dadosRecebimento) {
      this.entity.dadosRecebimento = new DadosRecebimento();
      this.entity.dadosRecebimento.formaPagamento = FormaPagamento[FormaPagamento.DEPOSITO_BANCARIO]
    }

    this.populeForm();

    this.bancoRepository.listByFilters({size: 500})
      .subscribe((result) => {
        this.bancos = result.content;
      })
  }

  /**
   *
   */
  public listBancos() {

    let nomeBanco = this.entity.dadosRecebimento.contaBancaria.banco || null;
    if (DadosBancariosFormComponent.isString(nomeBanco) && !(nomeBanco as any).length) {
      this.bancos = [];
      return;
    }

    this.bancoRepository.listByFilters({defaultFilter: this.entity.dadosRecebimento.contaBancaria.banco})
      .subscribe((result) => {
        this.bancos = result.content;
        if (this.bancos.length && this.entity.dadosRecebimento.contaBancaria.banco)
          if (this.bancos[0].codigo.toLocaleLowerCase() === ((this.entity.dadosRecebimento.contaBancaria.banco.constructor === String) && (this.entity.dadosRecebimento.contaBancaria.banco as any).toLowerCase()))
            this.entity.dadosRecebimento.contaBancaria.banco = this.bancos[0];
      });
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
   * @param object
   */
  public displayNome(object) {
    return object && object.nome ? object.nome : null;
  }

  /**
   *
   * @param entity
   */
  emit(entity: any) {
    this.save.emit(entity);
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

}
