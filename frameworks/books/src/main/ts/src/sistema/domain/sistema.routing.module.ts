import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashboardViewComponent} from "./presentation/dashboard-view.component";
import {CadastrosViewComponent} from "./presentation/cadastros/cadastros-view.component";
import {AlterarEditoraComponent} from "./presentation/cadastros/editora/alterar-editora/alterar-editora.component";
import {VisualizarEditoraComponent} from "./presentation/cadastros/editora/visualizar-editora/visualizar-editora.component";
import {InserirEditoraComponent} from "./presentation/cadastros/editora/inserir-editora/inserir-editora.component";
import {ConsultarEditorasComponent} from "./presentation/cadastros/editora/consultar-editoras/consultar-editoras.component";
import {EditoraViewComponent} from "./presentation/cadastros/editora/editora-view.component";

const routes: Routes = [
    {
        path: '',
        component: DashboardViewComponent,
        children: [
            {
                path: '', redirectTo: 'cadastros', pathMatch: 'full'
            },
            {
                path: 'cadastros',
                component: CadastrosViewComponent,
                children: [
                    {
                        path: '', redirectTo: 'editoras', pathMatch: 'full',
                    },
                    {
                        path: 'editoras', component: EditoraViewComponent,
                        children: [
                            {path: 'get', redirectTo: '', pathMatch: 'full'},
                            {path: '', component: ConsultarEditorasComponent},
                            {path: 'adicionar', component: InserirEditoraComponent},
                            {path: 'editar/:id', component: AlterarEditoraComponent},
                            {path: ':id/editar', component: AlterarEditoraComponent},
                            {path: ':id', component: VisualizarEditoraComponent}
                        ]
                    },
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
