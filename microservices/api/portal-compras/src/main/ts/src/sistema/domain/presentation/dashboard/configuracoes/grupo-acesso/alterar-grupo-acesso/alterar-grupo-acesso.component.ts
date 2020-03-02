import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../../services/message.service';
import {GrupoAcesso} from "../../../../../entity/grupo-acesso.model";
import {GrupoAcessoRepository} from "../../../../../repository/grupo-acesso.repository";

@Component({
  selector: 'alterar-grupo-acesso',
  templateUrl: 'alterar-grupo-acesso.component.html',
  styleUrls: ['../grupo-acesso.component.scss']
})
export class AlterarGrupoAcessoComponent implements OnInit {


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
    homeView.toolbar.subhead = 'Grupo de Acesso / Editar';
  }

  back() {
    if (this.activatedRoute.snapshot.routeConfig.path === 'editar/:id')
      this.router.navigate(['configuracoes/grupos-acesso']);
    else
      this.router.navigate(['configuracoes/grupos-acesso/' + (+this.activatedRoute.snapshot.params.id)]);
  }

  ngOnInit() {
    this.findById();
  }

  public findById() {
    this.grupoAcessoRepository.findById(+this.activatedRoute.snapshot.params.id)
      .subscribe((result) =>
        this.grupoAcesso = result
      );
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
