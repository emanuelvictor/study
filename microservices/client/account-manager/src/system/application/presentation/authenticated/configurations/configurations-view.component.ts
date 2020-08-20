import {Component} from '@angular/core';
import {DefaultCanActivate} from "../../../has-permission/default-can-activate";
import {AuthenticationService} from "../../../../domain/services/authentication.service";
import {Router} from "@angular/router";

// @ts-ignore
@Component({
  selector: 'configurations-view',
  templateUrl: './configurations-view.component.html',
  styleUrls: ['./configurations-view.component.scss']
})
export class ConfigurationsViewComponent extends DefaultCanActivate {

  /**
   *
   * @param authenticationService
   * @param router
   */
  constructor(authenticationService: AuthenticationService, router: Router) {

    super(authenticationService, router);

    this.fallbackRoute = 'minha-conta';

    this.permissions = ['root', 'usuarios', 'usuarios/get', 'grupos-acesso', 'grupos-acesso/get']

  }
}
