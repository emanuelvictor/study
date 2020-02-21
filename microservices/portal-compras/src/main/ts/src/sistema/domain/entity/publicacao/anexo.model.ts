import {Publicacao} from "./publicacao.model";
import {Arquivo} from "./arquivo.model";

export class Anexo extends Arquivo {

  public publicacao: Publicacao;

  public type: string;

  constructor() {
    super();
  }

}
