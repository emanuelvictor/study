import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {MatSnackBar} from '@angular/material';
import 'rxjs/add/operator/toPromise';
import {textMasks} from '../../../../../../../application/controls/text-masks/text-masks';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {cpfValidator} from "../../../../../../../application/controls/validators/validators";
import {Fornecedor} from "../../../../../../entity/fornecedor/fornecedor.model";
import {StatusFornecedor} from "../../../../../../entity/fornecedor/status-fornecedor.enum";
import {AuthenticationService} from "../../../../../../services/authentication.service";

@Component({
  selector: 'fornecedor-pessoa-fisica-form',
  templateUrl: './fornecedor-pessoa-fisica-form.component.html',
  styleUrls: ['./fornecedor-pessoa-fisica-form.component.css']
})
export class FornecedorPessoaFisicaFormComponent implements OnInit, OnDestroy {

  /**
   *
   */
  @Input()
  form: any;

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
  constructor(private authenticationService: AuthenticationService,
              private snackBar: MatSnackBar, private fb: FormBuilder) {
  }

  /**
   *
   */
  ngOnInit() {
    const formGroup = new FormGroup({
      cpf: new FormControl('cpf', [Validators.required, cpfValidator()]),
    });

    if (StatusFornecedor[this.fornecedor.status] === (StatusFornecedor.APROVADO as any))
      formGroup.disable();

    if (this.fornecedor.usuario.id === this.authenticationService.usuarioAutenticado.id) {
      formGroup.get('cpf').disable()
    }

    if (!this.form) {
      this.form = this.fb.group({});
    }

    this.form.addControl('pessoaFisica', formGroup);
  }

  /**
   *
   */
  ngOnDestroy(): void {
    this.form.removeControl('pessoaFisica');
  }
}
