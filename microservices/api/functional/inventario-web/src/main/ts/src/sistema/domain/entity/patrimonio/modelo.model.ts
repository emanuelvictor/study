import {Marca} from "./marca.model";
import {Abstract} from "../abstract/abstract.model";

export class Modelo extends Abstract {

  /**
   *
   */
  marca: Marca;

  /**
   *
   */
  nome: string;

  /**
   *
   * @param nome
   * @param marca
   */
  constructor(nome: string, marca?: Marca) {
    super();
    this.nome = nome;
    this.marca = marca;
  }
}
