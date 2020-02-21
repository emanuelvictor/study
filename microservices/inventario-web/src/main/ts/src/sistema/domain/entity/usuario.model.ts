import {GrupoAcesso} from "./grupo-acesso.model";
import {Pessoa} from './pessoa.model';

export class Usuario extends Pessoa {

  /**
   *
   */
  public nome: string;

  /**
   *
   */
  public email: string;

  /**
   *
   */
  public senha: string;

  /**
   *
   */
  public interno: boolean = false;

  /**
   *
   */
  public ativo: boolean = true;

  /**
   *
   */
  public grupoAcesso: GrupoAcesso;

  /**
   *
   */
  public isGestor: boolean = false;

  /**
   *
   */
  public isPatrimonio: boolean = false;

  /**
   *
   */
  public root: boolean = false;

  /**
   *
   */
  public username: string;

  /**
   *
   */
  public authorities: any;

  /**
   *
   */
  executores: [] = [];

  /**
   *
   */
  centrosCusto: [] = [];

  /**
   *
   */
  constructor(id?: number) {
    super();
    if (id)
      this.id = id;
    else {
      this.root = false;
      this.ativo = true;
    }
  }

  /**
   *
   */
  get isRoot(): boolean {
    return this.root;
  }

}
