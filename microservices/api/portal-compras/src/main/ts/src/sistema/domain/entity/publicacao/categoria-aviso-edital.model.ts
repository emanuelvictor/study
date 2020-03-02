import {Categoria} from "../categoria.model";
import {AvisoEdital} from "./aviso-edital.model";
import {Abstract} from "../abstract/abstract.model";

export class CategoriaAvisoEdital extends Abstract {

  /**
   *
   */
  public categoria: Categoria;

  /**
   *
   */
  public avisoEdital: AvisoEdital;

}
