import {ContaBancaria} from './conta-bancaria.model';
import {FormaPagamento} from './forma-pagamento.enum';

export class DadosRecebimento {

    public formaPagamento: any;

    public contaBancaria: ContaBancaria = new ContaBancaria();

    constructor() {
        this.formaPagamento = FormaPagamento[FormaPagamento.DEPOSITO_BANCARIO];
    }
}
