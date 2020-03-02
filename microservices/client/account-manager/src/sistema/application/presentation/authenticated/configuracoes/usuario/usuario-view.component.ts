import {Component} from '@angular/core';
import {DefaultCanActivate} from "../../../../has-permission/default-can-activate";
import {AuthenticationService} from "../../../../../domain/services/authentication.service";
import {Router} from "@angular/router";

// @ts-ignore
@Component({
  selector: 'usuario-view',
  templateUrl: './usuario-view.component.html',
  styleUrls: ['./usuario-view.component.scss']
})
export class UsuarioViewComponent extends DefaultCanActivate {

  /**
   *
   * @param authenticationService
   * @param router
   */
  constructor(authenticationService: AuthenticationService, router: Router) {

    super(authenticationService, router);

    this.fallbackRoute = 'configuracoes/grupos-acesso';

    this.permissions = ['root', 'usuarios', 'usuarios/get']

  }
}
