import {Publicacao} from "./publicacao.model";
import {CategoriaAvisoEdital} from "./categoria-aviso-edital.model";

export class AvisoEdital extends Publicacao {

  /**
   *
   */
  public prazoPropostas: string;

  /**
   *
   */
  public numeroEdital: string;

  /**
   *
   */
  public numeroProcesso: string;

  /**
   *
   */
  public status: any;

  /**
   *
   */
  public categoriasAvisosEditais: CategoriaAvisoEdital[];

  /**
   * 
   */
  public dataModificacao: Date;

}
