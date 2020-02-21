import {Component} from '@angular/core';
import {Fornecedor} from "../../../../../entity/fornecedor/fornecedor.model";
import {AuthenticationService} from "../../../../../services/authentication.service";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'inicio',
  templateUrl: 'inicio.component.html',
  styleUrls: ['../../fornecedor.component.scss']
})
export class InicioComponent {

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
