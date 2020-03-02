import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {UsuarioRepository} from "../../../../../../domain/repository/usuario.repository";
import {AbstractControl, FormBuilder, ValidatorFn} from "@angular/forms";
import {UsuarioService} from "../../../../../../domain/services/usuario.service";
import {MessageService} from "../../../../../../domain/services/message.service";

// @ts-ignore
@Component({
  selector: 'alterar-senha',
  templateUrl: './alterar-senha.component.html',
  styleUrls: ['./alterar-senha.component.css']
})
export class AlterarSenhaComponent {

  /**
   *
   */
  public usuarioDialog: any = {
    novaSenha: '',
    confirmarNovaSenha: ''
  };

  public form: any;

  /**
   *
   * @param dialogRef
   * @param data
   * @param usuarioService
   * @param messageService
   * @param usuarioRepository
   * @param fb
   */
  constructor(public usuarioService: UsuarioService,
              public messageService: MessageService,
              private usuarioRepository: UsuarioRepository,
              public dialogRef: MatDialogRef<AlterarSenhaComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any, public fb: FormBuilder) {
  }


  /**
   *
   */
  ngOnInit() {
    this.form = this.fb.group({
      novaSenha: ['novaSenha', [this.novaSenhaValidator()]],
      confirmarNovaSenha: ['confirmarNovaSenha', [this.confirmarSenhaValidator()]],
    });
  }

  /**
   *
   * @param exception
   * @param validatorFn
   */
  public novaSenhaValidator(exception?: string, validatorFn?: ValidatorFn): ValidatorFn {
    if (validatorFn)
      return validatorFn;
    return (c: AbstractControl): { [key: string]: any } => {

      if (!c.value || !c.value.length) return {
        exception: exception ? exception : 'Defina uma a nova senha'
      };

      for (let i = 0; i < c.value; i++) {
        if (!c.value[i].match('/^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&+,.])[A-Za-z\\d$@$!%*#?&+,.]{8,}$/')) {
          return {
            exception: exception ? exception : 'Mínimo de 8 caracteres com letras, números e caracteres especiais'
          }
        }
      }

      return null
    }
  }

  /**
   *
   * @param exception
   * @param validatorFn
   */
  public confirmarSenhaValidator(exception?: string, validatorFn?: ValidatorFn): ValidatorFn {
    if (validatorFn)
      return validatorFn;
    return (c: AbstractControl): { [key: string]: any } => {

      if (!c.value || !c.value.length) return {
        exception: exception ? exception : 'Confirme a nova senha'
      };

      if (this.usuarioDialog.novaSenha !== c.value)
        return {
          exception: exception ? exception : 'A nova senha e a confirmação não coincidem'
        };

      return null
    }
  }


  /**
   *
   * @param form
   */
  public updateSenhaUsuario(form) {

    if (form.invalid) {
      return
    }

    const {novaSenha} = this.usuarioDialog;

    this.usuarioRepository.changePassword(this.data.usuario, novaSenha)
      .then(result => {
        if (result) {
          this.dialogRef.close();
          this.messageService.toastSuccess('Senha atualizada com sucesso');
        }
      })
  }
}
