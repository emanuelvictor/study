import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {Usuario} from "../entity/usuario.model";
import {PageSerialize} from "../../infrastructure/page-serialize/page-serialize";

@Injectable()
export class UsuarioRepository extends BaseRepository<Usuario> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'usuarios');
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

  getAuthoritiesByUsuarioId(usuarioId: number): any {
    return this.httpClient.get<any>(this.collectionName + '/' + usuarioId + '/authorities');
  }
}
