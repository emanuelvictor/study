import {GrupoAcesso} from "./grupo-acesso.model";
import {Pessoa} from './pessoa.model';
import {Fornecedor} from "./fornecedor/fornecedor.model";

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
  public administrador: boolean = false;

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
  public fornecedor: Fornecedor;

  constructor() {
    super();
    this.administrador = false;
    this.ativo = true;
  }


  /**
   *
   */
  get isAdministrador(): boolean {
    return this.administrador;
  }

  /**
   *
   */
  get isFornecedor(): boolean {
    return this.fornecedor != null;
  }

}
