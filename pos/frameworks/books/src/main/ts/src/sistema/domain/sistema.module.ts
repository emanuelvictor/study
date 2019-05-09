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
import {CadastrosViewComponent} from "./presentation/cadastros/cadastros-view.component";
import {EmConstrucaoComponent} from "../application/controls/not-found/em-construcao.component";
import {EditoraRepository} from "./repository/editora.repository";
import {EvDatepicker} from "../application/controls/ev-datepicker/ev-datepicker";
import {LivroRepository} from "./repository/livro.repository";
import {FirstUppercasePipe} from "../application/utils/utils";
import {CnpjValidator, CpfValidator} from "../application/controls/validators/validators";
import {DocumentoPipe} from "../application/controls/documento-pipe/documento-pipe";
import {VisualizarEditoraComponent} from "./presentation/cadastros/editora/visualizar-editora/visualizar-editora.component";
import {AlterarEditoraComponent} from "./presentation/cadastros/editora/alterar-editora/alterar-editora.component";
import {ConsultarEditorasComponent} from "./presentation/cadastros/editora/consultar-editoras/consultar-editoras.component";
import {EditoraViewComponent} from "./presentation/cadastros/editora/editora-view.component";
import {InserirEditoraComponent} from "./presentation/cadastros/editora/inserir-editora/inserir-editora.component";
import {EditoraFormComponent} from "./presentation/cadastros/editora/inserir-editora/editora-form/editora-form.component";
import {LivroViewComponent} from "./presentation/cadastros/livro/livro-view.component";
import {VisualizarLivroComponent} from "./presentation/cadastros/livro/visualizar-livro/visualizar-livro.component";
import {AlterarLivroComponent} from "./presentation/cadastros/livro/alterar-livro/alterar-livro.component";
import {ConsultarLivrosComponent} from "./presentation/cadastros/livro/consultar-livros/consultar-livros.component";
import {InserirLivroComponent} from "./presentation/cadastros/livro/inserir-livro/inserir-livro.component";
import {LivroFormComponent} from "./presentation/cadastros/livro/inserir-livro/livro-form/livro-form.component";

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

    //Cadastros
    CadastrosViewComponent,

    // Editora
    EditoraViewComponent,
    ConsultarEditorasComponent,
    VisualizarEditoraComponent,
    AlterarEditoraComponent,
    InserirEditoraComponent,
    EditoraFormComponent,

    // Livro
    LivroViewComponent,
    ConsultarLivrosComponent,
    VisualizarLivroComponent,
    AlterarLivroComponent,
    InserirLivroComponent,
    LivroFormComponent,

    // PIPES
    CapitalizePipe,
    UserInitialsPipe,
    FirstUppercasePipe,

    // DIRECTIVES
    FocusOnInitDirective,

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
    DeleteDialogComponent
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
