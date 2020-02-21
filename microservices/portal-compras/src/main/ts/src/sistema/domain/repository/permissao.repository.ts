import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {Permissao} from "../entity/permissao.model";

@Injectable()
export class PermissaoRepository extends BaseRepository<Permissao> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'permissoes');
  }

}
