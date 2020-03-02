import {Localizacao} from "./localizacao.model";
import {Abstract} from "../abstract/abstract.model";
import {CentroCustoInventario} from "./inventario/centro-custo-inventario.model";

export class Patrimonio extends Abstract {

  departamento: string;
  complemento: string;
  observacao: string;

  codigoBase: string;
  item: string;
  plaqueta: string;
  descricao: string;
  encontrado: boolean;

  sobraFisica: boolean;

  modelo: string;

  marca: string;

  localizacaoAnterior: Localizacao;

  localizacao: Localizacao;

  centroCustoInventario: CentroCustoInventario;

}
