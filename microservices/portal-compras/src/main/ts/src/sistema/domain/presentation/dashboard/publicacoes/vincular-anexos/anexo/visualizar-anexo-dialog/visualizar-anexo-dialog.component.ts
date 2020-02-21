import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {DomSanitizer} from "@angular/platform-browser";
import {Anexo} from "../../../../../../entity/publicacao/anexo.model";

@Component({
  selector: 'visualizar-anexo-dialog',
  templateUrl: 'visualizar-anexo-dialog.component.html',
  styleUrls: ['visualizar-anexo-dialog.component.scss']
})
export class VisualizarAnexoDialogComponent {

  /**
   * @type {string}
   */
  url: any;

  /**
   *
   */
  anexo: Anexo;

  /**
   * @param _sanitizer
   * @param data
   * @param dialogRef
   */
  constructor(private _sanitizer: DomSanitizer,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private dialogRef: MatDialogRef<VisualizarAnexoDialogComponent>) {
    const urlCreator = window.URL;
    this.anexo = data.anexo;
    // this.anexo.caminho = location.protocol + '//' + location.host + location.pathname + '/api/' + data.anexo.caminho;
    // this.anexo.caminho = window.location.origin + '/api/' + data.anexo.caminho;
    this.url = (this._sanitizer.bypassSecurityTrustResourceUrl(urlCreator.createObjectURL(data.anexo.conteudo)) as any);
  }

  /**
   *
   */
  open() {
    window.open(window.URL.createObjectURL(this.anexo.conteudo), '_blank');
  }
}
