import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {DomSanitizer} from "@angular/platform-browser";
import {AnexoEmail} from "../../../../../../../entity/fornecedor/email/anexo-email.model";

@Component({
  selector: 'visualizar-anexo-email-dialog',
  templateUrl: 'visualizar-anexo-email-dialog.component.html',
  styleUrls: ['visualizar-anexo-email-dialog.component.scss']
})
export class VisualizarAnexoEmailDialogComponent {

  /**
   * @type {string}
   */
  url: any;

  /**
   *
   */
  anexoEmail: AnexoEmail;

  /**
   * @param _sanitizer
   * @param data
   * @param dialogRef
   */
  constructor(private _sanitizer: DomSanitizer,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private dialogRef: MatDialogRef<VisualizarAnexoEmailDialogComponent>) {
    const urlCreator = window.URL;
    this.anexoEmail = data.anexoEmail;
    // this.anexoEmail.caminho = location.protocol + '//' + location.host + location.pathname + '/api/' + data.anexoEmail.caminho;
    this.anexoEmail.caminho = window.location.origin + '/api/' + data.anexoEmail.caminho;
    this.url = (this._sanitizer.bypassSecurityTrustResourceUrl(urlCreator.createObjectURL(data.anexoEmail.conteudo)) as any);
  }

  /**
   *
   */
  open() {
    window.open(window.URL.createObjectURL(this.anexoEmail.conteudo), '_blank');
  }
}
