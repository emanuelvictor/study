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
import {MessageService} from './services/message.service';
import {DialogService} from './services/dialog.service';
// PIPES
import {CapitalizePipe} from '../application/controls/pipes/capitalize.pipe';
import {UserInitialsPipe} from '../application/controls/pipes/user-initials.pipe';
// COMPONENTS
import {SistemaComponent} from './presentation/sistema.component';
import {DashboardViewComponent} from './presentation/dashboard-view.component';
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
import {AlterarSenhaDialogComponent} from './presentation/configuracoes/usuario/alterar-senha-dialog.component';
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
import {SistemaRoutingModule} from "./sistema.routing.module";
import {ConsultarCategoriasComponent} from "./presentation/cadastros/categoria/consultar-categorias/consultar-categorias.component";
import {VisualizarCategoriaComponent} from "./presentation/cadastros/categoria/visualizar-categoria/visualizar-categoria.component";
import {AlterarCategoriaComponent} from "./presentation/cadastros/categoria/alterar-categoria/alterar-categoria.component";
import {InserirCategoriaComponent} from "./presentation/cadastros/categoria/inserir-categoria/inserir-categoria.component";
import {CategoriaFormComponent} from "./presentation/cadastros/categoria/inserir-categoria/categoria-form/categoria-form.component";
import {CadastrosViewComponent} from "./presentation/cadastros/cadastros-view.component";
import {ConsultarTiposDocumentosComponent} from "./presentation/cadastros/tipo-documento/consultar-tipos-documentos/consultar-tipos-documentos.component";
import {ConsultarTiposCadastrosComponent} from "./presentation/cadastros/tipo-cadastro/consultar-tipos-cadastros/consultar-tipos-cadastros.component";
import {VisualizarTipoDocumentoComponent} from "./presentation/cadastros/tipo-documento/visualizar-tipo-documento/visualizar-tipo-documento.component";
import {AlterarTipoDocumentoComponent} from "./presentation/cadastros/tipo-documento/alterar-tipo-documento/alterar-tipo-documento.component";
import {InserirTipoDocumentoComponent} from "./presentation/cadastros/tipo-documento/inserir-tipo-documento/inserir-tipo-documento.component";
import {TipoDocumentoFormComponent} from "./presentation/cadastros/tipo-documento/inserir-tipo-documento/tipo-documento-form/tipo-documento-form.component";
import {EmConstrucaoComponent} from "../application/controls/not-found/em-construcao.component";
import {ConfiguracoesViewComponent} from "./presentation/configuracoes/configuracoes-view.component";
import {ConsultarGruposAcessoComponent} from "./presentation/configuracoes/grupo-acesso/consultar-grupos-acessos/consultar-grupos-acesso.component";
import {InserirGrupoAcessoComponent} from "./presentation/configuracoes/grupo-acesso/inserir-grupo-acesso/inserir-grupo-acesso.component";
import {GrupoAcessoFormComponent} from "./presentation/configuracoes/grupo-acesso/inserir-grupo-acesso/grupo-acesso-form/grupo-acesso-form.component";
import {VisualizarGrupoAcessoComponent} from "./presentation/configuracoes/grupo-acesso/visualizar-grupo-acesso/visualizar-grupo-acesso.component";
import {AlterarGrupoAcessoComponent} from "./presentation/configuracoes/grupo-acesso/alterar-grupo-acesso/alterar-grupo-acesso.component";
import {VisualizarUsuarioComponent} from "./presentation/configuracoes/usuario/visualizar-usuario/visualizar-usuario.component";
import {ConsultarUsuariosComponent} from "./presentation/configuracoes/usuario/consultar-usuarios/consultar-usuarios.component";
import {EditarUsuarioComponent} from "./presentation/configuracoes/usuario/alterar-usuario/editar-usuario.component";
import {UsuarioFormComponent} from "./presentation/configuracoes/usuario/inserir-usuario/usuario-form/usuario-form.component";
import {InserirUsuarioComponent} from "./presentation/configuracoes/usuario/inserir-usuario/inserir-usuario.component";
import {AdministradorFormComponent} from "./presentation/configuracoes/usuario/inserir-usuario/usuario-form/administrador-form/administrador-form.component";
import {VincularPermissoesComponent} from "./presentation/configuracoes/grupo-acesso/inserir-grupo-acesso/grupo-acesso-form/vincular-permissoes/vincular-permissoes.component";
import {UsuarioViewComponent} from "./presentation/configuracoes/usuario/usuario-view.component";
import {GrupoAcessoViewComponent} from "./presentation/configuracoes/grupo-acesso/grupo-acesso-view.component";
import {TipoDocumentoViewComponent} from "./presentation/cadastros/tipo-documento/tipo-documento-view.component";
import {CategoriaViewComponent} from "./presentation/cadastros/categoria/categoria-view.component";
import {TipoCadastroViewComponent} from "./presentation/cadastros/tipo-cadastro/tipo-cadastro-view.component";
import {InserirTipoCadastroComponent} from './presentation/cadastros/tipo-cadastro/inserir-tipo-cadastro/inserir-tipo-cadastro.component';
import {TipoCadastroFormComponent} from './presentation/cadastros/tipo-cadastro/inserir-tipo-cadastro/tipo-cadastro-form/tipo-cadastro-form.component';
import {VisualizarTipoCadastroComponent} from './presentation/cadastros/tipo-cadastro/visualizar-tipo-cadastro/visualizar-tipo-cadastro.component';
import {AlterarTipoCadastroComponent} from './presentation/cadastros/tipo-cadastro/alterar-tipo-cadastro/alterar-tipo-cadastro.component';
import {PublicacoesViewComponent} from "./presentation/publicacoes/publicacoes-view.component";
import {AvisosEditaisViewComponent} from "./presentation/publicacoes/avisos-editais/avisos-editais-view.component";
import {AvisosContratacoesViewComponent} from "./presentation/publicacoes/avisos-contratacoes/avisos-contratacoes-view.component";
import {InserirAvisoContratacaoComponent} from "./presentation/publicacoes/avisos-contratacoes/inserir-aviso-contratacao/inserir-aviso-contratacao.component";
import {AvisoContratacaoFormComponent} from "./presentation/publicacoes/avisos-contratacoes/inserir-aviso-contratacao/aviso-contratacao-form/aviso-contratacao-form.component";
import {EditoraRepository} from "./repository/editora.repository";
import {EvDatepicker} from "../application/controls/ev-datepicker/ev-datepicker";
import {ConsultarAvisosContratacoesComponent} from "./presentation/publicacoes/avisos-contratacoes/consultar-avisos-contratacoes/consultar-avisos-contratacoes.component";
import {AlterarAvisoContratacaoComponent} from "./presentation/publicacoes/avisos-contratacoes/alterar-aviso-contratacao/alterar-aviso-contratacao.component";
import {AnexoDialogComponent} from "../application/controls/anexo-dialog/anexo-dialog.component";
import {ExtratosContratoViewComponent} from "./presentation/publicacoes/extratos-contratos/extratos-contrato-view.component";
import {InserirExtratoContratoComponent} from "./presentation/publicacoes/extratos-contratos/inserir-extrato-contratacao/inserir-extrato-contrato.component";
import {AlterarExtratoContratoComponent} from "./presentation/publicacoes/extratos-contratos/alterar-extrato-contratacao/alterar-extrato-contrato.component";
import {ExtratoContratoFormComponent} from "./presentation/publicacoes/extratos-contratos/inserir-extrato-contratacao/extrato-contratacao-form/extrato-contrato-form.component";
import {ConsultarExtratosContratosComponent} from "./presentation/publicacoes/extratos-contratos/consultar-extratos-contratacoes/consultar-extratos-contratos.component";
import {AvisoEditalFormComponent} from "./presentation/publicacoes/avisos-editais/inserir-aviso-edital/aviso-edital-form/aviso-edital-form.component";
import {InserirAvisoEditalComponent} from "./presentation/publicacoes/avisos-editais/inserir-aviso-edital/inserir-aviso-edital.component";
import {LivroRepository} from "./repository/livro.repository";
import {ConsultarAvisosEditaisComponent} from "./presentation/publicacoes/avisos-editais/consultar-aviso-edital/consultar-avisos-editais.component";
import {AlterarAvisoEditalComponent} from "./presentation/publicacoes/avisos-editais/alterar-aviso-edital/alterar-aviso-edital.component";
import {FirstUppercasePipe} from "../application/utils/utils";
import {VincularDocumentosComponent} from "./presentation/cadastros/tipo-cadastro/inserir-tipo-cadastro/tipo-cadastro-form/vincular-documentos/vincular-documentos.component";
import {VincularAnexosComponent} from "./presentation/publicacoes/vincular-anexos/vincular-anexos.component";
import {AnexoComponent} from "./presentation/publicacoes/vincular-anexos/anexo/anexo.component";
import {VisualizarAnexoDialogComponent} from "./presentation/publicacoes/vincular-anexos/anexo/visualizar-anexo-dialog/visualizar-anexo-dialog.component";
import {InserirFornecedorComponent} from "./presentation/fornecedor/inserir-fornecedor/inserir-fornecedor.component";
import {ConsultarFornecedoresComponent} from "./presentation/fornecedor/consultar-fornecedores/consultar-fornecedores.component";
import {AnalisarFornecedorComponent} from "./presentation/fornecedor/analisar-fornecedor/analisar-fornecedor.component";
import {AlterarFornecedorComponent} from "./presentation/fornecedor/alterar-fornecedor/alterar-fornecedor.component";
import {FornecedorViewComponent} from "./presentation/fornecedor/fornecedor-view.component";
import {DadosGeraisFormComponent} from "./presentation/fornecedor/inserir-fornecedor/dados-gerais-form/dados-gerais-form.component";
import {ContatosFormComponent} from "./presentation/fornecedor/inserir-fornecedor/contatos-form/contatos-form.component";
import {DadosBancariosFormComponent} from "./presentation/fornecedor/inserir-fornecedor/dados-bancarios-form/dados-bancarios-form.component";
import {DocumentosFormComponent} from "./presentation/fornecedor/inserir-fornecedor/documentos-form/documentos-form.component";
import {FornecedorPessoaJuridicaFormComponent} from "./presentation/fornecedor/inserir-fornecedor/dados-gerais-form/pessoa-juridica/fornecedor-pessoa-juridica-form.component";
import {FornecedorPessoaFisicaFormComponent} from "./presentation/fornecedor/inserir-fornecedor/dados-gerais-form/pessoa-fisica/fornecedor-pessoa-fisica-form.component";
import {CnpjValidator, CpfValidator} from "../application/controls/validators/validators";
import {VisualizarDocumentoDialogComponent} from './presentation/fornecedor/inserir-fornecedor/documentos-form/vincular-documentos/documento/visualizar-documento-dialog/visualizar-documento-dialog.component';
import {DocumentoComponent} from './presentation/fornecedor/inserir-fornecedor/documentos-form/vincular-documentos/documento/documento.component';
import {ListagemDocumentosComponent} from './presentation/fornecedor/inserir-fornecedor/documentos-form/vincular-documentos/listagem-documentos.component';
import {MinhaContaComponent} from "./presentation/fornecedor/minha-conta/minha-conta.component";
import {DocumentoPipe} from "../application/controls/documento-pipe/documento-pipe";
import {FeedbackFormComponent} from "./presentation/fornecedor/analisar-fornecedor/feedback-form/feedback-form.component";
import {ConfirmacaoRecusaComponent} from "../application/controls/confirmacao-recusa/confirmacao-recusa.component";

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
    DashboardViewComponent,

    // CONTROLS
    HorizontalSpaceComponent,
    VerticalSpaceComponent,
    DeleteDialogComponent,
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

    // PIPES
    CapitalizePipe,
    UserInitialsPipe,
    FirstUppercasePipe,

    // DIRECTIVES
    FocusOnInitDirective,

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
    ConfirmacaoRecusaComponent
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
    ConfirmacaoRecusaComponent
  ],
  providers: [
    // Repositories
    LivroRepository,
    EditoraRepository,

    // Services
    Describer,
    PaginationService,
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
