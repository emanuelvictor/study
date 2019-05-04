import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {GrupoAcesso} from "../entity/grupo-acesso.model";

@Injectable()
export class GrupoAcessoRepository extends BaseRepository<GrupoAcesso> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'grupos-acesso');
  }

}
