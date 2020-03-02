import {Abstract} from "../../abstract/abstract.model";
import {CentroCusto} from "../../pessoal.dto/centro-custo.model";
import {CentroCustoInventario} from "./centro-custo-inventario.model";
import {Usuario} from "../../usuario.model";

export class Executor extends Abstract {

  /**
   *
   */
  public centroCustoInventario: CentroCustoInventario;

  /**
   *
   */
  public usuario: Usuario;

  /**
   *
   */
  public matricula: string;

  /**
   *
   */
  public avulso: boolean;

  /**
   *
   * @param id
   */
  constructor(id?: number) {
    super();
    this.usuario = new Usuario();
    this.id = id
  }
}
