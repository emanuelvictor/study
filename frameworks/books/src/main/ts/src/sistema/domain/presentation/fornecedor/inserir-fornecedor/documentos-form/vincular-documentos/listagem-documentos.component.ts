import {Component, EventEmitter, Input, Output,} from '@angular/core';
import {FormGroup} from "@angular/forms"
import {Documento} from 'sistema/domain/entity/fornecedor/documento.model';

@Component({
  selector: 'listagem-documentos',
  templateUrl: './listagem-documentos.component.html',
  styleUrls: ['./listagem-documentos.component.scss']
})
export class ListagemDocumentosComponent {

  /**
   *
   */
  @Input()
  form: FormGroup;

  /**
   *
   */
  @Input()
  documentos: Documento[] = [];

  /**
   *
   */
  @Output()
  documentosChange: EventEmitter<any> = new EventEmitter<any>();

  /**
   *
   */
  hasDocumentacaoObrigatoria() {
    return this.documentos.filter(documento => documento.tipoCadastroTipoDocumento.obrigatorio).length
  }

  /**
   *
   */
  hasDocumentacaoNaoObrigatoria() {
    return this.documentos.filter(documento => !documento.tipoCadastroTipoDocumento.obrigatorio).length
  }
}
