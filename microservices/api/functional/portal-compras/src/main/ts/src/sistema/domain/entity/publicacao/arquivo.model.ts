import {Abstract} from "../abstract/abstract.model";

export class Arquivo extends Abstract {

  public conteudo: File;

  public caminho: string;

  public externo: boolean;

  private _nome: string;

  public link: string;

  constructor() {
    super();
    (this.conteudo as any) = {};
  }

  get nome(): string {
    if (this._nome === null && this.conteudo !== null)
      return this.conteudo.name;
    if (this._nome !== null && this.conteudo === null)
      (this.conteudo as any) = {name: this._nome};
    return this._nome;
  }

  set nome(value: string) {
    if (value === null && this.conteudo !== null)
      value = this.conteudo.name;
    if (value !== null && this.conteudo === null)
      (this.conteudo as any) = {name: value};
    this._nome = value;
  }
}
