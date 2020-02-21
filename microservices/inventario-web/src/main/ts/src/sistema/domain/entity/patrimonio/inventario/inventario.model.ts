import {Abstract} from "../../abstract/abstract.model";
import {CentroCusto} from "../../pessoal.dto/centro-custo.model";
import {CentroCustoInventario} from "./centro-custo-inventario.model";

export class Inventario extends Abstract {

  /**
   *
   */
  public nome: string;

  /**
   *
   */
  public centrosCusto: CentroCustoInventario[] = [];

  /**
   *
   */
  public dataInicio: any;

  /**
   *
   */
  public dataTermino: any;


  constructor(id?: number) {
    super();
    this.id = id
  }
}
