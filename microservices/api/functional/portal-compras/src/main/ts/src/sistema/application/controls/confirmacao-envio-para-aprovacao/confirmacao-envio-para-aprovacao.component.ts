import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

/**
 *
 */
@Component({
  selector: 'confirmacao-envio-para-aprovacao',
  templateUrl: 'confirmacao-envio-para-aprovacao.component.html',
  styleUrls: ['./confirmacao-envio-para-aprovacao.component.scss']
})
export class ConfirmacaoEnvioParaAprovacaoComponent {

  /**
   *
   * @param dialogRef
   * @param data
   */
  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<ConfirmacaoEnvioParaAprovacaoComponent>) {
  }
}
