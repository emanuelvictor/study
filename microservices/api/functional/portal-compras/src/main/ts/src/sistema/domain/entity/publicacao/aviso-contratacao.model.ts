import {Publicacao} from "./publicacao.model";

export class AvisoContratacao extends Publicacao {

  /**
   *
   */
  public numeroModalidade: string;

  /**
   *
   */
  public modalidade: any;

  /**
   *
   */
  public numeroProcesso: string;

  /**
   * 
   */
  public dataModificacao: Date;

  /**
   *
   */
  public razaoSocial : string;

}
