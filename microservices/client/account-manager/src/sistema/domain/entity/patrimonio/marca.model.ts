import {Abstract} from "../abstract/abstract.model";

export class Marca extends Abstract {

  /**
   *
   */
  nome: string;

  /**
   *
   * @param nome
   */
  constructor(nome: string) {
    super();
    this.nome = nome;
  }
}
