import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from '../application/presentation/login/login.component';
import {CadastrarSenhaComponent} from '../application/presentation/gerenciar-senha/cadastrar-senha.component';
import {RecuperarSenhaComponent} from '../application/presentation/gerenciar-senha/recuperar-senha.component';
import {AuthenticatedViewComponent} from "../application/presentation/authenticated/authenticated-view.component";
import {EditarUsuarioComponent} from "../application/presentation/authenticated/configuracoes/usuario/alterar-usuario/editar-usuario.component";
import {AuthenticationService} from "./services/authentication.service";
import {VisualizarUsuarioComponent} from "../application/presentation/authenticated/configuracoes/usuario/visualizar-usuario/visualizar-usuario.component";
import {GrupoAcessoViewComponent} from "../application/presentation/authenticated/configuracoes/grupo-acesso/grupo-acesso-view.component";
import {ConsultarGruposAcessoComponent} from "../application/presentation/authenticated/configuracoes/grupo-acesso/consultar-grupos-acessos/consultar-grupos-acesso.component";
import {AlterarGrupoAcessoComponent} from "../application/presentation/authenticated/configuracoes/grupo-acesso/alterar-grupo-acesso/alterar-grupo-acesso.component";
import {VisualizarGrupoAcessoComponent} from "../application/presentation/authenticated/configuracoes/grupo-acesso/visualizar-grupo-acesso/visualizar-grupo-acesso.component";
import {InserirGrupoAcessoComponent} from "../application/presentation/authenticated/configuracoes/grupo-acesso/inserir-grupo-acesso/inserir-grupo-acesso.component";
import {InserirUsuarioComponent} from "../application/presentation/authenticated/configuracoes/usuario/inserir-usuario/inserir-usuario.component";
import {ConsultarUsuariosComponent} from "../application/presentation/authenticated/configuracoes/usuario/consultar-usuarios/consultar-usuarios.component";
import {UsuarioViewComponent} from "../application/presentation/authenticated/configuracoes/usuario/usuario-view.component";
import {ConfiguracoesViewComponent} from "../application/presentation/authenticated/configuracoes/configuracoes-view.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'recuperar-senha', component: RecuperarSenhaComponent},
  {path: 'cadastrar-senha/:codigo', component: CadastrarSenhaComponent},
  {
    path: '', component: AuthenticatedViewComponent, canActivate: [AuthenticationService],
    children: [
      {
        path: '', redirectTo: 'bens', pathMatch: 'full',
      },
      {
        path: 'configuracoes',
        component: ConfiguracoesViewComponent,
        children: [
          {
            path: '', redirectTo: 'usuarios', pathMatch: 'full',
          },
          {
            path: 'usuarios', component: UsuarioViewComponent,
            canActivate: [UsuarioViewComponent],
            children: [
              {path: 'get', redirectTo: '', pathMatch: 'full'},
              {path: '', component: ConsultarUsuariosComponent},
              {path: 'adicionar', component: InserirUsuarioComponent},
              {path: 'editar/:id', component: EditarUsuarioComponent},
              {path: ':id/editar', component: EditarUsuarioComponent},
              {path: ':id', component: VisualizarUsuarioComponent}
            ]
          },
          {
            path: 'grupos-acesso', component: GrupoAcessoViewComponent,
            children: [
              {path: 'get', redirectTo: '', pathMatch: 'full'},
              {path: '', component: ConsultarGruposAcessoComponent},
              {path: 'adicionar', component: InserirGrupoAcessoComponent},
              {path: 'editar/:id', component: AlterarGrupoAcessoComponent},
              {path: ':id/editar', component: AlterarGrupoAcessoComponent},
              {path: ':id', component: VisualizarGrupoAcessoComponent}
            ]
          }
        ]
      }
    ]
  }
];

/**
 *
 */
@NgModule({
  imports: [RouterModule.forRoot(routes/*, {useHash: true}*/)],
  exports: [RouterModule],
  providers: []
})
export class SistemaRoutingModule {
}
