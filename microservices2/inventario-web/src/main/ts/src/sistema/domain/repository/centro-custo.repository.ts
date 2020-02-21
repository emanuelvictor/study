// @ts-ignore
import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
// @ts-ignore
import {HttpClient} from "@angular/common/http";
import {CentroCusto} from "../entity/pessoal.dto/centro-custo.model";
import {Observable} from "rxjs";
import {CentroCustoInventario} from "../entity/patrimonio/inventario/centro-custo-inventario.model";

@Injectable()
export class CentroCustoRepository extends BaseRepository<CentroCusto> {

  /**
   *
   * @param httpClient
   */
  constructor(httpClient: HttpClient) {
    super(httpClient, 'centros-custo');
  }

  /**
   *
   * @param codigo
   */
  public findByCodigo(codigo: string): Observable<any> {
    return this.httpClient.get(this.collectionName + '/' + codigo)
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
   * @param centroCustoCodigo
   */
  public listColaboradoresByCentroCustoCodigo(centroCustoCodigo: string): Observable<any> {
    return this.httpClient.get(this.collectionName + '/' + centroCustoCodigo + '/colaboradores')
  }

  /**
   *
   * @param id
   */
  public findCentroCustoInventarioById(id: number): Observable<any> {
    return this.httpClient.get(this.collectionName + '/centro-custo-inventario/' + id)
  }

  /**
   *
   * @param id
   * @param centroCustoCodigo
   */
  findByInventarioIdAndCentroCustoCodigo(id: number, centroCustoCodigo: string): Observable<CentroCustoInventario> {
    return this.httpClient.get<CentroCustoInventario>(this.collectionName + '/' + id + '/' + centroCustoCodigo)
  }

  /**
   *
   * @param id
   * @param codigo
   */
  public executarCentroCustoInventario(id, codigo): Observable<any> {
    return this.httpClient.get(this.collectionName + '/' + id + '/' + codigo + '/executar')
  }

  /**
   *
   * @param id
   * @param codigo
   */
  public toAnaliseCentroCustoInventario(id, codigo): Observable<any> {
    return this.httpClient.get(this.collectionName + '/' + id + '/' + codigo + '/to-analise')
  }

  /**
   *
   * @param id
   * @param codigo
   */
  aprovarCentroCustoInventario(id: number, codigo: string) {
    return this.httpClient.get(this.collectionName + '/' + id + '/' + codigo + '/aprovar')
  }

  /**
   *
   * @param codigo
   */
  hasInventariaveis(codigo: string) {
    return this.httpClient.get(this.collectionName + '/' + codigo + '/has-inventariaveis')
  }

  /**
   *
   * @param codigo
   */
  inventariados(codigo: string) {
    return this.httpClient.get(this.collectionName + '/' + codigo + '/inventariados')
  }

  /**
   *
   * @param codigo
   */
  naoInventariados(codigo: string) {
    return this.httpClient.get(this.collectionName + '/' + codigo + '/nao-inventariados')
  }

  /**
   *
   * @param codigo
   */
  encontrados(codigo: string) {
    return this.httpClient.get(this.collectionName + '/' + codigo + '/encontrados')
  }

  /**
   *
   * @param codigo
   */
  naoEncontrados(codigo: string) {
    return this.httpClient.get(this.collectionName + '/' + codigo + '/nao-encontrados')
  }
}
