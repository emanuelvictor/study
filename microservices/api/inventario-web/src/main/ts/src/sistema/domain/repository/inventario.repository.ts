import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {Inventario} from "../entity/patrimonio/inventario/inventario.model";
import {CentroCustoInventario} from "../entity/patrimonio/inventario/centro-custo-inventario.model";

@Injectable()
export class InventarioRepository extends BaseRepository<Inventario> {

  /**
   *
   * @param httpClient
   */
  constructor(httpClient: HttpClient) {
    super(httpClient, 'inventarios');
  }

  /**
   *
   * @param inventarioId
   * @param centroCustoCodigo
   */
  findByInventarioIdAndCentroCustoCodigo(inventarioId: number, centroCustoCodigo: string): Promise<CentroCustoInventario> {
    return this.httpClient.get<CentroCustoInventario>(this.collectionName + '/' + inventarioId + '/' + centroCustoCodigo).toPromise();
  }

  /**
   *
   * @param inventarioId
   * @param centroCustoCodigo
   */
  finalizar(inventarioId: number, centroCustoCodigo: string): Promise<CentroCustoInventario> {
    return this.httpClient.get<CentroCustoInventario>(this.collectionName + '/' + inventarioId + '/' + centroCustoCodigo + '/finalizar').toPromise();
  }

  /**
   * Rollback pra suprir as cabacices dos usuários
   * @param inventarioId
   * @param centroCustoCodigo
   */
  desfinalizar(inventarioId: number, centroCustoCodigo: string) {
    return this.httpClient.get<CentroCustoInventario>(this.collectionName + '/' + inventarioId + '/' + centroCustoCodigo + '/desfinalizar').toPromise();
  }

  /**
   * @param inventarioId
   * @param centroCustoCodigo
   */
  exportar(inventarioId: number, centroCustoCodigo: string): void {
    window.open(this.collectionName + '/' + inventarioId + '/' + centroCustoCodigo + '/exportar/?' + Math.floor(Math.random() * 2000).toString(), '_blank');
  }

  /**
   *
   * @param inventarioId
   * @param centroCustoCodigo
   */
  exportarSobrasFisicas(inventarioId: number, centroCustoCodigo: string): void {
    window.open(this.collectionName + '/' + inventarioId + '/' + centroCustoCodigo + '/exportar/sobras-fisicas/?' + Math.floor(Math.random() * 2000).toString(), '_blank');
  }
}
