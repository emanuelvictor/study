import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {DomSanitizer} from "@angular/platform-browser";
import {Documento} from "../../../../../../../../entity/fornecedor/documento.model";

@Component({
  selector: 'visualizar-documento-dialog',
  templateUrl: 'visualizar-documento-dialog.component.html',
  styleUrls: ['./visualizar-documento-dialog.component.scss']
})
export class VisualizarDocumentoDialogComponent {

  /**
   * @type {string}
   */
  url: any;

  /**
   *
   */
  documento: Documento;

  /**
   * @param _sanitizer
   * @param data
   * @param dialogRef
   */
  constructor(private _sanitizer: DomSanitizer,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private dialogRef: MatDialogRef<VisualizarDocumentoDialogComponent>) {
    const urlCreator = window.URL;
    this.documento = data.documento;
    // this.anexo.caminho = location.protocol + '//' + location.host + location.pathname + '/api/' + data.anexo.caminho;
    this.documento.caminho = window.location.origin + '/api/' + data.documento.caminho;
    console.log(this.documento.caminho);
    this.url = (this._sanitizer.bypassSecurityTrustResourceUrl(urlCreator.createObjectURL(data.documento.conteudo)) as any);
  }
}
