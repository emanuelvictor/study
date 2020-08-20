import {SharedModule} from '../../shared/shared.module';
import {Interceptor} from '../application/interceptor/interceptor';
import {Describer} from '../application/describer/describer';
import {AuthenticationService} from './services/authentication.service';
import {WildcardService} from './services/wildcard.service';
import {MessageService} from './services/message.service';
import {DialogService} from './services/dialog.service';
import {LoginComponent} from '../application/presentation/login/login.component';
import {AuthenticatedViewComponent} from '../application/presentation/authenticated/authenticated-view.component';
import {DeleteDialogComponent} from '../application/controls/delete-dialog/delete-dialog.component';
import {CrudViewComponent} from '../application/controls/crud/crud-view.component';
import {ListPageComponent} from '../application/controls/crud/list/list-page.component';
import {DetailPageComponent} from '../application/controls/crud/detail/detail-page.component';
import {FormPageComponent} from '../application/controls/crud/form/form-page.component';
import {UpdatePasswordDialogComponent} from '../application/presentation/authenticated/configurations/user/update-password-dialog.component';
import {getPaginatorIntl} from './services/portuguese-paginator-intl';
import {PaginationService} from './services/pagination.service';
import {SystemRoutingModule} from "./system.routing.module";
import {EmConstrucaoComponent} from "../application/controls/not-found/em-construcao.component";
import {AccessGroupRepository} from "./repository/access-group.repository";
import {ConsultAccessGroupComponent} from "../application/presentation/authenticated/configurations/access-group/consult-access-group/consult-access-group.component";
import {InsertAccessGroupComponent} from "../application/presentation/authenticated/configurations/access-group/insert-access-group/insert-access-group.component";
import {AccessGroupFormComponent} from "../application/presentation/authenticated/configurations/access-group/insert-access-group/access-group-form/access-group-form.component";
import {UpdateAccessGroupComponent} from "../application/presentation/authenticated/configurations/access-group/update-access-group/update-access-group.component";
import {UserRepository} from "./repository/user.repository";
import {ViewUserComponent} from "../application/presentation/authenticated/configurations/user/view-user/view-user.component";
import {ConsultUsersComponent} from "../application/presentation/authenticated/configurations/user/consult-users/consult-users.component";
import {UpdateUserComponent} from "../application/presentation/authenticated/configurations/user/update-user/update-user.component";
import {InsertUserComponent} from "../application/presentation/authenticated/configurations/user/insert-user/insert-user.component";
import {RootFormComponent} from "../application/presentation/authenticated/configurations/user/insert-user/user-form/root-form/root-form.component";
import {LinkPermissionsComponent} from "../application/presentation/authenticated/configurations/access-group/insert-access-group/access-group-form/link-permissions/link-permissions.component";
import {PermissionRepository} from "./repository/permission.repository";
import {UserViewComponent} from "../application/presentation/authenticated/configurations/user/user-view.component";
import {AccessGroupViewComponent} from "../application/presentation/authenticated/configurations/access-group/access-group-view.component";
import {EvDatepicker} from "../application/controls/ev-datepicker/ev-datepicker";
import {FirstUppercasePipe} from "../application/utils/utils";
import {HasPermissionDirective} from "../application/has-permission/has-permission";
import {CnpjValidator, CpfValidator} from "../application/controls/validators/validators";
import {DocumentoPipe} from "../application/controls/documento-pipe/documento-pipe";
import {UpdatePasswordComponent} from "../application/presentation/authenticated/configurations/user/update-password/update-password.component";
import {UserInitialsPipe} from "../application/controls/pipes/user-initials.pipe";
import {FormToolbarComponent} from 'system/application/controls/crud/cadastros/form-toolbar/form-toolbar.component';
import {ListTableComponent} from 'system/application/controls/crud/cadastros/list-table/list-table.component';
import {EntityFormComponent} from 'system/application/controls/crud/cadastros/entity-form/entity-form.component';
import {ButtonToggleAdvancedFiltersComponent} from 'system/application/controls/button-toggle-advanced-filters/button-toggle-advanced-filters.component';
import {ButtonClearFiltersComponent} from 'system/application/controls/button-clear-filters/button-clear-filters.component';
import {NoRecordsFoundComponent} from "system/application/controls/no-records-found/no-records-found.component";
import {FilterPipe} from "../application/controls/pipes/filter.pipe";
import {ConfigurationsViewComponent} from "../application/presentation/authenticated/configurations/configurations-view.component";
import {NoSubmitDirective} from "../application/controls/no-sumbit/no-submit.directive";
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
import {DadoComponent} from "../application/controls/data/dado.component";
import {PieChartModule} from "@swimlane/ngx-charts";
import {SystemComponent} from "../application/presentation/system.component";
import {ViewAccessGroupComponent} from "../application/presentation/authenticated/configurations/access-group/view-access-group/view-access-group.component";
import {UserFormComponent} from "../application/presentation/authenticated/configurations/user/insert-user/user-form/user-form.component";

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
    SystemComponent,
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
    ConfigurationsViewComponent,

    // Grupos de acesso
    AccessGroupFormComponent,
    UpdateAccessGroupComponent,
    InsertAccessGroupComponent,
    ConsultAccessGroupComponent,
    ViewAccessGroupComponent,
    AccessGroupViewComponent,

    // Usuario
    UserViewComponent,
    ConsultUsersComponent,
    ViewUserComponent,
    InsertUserComponent,
    UserFormComponent,
    UpdateUserComponent,
    UpdatePasswordDialogComponent,
    RootFormComponent,
    LinkPermissionsComponent,
    UpdatePasswordComponent,

    DadoComponent,

    // Has Permission
    HasPermissionDirective,

  ],
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    SystemRoutingModule,
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
    UpdatePasswordComponent,
    UpdatePasswordDialogComponent,
  ],
  providers: [

    // Repositories
    UserRepository,
    PermissionRepository,
    AccessGroupRepository,

    // Services
    Describer,
    WildcardService,
    PaginationService,
    AuthenticationService,

    UserViewComponent,
    ConfigurationsViewComponent,

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
  bootstrap: [SystemComponent]
})
export class SystemModule {
}
