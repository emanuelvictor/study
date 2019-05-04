import {Usuario} from '../usuario.model';
import {Endereco} from "../endereco/endereco.model";
import {TipoPessoaJuridica} from "./tipo-pessoa-juridica.enum";
import {TipoAtividadeEconomica} from "./tipo-atividade-economica.enum";
import {CategoriaFornecedor} from "./categoria-fornecedor.model";
import {AtividadeEconomica} from "./atividade-economica.model";
import {Documento} from './documento.model';
import {Abstract} from "../abstract/abstract.model";
import {StatusFornecedor} from "./status-fornecedor.enum";
import {DadosRecebimento} from "./dados-recebimento.model";
import {TipoCadastro} from "../tipo-cadastro.model";


export class Fornecedor extends Abstract {

  public usuario: Usuario = new Usuario();

  public atividadesEconomicas: TipoAtividadeEconomica[] = [];

  public tipoPessoaJuridica: any;

  public descricaoProdutosServicos: string;

  public inscricaoEstadual: string;

  public insencaoInscricaoEstadual: boolean;

  public inscricaoMunicipal: string;

  public simplesNacional: boolean;

  public anexoSimplesNacional: string;

  public cooperativa: boolean;

  public razaoSocial: string;

  public feedback: string;

  public tipoCadastro: TipoCadastro;

  public ativo: boolean;

  public souEmpresa: boolean;

  public site: string;

  public tipoAtividadeEconomica: any;

  public telefones: string[] = [];

  public emails: string[] = [];

  public endereco: Endereco;

  public categoriasFornecedor: CategoriaFornecedor[] = [];

  public documentos: Documento[] = [];

  /*
       ATIVIDADES ECONÔMICAS
    */
  public atividadesEconomicasPrimarias: AtividadeEconomica[] = [];

  public atividadesEconomicasSecundarias: AtividadeEconomica[] = [];

  public dadosRecebimento: DadosRecebimento = new DadosRecebimento();

  public status: StatusFornecedor;

  public siteKey: string = null;

  public recap: string = null;

  private _isRascunho: boolean;

  constructor(fornecedor?: any, id?: number) {
    super();
    this.id = id;
    this.insencaoInscricaoEstadual = false;
    this.simplesNacional = true;
    this.souEmpresa = true;
    this.cooperativa = false;
    this.tipoPessoaJuridica = TipoPessoaJuridica[TipoPessoaJuridica.MICROEMPREENDEDOR_INDIVIDUAL];

    // Conversão do tipo any para o tipo Fornecedor
    if (fornecedor) {
      this.id = fornecedor.id;
      this.insencaoInscricaoEstadual = fornecedor.insencaoInscricaoEstadual;
      this.simplesNacional = fornecedor.simplesNacional;
      this.souEmpresa = fornecedor.souEmpresa;
      this.cooperativa = fornecedor.cooperativa;
      this.razaoSocial = fornecedor.razaoSocial;
      this.tipoPessoaJuridica = fornecedor.tipoPessoaJuridica;
      this.endereco = fornecedor.endereco;
      this.status = fornecedor.status;
      this.dadosRecebimento = fornecedor.dadosRecebimento;
      this.atividadesEconomicasSecundarias = fornecedor.atividadesEconomicasSecundarias;
      this.atividadesEconomicasPrimarias = fornecedor.atividadesEconomicasPrimarias;
      this.usuario = fornecedor.usuario;
    }
  }

  get isRascunho(): boolean {
    if (this._isRascunho)
      return this._isRascunho;
    return (StatusFornecedor[this.status] === (StatusFornecedor.EM_CRIACAO as any) || StatusFornecedor[this.status] === (StatusFornecedor.RECUSADO as any))
  }

  set isRascunho(isRascunho: boolean) {
    this._isRascunho = isRascunho
  }

}
