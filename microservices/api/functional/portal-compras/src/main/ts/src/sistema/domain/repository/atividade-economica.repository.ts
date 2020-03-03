import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {AtividadeEconomica} from "../entity/fornecedor/atividade-economica.model";

@Injectable()
export class AtividadeEconomicaRepository extends BaseRepository<AtividadeEconomica> {

  /**
   *
   * @param httpClient
   */
  constructor(httpClient: HttpClient) {
    super(httpClient, 'atividades-economicas');
  }

}
