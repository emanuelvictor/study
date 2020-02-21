import {Abstract} from "../../abstract/abstract.model";
import {CentroCusto} from "../../pessoal.dto/centro-custo.model";
import {Inventario} from "./inventario.model";
import {Executor} from "./executor.model";

export class CentroCustoInventario extends Abstract {

  /**
   *
   */
  public inventario: Inventario;

  /**
   *
   */
  public centroCusto: CentroCusto;

  /**
   *
   */
  public executores: Executor[];

  /**
   *
   */
  public executoresAvulsos: Executor[];

  /**
   *
   */
  public status: any;

  /**
   *
   */
  public dataTerminoExtendida: any;

  /**
   *
   */
  public terminado: boolean;

  /**
   *
   * @param inventario
   * @param centroCusto
   */
  constructor(inventario?: Inventario, centroCusto?: CentroCusto) {
    super();
    this.inventario = inventario;
    this.centroCusto = centroCusto;
  }
}
