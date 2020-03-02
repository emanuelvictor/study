import 'rxjs/add/operator/debounceTime';
import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef, MatSnackBar} from "@angular/material";
import {Patrimonio} from "../../../../../../../../domain/entity/patrimonio/patrimonio.model";
import {PatrimonioRepository} from "../../../../../../../../domain/repository/patrimonio.repository";

// @ts-ignore
@Component({
  selector: 'alterar-localizacao-patrimonio',
  templateUrl: './alterar-localizacao-patrimonio.component.html',
  styleUrls: ['../../../inventarios.scss']
})
export class AlterarLocalizacaoPatrimonioComponent {

  /**
   *
   */
  public patrimonio: Patrimonio = new Patrimonio();

  /**
   *
   * @param patrimonioRepository
   * @param dialogRef
   * @param snackBar
   * @param data
   */
  constructor(private patrimonioRepository: PatrimonioRepository,
              public dialogRef: MatDialogRef<AlterarLocalizacaoPatrimonioComponent>,
              private snackBar: MatSnackBar, @Inject(MAT_DIALOG_DATA) public data: any) {
    this.patrimonio = Object.assign({}, data.patrimonio)
  }

  /**
   *
   * @param patrimonioId
   * @param localizacao
   */
  alterarLocalizacao(patrimonioId: number, localizacao: any) {
      this.patrimonioRepository.alterarLocalizacao(patrimonioId, localizacao)
        .then(result => {
          this.dialogRef.close(result);
          this.openSnackBar('Localização atualizada com sucesso!')
        })
  }

  /**
   *
   */
  excluir(patrimonioId: number) {
    this.patrimonioRepository.delete(patrimonioId)
      .then(() => {
        this.dialogRef.close(true);
        this.openSnackBar('Patrimônio excluído com sucesso!')
      })
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