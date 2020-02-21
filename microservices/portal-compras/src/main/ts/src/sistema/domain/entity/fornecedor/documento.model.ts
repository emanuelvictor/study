import {Arquivo} from '../publicacao/arquivo.model';
import {Fornecedor} from './fornecedor.model';
import {TipoCadastroTipoDocumento} from '../tipo-cadastro-tipo-documento';
import {Status} from '../publicacao/status.enum';

export class Documento extends Arquivo {

  public observacao: string;

  public fornecedor: Fornecedor;

  public tipoCadastroTipoDocumento: TipoCadastroTipoDocumento;

  public status: Status;

  public aprovado: boolean;

  public type: string;

  constructor() {
    super();
  }

}
