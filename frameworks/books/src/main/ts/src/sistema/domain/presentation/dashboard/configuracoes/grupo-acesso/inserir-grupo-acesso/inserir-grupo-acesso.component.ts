import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../../services/message.service';
import {GrupoAcesso} from "../../../../../entity/grupo-acesso.model";
import {GrupoAcessoRepository} from "../../../../../repository/grupo-acesso.repository";

@Component({
  selector: 'inserir-grupo-acesso',
  templateUrl: 'inserir-grupo-acesso.component.html',
  styleUrls: ['../grupo-acesso.component.scss']
})
export class InserirGrupoAcessoComponent {

  /**
   *
   */
  grupoAcesso: GrupoAcesso = new GrupoAcesso();

  /**
   *
   * @param router
   * @param homeView
   * @param activatedRoute
   * @param messageService
   * @param grupoAcessoRepository
   */
  constructor(private router: Router,
              private homeView: DashboardViewComponent,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private grupoAcessoRepository: GrupoAcessoRepository) {

    homeView.toolbar.subhead = 'Grupo de Acesso / Adicionar';
    this.grupoAcesso.gruposAcessoPermissoes = [];

  }

  public save(form) {

    if (form.invalid) {
      this.messageService.toastWarning();
      return;
    }

    this.grupoAcessoRepository.save(this.grupoAcesso)
      .then(() => {
        this.router.navigate(['configuracoes/grupos-acesso']);
        this.messageService.toastSuccess();
      });
  }

}
