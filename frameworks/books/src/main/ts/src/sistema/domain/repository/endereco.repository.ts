import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {Endereco} from '../entity/endereco/endereco.model';

@Injectable()
export class EnderecoRepository extends BaseRepository<Endereco> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'enderecos');
  }

}
