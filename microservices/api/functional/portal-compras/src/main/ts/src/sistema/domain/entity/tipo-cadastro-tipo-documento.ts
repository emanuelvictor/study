import {Abstract} from "./abstract/abstract.model";
import {TipoDocumento} from './tipo-documento.model';
import {TipoCadastro} from './tipo-cadastro.model';

export class TipoCadastroTipoDocumento extends Abstract {

  /**
   * 
   */
  public tipoDocumento: TipoDocumento;

  /**
   * 
   */
  public tipoCadastro: TipoCadastro;

  /**
   *
   */
  public obrigatorio: boolean = false;

  /**
   *
   */
  public interno: boolean = false;

}
