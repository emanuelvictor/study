import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RecuperarSenhaComponent} from "../../sistema/application/presentation/gerenciar-senha/recuperar-senha.component";
import {CadastrarSenhaComponent} from "../../sistema/application/presentation/gerenciar-senha/cadastrar-senha.component";
import {HomeViewComponent} from "../application/presentation/home/home-view.component";
import {LandingPageComponent} from 'portal/application/presentation/landing-page/landing-page.component';
import { LoginComponent } from 'portal/application/presentation/login/login.component';

const routes: Routes = [
  {path: 'recuperar-senha', component: RecuperarSenhaComponent},
  {path: 'cadastrar-senha/:codigo', component: CadastrarSenhaComponent},
  {
    path: '',
    component: HomeViewComponent,
    children: [
      {path: 'login', component: LoginComponent},
      {path: '', component: LandingPageComponent},
    ]
  },
];

/**
 *
 */
@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule],
  providers: []
})
export class PortalRoutingModule {
}
