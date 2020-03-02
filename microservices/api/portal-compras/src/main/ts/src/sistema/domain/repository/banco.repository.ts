import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {Banco} from '../entity/fornecedor/banco.model';

@Injectable()
export class BancoRepository extends BaseRepository<Banco> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'bancos');
  }

}
