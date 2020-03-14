import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {MatSnackBar} from '@angular/material';
import 'rxjs/add/operator/toPromise';
import {textMasks} from '../../../../../../../application/controls/text-masks/text-masks';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {cnpjValidator, max, obrigatorio} from "../../../../../../../application/controls/validators/validators";
import {Fornecedor} from "../../../../../../entity/fornecedor/fornecedor.model";
import {enumToArrayString} from "../../../../../../../application/utils/utils";
import {TipoPessoaJuridica} from "../../../../../../entity/fornecedor/tipo-pessoa-juridica.enum";
import {HttpClient} from "@angular/common/http";
import {MessageService} from "../../../../../../services/message.service";
import {AtividadeEconomica} from 'sistema/domain/entity/fornecedor/atividade-economica.model';
import {StatusFornecedor} from "../../../../../../entity/fornecedor/status-fornecedor.enum";
import {FornecedorRepository} from "../../../../../../repository/fornecedor.repository";
import {AuthenticationService} from "../../../../../../services/authentication.service";
import {Endereco} from "../../../../../../entity/endereco/endereco.model";
import {Cidade} from "../../../../../../entity/endereco/cidade.model";
import {Estado} from "../../../../../../entity/endereco/estado.model";
import {EnderecoRepository} from "../../../../../../repository/endereco.repository";
import {TdLoadingService} from "@covalent/core";

@Component({
  selector: 'fornecedor-pessoa-juridica-form',
  templateUrl: './fornecedor-pessoa-juridica-form.component.html',
  styleUrls: ['./fornecedor-pessoa-juridica-form.component.css']
})
export class FornecedorPessoaJuridicaFormComponent implements OnInit, OnDestroy {

  /**
   *
   */
  url: string = 'https://www.receitaws.com.br/v1/cnpj/';

  /**
   *
   */
  @Input() form: any;

  /**
   *
   */
  masks = textMasks;

  /**
   *
   * @type {{}}
   */
  @Input()
  fornecedor: Fornecedor;

  /**
   *
   */
  @Input()
  atividadesEconomicas: AtividadeEconomica[];

  /**
   *
   */
  tiposPessoasJuridicas = enumToArrayString(TipoPessoaJuridica);

  /**
   *
   * @param snackBar
   * @param messageService
   * @param _loadingService
   * @param enderecoRepository
   * @param http
   * @param fb
   * @param fornecedorRepository
   * @param authenticationService
   */
  constructor(private snackBar: MatSnackBar,
              private messageService: MessageService,
              public _loadingService: TdLoadingService,
              private enderecoRepository: EnderecoRepository,
              private http: HttpClient, private fb: FormBuilder,
              private fornecedorRepository: FornecedorRepository,
              private authenticationService: AuthenticationService) {
  }

  /**
   *
   */
  ngOnInit() {
    const formGroup: FormGroup = new FormGroup({
      simplesNacional: new FormControl(this.fornecedor.simplesNacional, []),
      cooperativa: new FormControl(this.fornecedor.cooperativa, []),
      possuiInscricaoEstadual: new FormControl(this.fornecedor.possuiInscricaoEstadual, []),
      tipoPessoaJuridica: new FormControl(this.fornecedor.tipoPessoaJuridica, []),
      cnpj: new FormControl('cnpj', [cnpjValidator(), obrigatorio('CNPJ Obrigatório')]),
      razaoSocial: new FormControl('razaoSocial', [obrigatorio('Razão Social Obrigatória'), max(150)]),
      inscricaoEstadual: new FormControl(this.fornecedor.inscricaoEstadual, [obrigatorio('Inscrição Social obrigatória')]),
      inscricaoMunicipal: new FormControl(this.fornecedor.inscricaoMunicipal, [obrigatorio('Inscrição Municipal obrigatória')])
    });

    if (StatusFornecedor[this.fornecedor.status] === (StatusFornecedor.APROVADO as any))
      formGroup.disable();

    if (!this.form)
      this.form = this.fb.group({});

    this.form.addControl('pessoaJuridica', formGroup);

    this.changeIsencaoInscricaoEstadual()
  }

  /**
   *
   */
  changeIsencaoInscricaoEstadual() {
    if (!this.fornecedor.possuiInscricaoEstadual) {
      this.fornecedor.inscricaoEstadual = 'ISENTO';
      this.form.controls['pessoaJuridica'].get('inscricaoEstadual').disable();
    } else if (this.fornecedor.inscricaoEstadual === 'ISENTO') {
      this.fornecedor.inscricaoEstadual = null;
      if (StatusFornecedor[this.fornecedor.status] === (StatusFornecedor.APROVADO as any))
        this.form.controls['pessoaJuridica'].get('inscricaoEstadual').disable();
      else
        this.form.controls['pessoaJuridica'].get('inscricaoEstadual').enable()
    }
  }

  /**
   *
   */
  ngOnDestroy(): void {
    this.form.removeControl('pessoaJuridica');
  }

  /**
   *
   */
  findCnpj(): void {
    if (!this.form.get('pessoaJuridica').get('cnpj').valid)
      return;

    this._loadingService.register('overlayStarSyntax');

    this.fornecedorRepository.listByFilters({defaultFilter: this.fornecedor.usuario.documento.replace('.', '').replace('.', '').replace('/', '').replace('-', '')})
      .subscribe(result => {
        if (!this.fornecedor.id)
          if (result.content.length) {
            this._loadingService.resolve('overlayStarSyntax');
            this.form.get('pessoaJuridica').get('cnpj').setErrors({exception: 'CNPJ já cadastrado!'});
            return
          } else {
            this.form.get('pessoaJuridica').get('cnpj').setErrors(null);
            this.findFromWebService()
          }
        else
          this.fornecedorRepository.findById(this.fornecedor.id)
            .subscribe(fornecedor => {
              if (result.content.length && result.content[0].usuario.documento !== fornecedor.usuario.documento) {
                this._loadingService.resolve('overlayStarSyntax');
                this.form.get('pessoaJuridica').get('cnpj').setErrors({exception: 'CNPJ já cadastrado!'});
                return
              } else {
                this.form.get('pessoaJuridica').get('cnpj').setErrors(null);
                this.findFromWebService()
              }
            })
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
        this.fornecedor.endereco.cidade = new Cidade();

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
            this.fornecedor.endereco.cidade = result.content[0]
          });

        if (data.atividade_principal) {
          this.fornecedor.atividadesEconomicasPrimarias = [];
          data.atividade_principal.forEach(atividade_principal => {
            const a = this.atividadesEconomicas.filter(value => (value as any).code.replace(/[&\/\\#,+()$~%.'":*?<>{}-]/g, '') === atividade_principal.code.replace(/[&\/\\#,+()$~%.'":*?<>{}-]/g, ''));
            if (a.length)
              this.fornecedor.atividadesEconomicasPrimarias.push(a[0])
          })
        }

        if (data.atividades_secundarias) {
          this.fornecedor.atividadesEconomicasSecundarias = [];
          data.atividades_secundarias.forEach(atividade_economica => {
            const a = this.atividadesEconomicas.filter(value => (value as any).code.replace(/[&\/\\#,+()$~%.'":*?<>{}-]/g, '') === atividade_economica.code.replace(/[&\/\\#,+()$~%.'":*?<>{}-]/g, ''));
            if (a.length)
              this.fornecedor.atividadesEconomicasSecundarias.push(a[0])
          })
        }
      }

      this._loadingService.resolve('overlayStarSyntax')
    }).catch(() => this._loadingService.resolve('overlayStarSyntax'))
  }

}