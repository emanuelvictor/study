import {Abstract} from "./abstract/abstract.model";
import {Permissao} from "./permissao.model";
import {GrupoAcesso} from "./grupo-acesso.model";

export class GrupoAcessoPermissao extends Abstract {

  public permissao: Permissao;

  public grupoAcesso: GrupoAcesso;

}