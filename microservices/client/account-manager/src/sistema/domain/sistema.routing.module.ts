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
import {BensViewComponent} from "../application/presentation/authenticated/bens/bens-view.component";
import {InventariosViewComponent} from "../application/presentation/authenticated/bens/inventario/inventarios-view.component";
import {ConsultarInventariosComponent} from "../application/presentation/authenticated/bens/inventario/consultar-inventarios/consultar-inventarios.component";
import {EditarInventarioComponent} from "../application/presentation/authenticated/bens/inventario/alterar-inventario/editar-inventario.component";
import {VisualizarInventarioComponent} from "../application/presentation/authenticated/bens/inventario/visualizar-inventario/visualizar-inventario.component";
import {InserirInventarioComponent} from "../application/presentation/authenticated/bens/inventario/inserir-inventario/inserir-inventario.component";
import {ExecutarInventarioComponent} from "../application/presentation/authenticated/bens/inventario/executar-inventario/executar-inventario.component";
import {PatrimoniosViewComponent} from "../application/presentation/authenticated/bens/patrimonios/patrimonios-view.component";
import {ConsultarPatrimoniosComponent} from "../application/presentation/authenticated/bens/patrimonios/consultar-patrimonios/consultar-patrimonios.component";
import {VisualizarPatrimonioComponent} from "../application/presentation/authenticated/bens/patrimonios/visualizar-patrimonio/visualizar-patrimonio.component";

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
      },
      {
        path: 'bens',
        component: BensViewComponent,
        children: [
          {
            path: '', redirectTo: 'inventarios', pathMatch: 'full',
          },
          {
            path: 'inventarios', component: InventariosViewComponent,
            canActivate: [InventariosViewComponent],
            children: [
              {path: 'get', redirectTo: '', pathMatch: 'full'},
              {path: '', component: ConsultarInventariosComponent},
              {path: 'adicionar', component: InserirInventarioComponent},
              {path: 'editar/:id', component: EditarInventarioComponent},
              {path: ':id/editar', component: EditarInventarioComponent},
              {path: ':id', component: VisualizarInventarioComponent},
              {path: ':id/executar/:codigo', component: ExecutarInventarioComponent}
            ]
          },
          {
            path: 'patrimonios', component: PatrimoniosViewComponent,
            children: [
              {path: '', component: ConsultarPatrimoniosComponent},
              {path: ':id', component: VisualizarPatrimonioComponent},
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
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule],
  providers: []
})
export class SistemaRoutingModule {
}
