import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {Editora} from "../entity/editora.model";

@Injectable()
export class EditoraRepository extends BaseRepository<Editora> {

  /**
   *
   * @param httpClient
   */
  constructor(httpClient: HttpClient) {
    super(httpClient, 'editoras', null);
  }

}
