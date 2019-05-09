import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {Livro} from "../entity/livro.model";

@Injectable()
export class LivroRepository extends BaseRepository<Livro> {

  /**
   * @param httpClient
   */
  constructor(httpClient: HttpClient) {
    super(httpClient, 'livros');
  }

}
