import {Component, EventEmitter, Input, Output,} from '@angular/core';
import {FormGroup} from "@angular/forms"
import {Documento} from 'sistema/domain/entity/fornecedor/documento.model';
import {AuthenticationService} from "../../../../../../services/authentication.service";

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
   * @param authenticationService
   */
  constructor(public authenticationService: AuthenticationService) {
  }

  /**
   *
   */
  hasDocumentacaoObrigatoria() {
    return this.documentos.filter(documento => documento.tipoCadastroTipoDocumento.obrigatorio && !documento.tipoCadastroTipoDocumento.interno).length
  }

  /**
   *
   */
  hasDocumentacaoNaoObrigatoria() {
    return this.documentos.filter(documento => !documento.tipoCadastroTipoDocumento.obrigatorio && !documento.tipoCadastroTipoDocumento.interno).length
  }

  /**
   *
   */
  hasDocumentacaoInterna() {
    return !this.authenticationService.usuarioAutenticado.fornecedor && this.documentos.filter(documento => documento.tipoCadastroTipoDocumento.interno).length
  }
}
