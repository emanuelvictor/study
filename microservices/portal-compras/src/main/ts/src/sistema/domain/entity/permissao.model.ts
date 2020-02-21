import {Abstract} from "./abstract/abstract.model";

export class Permissao extends Abstract {

  /**
   *
   */
  public nome: string;

  /**
   *
   */
  private identificador: string;

  /**
   *
   */
  public permissaoSuperior: number;

  /**
   *
   */
  public permissoesInferiores: Permissao[]

}