import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {Categoria} from "../entity/categoria.model";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class CategoriaRepository extends BaseRepository<Categoria> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'categorias');
  }

}
