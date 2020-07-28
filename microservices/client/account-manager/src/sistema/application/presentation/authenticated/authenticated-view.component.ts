import {Component, OnDestroy, OnInit} from '@angular/core';
import {LoadingMode, LoadingType, TdLoadingService} from '@covalent/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatDialog} from '@angular/material';
import {Subscription} from 'rxjs';
import {MessageService} from '../../../domain/services/message.service';
import {TranslateService} from '@ngx-translate/core';
import {AuthenticationService} from "../../../domain/services/authentication.service";
import {AlterarSenhaDialogComponent} from "./configuracoes/usuario/alterar-senha-dialog.component";
import {UserDetails} from "../../../infrastructure/authentication/user-details";

// @ts-ignore
@Component({
  selector: 'authenticated-view',
  templateUrl: './authenticated-view.component.html',
  styleUrls: ['./authenticated-view.component.scss']
})
export class AuthenticatedViewComponent implements OnInit, OnDestroy {
  /**
   *
   */
  public usuario: UserDetails;
  public routerSubscription: Subscription;
  public userSubscription: Subscription;

  /**
   *
   */
  public toolbar: any = {headline: 'Cadastros', subhead: ''};

  /**
   *
   * @param translate
   * @param activeRoute
   * @param messageService
   * @param loadingService
   * @param dialog
   * @param router
   * @param authenticationService
   */
  constructor(private translate: TranslateService,
              private activeRoute: ActivatedRoute,
              private messageService: MessageService,
              private loadingService: TdLoadingService,
              private dialog: MatDialog, private router: Router,
              private authenticationService: AuthenticationService) {

    // this language will be used as a fallback when a translation isn't found in the current language
    translate.setDefaultLang('pt-br');

    // the lang to use, if the lang isn't available, it will use the current loader to get them
    translate.use('pt-br');

    this.loadingService.create({
      name: 'loadingLogin',
      mode: LoadingMode.Indeterminate,
      type: LoadingType.Circular,
      color: 'primary'
    })
  }

  /**
   *
   */
  ngOnInit() {
    this.getAuthenticatedUser();
  }

  /**
   *
   */
  public logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
    localStorage.clear()
  }

  /**
   *
   */
  public getAuthenticatedUser() {
    this.authenticationService.getObservedLoggedUser()
      .subscribe(authenticatedUser => {
        if (authenticatedUser)
          this.usuario = authenticatedUser
      })
  }

  /**
   *
   */
  public openDialogChangePassword() {
    this.dialog.open(AlterarSenhaDialogComponent, {
      width: '400px',
      height: 'auto',
      data: {usuario: this.usuario || null}
    })
  }

  /**
   * Verifica se o usuário logado é ADMINISTRADOR e se está editando ele mesmo.
   */
  public itsMe(usuario: any): boolean {
    const authenticatedUser = this.usuario;
    return authenticatedUser && ((authenticatedUser as any).isRoot || (authenticatedUser as any).id === usuario.id)
  }

  /**
   *
   */
  ngOnDestroy() {
    if (this.userSubscription) this.userSubscription.unsubscribe();
    if (this.routerSubscription) this.routerSubscription.unsubscribe()
  }
}
