import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot} from "@angular/router";
import {Usuario} from "../entity/usuario.model";
import {isNullOrUndefined} from "util";
import {Observable} from "rxjs";
import 'rxjs/add/operator/map';
import {UsuarioRepository} from "../repository/usuario.repository";

@Injectable()
export class AuthenticationService implements CanActivate, CanActivateChild {

  /**
   * TODO Falcatrua, não faz sentido a autenticação estar em uma API, por isso ela está sempre na raiz.
   * Dessa forma se faz necessária inserção deste IF
   * @type {string}
   */
  public pathToAuthenticate: string = 'authenticate';

  /**
   *
   * @type {string}
   */
  public pathToLogout: string = 'logout';

  /**
   *
   * @type {string}
   */
  public pathToAuthenticated = 'authenticated';

  /**
   *
   */
  public usuarioAutenticadoEmitter: EventEmitter<any>;

  /**
   *
   */
  private _usuarioAutenticado: Usuario = null;

  /**
   *
   * @param usuarioRepository
   * @param router
   * @param http
   */
  constructor(private usuarioRepository: UsuarioRepository,
              private router: Router, private http: HttpClient) {

    this.usuarioAutenticadoEmitter = new EventEmitter();

    this.requestContaAutenticada().subscribe(result => {
      this.usuarioAutenticado = result;
    });

    if (window.location.pathname.indexOf('sistema') > -1) {
      this.pathToAuthenticate = '../authenticate';
      this.pathToLogout = '../logout';
      this.pathToAuthenticated = '../authenticated';
    }

  }

  /**
   *
   * @param route
   * @param state
   * @returns {boolean}
   */
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {

    return this.requestContaAutenticada()
      .map(auth => {
        if (isNullOrUndefined(auth)) {
          this.router.navigate(['login']);
          return false;
        } else {
          return true;
        }
      });
  }

  /**
   *
   * @param route
   * @param state
   * @returns {boolean}
   */
  canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.canActivate(route, state)
  }

  /**
   *
   */
  public requestContaAutenticada(): Observable<Usuario> {
    return new Observable(observer => {
      this.http.get<Usuario>(this.pathToAuthenticated).subscribe(result => {
        if (result && result.id)
          this.getAuthoritiesByUsuarioId(result.id).subscribe(authorities => {
            result.authorities = authorities;
            observer.next(result)
          });
        else observer.next(null)
      })
    })
  }

  /**
   *
   */
  public getAuthoritiesByUsuarioId(usuarioId: number): Observable<Usuario> {
    return this.usuarioRepository.getAuthoritiesByUsuarioId(usuarioId)
  }

  /**
   *
   */
  get usuarioAutenticado(): Usuario {
    return this._usuarioAutenticado
  }

  /**
   *
   * @param usuarioAutenticado
   */
  set usuarioAutenticado(usuarioAutenticado: Usuario) {
    this._usuarioAutenticado = usuarioAutenticado;
    this.usuarioAutenticadoEmitter.emit(this.requestContaAutenticada());
  }


  /**
   *
   * @param usuario
   */
  public login(usuario: any): Promise<any> {
    let body = new HttpParams();
    body = body.set('username', usuario.email ? usuario.email : (usuario.documento ? usuario.documento : ''));
    body = body.set('senha', usuario.senha ? usuario.senha : '');
    return this.http.post(this.pathToAuthenticate, body).toPromise();
  }

  /**
   *
   */
  public logout(): Promise<any> {
    return this.http.get(this.pathToLogout).toPromise();
  }

  /**
   *
   * @param codigo
   * @param senha
   */
  public resetSenha(codigo: any, senha: any): Promise<any> {
    let params = new HttpParams();
    params = params.set('senha', senha);

    return this.http.post(`/cadastrar-senha/${codigo}`, params).toPromise();
  }

  /**
   *
   * @param email
   */
  public recoverSenha(email: any): Promise<any> {
    return this.http.get(`/recuperar-senha/${email}`).toPromise();
  }
}
