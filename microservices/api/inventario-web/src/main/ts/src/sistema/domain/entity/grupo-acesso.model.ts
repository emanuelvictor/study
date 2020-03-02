import {Abstract} from "./abstract/abstract.model";
import {GrupoAcessoPermissao} from "./grupo-acesso-permissao.model";

export class GrupoAcesso extends Abstract {

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
  public gruposAcessoPermissoes: GrupoAcessoPermissao[];


}