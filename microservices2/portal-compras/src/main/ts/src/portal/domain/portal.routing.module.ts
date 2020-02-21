import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
// COMPONENTS
import {HomeViewComponent} from './views/home/home-view.component';
// =============================================
// CADASTROS
// =============================================
//PORTAL COMPRAS
import {PrincipalComponent} from './views/home/principal/principal.component';
import {FornecedorViewComponent} from './views/home/cadastros/fornecedor/fornecedor-view.component';
import {CadastrarFornecedorComponent} from './views/home/cadastros/fornecedor/cadastrar-fornecedor/cadastrar-fornecedor.component';
import {LoginFornecedorComponent} from './views/home/cadastros/fornecedor/login-fornecedor/login-fornecedor.component';
import {PublicacoesViewComponent} from './views/home/publicacao/publicacoes-view.component';
import {ConsultarAvisosEditaisComponent} from './views/home/publicacao/avisos-editais/consultar-avisos-editais.component';
import {ConsultarAvisosContratacoesComponent} from './views/home/publicacao/avisos-contratacoes/consultar-avisos-contratacoes.component';
import {ConsultarExtratosContratosComponent} from './views/home/publicacao/extratos-contratos/consultar-extratos-contratos.component';
import {RecuperarSenhaComponent} from "../../sistema/domain/presentation/gerenciar-senha/recuperar-senha.component";
import {CadastrarSenhaComponent} from "../../sistema/domain/presentation/gerenciar-senha/cadastrar-senha.component";

const routes: Routes = [
  {path: 'recuperar-senha', component: RecuperarSenhaComponent},
  {path: 'cadastrar-senha/:codigo', component: CadastrarSenhaComponent},
  {
    path: '',
    component: HomeViewComponent,
    // canActivate: [AuthGuard],
    children: [
      {
        path: '', component: FornecedorViewComponent,
        children: [
          {path: '', component: PrincipalComponent},
          {path: 'cadastrar', component: CadastrarFornecedorComponent},
          {path: 'login', component: LoginFornecedorComponent}
        ],
      },
      {
        path: 'publicacoes', component: PublicacoesViewComponent,
        children: [
          {path: 'avisos-editais', component: ConsultarAvisosEditaisComponent},
          {path: 'avisos-contratacoes', component: ConsultarAvisosContratacoesComponent},
          {path: 'extratos-contratos', component: ConsultarExtratosContratosComponent}
        ]
      }
    ]
  }
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
