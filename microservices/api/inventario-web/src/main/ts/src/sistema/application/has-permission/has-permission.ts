import {Directive, ElementRef, Input, OnInit, TemplateRef, ViewContainerRef} from "@angular/core";
import {AuthenticationService} from "../../domain/services/authentication.service";

@Directive({selector: '[hasPermission]'})
export class HasPermissionDirective implements OnInit {

  /**
   *
   */
  private operation;

  /**
   *
   */
  private currentUser;

  /**
   *
   */
  private authorities = [];

  /**
   *
   * @param element
   * @param templateRef
   * @param viewContainer
   * @param authenticationService
   */
  constructor(private element: ElementRef,
              private templateRef: TemplateRef<any>,
              private viewContainer: ViewContainerRef,
              private authenticationService: AuthenticationService) {
  }

  /**
   *
   */
  ngOnInit() {
    if (!this.authenticationService.usuarioAutenticado || !this.authenticationService.usuarioAutenticado.authorities || !this.authenticationService.usuarioAutenticado.authorities.length)
      this.authenticationService.requestContaAutenticada().subscribe(user => {
        this.currentUser = user;

        this.authenticationService.getAuthoritiesByUsuarioId(user.id).subscribe(authorities => {
          this.currentUser.authorities = authorities;

          this.authenticationService.usuarioAutenticado.authorities = authorities;
          this.updateView()
        })
      });
    else {
      this.currentUser = this.authenticationService.usuarioAutenticado;
      this.updateView()
    }
  }

  /**
   *
   * @param val
   */
  @Input()
  set hasPermission(val) {
    this.authorities = val;
    this.updateView()
  }

  @Input()
  set hasPermissionOperation(operation: string) {
    this.operation = operation
  }

  /**
   *
   */
  private updateView() {
    if (HasPermissionDirective.checkPermission(this.currentUser, this.authorities)) {
      this.viewContainer.createEmbeddedView(this.templateRef)
    } else {
      this.viewContainer.clear()
    }
  }

  /**
   *
   */
  public static checkPermission(currentUser, authorities) {

    let hasPermission = false;
    if (currentUser && currentUser.authorities) {
      for (const checkPermission of authorities) {
        const permissionFound = currentUser.authorities.map(authority => authority.authority).find(x => x.toUpperCase() === checkPermission.toUpperCase());
        if (permissionFound)
          hasPermission = true
      }
    }

    return hasPermission
  }
}
