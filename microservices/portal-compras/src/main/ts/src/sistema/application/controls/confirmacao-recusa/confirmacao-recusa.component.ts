import {Component, ElementRef, Inject, Renderer} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef, MatSnackBar} from '@angular/material';
import {FormBuilder} from "@angular/forms";
import {DomSanitizer} from "@angular/platform-browser";
import {ActivatedRoute} from "@angular/router";
import {Fornecedor} from "../../../domain/entity/fornecedor/fornecedor.model";
import {Documento} from "../../../domain/entity/fornecedor/documento.model";

/**
 *
 */
@Component({
  selector: 'confirmacao-recusa',
  templateUrl: 'confirmacao-recusa.component.html',
  styleUrls: ['./confirmacao-recusa.component.scss']
})
export class ConfirmacaoRecusaComponent {

  /**
   *
   */
  fornecedor: Fornecedor;

  /**
   *
   */
  documentos: Documento[];

  /**
   *
   * @param activatedRoute
   * @param data
   * @param element
   * @param dialogRef
   * @param renderer
   * @param snackBar
   * @param fb
   * @param _sanitizer
   */
  constructor(public activatedRoute: ActivatedRoute,
              @Inject(MAT_DIALOG_DATA) public data: any,
              @Inject(ElementRef) public element: ElementRef,
              public renderer: Renderer, public snackBar: MatSnackBar,
              public fb: FormBuilder, private _sanitizer: DomSanitizer,
              private dialogRef: MatDialogRef<ConfirmacaoRecusaComponent>) {
    this.fornecedor = data.entity;
    this.documentos = this.fornecedor.documentos.filter(value => value.aprovado === false)
  }

  /**
   *
   * @param recusar
   */
  recusar(recusar: boolean) {
    this.dialogRef.close(recusar)
  }
}
