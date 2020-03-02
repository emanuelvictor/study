// ANGULAR
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CUSTOM_ELEMENTS_SCHEMA, LOCALE_ID, NgModule } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientJsonpModule, HttpClientModule } from '@angular/common/http';

import { CovalentSearchModule } from '@covalent/core/search'
// MODULES
import { SharedModule } from '../../shared/shared.module';
// UTILS
// SERVICES
// PIPES
// COMPONENTS
// CONTROLS
// INTERNACIONALIZACAO MATERIAL PAGINATOR
import { MatExpansionModule, MatPaginatorIntl } from '@angular/material';

/**
 * The internationalization (i18n) library for Angular 2+
 * https://github.com/ngx-translate/core
 */
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { PortalRoutingModule } from "./portal.routing.module";
import { FileRepository } from 'sistema/domain/repository/file.repository';
import {RecaptchaFormsModule, RecaptchaModule} from "ng-recaptcha";
import { FlexLayoutModule } from '@angular/flex-layout';
import { AuthenticationService } from "../../sistema/domain/services/authentication.service";
import { UsuarioRepository } from "../../sistema/domain/repository/usuario.repository";
import { MessageService } from "../../sistema/domain/services/message.service";
import { Interceptor } from "../../sistema/application/interceptor/interceptor";
import { DeleteDialogComponent } from "../../sistema/application/controls/delete-dialog/delete-dialog.component";
import { DialogService } from "../../sistema/domain/services/dialog.service";
import { PaginationService } from "../../sistema/domain/services/pagination.service";
import { HomeViewComponent } from "../application/presentation/home/home-view.component";
import { PortalComponent } from "../application/presentation/portal.component";
import { WildcardService } from "../../sistema/domain/services/wildcard.service";
import { Describer } from "../../sistema/application/describer/describer";
import { getPaginatorIntl } from "../../sistema/domain/services/portuguese-paginator-intl";
import { FooterComponent } from 'portal/application/presentation/footer/footer.component';
import { LandingPageComponent } from 'portal/application/presentation/landing-page/landing-page.component';
import { ListingNavComponent } from 'portal/application/presentation/listing-nav/listing-nav.component';
import { OpenPositionsComponent } from 'portal/application/presentation/listing-nav/open-positions/open-positions.component';
import { InProgressComponent } from 'portal/application/presentation/listing-nav/in-progress/in-progress.component';
import { ClosedPositionsComponent } from 'portal/application/presentation/listing-nav/closed-positions/closed-positions.component';
import { LoginComponent } from 'portal/application/presentation/login/login.component';
import { CadastroComponent } from 'portal/application/presentation/cadastro/cadastro.component';
import localePt from '@angular/common/locales/pt';

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
    FooterComponent,
    LandingPageComponent,
    ListingNavComponent,
    OpenPositionsComponent,
    InProgressComponent,
    ClosedPositionsComponent,
    LoginComponent,
    CadastroComponent
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
    FlexLayoutModule,
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
    UsuarioRepository,

    // Services
    Describer,
    WildcardService,
    PaginationService,
    AuthenticationService,
    MessageService,
    DialogService,

    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    },

    // Internacionalizacao MatPaginator
    { provide: MatPaginatorIntl, useValue: getPaginatorIntl() },
    { provide: LOCALE_ID, useValue: 'pt-BR' }
  ],
  bootstrap: [PortalComponent]
})
export class PortalModule {
}
