import {Component} from '@angular/core';
import {AuthenticationService} from '../../../domain/services/authentication.service';
import {MessageService} from '../../../domain/services/message.service';
import {ActivatedRoute, Router} from '@angular/router';

// @ts-ignore
@Component({
  selector: 'recuperar-senha',
  templateUrl: 'recuperar-senha.component.html',
  styleUrls: ['../login/login.component.scss', 'gerenciar-senha.component.scss']
})
export class RecuperarSenhaComponent {

  /**
   *
   */
  public usuario: any = {};

  /**
   *
   * @param authService
   * @param messageService
   * @param router
   * @param activatedRoute
   */
  constructor(public authService: AuthenticationService,
              public messageService: MessageService,
              public router: Router,
              public activatedRoute: ActivatedRoute) {
    this.usuario.codigo = this.activatedRoute.snapshot.params.codigo || null;
  }

  /**
   *
   * @param form
   */
  public recoverPassword(form) {

    if (form.invalid) {
      this.messageService.toastWarning('Favor informar um e-mail válido.');
      return;
    }

    this.authService.recoverSenha(this.usuario.email)
      .then(() => {
        this.messageService.toastSuccess('Favor verificar seu e-mail para redefinir sua senha.');
        this.router.navigate(['/login']);
      });
  }
}
