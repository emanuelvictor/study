import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from '../application/presentation/login/login.component';
import {InsertPasswordComponent} from '../application/presentation/manage-password/insert-password.component';
import {RecoveryPasswordComponent} from '../application/presentation/manage-password/recovery-password.component';
import {AuthenticatedViewComponent} from "../application/presentation/authenticated/authenticated-view.component";
import {UpdateUserComponent} from "../application/presentation/authenticated/configurations/user/update-user/update-user.component";
import {AuthenticationService} from "./services/authentication.service";
import {ViewUserComponent} from "../application/presentation/authenticated/configurations/user/view-user/view-user.component";
import {AccessGroupViewComponent} from "../application/presentation/authenticated/configurations/access-group/access-group-view.component";
import {ConsultAccessGroupComponent} from "../application/presentation/authenticated/configurations/access-group/consult-access-group/consult-access-group.component";
import {UpdateAccessGroupComponent} from "../application/presentation/authenticated/configurations/access-group/update-access-group/update-access-group.component";
import {ViewAccessGroupComponent} from "../application/presentation/authenticated/configurations/access-group/view-access-group/view-access-group.component";
import {InsertAccessGroupComponent} from "../application/presentation/authenticated/configurations/access-group/insert-access-group/insert-access-group.component";
import {InsertUserComponent} from "../application/presentation/authenticated/configurations/user/insert-user/insert-user.component";
import {ConsultUsersComponent} from "../application/presentation/authenticated/configurations/user/consult-users/consult-users.component";
import {UserViewComponent} from "../application/presentation/authenticated/configurations/user/user-view.component";
import {ConfigurationsViewComponent} from "../application/presentation/authenticated/configurations/configurations-view.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'recuperar-senha', component: RecoveryPasswordComponent},
  {path: 'cadastrar-senha/:codigo', component: InsertPasswordComponent},
  {
    path: '', component: AuthenticatedViewComponent, canActivate: [AuthenticationService],
    children: [
      {
        path: '', redirectTo: 'configuracoes', pathMatch: 'full',
      },
      {
        path: 'configuracoes',
        component: ConfigurationsViewComponent,
        children: [
          {
            path: '', redirectTo: 'usuarios', pathMatch: 'full',
          },
          {
            path: 'usuarios', component: UserViewComponent,
            // canActivate: [UserViewComponent],
            children: [
              {path: 'get', redirectTo: '', pathMatch: 'full'},
              {path: '', component: ConsultUsersComponent},
              {path: 'adicionar', component: InsertUserComponent},
              {path: 'editar/:id', component: UpdateUserComponent},
              {path: ':id/editar', component: UpdateUserComponent},
              {path: ':id', component: ViewUserComponent}
            ]
          },
          {
            path: 'grupos-acesso', component: AccessGroupViewComponent,
            children: [
              {path: 'get', redirectTo: '', pathMatch: 'full'},
              {path: '', component: ConsultAccessGroupComponent},
              {path: 'adicionar', component: InsertAccessGroupComponent},
              {path: 'editar/:id', component: UpdateAccessGroupComponent},
              {path: ':id/editar', component: UpdateAccessGroupComponent},
              {path: ':id', component: ViewAccessGroupComponent}
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
export class SystemRoutingModule {
}
