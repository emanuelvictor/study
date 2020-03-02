import {AnexoEmail} from "./anexo-email.model";
import {StatusFornecedor} from "../status-fornecedor.enum";
import {Categoria} from "../../categoria.model";

export class Email {

  /**
   *
   */
  public assunto: string;

  /**
   *
   */
  public conteudo: string;

  /**
   *
   */
  public status: StatusFornecedor;

  /**
   *
   */
  public categorias: Categoria[];

  /**
   *
   */
  public anexosEmail: AnexoEmail[];

  /**
   *
   */
  constructor() {
    this.categorias = [];
    this.anexosEmail = []
  }
}
