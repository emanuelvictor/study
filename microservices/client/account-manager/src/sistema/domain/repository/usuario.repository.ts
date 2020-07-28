import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Usuario} from "../entity/usuario.model";
import {PageSerialize} from "../../infrastructure/page-serialize/page-serialize";
import {Observable} from "rxjs";

@Injectable()
export class UsuarioRepository extends BaseRepository<Usuario> {

  /**
   *
   * @param httpClient
   */
  constructor(httpClient: HttpClient) {
    super(httpClient, 'account-manager/api/users');
  }

  /**
   *
   * @param username
   */
  findByLdapUsername(username: string): any {
    const params = PageSerialize.getHttpParamsFromPageable({username: username});

    return this.httpClient.get<any>(this.collectionName + '/ldap', {
      params: params
    })
  }

  /**
   *
   * @param {Usuario} usuario
   * @param {string} newPassword
   * @returns {Observable<{}>}
   */
  public changePassword(usuario: Usuario, newPassword: string): Promise<Usuario> {
    usuario.senha = newPassword;

    const params: HttpParams = new HttpParams().set('newPassword', newPassword);

    return this.httpClient.get<Usuario>(this.collectionName + '/' + usuario.id + '/change-password', {
      params: params
    }).toPromise();
  }

  /**
   *
   * @param username
   */
  public findByUsername(username: string): Observable<Usuario> {
    return this.httpClient.get<Usuario>(this.collectionName + '/' + username);
  }
}
