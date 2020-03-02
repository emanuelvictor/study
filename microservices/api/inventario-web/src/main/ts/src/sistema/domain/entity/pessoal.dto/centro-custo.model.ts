import {Abstract} from "../abstract/abstract.model";
import {Usuario} from "../usuario.model";
import {Executor} from "../patrimonio/inventario/executor.model";

export class CentroCusto extends Abstract {

  /**
   *
   */
  public gestor: Usuario;

  // /**
  //  *Todo SÃO OS COLABORADORES
  //  */
  // public executores: Executor[];
  //
  // /**
  //  *
  //  */
  // public executoresAvulsos: Executor[];

  /**
   *
   */
  public codigo: string;

  /**
   *
   */
  public descricao: string;

  /**
   *
   * @param codigo
   * @param descricao
   * @param gestor
   */
  constructor(codigo?: string, descricao?: string, gestor?: Usuario) {
    super();
    this.codigo = codigo;
    this.descricao = descricao;
    this.gestor = gestor
  }
}
