import {Banco} from './banco.model';
import {TipoConta} from './tipo-conta.enum';

export class ContaBancaria {

    public identificacao: string;

    public numero: string;

    public agencia: string;

    public digito: number;

    public tipoContaBancaria: any;

    public banco: Banco;

    constructor() {
        this.tipoContaBancaria = TipoConta[TipoConta.CONTA_CORRENTE];
    }

}
