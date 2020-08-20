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
import {environment} from "../../../environments/environment";

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
   * @param userRepository
   * @param router
   * @param http
   */
  constructor(private userRepository: UserRepository,
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
        window.location.href = `${environment.SSO}/oauth/authorize?response_type=code&client_id=browser&redirect_uri=http://localhost:4200` + (state.url ? '&state=' + state.url : '');
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

      const authorizationCode: string = getParameterByName('code');

      if (localStorage.getItem('refresh_token')) { // Have the access token and it is invalid, but have the refresh token, get the access token by refresh token

        this.getAccessTokenByRefreshToken(localStorage.getItem('refresh_token')).then(result => {
          this.access = new Access(result);
          localStorage.setItem('refresh_token', (result as any).refresh_token);
          this.getLoggedUser(this.access.access_token).then(result => {
            this.user = result;
            resolve(result)
          }).catch(err => reject(err))
        }).catch(err => reject(err))

      } else if (!this.access && !authorizationCode) { // No have access token and no have code, must return null and redirect to SSO

        resolve(null)

      } else if ((!this.access || !this.access.access_token) && authorizationCode) { // No have access token but have code, must get the access token by authorization code.

        this.getAccessTokenByAuthorizationCode(authorizationCode).then(result => {
          this.access = new Access(result);
          localStorage.setItem('refresh_token', (result as any).refresh_token);
          this.getLoggedUser(this.access.access_token).then(result => {
            this.user = result;
            resolve(result)
          }).catch(err => reject(err))
        }).catch(err => reject(err))

      } else if (this.access && this.access.access_token)
        this.getLoggedUser(this.access.access_token).then(result => {
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
    return this.http.post<Access>(`${environment.SSO}/oauth/token?grant_type=authorization_code&code=${authorizationCode}&client_id=browser&client_secret=browser&redirect_uri=http://localhost:4200`, {}).toPromise()
  }

  /**
   *
   * @param refreshToken
   */
  public getAccessTokenByRefreshToken(refreshToken: string): Promise<Access> {
    return this.http.post<Access>(`${environment.SSO}/oauth/token?grant_type=refresh_token&refresh_token=${refreshToken}&client_id=browser&client_secret=browser`, {}).toPromise()
  }

  /**
   *
   * @param accessToken
   */
  private getLoggedUser(accessToken: string): Promise<User> {
    if (!accessToken)
      return Promise.resolve(null);
    return this.http.get<User>(`${environment.SSO}/oauth/principal/${accessToken}`).toPromise()
  }

  /**
   * @param accessToken
   */
  public getAuthorities(accessToken: string): Observable<any> {
    return this.http.get<any>(`${environment.SSO}/oauth/authorities/${accessToken}`);
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
   * @param code
   * @param password
   */
  public resetPassword(code: any, password: any): Promise<any> {
    let params = new HttpParams();
    params = params.set('password', password);

    return this.http.post(`/insert-password/${code}`, params).toPromise();
  }

  /**
   *
   * @param email
   */
  public recoverPassword(email: any): Promise<any> {
    return this.http.get(`/recover-password/${email}`).toPromise();
  }
}
