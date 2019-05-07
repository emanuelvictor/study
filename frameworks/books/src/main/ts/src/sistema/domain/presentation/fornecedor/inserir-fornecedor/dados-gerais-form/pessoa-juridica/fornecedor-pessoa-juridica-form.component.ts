import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {MatSnackBar} from '@angular/material';
import 'rxjs/add/operator/toPromise';
import {textMasks} from '../../../../../../application/controls/text-masks/text-masks';
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {cnpjValidator, obrigatorio} from "../../../../../../application/controls/validators/validators";
import {Fornecedor} from "../../../../../entity/fornecedor/fornecedor.model";
import {enumToArrayString} from "../../../../../../application/utils/utils";
import {TipoPessoaJuridica} from "../../../../../entity/fornecedor/tipo-pessoa-juridica.enum";
import {HttpClient} from "@angular/common/http";
import {MessageService} from "../../../../../services/message.service";
import {AtividadeEconomica} from 'sistema/domain/entity/fornecedor/atividade-economica.model';

@Component({
  selector: 'fornecedor-pessoa-juridica-form',
  templateUrl: './fornecedor-pessoa-juridica-form.component.html',
  styleUrls: ['./fornecedor-pessoa-juridica-form.component.css']
})
export class FornecedorPessoaJuridicaFormComponent implements OnInit, OnDestroy {

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
   */
  constructor(private snackBar: MatSnackBar,
              private messageService: MessageService,
              private http: HttpClient, private fb: FormBuilder) {
  }

  /**
   *
   */
  ngOnInit() {
    const formGroup: FormGroup = new FormGroup({
      cnpj: new FormControl('cnpj', [cnpjValidator(), obrigatorio('CNPJ Obrigatório')]),
      razaoSocial: new FormControl('razaoSocial', [obrigatorio('Razão Social Obrigatória')]),
      inscricaoEstadual: new FormControl('', [obrigatorio('Inscrição Social Obrigatória')]),
      inscricaoMunicipal: new FormControl('', [obrigatorio('Inscrição Municipal Obrigatória')])
    });

    if (!this.form) {
      this.form = this.fb.group({});
    }

    this.form.addControl('pessoaJuridica', formGroup);
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

    let filterCnpj = this.fornecedor.usuario.documento.replace(/\./g, "").replace(/\//g, "").replace(/-/g, "");

    this.http.jsonp(this.url + filterCnpj, 'callback').subscribe((data: any) => {
      if (data.status === 'OK') {
        if (data.nome)
          this.fornecedor.razaoSocial = data.nome;
        if (data.fantasia)
          this.fornecedor.usuario.nome = data.fantasia;
        if (data.telefone)
          this.fornecedor.telefones = [data.telefone];
        if (data.email)
          this.fornecedor.usuario.email = data.email;
        if (this.fornecedor.usuario.email)
          this.fornecedor.emails = [this.fornecedor.usuario.email];

        if (data.atividade_principal)
          data.atividade_principal.forEach( atividade_principal => {
            const a = this.atividadesEconomicas.filter(value => (value as any).code.replace(/[&\/\\#,+()$~%.'":*?<>{}-]/g, '') === atividade_principal.code.replace(/[&\/\\#,+()$~%.'":*?<>{}-]/g, ''));
            if (a.length)
              this.fornecedor.atividadesEconomicasPrimarias.push(a[0])
          });

        const controlAtividadePrimaria = this.form.get('atividadesEconomicasPrimarias') as FormArray;
        this.clearFormArray(controlAtividadePrimaria);
        this.fornecedor.atividadesEconomicasPrimarias.forEach((atividadePrimaria: AtividadeEconomica) => {
          controlAtividadePrimaria.push(this.initName(atividadePrimaria._code))
        });

        if (data.atividades_secundarias)
          data.atividades_secundarias.forEach( atividade_economica => {
            const a = this.atividadesEconomicas.filter(value => (value as any).code.replace(/[&\/\\#,+()$~%.'":*?<>{}-]/g, '') === atividade_economica.code.replace(/[&\/\\#,+()$~%.'":*?<>{}-]/g, ''));
            if (a.length)
              this.fornecedor.atividadesEconomicasSecundarias.push(a[0])
          });

        const controlAtividadeSecundaria = this.form.get('atividadesEconomicasSecundarias') as FormArray;
        this.clearFormArray(controlAtividadeSecundaria);
        this.fornecedor.atividadesEconomicasSecundarias.forEach((atividadeSecundaria: AtividadeEconomica) => {
          controlAtividadeSecundaria.push(this.initName(atividadeSecundaria._code));
        })
      }

    });
  }

  /**
   *
   */
  changeIsencaoInscricaoEstadual() {
    if (this.fornecedor.insencaoInscricaoEstadual) {
      this.fornecedor.inscricaoEstadual = 'ISENTO';
      this.form.controls['pessoaJuridica'].get('inscricaoEstadual').disable()
    } else if (this.fornecedor.inscricaoEstadual === 'ISENTO') {
      this.fornecedor.inscricaoEstadual = null;
      this.form.controls['pessoaJuridica'].get('inscricaoEstadual').enable()
    }
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
   * @param formArray
   */
  clearFormArray = (formArray: FormArray) => {
    while (formArray.length !== 0) {
      formArray.removeAt(0)
    }
  }

}
