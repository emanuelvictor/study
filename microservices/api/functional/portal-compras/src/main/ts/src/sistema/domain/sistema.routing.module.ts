import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './presentation/login/login.component';
import {CadastrarSenhaComponent} from './presentation/gerenciar-senha/cadastrar-senha.component';
import {RecuperarSenhaComponent} from './presentation/gerenciar-senha/recuperar-senha.component';
import {ConsultarCategoriasComponent} from "./presentation/dashboard/cadastros/categoria/consultar-categorias/consultar-categorias.component";
import {AlterarCategoriaComponent} from "./presentation/dashboard/cadastros/categoria/alterar-categoria/alterar-categoria.component";
import {VisualizarCategoriaComponent} from "./presentation/dashboard/cadastros/categoria/visualizar-categoria/visualizar-categoria.component";
import {DashboardViewComponent} from "./presentation/dashboard/dashboard-view.component";
import {InserirCategoriaComponent} from "./presentation/dashboard/cadastros/categoria/inserir-categoria/inserir-categoria.component";
import {CadastrosViewComponent} from "./presentation/dashboard/cadastros/cadastros-view.component";
import {ConsultarTiposDocumentosComponent} from "./presentation/dashboard/cadastros/tipo-documento/consultar-tipos-documentos/consultar-tipos-documentos.component";
import {InserirTipoDocumentoComponent} from "./presentation/dashboard/cadastros/tipo-documento/inserir-tipo-documento/inserir-tipo-documento.component";
import {AlterarTipoDocumentoComponent} from "./presentation/dashboard/cadastros/tipo-documento/alterar-tipo-documento/alterar-tipo-documento.component";
import {VisualizarTipoDocumentoComponent} from "./presentation/dashboard/cadastros/tipo-documento/visualizar-tipo-documento/visualizar-tipo-documento.component";
import {EmConstrucaoComponent} from "../application/controls/not-found/em-construcao.component";
import {ConfiguracoesViewComponent} from "./presentation/dashboard/configuracoes/configuracoes-view.component";
import {ConsultarGruposAcessoComponent} from "./presentation/dashboard/configuracoes/grupo-acesso/consultar-grupos-acessos/consultar-grupos-acesso.component";
import {InserirGrupoAcessoComponent} from "./presentation/dashboard/configuracoes/grupo-acesso/inserir-grupo-acesso/inserir-grupo-acesso.component";
import {VisualizarGrupoAcessoComponent} from "./presentation/dashboard/configuracoes/grupo-acesso/visualizar-grupo-acesso/visualizar-grupo-acesso.component";
import {AlterarGrupoAcessoComponent} from "./presentation/dashboard/configuracoes/grupo-acesso/alterar-grupo-acesso/alterar-grupo-acesso.component";
import {VisualizarUsuarioComponent} from "./presentation/dashboard/configuracoes/usuario/visualizar-usuario/visualizar-usuario.component";
import {ConsultarUsuariosComponent} from "./presentation/dashboard/configuracoes/usuario/consultar-usuarios/consultar-usuarios.component";
import {EditarUsuarioComponent} from "./presentation/dashboard/configuracoes/usuario/alterar-usuario/editar-usuario.component";
import {InserirUsuarioComponent} from "./presentation/dashboard/configuracoes/usuario/inserir-usuario/inserir-usuario.component";
import {UsuarioViewComponent} from "./presentation/dashboard/configuracoes/usuario/usuario-view.component";
import {GrupoAcessoViewComponent} from "./presentation/dashboard/configuracoes/grupo-acesso/grupo-acesso-view.component";
import {TipoDocumentoViewComponent} from "./presentation/dashboard/cadastros/tipo-documento/tipo-documento-view.component";
import {CategoriaViewComponent} from "./presentation/dashboard/cadastros/categoria/categoria-view.component";
import {AuthenticationService} from "./services/authentication.service";
import {TipoCadastroViewComponent} from './presentation/dashboard/cadastros/tipo-cadastro/tipo-cadastro-view.component';
import {ConsultarTiposCadastrosComponent} from './presentation/dashboard/cadastros/tipo-cadastro/consultar-tipos-cadastros/consultar-tipos-cadastros.component';
import {InserirTipoCadastroComponent} from './presentation/dashboard/cadastros/tipo-cadastro/inserir-tipo-cadastro/inserir-tipo-cadastro.component';
import {VisualizarTipoCadastroComponent} from './presentation/dashboard/cadastros/tipo-cadastro/visualizar-tipo-cadastro/visualizar-tipo-cadastro.component';
import {AlterarTipoCadastroComponent} from './presentation/dashboard/cadastros/tipo-cadastro/alterar-tipo-cadastro/alterar-tipo-cadastro.component';
import {PublicacoesViewComponent} from "./presentation/dashboard/publicacoes/publicacoes-view.component";
import {AvisosContratacoesViewComponent} from "./presentation/dashboard/publicacoes/avisos-contratacoes/avisos-contratacoes-view.component";
import {AvisosEditaisViewComponent} from "./presentation/dashboard/publicacoes/avisos-editais/avisos-editais-view.component";
import {InserirAvisoContratacaoComponent} from "./presentation/dashboard/publicacoes/avisos-contratacoes/inserir-aviso-contratacao/inserir-aviso-contratacao.component";
import {ConsultarAvisosContratacoesComponent} from "./presentation/dashboard/publicacoes/avisos-contratacoes/consultar-avisos-contratacoes/consultar-avisos-contratacoes.component";
import {AlterarAvisoContratacaoComponent} from "./presentation/dashboard/publicacoes/avisos-contratacoes/alterar-aviso-contratacao/alterar-aviso-contratacao.component";
import {ExtratosContratoViewComponent} from "./presentation/dashboard/publicacoes/extratos-contratos/extratos-contrato-view.component";
import {ConsultarExtratosContratosComponent} from "./presentation/dashboard/publicacoes/extratos-contratos/consultar-extratos-contratacoes/consultar-extratos-contratos.component";
import {InserirExtratoContratoComponent} from "./presentation/dashboard/publicacoes/extratos-contratos/inserir-extrato-contratacao/inserir-extrato-contrato.component";
import {AlterarExtratoContratoComponent} from "./presentation/dashboard/publicacoes/extratos-contratos/alterar-extrato-contratacao/alterar-extrato-contrato.component";
import {InserirAvisoEditalComponent} from "./presentation/dashboard/publicacoes/avisos-editais/inserir-aviso-edital/inserir-aviso-edital.component";
import {ConsultarAvisosEditaisComponent} from "./presentation/dashboard/publicacoes/avisos-editais/consultar-aviso-edital/consultar-avisos-editais.component";
import {AlterarAvisoEditalComponent} from "./presentation/dashboard/publicacoes/avisos-editais/alterar-aviso-edital/alterar-aviso-edital.component";
import {FornecedorViewComponent} from "./presentation/dashboard/fornecedor/fornecedor-view.component";
import {ConsultarFornecedoresComponent} from "./presentation/dashboard/fornecedor/consultar-fornecedores/consultar-fornecedores.component";
import {InserirFornecedorComponent} from "./presentation/dashboard/fornecedor/inserir-fornecedor/inserir-fornecedor.component";
import {AlterarFornecedorComponent} from "./presentation/dashboard/fornecedor/alterar-fornecedor/alterar-fornecedor.component";
import {AnalisarFornecedorComponent} from "./presentation/dashboard/fornecedor/analisar-fornecedor/analisar-fornecedor.component";
import {MinhaContaComponent} from "./presentation/dashboard/fornecedor/minha-conta/minha-conta.component";
import {MeusDadosGeraisComponent} from "./presentation/dashboard/fornecedor/minha-conta/meus-dados-gerais/meus-dados-gerais.component";
import {MeusDocumentosComponent} from "./presentation/dashboard/fornecedor/minha-conta/meus-documentos/meus-documentos.component";
import {MeusDadosBancariosComponent} from "./presentation/dashboard/fornecedor/minha-conta/meus-dados-bancarios/meus-dados-bancarios.component";
import {MeusContatosComponent} from "./presentation/dashboard/fornecedor/minha-conta/meus-contatos/meus-contatos.component";
import {InicioComponent} from "./presentation/dashboard/fornecedor/minha-conta/inicio/inicio.component";
import {DashboardComponent} from "./presentation/dashboard/dashboard.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'recuperar-senha', component: RecuperarSenhaComponent},
  {path: 'cadastrar-senha/:codigo', component: CadastrarSenhaComponent},
  {
    path: '',
    component: DashboardViewComponent,
    canActivate: [AuthenticationService],
    children: [
      {
        path: '', redirectTo: 'minha-conta', pathMatch: 'full'
      },
      {
        path: 'minha-conta', component: MinhaContaComponent,
        children: [
          {path: 'inicio', component: InicioComponent},
          {path: 'meus-dados-gerais', component: MeusDadosGeraisComponent},
          {path: 'meus-contatos', component: MeusContatosComponent},
          {path: 'meus-dados-bancarios', component: MeusDadosBancariosComponent},
          {path: 'meus-documentos', component: MeusDocumentosComponent},
        ]
      },
      {
        path: 'cadastros',
        component: CadastrosViewComponent,
        children: [
          {
            path: '', redirectTo: 'categorias', pathMatch: 'full',
          },
          {
            path: 'categorias', component: CategoriaViewComponent,
            children: [
              {path: 'get', redirectTo: '', pathMatch: 'full'},
              {path: '', component: ConsultarCategoriasComponent},
              {path: 'adicionar', component: InserirCategoriaComponent},
              {path: 'editar/:id', component: AlterarCategoriaComponent},
              {path: ':id/editar', component: AlterarCategoriaComponent},
              {path: ':id', component: VisualizarCategoriaComponent}
            ]
          },
          {
            path: 'tipos-documentos', component: TipoDocumentoViewComponent,
            children: [
              {path: 'get', redirectTo: '', pathMatch: 'full'},
              {path: '', component: ConsultarTiposDocumentosComponent},
              {path: 'adicionar', component: InserirTipoDocumentoComponent},
              {path: 'editar/:id', component: AlterarTipoDocumentoComponent},
              {path: ':id/editar', component: AlterarTipoDocumentoComponent},
              {path: ':id', component: VisualizarTipoDocumentoComponent}
            ]
          },
          {
            path: 'tipos-cadastros', component: TipoCadastroViewComponent,
            children: [
              {path: 'get', redirectTo: '', pathMatch: 'full'},
              {path: '', component: ConsultarTiposCadastrosComponent},
              {path: 'adicionar', component: InserirTipoCadastroComponent},
              {path: 'editar/:id', component: AlterarTipoCadastroComponent},
              {path: ':id/editar', component: AlterarTipoCadastroComponent},
              {path: ':id', component: VisualizarTipoCadastroComponent}
            ]
          },
        ]
      },
      {
        path: 'fornecedores', component: FornecedorViewComponent,
        children: [
          {path: 'get', redirectTo: '', pathMatch: 'full'},
          {path: '', component: ConsultarFornecedoresComponent},
          {path: 'adicionar', component: InserirFornecedorComponent},
          {path: 'editar/:id', component: AlterarFornecedorComponent},
          {path: ':id/editar', component: AlterarFornecedorComponent},
          {path: ':id', component: AnalisarFornecedorComponent},
        ]
      },
      {
        path: 'dashboard',
        children: [
          {path: '', component: DashboardComponent}
        ]
      },
      {
        path: 'publicacoes', component: PublicacoesViewComponent,
        children: [
          {
            path: '', redirectTo: 'avisos-contratacoes', pathMatch: 'full',
          },
          {
            path: 'avisos-contratacoes', component: AvisosContratacoesViewComponent,
            children: [
              {path: 'get', redirectTo: '', pathMatch: 'full'},
              {path: '', component: ConsultarAvisosContratacoesComponent},
              {path: 'adicionar', component: InserirAvisoContratacaoComponent},
              // {path: 'editar/:id', component: AlterarTipoCadastroComponent},
              // {path: ':id/editar', component: AlterarTipoCadastroComponent},
              {path: ':id', component: AlterarAvisoContratacaoComponent}
            ]
          },
          {
            path: 'avisos-editais', component: AvisosEditaisViewComponent,
            children: [
              {path: 'get', redirectTo: '', pathMatch: 'full'},
              {path: '', component: ConsultarAvisosEditaisComponent},
              {path: 'adicionar', component: InserirAvisoEditalComponent},
              {path: ':id', component: AlterarAvisoEditalComponent}
            ]
          },
          {
            path: 'extratos-contratos', component: ExtratosContratoViewComponent,
            children: [
              {path: 'get', redirectTo: '', pathMatch: 'full'},
              {path: '', component: ConsultarExtratosContratosComponent},
              {path: 'adicionar', component: InserirExtratoContratoComponent},
              // {path: 'editar/:id', component: AlterarTipoCadastroComponent},
              // {path: ':id/editar', component: AlterarTipoCadastroComponent},
              {path: ':id', component: AlterarExtratoContratoComponent}
            ]
          },
        ]
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
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule],
  providers: []
})
export class SistemaRoutingModule {
}
