import {Component, OnDestroy, OnInit} from '@angular/core';
import {LoadingMode, LoadingType, TdLoadingService} from '@covalent/core';
import {ActivatedRoute, NavigationStart, Router} from '@angular/router';
import {MatDialog} from '@angular/material';
import {Subscription} from 'rxjs';
import {AlterarSenhaDialogComponent} from './configuracoes/usuario/alterar-senha-dialog.component';
import {MessageService} from '../../services/message.service';
import {TranslateService} from '@ngx-translate/core';
import {AuthenticationService} from "../../services/authentication.service";
import {HasPermissionDirective} from "../../../application/has-permission/has-permission";
import {Usuario} from "../../entity/usuario.model";

@Component({
  selector: 'dashboard-view',
  templateUrl: './dashboard-view.component.html',
  styleUrls: ['./dashboard-view.component.scss']
})
export class DashboardViewComponent implements OnInit, OnDestroy {
  /**
   *
   */
  public usuario: Usuario;
  public routerSubscription: Subscription;
  public userSubscription: Subscription;

  /**
   *
   */
  matrix: any;

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
    });

    this.userSubscription = authenticationService.usuarioAutenticadoEmitter.subscribe(usuario => {
      this.usuario = usuario;
      if (usuario.id)
        this.authenticationService.getAuthoritiesByUsuarioId(usuario.id).subscribe(authorities => {
          this.usuario.authorities = authorities;
        })
    });

    this.matrix = {
      '/configuracoes': ['administrador', 'usuarios', 'usuarios/get', 'grupos-acesso', 'grupos-acesso/get'],
      '/publicacoes': ['administrador', 'avisos-contratacoes', 'avisos-editais', 'extratos-contratos'],
      '/cadastros': ['administrador', 'tipos-documentos', 'tipos-documentos/get', 'tipos-cadastros'],
      '/fornecedores': ['administrador', 'fornecedores', 'fornecedores/get']
    };

    this.authenticationService.requestContaAutenticada().subscribe(user => {
      this.usuario = user;
      this.routerSubscription = router.events.subscribe((event) => {
        if (event instanceof NavigationStart) {
          if (!this.usuario.authorities || !this.usuario.authorities.length)
            this.authenticationService.getAuthoritiesByUsuarioId(user.id).subscribe(authorities => {
              this.usuario.authorities = authorities;

              if (this.matrix && this.matrix[event.url])
                for (let i = 0; i < this.matrix[event.url].length; i++) {
                  const authority = this.matrix[event.url][i];
                  if (HasPermissionDirective.checkPermission(this.usuario, [authority])) {
                    if (authority === 'administrador') {
                      return
                    } else {
                      this.router.navigate([event.url + '/' + authority]);
                      return
                    }
                  }
                }
            })
        }
      })
    })

  }

  /**
   *
   */
  ngOnInit() {
    this.getAuthenticatedUser()
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
    this.authenticationService.requestContaAutenticada()
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
      width: '360px',
      height: 'auto',
      data: {usuario: this.usuario || null}
    })
  }

  /**
   * Verifica se o usuário logado é ADMINISTRADOR e se está editando ele mesmo.
   */
  public itsMe(usuario: any): boolean {
    const authenticatedUser = this.usuario;
    return (
      authenticatedUser &&
      authenticatedUser.isAdministrador &&
      authenticatedUser.id === usuario.id
    )
  }

  /**
   *
   */
  ngOnDestroy() {
    if (this.userSubscription) this.userSubscription.unsubscribe();
    if (this.routerSubscription) this.routerSubscription.unsubscribe()
  }
}
