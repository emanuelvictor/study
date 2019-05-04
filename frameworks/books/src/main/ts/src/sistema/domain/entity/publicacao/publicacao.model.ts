import {Abstract} from "../abstract/abstract.model";
import {Anexo} from "./anexo.model";

export class Publicacao extends Abstract {

  /**
   *
   */
  public objeto: string;

  /**
   *
   */
  public dataPublicacao: any;

  /**
   *
   */
  public anexos: Anexo[];

  /**
   *
   */
  constructor() {
    super();
    this.anexos = [new Anexo()];
  }

}
