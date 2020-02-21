import {Categoria} from "../categoria.model";
import {Abstract} from "../abstract/abstract.model";
import {Fornecedor} from "./fornecedor.model";

export class CategoriaFornecedor extends Abstract {

  /**
   *
   */
  public categoria: Categoria;

  /**
   *
   */
  public fornecedor: Fornecedor;

}
