// @ts-ignore
import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
// @ts-ignore
import {HttpClient} from "@angular/common/http";
import {CentroCustoInventario} from "../entity/patrimonio/inventario/centro-custo-inventario.model";
import {Subject} from "rxjs";

@Injectable()
export class CentroCustoInventarioRepository extends BaseRepository<CentroCustoInventario> {

  /**
   *
   */
  public subject: Subject<any> = new Subject<any>();

  /**
   *
   * @param httpClient
   */
  constructor(httpClient: HttpClient) {
    super(httpClient, 'centros-custo-inventario');
  }

  /**
   * Extende a data de termino do centroCustoInventario
   * @param id
   * @param centroCustoInventario
   */
  extenderDataTermino(id: number, centroCustoInventario: any): Promise<CentroCustoInventario> {
    console.log(centroCustoInventario);
    return this.httpClient.put<CentroCustoInventario>(this.collectionName + '/' + id + '/extender-data-termino', centroCustoInventario).toPromise()
  }

}
