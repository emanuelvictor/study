import {Abstract} from "../../abstract/abstract.model";
import {Localizacao} from "../localizacao.model";
import {CentroCustoInventario} from "../inventario/centro-custo-inventario.model";

export class PatrimonioDTO extends Abstract {

  codigoBase: string;
  item: string;
  plaqueta: string;
  descricao: string;

  modelo: string;

  marca: string;

  encontrado: boolean;

  localizacao: Localizacao;

  localizacaoAnterior: Localizacao;

  centroCustoInventario: CentroCustoInventario;

  centroCustoAnterior: any;

}
