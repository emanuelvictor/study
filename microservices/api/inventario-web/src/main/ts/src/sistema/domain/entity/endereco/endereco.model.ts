import {Cidade} from './cidade.model';
import {Abstract} from '../abstract/abstract.model';

export class Endereco extends Abstract {

  public logradouro: string;

  public complemento: string;

  public bairro: string;

  public cep: string;

  public numero: string;

  public caixaPostal: string;

  public cidade: Cidade;

  public latitude: number;

  public longitude: number;

  constructor(logradouro: string, complemento: string, bairro: string, cep: string, numero: string, cidade: Cidade, latitude: number, longitude: number) {
    super();
    this.logradouro = logradouro;
    this.complemento = complemento;
    this.bairro = bairro;
    this.cep = cep;
    this.numero = numero;
    this.cidade = cidade;
    this.latitude = latitude;
    this.longitude = longitude;
  }
}