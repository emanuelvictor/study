import {SharedModule} from '../../shared/shared.module';
import {Interceptor} from '../application/interceptor/interceptor';
import {Describer} from '../application/describer/describer';
import {AuthenticationService} from './services/authentication.service';
import {UsuarioService} from './services/usuario.service';
import {WildcardService} from './services/wildcard.service';
import {MessageService} from './services/message.service';
import {DialogService} from './services/dialog.service';
import {SistemaComponent} from '../application/presentation/sistema.component';
import {LoginComponent} from '../application/presentation/login/login.component';
import {AuthenticatedViewComponent} from '../application/presentation/authenticated/authenticated-view.component';
import {DeleteDialogComponent} from '../application/controls/delete-dialog/delete-dialog.component';
import {CrudViewComponent} from '../application/controls/crud/crud-view.component';
import {ListPageComponent} from '../application/controls/crud/list/list-page.component';
import {DetailPageComponent} from '../application/controls/crud/detail/detail-page.component';
import {FormPageComponent} from '../application/controls/crud/form/form-page.component';
import {AlterarSenhaDialogComponent} from '../application/presentation/authenticated/configuracoes/usuario/alterar-senha-dialog.component';
import {getPaginatorIntl} from './services/portuguese-paginator-intl';
import {PaginationService} from './services/pagination.service';
import {SistemaRoutingModule} from "./sistema.routing.module";
import {EmConstrucaoComponent} from "../application/controls/not-found/em-construcao.component";
import {GrupoAcessoRepository} from "./repository/grupo-acesso.repository";
import {ConsultarGruposAcessoComponent} from "../application/presentation/authenticated/configuracoes/grupo-acesso/consultar-grupos-acessos/consultar-grupos-acesso.component";
import {InserirGrupoAcessoComponent} from "../application/presentation/authenticated/configuracoes/grupo-acesso/inserir-grupo-acesso/inserir-grupo-acesso.component";
import {GrupoAcessoFormComponent} from "../application/presentation/authenticated/configuracoes/grupo-acesso/inserir-grupo-acesso/grupo-acesso-form/grupo-acesso-form.component";
import {VisualizarGrupoAcessoComponent} from "../application/presentation/authenticated/configuracoes/grupo-acesso/visualizar-grupo-acesso/visualizar-grupo-acesso.component";
import {AlterarGrupoAcessoComponent} from "../application/presentation/authenticated/configuracoes/grupo-acesso/alterar-grupo-acesso/alterar-grupo-acesso.component";
import {UsuarioRepository} from "./repository/usuario.repository";
import {VisualizarUsuarioComponent} from "../application/presentation/authenticated/configuracoes/usuario/visualizar-usuario/visualizar-usuario.component";
import {ConsultarUsuariosComponent} from "../application/presentation/authenticated/configuracoes/usuario/consultar-usuarios/consultar-usuarios.component";
import {EditarUsuarioComponent} from "../application/presentation/authenticated/configuracoes/usuario/alterar-usuario/editar-usuario.component";
import {UsuarioFormComponent} from "../application/presentation/authenticated/configuracoes/usuario/inserir-usuario/usuario-form/usuario-form.component";
import {InserirUsuarioComponent} from "../application/presentation/authenticated/configuracoes/usuario/inserir-usuario/inserir-usuario.component";
import {RootFormComponent} from "../application/presentation/authenticated/configuracoes/usuario/inserir-usuario/usuario-form/root-form/root-form.component";
import {VincularPermissoesComponent} from "../application/presentation/authenticated/configuracoes/grupo-acesso/inserir-grupo-acesso/grupo-acesso-form/vincular-permissoes/vincular-permissoes.component";
import {PermissaoRepository} from "./repository/permissao.repository";
import {UsuarioViewComponent} from "../application/presentation/authenticated/configuracoes/usuario/usuario-view.component";
import {GrupoAcessoViewComponent} from "../application/presentation/authenticated/configuracoes/grupo-acesso/grupo-acesso-view.component";
import {EvDatepicker} from "../application/controls/ev-datepicker/ev-datepicker";
import {FirstUppercasePipe} from "../application/utils/utils";
import {FileRepository} from "./repository/file.repository";
import {HasPermissionDirective} from "../application/has-permission/has-permission";
import {CnpjValidator, CpfValidator} from "../application/controls/validators/validators";
import {DocumentoPipe} from "../application/controls/documento-pipe/documento-pipe";
import {AlterarSenhaComponent} from "../application/presentation/authenticated/configuracoes/usuario/alterar-senha/alterar-senha.component";
import {UserInitialsPipe} from "../application/controls/pipes/user-initials.pipe";
import {FormToolbarComponent} from 'sistema/application/controls/crud/cadastros/form-toolbar/form-toolbar.component';
import {ListTableComponent} from 'sistema/application/controls/crud/cadastros/list-table/list-table.component';
import {EntityFormComponent} from 'sistema/application/controls/crud/cadastros/entity-form/entity-form.component';
import {ButtonToggleAdvancedFiltersComponent} from 'sistema/application/controls/button-toggle-advanced-filters/button-toggle-advanced-filters.component';
import {ButtonClearFiltersComponent} from 'sistema/application/controls/button-clear-filters/button-clear-filters.component';
import {NoRecordsFoundComponent} from "sistema/application/controls/no-records-found/no-records-found.component";
import {FilterPipe} from "../application/controls/pipes/filter.pipe";
import {ConfiguracoesViewComponent} from "../application/presentation/authenticated/configuracoes/configuracoes-view.component";
import {InventarioRepository} from "./repository/inventario.repository";
import {CentroCustoRepository} from "./repository/centro-custo.repository";
import {NoSubmitDirective} from "../application/controls/no-sumbit/no-submit.directive";
import {PatrimonioRepository} from "./repository/patrimonio.repository";
import {CUSTOM_ELEMENTS_SCHEMA, LOCALE_ID, NgModule} from "@angular/core";
import {CommonModule, registerLocaleData} from "@angular/common";
import {
  MAT_FORM_FIELD_DEFAULT_OPTIONS,
  MatFormFieldDefaultOptions,
  MatPaginatorIntl,
  MatTreeModule
} from "@angular/material";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientJsonpModule, HttpClientModule} from "@angular/common/http";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import localePt from '@angular/common/locales/pt';
import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {CovalentSearchModule} from "@covalent/core";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {DadoComponent} from "../application/controls/dado/dado.component";
import {PieChartModule} from "@swimlane/ngx-charts";
import {CentroCustoInventarioRepository} from "./repository/centro-custo-inventario.repository";

const appearance: MatFormFieldDefaultOptions = {
  appearance: 'outline'
};

registerLocaleData(localePt, 'pt-BR');

// Custom TranslateLoader while using AoT compilation
export function customTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

/**
 *
 */
// @ts-ignore
@NgModule({
  declarations: [
    // Directives
    NoSubmitDirective,

    // PIPES
    FilterPipe,
    UserInitialsPipe,

    // COMPONENTS
    SistemaComponent,
    LoginComponent,
    AuthenticatedViewComponent,

    // CONTROLS
    CrudViewComponent,
    ListPageComponent,
    DetailPageComponent,
    FormPageComponent,
    EmConstrucaoComponent,
    EvDatepicker,
    CpfValidator,
    CnpjValidator,
    DocumentoPipe,
    ButtonToggleAdvancedFiltersComponent,
    ButtonClearFiltersComponent,
    NoRecordsFoundComponent,

    FirstUppercasePipe,

    //Cadastros
    FormToolbarComponent,
    ListTableComponent,
    EntityFormComponent,

    // Configuracoes
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
    RootFormComponent,
    VincularPermissoesComponent,
    AlterarSenhaComponent,

    DadoComponent,

    // Has Permission
    HasPermissionDirective,

  ],
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    SistemaRoutingModule,
    HttpClientModule,
    CovalentSearchModule,
    MatTreeModule,
    HttpClientJsonpModule,

    SharedModule,

    // Translate i18n
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (customTranslateLoader),
        deps: [HttpClient]
      }
    }),
    PieChartModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  exports: [NoSubmitDirective],
  entryComponents: [
    DeleteDialogComponent,
    AlterarSenhaComponent,
    AlterarSenhaDialogComponent,
  ],
  providers: [

    //Infra
    FileRepository,

    // Repositories
    UsuarioRepository,
    PermissaoRepository,
    PatrimonioRepository,
    InventarioRepository,
    GrupoAcessoRepository,
    CentroCustoRepository,
    CentroCustoInventarioRepository,

    // Services
    Describer,
    UsuarioService,
    WildcardService,
    PaginationService,
    AuthenticationService,

    UsuarioViewComponent,
    ConfiguracoesViewComponent,

    DialogService,
    MessageService,

    {
      useValue: appearance,
      provide: MAT_FORM_FIELD_DEFAULT_OPTIONS
    },

    {
      multi: true,
      useClass: Interceptor,
      provide: HTTP_INTERCEPTORS
    },

    // Internacionalizacao MatPaginator
    {provide: MatPaginatorIntl, useValue: getPaginatorIntl()},
    {provide: LOCALE_ID, useValue: 'pt-BR'}
  ],
  bootstrap: [SistemaComponent]
})
export class SistemaModule {
}
