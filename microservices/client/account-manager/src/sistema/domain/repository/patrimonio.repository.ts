import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {Patrimonio} from "../entity/patrimonio/patrimonio.model";
import {Observable} from "rxjs";
import {PageSerialize} from "../../infrastructure/page-serialize/page-serialize";
import {Localizacao} from "../entity/patrimonio/localizacao.model";

@Injectable()
export class PatrimonioRepository extends BaseRepository<Patrimonio> {

  /**
   *
   * @param httpClient
   */
  constructor(httpClient: HttpClient) {
    super(httpClient, 'patrimonios')
  }

  /**
   *
   * @param pageable
   */
  listPatrimoniosByCentroCustoCodigoAndFilters(pageable): Observable<any> {

    const params = PageSerialize.getHttpParamsFromPageable(pageable);

    return this.httpClient.get(this.collectionName, {
      params: params
    })
  }

  /**
   *
   * @param pageable
   */
  listNovosPatrimoniosByCentroCustoCodigoAndFilters(pageable): Observable<any> {

    const params = PageSerialize.getHttpParamsFromPageable(pageable);

    return this.httpClient.get(this.collectionName + '/novos', {
      params: params
    })
  }

  /**
   *
   * @param pageable
   */
  listByLocalizacoesFilters(pageable): Observable<any> {

    const params = PageSerialize.getHttpParamsFromPageable(pageable);

    return this.httpClient.get(this.collectionName + '/localizacoes', {
      params: params
    })
  }

  /**
   *
   * @param pageable
   */
  listSobrasFisicasByCentroCustoCodigoAndFilters(pageable): Observable<any> {
    const params = PageSerialize.getHttpParamsFromPageable(pageable);

    return this.httpClient.get(this.collectionName + '/sobras-fisicas', {
      params: params
    })
  }

  /**
   *
   * @param pageable
   */
  listAllByFilters(pageable: any): Observable<any> {
    const params = PageSerialize.getHttpParamsFromPageable(pageable);

    return this.httpClient.get(this.collectionName + '/all', {
      params: params
    })
  }

  /**
   *
   * @param numeroPlaqueta
   */
  findByNumeroPlaqueta(numeroPlaqueta: string): Observable<any> {
    return this.httpClient.get<any>(this.collectionName + '/' + numeroPlaqueta);
  }

  /**
   *
   * @param id
   */
  findById(id: number): Observable<any> {
    return this.httpClient.get<any>(this.collectionName + '/' + id + '/by-id');
  }

  /**
   *
   * @param patrimonio
   */
  encontrar(patrimonio: Patrimonio): Promise<Patrimonio> {
    return this.httpClient.post<Patrimonio>(this.collectionName + '/encontrar', patrimonio).toPromise();
  }

  /**
   *
   * @param patrimonio
   */
  inserirNovoPatrimonio(patrimonio: Patrimonio): Promise<Patrimonio> {
    return this.httpClient.post<Patrimonio>(this.collectionName + '/novo-patrimonio', patrimonio).toPromise();
  }

  /**
   *
   * @param patrimonio
   */
  inserirSobraFisica(patrimonio: Patrimonio): Promise<Patrimonio> {
    return this.httpClient.post<Patrimonio>(this.collectionName + '/sobra-fisica', patrimonio).toPromise();
  }

  /**
   *
   * @param patrimonioId
   * @param patrimonio
   */
  alterarSobraFisica(patrimonioId: number, patrimonio: Patrimonio): Promise<Patrimonio> {
    return this.httpClient.post<Patrimonio>(this.collectionName + '/sobra-fisica/' + patrimonioId , patrimonio).toPromise();
  }

  /**
   *
   * @param patrimonioId
   * @param localizacao
   */
  alterarLocalizacao(patrimonioId: number, localizacao: Localizacao): Promise<Patrimonio> {
    return this.httpClient.post<Patrimonio>(this.collectionName + '/' + patrimonioId + '/alterar-localizacao', localizacao).toPromise()
  }

  /**
   *
   * @param pageable
   */
  exportar(pageable: any): Observable<Blob> {

    const params = PageSerialize.getHttpParamsFromPageable(pageable);

    return this.httpClient.get(this.collectionName + '/export?nocache=' + Math.floor(Math.random() * 2000).toString(), {
      params: params,
      responseType: 'blob'
    });
  }
}
