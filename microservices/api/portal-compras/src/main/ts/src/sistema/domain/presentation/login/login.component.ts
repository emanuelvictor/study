import {Component} from '@angular/core';

import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/authentication.service';

/**
 *
 */
@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  /**
   *
   */
  public usuario: any = {};

  /**
   *
   * @param router
   * @param authenticationService
   */
  constructor(private router: Router, private authenticationService: AuthenticationService) {
  }

  /**
   *
   */
  public login() {
    this.authenticationService.login(this.usuario)
      .then((result) => {
        localStorage.setItem('curUser', result.nome);
        this.authenticationService.requestContaAutenticada()
          .subscribe(authenticated => {
            this.usuario = authenticated;
            this.authenticationService.usuarioAutenticado = this.usuario;

            this.router.navigate(['/']);
          });
      });
  }
}
