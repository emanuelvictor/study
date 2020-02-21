import {Component, ElementRef, Inject, OnInit, Renderer, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {textMasks} from 'sistema/application/controls/text-masks/text-masks';
import {Fornecedor} from 'sistema/domain/entity/fornecedor/fornecedor.model';
import {RecaptchaComponent} from "ng-recaptcha";
import {cnpjValidator, max, obrigatorio} from "../../../../../../../sistema/application/controls/validators/validators";
import {MatSnackBar} from "@angular/material";
import {FornecedorRepository} from "../../../../../../../sistema/domain/repository/fornecedor.repository";
import {Router} from "@angular/router";
import {MessageService} from "../../../../../../../sistema/domain/services/message.service";
import {AtividadeEconomica} from "../../../../../../../sistema/domain/entity/fornecedor/atividade-economica.model";
import {AtividadeEconomicaRepository} from "../../../../../../../sistema/domain/repository/atividade-economica.repository";
import {debounce} from "../../../../../../../sistema/application/utils/debounce";
import {PageSerialize} from "../../../../../../../sistema/infrastructure/page-serialize/page-serialize";
import {EnderecoRepository} from "../../../../../../../sistema/domain/repository/endereco.repository";
import {Endereco} from "../../../../../../../sistema/domain/entity/endereco/endereco.model";
import {Cidade} from "../../../../../../../sistema/domain/entity/endereco/cidade.model";
import {Estado} from "../../../../../../../sistema/domain/entity/endereco/estado.model";
import {TdLoadingService} from "@covalent/core";

@Component({
  selector: 'cadastrar-fornecedor',
  templateUrl: './cadastrar-fornecedor.component.html',
  styleUrls: ['./cadastrar-fornecedor.component.scss']
})
export class CadastrarFornecedorComponent implements OnInit {

  masks = textMasks;

  fornecedor: Fornecedor = new Fornecedor();

  @ViewChild('reCaptcha')
  reCaptcha: RecaptchaComponent;

  form: FormGroup;

  public debounce = debounce;

  /**
   *
   */
  atividadesEconomicas: AtividadeEconomica[];

  /**
   *
   * @param _loadingService
   * @param enderecoRepository
   * @param router
   * @param element
   * @param renderer'
   * @param snackBar
   * @param fornecedorRepository
   * @param fb
   * @param http
   * @param messageService
   * @param atividadeEconomicaRepository
   */
  constructor(public _loadingService: TdLoadingService,
              private enderecoRepository: EnderecoRepository,
              @Inject(ElementRef) private element: ElementRef,
              private fornecedorRepository: FornecedorRepository,
              private router: Router, private renderer: Renderer,
              private snackBar: MatSnackBar, private fb: FormBuilder,
              private http: HttpClient, public messageService: MessageService,
              private atividadeEconomicaRepository: AtividadeEconomicaRepository) {

    this.fornecedor.telefones.push();
    this.fornecedor.telefones.push()

  }

  /**
   *
   */
  ngOnInit() {
    this.http.get('api/fornecedores/sitekey', {responseType: 'text'})
      .subscribe(result => {
        this.fornecedor.siteKey = result
      });

    this.form = this.fb.group({
      cnpj: ['cnpj', [Validators.required, cnpjValidator()]],
      razaoSocial: ['razaoSocial', [obrigatorio(), max(150)]],
      nomeFantasia: ['nomeFantasia', [obrigatorio(), max(150)]],
      telefone1: ['telefone1', [Validators.required]],
      email: ['email', [Validators.required, Validators.email]],
      recaptchaReactive: new FormControl(null, Validators.required)
    });

    this.atividadeEconomicaRepository.listByFilters({size: 100000})
      .subscribe((result) => {
        this.atividadesEconomicas = result.content
      })
  }


  /**
   *
   * @param {string} captchaResponse
   */
  resolved(captchaResponse: string) {
    this.fornecedor.recap = captchaResponse
  }

  /**
   *
   */
  reset() {
    this.reCaptcha.reset()
  }

  /**
   *
   */
  findCnpj(): void {

    if (!this.form.get('cnpj').valid)
      return;

    this._loadingService.register('overlayStarSyntax');

    const params = PageSerialize.getHttpParamsFromPageable({defaultFilter: this.fornecedor.usuario.documento.replace('.', '').replace('.', '').replace('/', '').replace('-', '')});
    this.http.get<any>('api/fornecedores/exists', {params: params}).subscribe(result => {
      if (result.content.length) {
        this._loadingService.resolve('overlayStarSyntax');
        this.form.get('cnpj').setErrors({exception: 'CNPJ já cadastrado!'});
        return
      } else {
        this.form.get('cnpj').setErrors(null);
        this.findFromWebService()
      }
    })
  }

  /**
   *
   */
  private findFromWebService() {
    const cnpj = this.fornecedor.usuario.documento.replace(/\./g, '').replace(/\//g, '').replace(/-/g, '');

    this.fornecedorRepository.findFornecedorWsExternoByCNPJ(cnpj).toPromise().then((data: any) => {
      if (data.status === 'OK') {
        if (data.nome)
          this.fornecedor.razaoSocial = data.nome;

        if (data.fantasia)
          this.fornecedor.usuario.nome = data.fantasia;
        else
          this.fornecedor.usuario.nome = this.fornecedor.razaoSocial;

        if (data.telefone)
          this.fornecedor.telefones = [data.telefone];

        if (data.email)
          this.fornecedor.usuario.email = data.email;

        if (this.fornecedor.usuario.email)
          this.fornecedor.emails = [this.fornecedor.usuario.email];

        this.fornecedor.endereco = new Endereco();

        if (data.cep)
          this.fornecedor.endereco.cep = data.cep.replace('.', '').replace('.', '').replace('-', '');

        if (data.logradouro)
          this.fornecedor.endereco.logradouro = data.logradouro;

        if (data.numero)
          this.fornecedor.endereco.numero = data.numero;

        if (data.municipio) {
          this.fornecedor.endereco.cidade = new Cidade();
          this.fornecedor.endereco.cidade.nome = data.municipio
        }

        if (data.uf) {
          this.fornecedor.endereco.cidade.estado = new Estado();
          this.fornecedor.endereco.cidade.estado.uf = data.uf
        }

        if (this.fornecedor.endereco.cidade && this.fornecedor.endereco.cidade.nome && this.fornecedor.endereco.cidade.estado.uf)
          this.enderecoRepository.listCidadesByFilters({
            defaultFilter: this.fornecedor.endereco.cidade.nome,
            uf: this.fornecedor.endereco.cidade.estado.uf
          }).subscribe(result => {
            console.log('result ');
            console.log(result);
            this.fornecedor.endereco.cidade = result.content[0]
          });

        if (data.atividade_principal)
          data.atividade_principal.forEach(atividade_principal => {
            const a = this.atividadesEconomicas.filter(value => (value as any).code.replace(/[&\/\\#,+()$~%.'":*?<>{}-]/g, '') === atividade_principal.code.replace(/[&\/\\#,+()$~%.'":*?<>{}-]/g, ''));
            if (a.length)
              this.fornecedor.atividadesEconomicasPrimarias.push(a[0])
          });

        if (data.atividades_secundarias)
          data.atividades_secundarias.forEach(atividade_economica => {
            const a = this.atividadesEconomicas.filter(value => (value as any).code.replace(/[&\/\\#,+()$~%.'":*?<>{}-]/g, '') === atividade_economica.code.replace(/[&\/\\#,+()$~%.'":*?<>{}-]/g, ''));
            if (a.length)
              this.fornecedor.atividadesEconomicasSecundarias.push(a[0])
          })
      }

      this._loadingService.resolve('overlayStarSyntax')
    }).catch(() => this._loadingService.resolve('overlayStarSyntax'))
  }

  /**
   *
   */
  public saveEntity(form: any): void {
    let valid = true;
    const controls: any = [];
    Object.keys(form.controls).map(function (key) {
      if (form.controls[key].invalid) {
        const control = form.controls[key];
        control.key = '#' + key;
        if (control.controls && Object.keys(control.controls).length) {
          Object.keys(control.controls).map(function (key) {
            if (control.controls[key].invalid) {
              const controlInner = control.controls[key];
              controlInner.key = '#' + key;
              controls.push(controlInner);
            }
          });
        } else {
          controls.push(control);
        }
      }
    });

    for (const control of controls) {
      if (control) {
        const element = this.element.nativeElement.querySelector(control.key);
        if (element && control.invalid) {
          this.renderer.invokeElementMethod(element, 'focus', []);
          valid = false;
          break;
        }
        if (control.controls && Object.keys(control.controls).length && control.invalid) {
          valid = this.validateForm(control)
        }
      }
    }

    if (form.valid && valid) {
      this.emit(this.fornecedor);
    }
  }

  validateForm(form: any): boolean {
    let valid = true;
    const controls: any = [];
    Object.keys(form.controls).map(function (key) {
      if (form.controls[key].invalid) {
        const control = form.controls[key];
        control.key = '#' + key;
        if (control.controls && Object.keys(control.controls).length) {
          Object.keys(control.controls).map(function (key) {
            if (control.controls[key].invalid) {
              const controlInner = control.controls[key];
              controlInner.key = '#' + key;
              controls.push(controlInner);
            }
          });
        } else {
          controls.push(control);
        }
      }
    });

    for (const control of controls) {
      if (control) {
        const element = this.element.nativeElement.querySelector(control.key);
        if (element && control.invalid) {
          this.renderer.invokeElementMethod(element, 'focus', []);
          valid = false;
          break;
        }
        if (control.controls && Object.keys(control.controls).length && control.invalid) {
          valid = this.validateForm(control);
        }
      }
    }
    return valid;
  }

  /**
   *
   * @param entity
   */
  emit(entity: Fornecedor) {

    this._loadingService.register('overlayStarSyntax');
    const entityToSave = Object.assign({}, entity);
    entityToSave.telefones = entityToSave.telefones.filter(value => value);

    this.fornecedorRepository.save(entityToSave, entityToSave.recap).then(() => {

      this.reset();
      this.router.navigate(['login'], {queryParams: {realizado: entity.usuario.email}});
      this._loadingService.resolve('overlayStarSyntax')

    }).catch(() => this._loadingService.resolve('overlayStarSyntax'))
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
      return textMasks.phone9
    }
  }

}
