// ANGULAR
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CUSTOM_ELEMENTS_SCHEMA, LOCALE_ID, NgModule} from '@angular/core';
import {registerLocaleData} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientJsonpModule, HttpClientModule} from '@angular/common/http';

import {CovalentSearchModule} from '@covalent/core/search'
// MODULES
import {SharedModule} from '../../shared/shared.module';
// UTILS
import {Describer} from '../application/describer/describer';
import {AuthGuard} from './services/auth-guard.service';
import {FocusOnInitDirective} from './utils/focus-on-init.directive';
// SERVICES
import {WildcardService} from './services/wildcard.service';
// PIPES
import {UserInitialsPipe} from './pipes/user-initials.pipe';
// COMPONENTS
import {PortalComponent} from './views/portal.component';
import {HomeViewComponent} from './views/home/home-view.component';
// CONTROLS
import {NoRecordsFoundComponent} from '../application/controls/no-records-found/no-records-found.component';
// =============================================
// CADASTROS
// =============================================
// INTERNACIONALIZACAO MATERIAL PAGINATOR
import {getPaginatorIntl} from './services/portuguese-paginator-intl';
import {MatExpansionModule, MatPaginatorIntl} from '@angular/material';

import localePt from '@angular/common/locales/pt';
/**
 * The internationalization (i18n) library for Angular 2+
 * https://github.com/ngx-translate/core
 */
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {PaginationService} from './services/pagination.service';
import {PortalRoutingModule} from "./portal.routing.module";
import {AvisoEditalRepository} from 'sistema/domain/repository/aviso-edital.repository';
import {PrincipalComponent} from './views/home/principal/principal.component';
import {CadastrarFornecedorComponent} from './views/home/cadastros/fornecedor/cadastrar-fornecedor/cadastrar-fornecedor.component';
import {FornecedorViewComponent} from './views/home/cadastros/fornecedor/fornecedor-view.component';
import {TemplatePortalComponent} from 'portal/application/controls/template-portal/template-portal.component';
import {FileRepository} from 'sistema/application/upload-file-repository/file.repository';
import {LoginFornecedorComponent} from './views/home/cadastros/fornecedor/login-fornecedor/login-fornecedor.component';
import {PublicacoesViewComponent} from './views/home/publicacao/publicacoes-view.component';
import {ConsultarAvisosEditaisComponent} from './views/home/publicacao/avisos-editais/consultar-avisos-editais.component';
import {ConsultarAvisosContratacoesComponent} from './views/home/publicacao/avisos-contratacoes/consultar-avisos-contratacoes.component';
import {ConsultarExtratosContratosComponent} from './views/home/publicacao/extratos-contratos/consultar-extratos-contratos.component';
import {AvisoContratacaoRepository} from 'sistema/domain/repository/aviso-contratacao.repository';
import {ExtratoContratoRepository} from 'sistema/domain/repository/extrato-contrato.repository';
import {RecaptchaModule} from "ng-recaptcha";
import {RecaptchaFormsModule} from "ng-recaptcha/forms";
import {FornecedorRepository} from "../../sistema/domain/repository/fornecedor.repository";
import {AuthenticationService} from "../../sistema/domain/services/authentication.service";
import {UsuarioRepository} from "../../sistema/domain/repository/usuario.repository";
import {MessageService} from "../../sistema/domain/services/message.service";
import {Interceptor} from "../../sistema/application/interceptor/interceptor";
import {AtividadeEconomicaRepository} from "../../sistema/domain/repository/atividade-economica.repository";
import {DeleteDialogComponent} from "../../sistema/application/controls/delete-dialog/delete-dialog.component";
import {DialogService} from "../../sistema/domain/services/dialog.service";
import {CategoriaRepository} from "../../sistema/domain/repository/categoria.repository";
import {EnderecoRepository} from "../../sistema/domain/repository/endereco.repository";

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
    PortalComponent,
    HomeViewComponent,

    // CONTROLS
    NoRecordsFoundComponent,
    TemplatePortalComponent,

    // Principal
    PrincipalComponent,

    // Fornecedor
    FornecedorViewComponent,
    CadastrarFornecedorComponent,
    LoginFornecedorComponent,

    // Publicacao
    PublicacoesViewComponent,
    ConsultarAvisosEditaisComponent,
    ConsultarAvisosContratacoesComponent,
    ConsultarExtratosContratosComponent,

    // // Usuario
    // ConsultarUsuariosComponent,
    // DetalhesUsuarioComponent,
    // EditarUsuarioComponent,
    // AlterarSenhaDialogComponent,

    // PIPES
    UserInitialsPipe,

    // DIRECTIVES
    FocusOnInitDirective,

  ],
  imports: [
    SharedModule,
    BrowserModule,
    BrowserAnimationsModule,
    PortalRoutingModule,
    HttpClientModule,
    CovalentSearchModule,
    MatExpansionModule,
    HttpClientJsonpModule,

    RecaptchaModule,
    RecaptchaFormsModule,

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
    DeleteDialogComponent
  ],
  providers: [
    //Infra
    FileRepository,

    // Repositories
    AvisoEditalRepository,
    AvisoContratacaoRepository,
    ExtratoContratoRepository,
    UsuarioRepository,
    EnderecoRepository,
    AtividadeEconomicaRepository,
    FornecedorRepository,
    CategoriaRepository,

    // Services
    Describer,
    WildcardService,
    PaginationService,
    AuthenticationService,
    MessageService,
    DialogService,

    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    },

    // Internacionalizacao MatPaginator
    {provide: MatPaginatorIntl, useValue: getPaginatorIntl()},
    {provide: LOCALE_ID, useValue: 'pt-BR'}
  ],
  bootstrap: [PortalComponent]
})
export class PortalModule {
}
