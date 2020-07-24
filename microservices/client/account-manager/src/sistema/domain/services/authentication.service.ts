import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot} from "@angular/router";
import {Usuario} from "../entity/usuario.model";
import {isNullOrUndefined} from "util";
import {Observable} from "rxjs";
import 'rxjs/add/operator/map';
import {UsuarioRepository} from "../repository/usuario.repository";
import {getParameterByName} from "../../application/utils/utils";

@Injectable()
export class AuthenticationService implements CanActivate, CanActivateChild {

  /**
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

  private _access = {
    access_token: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjI1OTU2MDY1MjYsInVzZXJfbmFtZSI6ImFkbWluQGFkbWluLmNvbSIsImF1dGhvcml0aWVzIjpbInJvb3QiXSwianRpIjoiOWQ2NWY5OWEtNjMwMC00NzU2LThmMWMtZTRiYjE2NzU1NWVmIiwiY2xpZW50X2lkIjoiYnJvd3NlciIsInNjb3BlIjpbIm5vbmUiXX0.s9OaZhdbB5VC_4YnqKOOHFG1LXM0fN6ZZYILz1d_dXs",
    expires_in: 999999998,
    integrator: "admin@admin.comIxBu",
    refresh_token: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbkBhZG1pbi5jb20iLCJzY29wZSI6WyJub25lIl0sImF0aSI6IjlkNjVmOTlhLTYzMDAtNDc1Ni04ZjFjLWU0YmIxNjc1NTVlZiIsImV4cCI6MjU5NTYwNjUyNSwiYXV0aG9yaXRpZXMiOlsicm9vdCJdLCJqdGkiOiJhM2I4ZDIwMS01Njg2LTQ2OTktYTEzZi00OGEyOWE4N2M5MjEiLCJjbGllbnRfaWQiOiJicm93c2VyIn0.FHz0_x56iuOEtoGmznsG6elQJqYzHRy3eDu7GffjyME",
    scope: "none",
    token_type: "bearer"
  }

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
          return true
        }
      })
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
      if (this._usuarioAutenticado && this._usuarioAutenticado.authorities) {
        observer.next(this._usuarioAutenticado);
        return
      }

      if (!this._access.access_token && getParameterByName('code'))
        window.location.href = 'http://localhost:8081/oauth/authorize?response_type=code&client_id=browser&redirect_uri=http://localhost:4200/login&scope=none';

      this.http.post('/oauth/token?grant_type=authorization_code&code=' + getParameterByName('code') + '&client_id=browser&client_secret=browser&redirect_uri=http://localhost:4200/login', {})
        .subscribe(result => {
          this._access = <any>result;
        })

      // this.http.get<Usuario>('localhost:8084/user').subscribe(result => {
      //   if (result && result.id)
      //     this.getAuthoritiesByUsuarioId(result.id).subscribe(authorities => {
      //       result.authorities = authorities;
      //       this._usuarioAutenticado = result;
      //       this.getExecutoresByUsuarioId(result.id).subscribe(executores => {
      //         result.executores = executores;
      //         this._usuarioAutenticado = result;
      //         this.getCentrosCustoByGestorId(result.id).subscribe(centrosCusto => {
      //           result.centrosCusto = centrosCusto;
      //           this._usuarioAutenticado = result;
      //           observer.next(result)
      //         })
      //       })
      //     });
      //   else observer.next(null)
      // })
    })
  }

  /**
   *
   */
  public getAuthoritiesByUsuarioId(usuarioId: number): Observable<any> {
    return this.usuarioRepository.getAuthoritiesByUsuarioId(usuarioId)
  }

  /**
   *
   */
  public getExecutoresByUsuarioId(usuarioId: number): Observable<any> {
    return this.usuarioRepository.getExecutoresByUsuarioId(usuarioId)
  }

  /**
   *
   */
  public getCentrosCustoByGestorId(usuarioId: number): Observable<any> {
    return this.usuarioRepository.getCentrosCustoByGestorId(usuarioId)
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
    return new Promise<any>((resolve, reject) => {
      this.http.get(this.pathToLogout).toPromise().then(result => {
        this._usuarioAutenticado = null;
        resolve('Logout efetuado com sucesso')
      }).catch(err => reject(err))
    })
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
