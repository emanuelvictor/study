import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot} from "@angular/router";
import {isNullOrUndefined} from "util";
import {Observable} from "rxjs";
import 'rxjs/add/operator/map';
import {UsuarioRepository} from "../repository/usuario.repository";
import {getParameterByName} from "../../application/utils/utils";
import {Access} from "../../infrastructure/authentication/access";
import {UserDetails} from "../../infrastructure/authentication/user-details";

@Injectable()
export class AuthenticationService implements CanActivate, CanActivateChild {

  /**
   *
   */
  private _user: UserDetails;

  /**
   *
   */
  private _access: Access;

  get access(): Access {
    return this._access;
  }

  set access(access: Access) {
    this._access = access;
  }

  /**
   *
   * @param usuarioRepository
   * @param router
   * @param http
   */
  constructor(private usuarioRepository: UsuarioRepository,
              private router: Router, private http: HttpClient) {

    this.getObservedLoggedUser().subscribe(result =>
      this.user = result
    )

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
   * @param route
   * @param state
   * @returns {boolean}
   */
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.getObservedLoggedUser().map(auth => {
      if (isNullOrUndefined(auth)) {
        window.location.href = 'http://localhost:8081/oauth/authorize?response_type=code&client_id=browser&redirect_uri=http://localhost:4200&scope=none';
        return false
      } else {
        return true
      }
    }).catch(err => {
      return new Observable(observer => {
        observer.error(err);
        window.location.href = 'http://localhost:8081/oauth/authorize?response_type=code&client_id=browser&redirect_uri=http://localhost:4200&scope=none'
      })
    })
  }

  /**
   *
   */
  public getObservedLoggedUser(): Observable<UserDetails> {
    return new Observable(observer => {
      this.getPromiseLoggedUser().then(result => observer.next(result)).catch(err => observer.error(err))
    })
  }

  /**
   * Utilized for the transaction control
   */
  getPromiseLoggedUserInstance: Promise<UserDetails>;

  /**
   *
   */
  public getPromiseLoggedUser(): Promise<UserDetails> {

    this.getPromiseLoggedUserInstance = this.getPromiseLoggedUserInstance ? this.getPromiseLoggedUserInstance : new Promise<UserDetails>((resolve, reject) => {
      if ((!this.access || !this.access.access_token) && !getParameterByName('code')) {
        return resolve(null) // TODO aqui deve ser resolve mesmo
      }

      if (getParameterByName('code'))
        this.getAccessTokenByAuthorizationCode(getParameterByName('code')).then(result => {
          this.access = result;
          this.getLoggedUserByAccessToken(this.access.access_token).then(result => {
            return resolve(result)
          }).catch(err => reject(err))
        }).catch(err => reject(err))
    })

    return this.getPromiseLoggedUserInstance
  }

  /**
   *
   * @param authorizationCode
   */
  public async getAccessTokenByAuthorizationCode(authorizationCode: string): Promise<Access> {
    return (await this.http.post<Access>('/oauth/token?grant_type=authorization_code&code=' + authorizationCode + '&client_id=browser&client_secret=browser&redirect_uri=http://localhost:4200', {}).toPromise())
  }

  /**
   *
   * @param access_token
   */
  private async getLoggedUserByAccessToken(access_token: string): Promise<UserDetails> {
    return (await this.http.get<UserDetails>('/oauth/principal/' + access_token, {}).toPromise())
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
  get user(): UserDetails {
    return this._user
  }

  /**
   *
   * @param user
   */
  set user(user: UserDetails) {
    this._user = user;
  }

  /**
   *
   */
  public logout(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
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
