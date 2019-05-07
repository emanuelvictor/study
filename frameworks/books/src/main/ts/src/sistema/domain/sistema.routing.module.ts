import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ConsultarCategoriasComponent} from "./presentation/cadastros/categoria/consultar-categorias/consultar-categorias.component";
import {AlterarCategoriaComponent} from "./presentation/cadastros/categoria/alterar-categoria/alterar-categoria.component";
import {VisualizarCategoriaComponent} from "./presentation/cadastros/categoria/visualizar-categoria/visualizar-categoria.component";
import {DashboardViewComponent} from "./presentation/dashboard-view.component";
import {InserirCategoriaComponent} from "./presentation/cadastros/categoria/inserir-categoria/inserir-categoria.component";
import {CadastrosViewComponent} from "./presentation/cadastros/cadastros-view.component";
import {ConsultarTiposDocumentosComponent} from "./presentation/cadastros/tipo-documento/consultar-tipos-documentos/consultar-tipos-documentos.component";
import {InserirTipoDocumentoComponent} from "./presentation/cadastros/tipo-documento/inserir-tipo-documento/inserir-tipo-documento.component";
import {AlterarTipoDocumentoComponent} from "./presentation/cadastros/tipo-documento/alterar-tipo-documento/alterar-tipo-documento.component";
import {VisualizarTipoDocumentoComponent} from "./presentation/cadastros/tipo-documento/visualizar-tipo-documento/visualizar-tipo-documento.component";
import {EmConstrucaoComponent} from "../application/controls/not-found/em-construcao.component";
import {ConfiguracoesViewComponent} from "./presentation/configuracoes/configuracoes-view.component";
import {ConsultarGruposAcessoComponent} from "./presentation/configuracoes/grupo-acesso/consultar-grupos-acessos/consultar-grupos-acesso.component";
import {InserirGrupoAcessoComponent} from "./presentation/configuracoes/grupo-acesso/inserir-grupo-acesso/inserir-grupo-acesso.component";
import {VisualizarGrupoAcessoComponent} from "./presentation/configuracoes/grupo-acesso/visualizar-grupo-acesso/visualizar-grupo-acesso.component";
import {AlterarGrupoAcessoComponent} from "./presentation/configuracoes/grupo-acesso/alterar-grupo-acesso/alterar-grupo-acesso.component";
import {VisualizarUsuarioComponent} from "./presentation/configuracoes/usuario/visualizar-usuario/visualizar-usuario.component";
import {ConsultarUsuariosComponent} from "./presentation/configuracoes/usuario/consultar-usuarios/consultar-usuarios.component";
import {EditarUsuarioComponent} from "./presentation/configuracoes/usuario/alterar-usuario/editar-usuario.component";
import {InserirUsuarioComponent} from "./presentation/configuracoes/usuario/inserir-usuario/inserir-usuario.component";
import {UsuarioViewComponent} from "./presentation/configuracoes/usuario/usuario-view.component";
import {GrupoAcessoViewComponent} from "./presentation/configuracoes/grupo-acesso/grupo-acesso-view.component";
import {TipoDocumentoViewComponent} from "./presentation/cadastros/tipo-documento/tipo-documento-view.component";
import {CategoriaViewComponent} from "./presentation/cadastros/categoria/categoria-view.component";
import {TipoCadastroViewComponent} from './presentation/cadastros/tipo-cadastro/tipo-cadastro-view.component';
import {ConsultarTiposCadastrosComponent} from './presentation/cadastros/tipo-cadastro/consultar-tipos-cadastros/consultar-tipos-cadastros.component';
import {InserirTipoCadastroComponent} from './presentation/cadastros/tipo-cadastro/inserir-tipo-cadastro/inserir-tipo-cadastro.component';
import {VisualizarTipoCadastroComponent} from './presentation/cadastros/tipo-cadastro/visualizar-tipo-cadastro/visualizar-tipo-cadastro.component';
import {AlterarTipoCadastroComponent} from './presentation/cadastros/tipo-cadastro/alterar-tipo-cadastro/alterar-tipo-cadastro.component';
import {PublicacoesViewComponent} from "./presentation/publicacoes/publicacoes-view.component";
import {AvisosContratacoesViewComponent} from "./presentation/publicacoes/avisos-contratacoes/avisos-contratacoes-view.component";
import {AvisosEditaisViewComponent} from "./presentation/publicacoes/avisos-editais/avisos-editais-view.component";
import {InserirAvisoContratacaoComponent} from "./presentation/publicacoes/avisos-contratacoes/inserir-aviso-contratacao/inserir-aviso-contratacao.component";
import {ConsultarAvisosContratacoesComponent} from "./presentation/publicacoes/avisos-contratacoes/consultar-avisos-contratacoes/consultar-avisos-contratacoes.component";
import {AlterarAvisoContratacaoComponent} from "./presentation/publicacoes/avisos-contratacoes/alterar-aviso-contratacao/alterar-aviso-contratacao.component";
import {ExtratosContratoViewComponent} from "./presentation/publicacoes/extratos-contratos/extratos-contrato-view.component";
import {ConsultarExtratosContratosComponent} from "./presentation/publicacoes/extratos-contratos/consultar-extratos-contratacoes/consultar-extratos-contratos.component";
import {InserirExtratoContratoComponent} from "./presentation/publicacoes/extratos-contratos/inserir-extrato-contratacao/inserir-extrato-contrato.component";
import {AlterarExtratoContratoComponent} from "./presentation/publicacoes/extratos-contratos/alterar-extrato-contratacao/alterar-extrato-contrato.component";
import {InserirAvisoEditalComponent} from "./presentation/publicacoes/avisos-editais/inserir-aviso-edital/inserir-aviso-edital.component";
import {ConsultarAvisosEditaisComponent} from "./presentation/publicacoes/avisos-editais/consultar-aviso-edital/consultar-avisos-editais.component";
import {AlterarAvisoEditalComponent} from "./presentation/publicacoes/avisos-editais/alterar-aviso-edital/alterar-aviso-edital.component";
import {FornecedorViewComponent} from "./presentation/fornecedor/fornecedor-view.component";
import {ConsultarFornecedoresComponent} from "./presentation/fornecedor/consultar-fornecedores/consultar-fornecedores.component";
import {InserirFornecedorComponent} from "./presentation/fornecedor/inserir-fornecedor/inserir-fornecedor.component";
import {AlterarFornecedorComponent} from "./presentation/fornecedor/alterar-fornecedor/alterar-fornecedor.component";
import {AnalisarFornecedorComponent} from "./presentation/fornecedor/analisar-fornecedor/analisar-fornecedor.component";
import {MinhaContaComponent} from "./presentation/fornecedor/minha-conta/minha-conta.component";

const routes: Routes = [
  {
    path: '',
    component: DashboardViewComponent,
    children: [
      {
        path: '', redirectTo: 'minha-conta', pathMatch: 'full'
      },
      {
        path: 'minha-conta', component: MinhaContaComponent
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
          {path: '', component: EmConstrucaoComponent}
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
