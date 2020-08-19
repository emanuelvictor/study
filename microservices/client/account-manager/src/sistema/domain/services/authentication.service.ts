import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot} from "@angular/router";
import {isNullOrUndefined} from "util";
import {Observable} from "rxjs";
import 'rxjs/add/operator/map';
import {UserRepository} from "../repository/user.repository";
import {getParameterByName} from "../../application/utils/utils";
import {Access} from "../../infrastructure/authentication/access";
import {UserDetails} from "../../infrastructure/authentication/user-details";
import {User} from "../entity/user.model";

@Injectable()
export class AuthenticationService implements CanActivate, CanActivateChild {

  public user: User;

  public access: Access;

  /**
   * Utilized for the transaction control.
   * This prevents the transaction from occurring twice.
   */
  getPromiseLoggedUserInstance: Promise<UserDetails>;

  /**
   *
   * @param usuarioRepository
   * @param router
   * @param http
   */
  constructor(private usuarioRepository: UserRepository,
              private router: Router, private http: HttpClient) {
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
        window.location.href = 'http://localhost:8081/oauth/authorize?response_type=code&client_id=browser&redirect_uri=http://localhost:4200&scope=none' + (state.url ? '&state=' + state.url : '');
        return false
      } else {
        const stateReturned: string = getParameterByName('state');
        if (stateReturned) {
          this.router.navigate([stateReturned])
        }
        return true
      }
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
   *
   */
  public getPromiseLoggedUser(): Promise<UserDetails> {

    this.getPromiseLoggedUserInstance = this.getPromiseLoggedUserInstance ? this.getPromiseLoggedUserInstance : new Promise<UserDetails>((resolve, reject) => {

      const code: string = getParameterByName('code');

      if (!this.access && !code)
        resolve(null)

      if (!this.access && code)
        this.getAccessTokenByAuthorizationCode(code).then(result => {
          this.access = result;
          this.getLoggedUser(this.access).then(result => {
            this.user = result;
            resolve(result)
          }).catch(err => reject(err))
        }).catch(err => reject(err))

      if (this.access)
        this.getLoggedUser(this.access).then(result => {
          this.user = result;
          resolve(result)
        }).catch(err => reject(err))

    })

    return this.getPromiseLoggedUserInstance
  }

  /**
   *
   * @param authorizationCode
   */
  public getAccessTokenByAuthorizationCode(authorizationCode: string): Promise<Access> {
    return this.http.post<Access>('http://localhost:8081/oauth/token?grant_type=authorization_code&code=' + authorizationCode + '&client_id=browser&client_secret=browser&redirect_uri=http://localhost:4200', {}).toPromise()
  }

  /**
   *
   * @param access
   */
  private getLoggedUser(access?: Access): Promise<User> {
    return this.http.get<User>('http://localhost:8081/oauth/principal/' + (access ? access.access_token : this.access.access_token)).toPromise()
  }

  /**
   *
   */
  public getAuthorities(access?: Access): Observable<any> {
    return this.http.get<any>('http://localhost:8081/oauth/authorities/' + (access ? access.access_token : this.access.access_token));
  }

  /**
   * TODO
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
