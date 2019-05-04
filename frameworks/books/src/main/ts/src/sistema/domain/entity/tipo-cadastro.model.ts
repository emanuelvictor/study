import {Abstract} from "./abstract/abstract.model";
import {TipoCadastroTipoDocumento} from './tipo-cadastro-tipo-documento';

export class TipoCadastro extends Abstract {

  /**
   *
   */
  public nome: string;

  /**
   *
   */
  public ativo: boolean;

  /**
   *
   */
  public documentos: TipoCadastroTipoDocumento[] = [];


  constructor(id?: number) {
    super();
    this.id = id
  }
}
