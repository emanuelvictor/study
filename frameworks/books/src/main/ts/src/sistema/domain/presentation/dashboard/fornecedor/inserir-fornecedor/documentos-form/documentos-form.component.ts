import {Component, ElementRef, EventEmitter, Inject, Input, OnInit, Output, Renderer} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material';
import {AbstractControl, FormBuilder, FormControl, FormGroup, ValidatorFn} from "@angular/forms"
import 'rxjs/add/operator/debounceTime';
import {CrudViewComponent} from "../../../../../../application/controls/crud/crud-view.component";
import {MessageService} from "../../../../../services/message.service";
import {DashboardViewComponent} from "../../../dashboard-view.component";
import {UsuarioRepository} from "../../../../../repository/usuario.repository";
import {TipoCadastroRepository} from 'sistema/domain/repository/tipo-cadastro.repository';
import {Fornecedor} from 'sistema/domain/entity/fornecedor/fornecedor.model';
import {Documento} from 'sistema/domain/entity/fornecedor/documento.model';
import {obrigatorio} from "../../../../../../application/controls/validators/validators";
import {TipoCadastro} from "../../../../../entity/tipo-cadastro.model";
import {FornecedorRepository} from "../../../../../repository/fornecedor.repository";
import {TdLoadingService} from "@covalent/core";

@Component({
  selector: 'documentos-form',
  templateUrl: './documentos-form.component.html',
  styleUrls: ['../../fornecedor.component.scss']
})
export class DocumentosFormComponent extends CrudViewComponent implements OnInit {

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
  @Input()
  entity: Fornecedor;

  /**
   *
   */
  tiposCadastros: TipoCadastro[];

  /**
   *
   */
  public pageable: any = {
    page: 0,
    sort: null,
    defaultFilter: [],
    ativoFilter: true
  };

  /**
   *
   * @param activatedRoute
   * @param messageService
   * @param homeView
   * @param _loadingService
   * @param usuarioRepository
   * @param element
   * @param fb
   * @param renderer
   * @param router
   * @param snackBar
   * @param fornecedorRepository
   * @param tipoCadastroRepository
   */
  constructor(public activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: DashboardViewComponent,
              private _loadingService: TdLoadingService,
              private usuarioRepository: UsuarioRepository,
              @Inject(ElementRef) public element: ElementRef,
              public fb: FormBuilder, public renderer: Renderer,
              private fornecedorRepository: FornecedorRepository,
              private router: Router, public snackBar: MatSnackBar,
              private tipoCadastroRepository: TipoCadastroRepository) {
    super(snackBar, element, fb, renderer, activatedRoute);
  }

  /**
   *
   */
  ngOnInit() {

    this._loadingService.register('carregando-documentos');
    let value: number = 10;
    const interval = setInterval(() => {
      this._loadingService.setValue('carregando-documentos', value);
      value = value + 10;
      // if (value > 10) {
      //   clearInterval(interval);
      // }
    }, 0);


    const formGroup: FormGroup = new FormGroup({
      tipoCadastro: new FormControl('tipoCadastro', obrigatorio(null, this.selecaoObrigatoria('Qual é o seu tipo de cadastro?')))
    });

    if (!this.form) {
      this.form = this.fb.group({});
    }

    this.form.addControl('documentos', formGroup);

    this.tipoCadastroRepository.listByFilters(this.pageable)
      .subscribe((result) => {
        this.tiposCadastros = result.content;

        if (this.entity.id)
          this.fornecedorRepository.findAllByFornecedorId(this.entity.id, null).subscribe(result => {
            if (result.content.length) {
              this.entity.documentos = result.content;
              // this.tipoCadastro = result.content[0].tipoCadastroTipoDocumento.tipoCadastro;

              const docsReturn = [];
              this.entity.tipoCadastro.documentos.forEach(tipoCadastroTipoDocumento => {
                const tipoDocumento = tipoCadastroTipoDocumento.tipoDocumento;

                this.entity.documentos.filter(value => value.tipoCadastroTipoDocumento.tipoDocumento.id === tipoDocumento.id)
                  .forEach(value => docsReturn.push(value));

              });

              this.entity.documentos = docsReturn;

            }

            if (this.entity.tipoCadastro)
              this.entity.tipoCadastro.documentos.forEach(tipoCadastroTipoDocumento => {
                if (!this.entity.documentos.filter(value => value.tipoCadastroTipoDocumento.tipoDocumento.id === tipoCadastroTipoDocumento.tipoDocumento.id).length) {
                  if (tipoCadastroTipoDocumento.tipoDocumento.ativo == true) {
                    const documento = new Documento();
                    documento.fornecedor = new Fornecedor(this.entity.id);
                    documento.tipoCadastroTipoDocumento = tipoCadastroTipoDocumento;
                    this.entity.documentos.push(documento);
                  }
                } else
                  this.entity.documentos.filter(value => value.tipoCadastroTipoDocumento.tipoDocumento.id === tipoCadastroTipoDocumento.tipoDocumento.id)
                    .forEach(value => value.tipoCadastroTipoDocumento.obrigatorio = tipoCadastroTipoDocumento.obrigatorio)
              });
            this._loadingService.resolve('carregando-documentos');
            clearInterval(interval)
          });
        else{
          this._loadingService.resolve('carregando-documentos');
          clearInterval(interval)
        }
      });

    if (!this.entity.documentos || !this.entity.documentos.length)
      this.entity.documentos = []
  }

  /**
   *
   * @param o1
   * @param o2
   */
  compareTiposCadastros(o1: any, o2: any): boolean {
    return o1 && o2 && o1.id && o2.id && o1.id === o2.id;
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
  findDocumentosByTipoCadastro() {
    if (this.entity.id)
      this.fornecedorRepository.findAllByFornecedorId(this.entity.id, null).subscribe(result => {
        if (result.content.length)
          this.entity.documentos = result.content;

        const docsReturn = [];
        this.entity.tipoCadastro.documentos.forEach(tipoCadastroTipoDocumento => {
          const tipoDocumento = tipoCadastroTipoDocumento.tipoDocumento;

          this.entity.documentos.filter(value => value.tipoCadastroTipoDocumento.tipoDocumento.id === tipoDocumento.id)
            .forEach(value => docsReturn.push(value));

        });

        this.entity.documentos = docsReturn;

        this.entity.tipoCadastro.documentos.forEach(tipoCadastroTipoDocumento => {
          if (!this.entity.documentos.filter(value => value.tipoCadastroTipoDocumento.tipoDocumento.id === tipoCadastroTipoDocumento.tipoDocumento.id).length) {
            if (tipoCadastroTipoDocumento.tipoDocumento.ativo === true) {

              const documento = new Documento();
              documento.fornecedor = new Fornecedor(this.entity.id);
              documento.tipoCadastroTipoDocumento = tipoCadastroTipoDocumento;
              this.entity.documentos.push(documento);

            }
          } else {
            this.entity.documentos.filter(value => value.tipoCadastroTipoDocumento.tipoDocumento.id === tipoCadastroTipoDocumento.tipoDocumento.id)
              .forEach(value => value.tipoCadastroTipoDocumento.obrigatorio = tipoCadastroTipoDocumento.obrigatorio)
          }
        });

      });
    else {
      this.entity.documentos = [];
      this.entity.tipoCadastro.documentos.forEach(tipoCadastroTipoDocumento => {
        if (tipoCadastroTipoDocumento.tipoDocumento.ativo === true) {
          const documento = new Documento();
          documento.fornecedor = new Fornecedor(this.entity.id);
          documento.tipoCadastroTipoDocumento = tipoCadastroTipoDocumento;
          this.entity.documentos.push(documento);
        }
      });
    }
  }

  /**
   *
   * @param entity
   */
  emit(entity: any) {
    this.save.emit(entity);
  }
}
