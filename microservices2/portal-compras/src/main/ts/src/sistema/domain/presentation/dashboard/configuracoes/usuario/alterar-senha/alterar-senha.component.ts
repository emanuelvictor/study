import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef, MatSnackBar} from '@angular/material';
import {UsuarioRepository} from "../../../../../repository/usuario.repository";

@Component({
  selector: 'alterar-senha',
  templateUrl: './alterar-senha.component.html',
  styleUrls: ['./alterar-senha.component.css']
})
export class AlterarSenhaComponent {

  /**
   *
   */
  newPassword: string;

  /**
   *
   */
  newPasswordConfirm: string;

  /**
   *
   */
  usuario: any;

  /**
   *
   * @param {MatSnackBar} snackBar
   * @param {UsuarioRepository} usuarioRepository
   * @param data
   * @param {MatDialogRef<AlterarSenhaComponent>} dialogRef
   */
  constructor(private snackBar: MatSnackBar,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private usuarioRepository: UsuarioRepository,
              private dialogRef: MatDialogRef<AlterarSenhaComponent>) {
    this.usuario = data
  }

  /**
   *
   * @param event
   */
  public alterarMinhaSenha(event: Event): void {
    event.preventDefault();
    this.usuarioRepository.changePassword(this.usuario, this.newPassword)
      .then(result => {
        if (result) {
          this.dialogRef.close();
          this.openSnackBar('Senha alterada com sucesso');
        }
      })
  }

  /**
   *
   * @param message
   */
  openSnackBar(message: string) {
    this.snackBar.open(message, 'Fechar', {
      duration: 5000
    });
  }
}
