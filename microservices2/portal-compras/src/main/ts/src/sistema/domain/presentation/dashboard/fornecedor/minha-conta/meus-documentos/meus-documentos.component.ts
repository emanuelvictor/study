import {Component} from '@angular/core';
import {Fornecedor} from "../../../../../entity/fornecedor/fornecedor.model";
import {AuthenticationService} from "../../../../../services/authentication.service";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'meus-documentos',
  templateUrl: 'meus-documentos.component.html',
  styleUrls: ['../../fornecedor.component.scss']
})
export class MeusDocumentosComponent {

  /**
   *
   */
  fornecedor: Fornecedor;

  /**
   *
   */
  form: FormGroup;

  /**
   *
   * @param authenticationService
   */
  constructor(private authenticationService: AuthenticationService) {
    this.fornecedor = this.authenticationService.usuarioAutenticado.fornecedor
  }
}
