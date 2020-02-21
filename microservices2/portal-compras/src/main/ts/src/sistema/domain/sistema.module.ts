// ANGULAR
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CUSTOM_ELEMENTS_SCHEMA, LOCALE_ID, NgModule} from '@angular/core';
import {CommonModule, registerLocaleData} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientJsonpModule, HttpClientModule} from '@angular/common/http';

import {CovalentSearchModule} from '@covalent/core/search'
// MODULES
import {SharedModule} from '../../shared/shared.module';
// UTILS
import {Interceptor} from '../application/interceptor/interceptor';
import {Describer} from '../application/describer/describer';
import {FocusOnInitDirective} from '../application/utils/focus-on-init.directive';
// SERVICES
import {AuthenticationService} from './services/authentication.service';
import {UsuarioService} from './services/usuario.service';
import {WildcardService} from './services/wildcard.service';
import {MessageService} from './services/message.service';
import {DialogService} from './services/dialog.service';
// PIPES
import {UserInitialsPipe} from '../application/controls/pipes/user-initials.pipe';
// COMPONENTS
import {SistemaComponent} from './presentation/sistema.component';
import {LoginComponent} from './presentation/login/login.component';
import {DashboardViewComponent} from './presentation/dashboard/dashboard-view.component';
// CONTROLS
import {HorizontalSpaceComponent} from '../application/controls/horizontal-space.component';
import {VerticalSpaceComponent} from '../application/controls/vertical-space.component';
import {DeleteDialogComponent} from '../application/controls/delete-dialog/delete-dialog.component';
import {CrudViewComponent} from '../application/controls/crud/crud-view.component';
import {ListPageComponent} from '../application/controls/crud/list/list-page.component';
import {DetailPageComponent} from '../application/controls/crud/detail/detail-page.component';
import {FormPageComponent} from '../application/controls/crud/form/form-page.component';
import {NoRecordsFoundComponent} from '../application/controls/no-records-found/no-records-found.component';
// =============================================
// CADASTROS
// =============================================
// USUARIOS
import {AlterarSenhaDialogComponent} from './presentation/dashboard/configuracoes/usuario/alterar-senha-dialog.component';
// INTERNACIONALIZACAO MATERIAL PAGINATOR
import {getPaginatorIntl} from './services/portuguese-paginator-intl';
import {MatPaginatorIntl, MatTreeModule} from '@angular/material';

import localePt from '@angular/common/locales/pt';
/**
 * The internationalization (i18n) library for Angular 2+
 * https://github.com/ngx-translate/core
 */
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {PaginationService} from './services/pagination.service';
import {SetorService} from './services/setor.service';
import {SistemaRoutingModule} from "./sistema.routing.module";
import {ConsultarCategoriasComponent} from "./presentation/dashboard/cadastros/categoria/consultar-categorias/consultar-categorias.component";
import {VisualizarCategoriaComponent} from "./presentation/dashboard/cadastros/categoria/visualizar-categoria/visualizar-categoria.component";
import {AlterarCategoriaComponent} from "./presentation/dashboard/cadastros/categoria/alterar-categoria/alterar-categoria.component";
import {InserirCategoriaComponent} from "./presentation/dashboard/cadastros/categoria/inserir-categoria/inserir-categoria.component";
import {CategoriaFormComponent} from "./presentation/dashboard/cadastros/categoria/inserir-categoria/categoria-form/categoria-form.component";
import {CategoriaRepository} from "./repository/categoria.repository";
import {CadastrosViewComponent} from "./presentation/dashboard/cadastros/cadastros-view.component";
import {ConsultarTiposDocumentosComponent} from "./presentation/dashboard/cadastros/tipo-documento/consultar-tipos-documentos/consultar-tipos-documentos.component";
import {ConsultarTiposCadastrosComponent} from "./presentation/dashboard/cadastros/tipo-cadastro/consultar-tipos-cadastros/consultar-tipos-cadastros.component";
import {VisualizarTipoDocumentoComponent} from "./presentation/dashboard/cadastros/tipo-documento/visualizar-tipo-documento/visualizar-tipo-documento.component";
import {AlterarTipoDocumentoComponent} from "./presentation/dashboard/cadastros/tipo-documento/alterar-tipo-documento/alterar-tipo-documento.component";
import {InserirTipoDocumentoComponent} from "./presentation/dashboard/cadastros/tipo-documento/inserir-tipo-documento/inserir-tipo-documento.component";
import {TipoDocumentoFormComponent} from "./presentation/dashboard/cadastros/tipo-documento/inserir-tipo-documento/tipo-documento-form/tipo-documento-form.component";
import {TipoDocumentoRepository} from "./repository/tipo-documento.repository";
import {EmConstrucaoComponent} from "../application/controls/not-found/em-construcao.component";
import {ConfiguracoesViewComponent} from "./presentation/dashboard/configuracoes/configuracoes-view.component";
import {GrupoAcessoRepository} from "./repository/grupo-acesso.repository";
import {ConsultarGruposAcessoComponent} from "./presentation/dashboard/configuracoes/grupo-acesso/consultar-grupos-acessos/consultar-grupos-acesso.component";
import {InserirGrupoAcessoComponent} from "./presentation/dashboard/configuracoes/grupo-acesso/inserir-grupo-acesso/inserir-grupo-acesso.component";
import {GrupoAcessoFormComponent} from "./presentation/dashboard/configuracoes/grupo-acesso/inserir-grupo-acesso/grupo-acesso-form/grupo-acesso-form.component";
import {VisualizarGrupoAcessoComponent} from "./presentation/dashboard/configuracoes/grupo-acesso/visualizar-grupo-acesso/visualizar-grupo-acesso.component";
import {AlterarGrupoAcessoComponent} from "./presentation/dashboard/configuracoes/grupo-acesso/alterar-grupo-acesso/alterar-grupo-acesso.component";
import {UsuarioRepository} from "./repository/usuario.repository";
import {VisualizarUsuarioComponent} from "./presentation/dashboard/configuracoes/usuario/visualizar-usuario/visualizar-usuario.component";
import {ConsultarUsuariosComponent} from "./presentation/dashboard/configuracoes/usuario/consultar-usuarios/consultar-usuarios.component";
import {EditarUsuarioComponent} from "./presentation/dashboard/configuracoes/usuario/alterar-usuario/editar-usuario.component";
import {UsuarioFormComponent} from "./presentation/dashboard/configuracoes/usuario/inserir-usuario/usuario-form/usuario-form.component";
import {InserirUsuarioComponent} from "./presentation/dashboard/configuracoes/usuario/inserir-usuario/inserir-usuario.component";
import {AdministradorFormComponent} from "./presentation/dashboard/configuracoes/usuario/inserir-usuario/usuario-form/administrador-form/administrador-form.component";
import {VincularPermissoesComponent} from "./presentation/dashboard/configuracoes/grupo-acesso/inserir-grupo-acesso/grupo-acesso-form/vincular-permissoes/vincular-permissoes.component";
import {PermissaoRepository} from "./repository/permissao.repository";
import {UsuarioViewComponent} from "./presentation/dashboard/configuracoes/usuario/usuario-view.component";
import {GrupoAcessoViewComponent} from "./presentation/dashboard/configuracoes/grupo-acesso/grupo-acesso-view.component";
import {TipoDocumentoViewComponent} from "./presentation/dashboard/cadastros/tipo-documento/tipo-documento-view.component";
import {CategoriaViewComponent} from "./presentation/dashboard/cadastros/categoria/categoria-view.component";
import {TipoCadastroViewComponent} from "./presentation/dashboard/cadastros/tipo-cadastro/tipo-cadastro-view.component";
import {TipoCadastroRepository} from './repository/tipo-cadastro.repository';
import {InserirTipoCadastroComponent} from './presentation/dashboard/cadastros/tipo-cadastro/inserir-tipo-cadastro/inserir-tipo-cadastro.component';
import {TipoCadastroFormComponent} from './presentation/dashboard/cadastros/tipo-cadastro/inserir-tipo-cadastro/tipo-cadastro-form/tipo-cadastro-form.component';
import {VisualizarTipoCadastroComponent} from './presentation/dashboard/cadastros/tipo-cadastro/visualizar-tipo-cadastro/visualizar-tipo-cadastro.component';
import {AlterarTipoCadastroComponent} from './presentation/dashboard/cadastros/tipo-cadastro/alterar-tipo-cadastro/alterar-tipo-cadastro.component';
import {PublicacoesViewComponent} from "./presentation/dashboard/publicacoes/publicacoes-view.component";
import {AvisosEditaisViewComponent} from "./presentation/dashboard/publicacoes/avisos-editais/avisos-editais-view.component";
import {AvisosContratacoesViewComponent} from "./presentation/dashboard/publicacoes/avisos-contratacoes/avisos-contratacoes-view.component";
import {InserirAvisoContratacaoComponent} from "./presentation/dashboard/publicacoes/avisos-contratacoes/inserir-aviso-contratacao/inserir-aviso-contratacao.component";
import {AvisoContratacaoFormComponent} from "./presentation/dashboard/publicacoes/avisos-contratacoes/inserir-aviso-contratacao/aviso-contratacao-form/aviso-contratacao-form.component";
import {AvisoContratacaoRepository} from "./repository/aviso-contratacao.repository";
import {EvDatepicker} from "../application/controls/ev-datepicker/ev-datepicker";
import {ConsultarAvisosContratacoesComponent} from "./presentation/dashboard/publicacoes/avisos-contratacoes/consultar-avisos-contratacoes/consultar-avisos-contratacoes.component";
import {AlterarAvisoContratacaoComponent} from "./presentation/dashboard/publicacoes/avisos-contratacoes/alterar-aviso-contratacao/alterar-aviso-contratacao.component";
import {AnexoDialogComponent} from "../application/controls/anexo-dialog/anexo-dialog.component";
import {ExtratoContratoRepository} from "./repository/extrato-contrato.repository";
import {ExtratosContratoViewComponent} from "./presentation/dashboard/publicacoes/extratos-contratos/extratos-contrato-view.component";
import {InserirExtratoContratoComponent} from "./presentation/dashboard/publicacoes/extratos-contratos/inserir-extrato-contratacao/inserir-extrato-contrato.component";
import {AlterarExtratoContratoComponent} from "./presentation/dashboard/publicacoes/extratos-contratos/alterar-extrato-contratacao/alterar-extrato-contrato.component";
import {ExtratoContratoFormComponent} from "./presentation/dashboard/publicacoes/extratos-contratos/inserir-extrato-contratacao/extrato-contratacao-form/extrato-contrato-form.component";
import {ConsultarExtratosContratosComponent} from "./presentation/dashboard/publicacoes/extratos-contratos/consultar-extratos-contratacoes/consultar-extratos-contratos.component";
import {AvisoEditalFormComponent} from "./presentation/dashboard/publicacoes/avisos-editais/inserir-aviso-edital/aviso-edital-form/aviso-edital-form.component";
import {InserirAvisoEditalComponent} from "./presentation/dashboard/publicacoes/avisos-editais/inserir-aviso-edital/inserir-aviso-edital.component";
import {AvisoEditalRepository} from "./repository/aviso-edital.repository";
import {ConsultarAvisosEditaisComponent} from "./presentation/dashboard/publicacoes/avisos-editais/consultar-aviso-edital/consultar-avisos-editais.component";
import {AlterarAvisoEditalComponent} from "./presentation/dashboard/publicacoes/avisos-editais/alterar-aviso-edital/alterar-aviso-edital.component";
import {FirstUppercasePipe} from "../application/utils/utils";
import {VincularDocumentosComponent} from "./presentation/dashboard/cadastros/tipo-cadastro/inserir-tipo-cadastro/tipo-cadastro-form/vincular-documentos/vincular-documentos.component";
import {FileRepository} from "../application/upload-file-repository/file.repository";
import {VincularAnexosComponent} from "./presentation/dashboard/publicacoes/vincular-anexos/vincular-anexos.component";
import {AnexoComponent} from "./presentation/dashboard/publicacoes/vincular-anexos/anexo/anexo.component";
import {VisualizarAnexoDialogComponent} from "./presentation/dashboard/publicacoes/vincular-anexos/anexo/visualizar-anexo-dialog/visualizar-anexo-dialog.component";
import {FileDropModule} from "ngx-file-drop";
import {HasPermissionDirective} from "../application/has-permission/has-permission";
import {InserirFornecedorComponent} from "./presentation/dashboard/fornecedor/inserir-fornecedor/inserir-fornecedor.component";
import {ConsultarFornecedoresComponent} from "./presentation/dashboard/fornecedor/consultar-fornecedores/consultar-fornecedores.component";
import {AnalisarFornecedorComponent} from "./presentation/dashboard/fornecedor/analisar-fornecedor/analisar-fornecedor.component";
import {AlterarFornecedorComponent} from "./presentation/dashboard/fornecedor/alterar-fornecedor/alterar-fornecedor.component";
import {FornecedorViewComponent} from "./presentation/dashboard/fornecedor/fornecedor-view.component";
import {DadosGeraisFormComponent} from "./presentation/dashboard/fornecedor/inserir-fornecedor/dados-gerais-form/dados-gerais-form.component";
import {ContatosFormComponent} from "./presentation/dashboard/fornecedor/inserir-fornecedor/contatos-form/contatos-form.component";
import {DadosBancariosFormComponent} from "./presentation/dashboard/fornecedor/inserir-fornecedor/dados-bancarios-form/dados-bancarios-form.component";
import {DocumentosFormComponent} from "./presentation/dashboard/fornecedor/inserir-fornecedor/documentos-form/documentos-form.component";
import {FornecedorPessoaJuridicaFormComponent} from "./presentation/dashboard/fornecedor/inserir-fornecedor/dados-gerais-form/pessoa-juridica/fornecedor-pessoa-juridica-form.component";
import {FornecedorPessoaFisicaFormComponent} from "./presentation/dashboard/fornecedor/inserir-fornecedor/dados-gerais-form/pessoa-fisica/fornecedor-pessoa-fisica-form.component";
import {CnpjValidator, CpfValidator} from "../application/controls/validators/validators";
import {EnderecoRepository} from './repository/endereco.repository';
import {AtividadeEconomicaRepository} from "./repository/atividade-economica.repository";
import {BancoRepository} from './repository/banco.repository';
import {VisualizarDocumentoDialogComponent} from './presentation/dashboard/fornecedor/inserir-fornecedor/documentos-form/vincular-documentos/documento/visualizar-documento-dialog/visualizar-documento-dialog.component';
import {DocumentoComponent} from './presentation/dashboard/fornecedor/inserir-fornecedor/documentos-form/vincular-documentos/documento/documento.component';
import {ListagemDocumentosComponent} from './presentation/dashboard/fornecedor/inserir-fornecedor/documentos-form/vincular-documentos/listagem-documentos.component';
import {FornecedorRepository} from "./repository/fornecedor.repository";
import {MinhaContaComponent} from "./presentation/dashboard/fornecedor/minha-conta/minha-conta.component";
import {DocumentoPipe} from "../application/controls/documento-pipe/documento-pipe";
import {FeedbackFormComponent} from "./presentation/dashboard/fornecedor/analisar-fornecedor/feedback-form/feedback-form.component";
import {ConfirmacaoRecusaComponent} from "../application/controls/confirmacao-recusa/confirmacao-recusa.component";
import {ConfirmacaoEnvioParaAprovacaoComponent} from "../application/controls/confirmacao-envio-para-aprovacao/confirmacao-envio-para-aprovacao.component";
import {MeusDadosGeraisComponent} from "./presentation/dashboard/fornecedor/minha-conta/meus-dados-gerais/meus-dados-gerais.component";
import {MeusDocumentosComponent} from "./presentation/dashboard/fornecedor/minha-conta/meus-documentos/meus-documentos.component";
import {MeusDadosBancariosComponent} from "./presentation/dashboard/fornecedor/minha-conta/meus-dados-bancarios/meus-dados-bancarios.component";
import {MeusContatosComponent} from "./presentation/dashboard/fornecedor/minha-conta/meus-contatos/meus-contatos.component";
import {InicioComponent} from "./presentation/dashboard/fornecedor/minha-conta/inicio/inicio.component";
import {DashboardComponent} from "./presentation/dashboard/dashboard.component";
import {EnviarEmailEmMassaDialogComponent} from "../application/controls/enviar-email-em-massa-dialog/enviar-email-em-massa-dialog.component";
import {VincularAnexosEmailComponent} from "./presentation/dashboard/fornecedor/consultar-fornecedores/vincular-anexos-email/vincular-anexos-email.component";
import {AnexoEmailComponent} from "./presentation/dashboard/fornecedor/consultar-fornecedores/vincular-anexos-email/anexo-email/anexo-email.component";
import {VisualizarAnexoEmailDialogComponent} from "./presentation/dashboard/fornecedor/consultar-fornecedores/vincular-anexos-email/anexo-email/visualizar-anexo-email-dialog/visualizar-anexo-email-dialog.component";
import {AlterarSenhaComponent} from "./presentation/dashboard/configuracoes/usuario/alterar-senha/alterar-senha.component";

registerLocaleData(localePt, 'pt-BR');


// Custom TranslateLoader while using AoT compilation
export function customTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

/**
 *
 */
@NgModule({
  declarations: [
    // COMPONENTS
    SistemaComponent,
    LoginComponent,
    DashboardViewComponent,

    // CONTROLS
    AnexoDialogComponent,
    CrudViewComponent,
    ListPageComponent,
    DetailPageComponent,
    FormPageComponent,
    NoRecordsFoundComponent,
    EmConstrucaoComponent,
    EvDatepicker,
    CpfValidator,
    CnpjValidator,
    DocumentoPipe,

    // PIPES
    UserInitialsPipe,
    FirstUppercasePipe,

    // Configurações
    ConfiguracoesViewComponent,

    // Grupos de acesso
    GrupoAcessoFormComponent,
    AlterarGrupoAcessoComponent,
    InserirGrupoAcessoComponent,
    ConsultarGruposAcessoComponent,
    VisualizarGrupoAcessoComponent,
    GrupoAcessoViewComponent,

    // Usuario
    UsuarioViewComponent,
    ConsultarUsuariosComponent,
    VisualizarUsuarioComponent,
    InserirUsuarioComponent,
    UsuarioFormComponent,
    EditarUsuarioComponent,
    AlterarSenhaDialogComponent,
    AdministradorFormComponent,
    VincularPermissoesComponent,
    AlterarSenhaComponent,

    //Cadastros
    CadastrosViewComponent,

    // Categoria
    CategoriaViewComponent,
    ConsultarCategoriasComponent,
    VisualizarCategoriaComponent,
    AlterarCategoriaComponent,
    InserirCategoriaComponent,
    CategoriaFormComponent,

    // Tipo Documento
    TipoDocumentoViewComponent,
    ConsultarTiposDocumentosComponent,
    VisualizarTipoDocumentoComponent,
    AlterarTipoDocumentoComponent,
    InserirTipoDocumentoComponent,
    TipoDocumentoFormComponent,

    // Tipo Cadastro
    TipoCadastroViewComponent,
    ConsultarTiposCadastrosComponent,
    VisualizarTipoCadastroComponent,
    AlterarTipoCadastroComponent,
    InserirTipoCadastroComponent,
    TipoCadastroFormComponent,
    VincularDocumentosComponent,

    // Publicações
    PublicacoesViewComponent,
    ExtratosContratoViewComponent,
    AvisosEditaisViewComponent,
    AvisosContratacoesViewComponent,

    // Avisos de Contratações
    InserirAvisoContratacaoComponent,
    AvisoContratacaoFormComponent,
    ConsultarAvisosContratacoesComponent,
    AlterarAvisoContratacaoComponent,

    // Extratos de contrataçoes
    InserirExtratoContratoComponent,
    AlterarExtratoContratoComponent,
    ExtratoContratoFormComponent,
    ConsultarExtratosContratosComponent,

    // Avisos de editais
    InserirAvisoEditalComponent,
    AvisoEditalFormComponent,
    ConsultarAvisosEditaisComponent,
    AlterarAvisoEditalComponent,

    // Anexos
    VisualizarAnexoDialogComponent,
    VincularAnexosComponent,
    AnexoComponent,

    // Documentos
    VisualizarDocumentoDialogComponent,
    ListagemDocumentosComponent,
    DocumentoComponent,

    // DIRECTIVES
    FocusOnInitDirective,

    // Has Permission
    HasPermissionDirective,

    // Fornecedor
    FornecedorViewComponent,
    InserirFornecedorComponent,
    DadosGeraisFormComponent,
    FornecedorPessoaFisicaFormComponent,
    FornecedorPessoaJuridicaFormComponent,
    ConsultarFornecedoresComponent,
    AnalisarFornecedorComponent,
    AlterarFornecedorComponent,
    ContatosFormComponent,
    DadosBancariosFormComponent,
    DocumentosFormComponent,
    MinhaContaComponent,
    FeedbackFormComponent,
    ConfirmacaoRecusaComponent,
    ConfirmacaoEnvioParaAprovacaoComponent,
    DashboardComponent,
    EnviarEmailEmMassaDialogComponent,
    VincularAnexosEmailComponent,
    VisualizarAnexoEmailDialogComponent,
    AnexoEmailComponent,

    // Visualizar Fornecedor
    MeusDadosGeraisComponent,
    MeusDocumentosComponent,
    MeusDadosBancariosComponent,
    MeusContatosComponent,
    InicioComponent
  ],
  imports: [
    SharedModule,
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    SistemaRoutingModule,
    HttpClientModule,
    CovalentSearchModule,
    MatTreeModule,
    FileDropModule,
    HttpClientJsonpModule,

    // Translate i18n
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (customTranslateLoader),
        deps: [HttpClient]
      }
    })
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  exports: [],
  entryComponents: [
    VisualizarAnexoDialogComponent,
    VisualizarDocumentoDialogComponent,
    AlterarSenhaDialogComponent,
    DeleteDialogComponent,
    AnexoDialogComponent,
    ConfirmacaoRecusaComponent,
    ConfirmacaoEnvioParaAprovacaoComponent,
    EnviarEmailEmMassaDialogComponent,
    VisualizarAnexoEmailDialogComponent,
    AlterarSenhaComponent
  ],
  providers: [
    //Infra
    FileRepository,


    // Repositories
    UsuarioRepository,
    CategoriaRepository,
    PermissaoRepository,
    AvisoEditalRepository,
    GrupoAcessoRepository,
    TipoCadastroRepository,
    TipoDocumentoRepository,
    AvisoContratacaoRepository,
    ExtratoContratoRepository,
    EnderecoRepository,
    AtividadeEconomicaRepository,
    BancoRepository,
    FornecedorRepository,

    // Services
    Describer,
    WildcardService,
    PaginationService,
    UsuarioService,
    SetorService,
    AuthenticationService,
    MessageService,
    DialogService,

    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    },

    // Internacionalizacao MatPaginator
    {provide: MatPaginatorIntl, useValue: getPaginatorIntl()},
    {provide: LOCALE_ID, useValue: 'pt-BR'}
  ],
  bootstrap: [SistemaComponent]
})
export class SistemaModule {
}
