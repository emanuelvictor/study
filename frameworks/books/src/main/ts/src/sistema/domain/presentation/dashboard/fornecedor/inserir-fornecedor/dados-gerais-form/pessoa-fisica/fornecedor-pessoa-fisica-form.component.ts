import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {MatSnackBar} from '@angular/material';
import 'rxjs/add/operator/toPromise';
import {textMasks} from '../../../../../../../application/controls/text-masks/text-masks';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {cpfValidator} from "../../../../../../../application/controls/validators/validators";
import {Fornecedor} from "../../../../../../entity/fornecedor/fornecedor.model";

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
  constructor(public snackBar: MatSnackBar, public fb: FormBuilder) {
  }

  /**
   *
   */
  ngOnInit() {
    const formGroup = new FormGroup({
      cpf: new FormControl('cpf', [Validators.required, cpfValidator()]),
    });
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
