import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {TipoCadastro} from "../entity/tipo-cadastro.model";

@Injectable()
export class TipoCadastroRepository extends BaseRepository<TipoCadastro> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'tipos-cadastros');
  }

}
