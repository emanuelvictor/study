import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {Categoria} from "../entity/categoria.model";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PageSerialize} from "../../infrastructure/page-serialize/page-serialize";

@Injectable()
export class CategoriaRepository extends BaseRepository<Categoria> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'categorias');
  }

  /**
   * Essa requisição traz somente as categorias vinculadas com fornecedores.
   * No back-end há um inner join.
   * Essa consulta é utilizada na seleção de categorias para o envio de e-mails em massa, uma vez que o sistema não pode enviar e-mails para categorias não vincualdas á fornecedores.
   * @param pageable
   */
  listByFiltersWithFornecedores(pageable: any): Observable<any> {

    const params = PageSerialize.getHttpParamsFromPageable(pageable);

    return this.httpClient.get(this.collectionName + '/withFornecedores', {
      params: params
    })
  }
}
