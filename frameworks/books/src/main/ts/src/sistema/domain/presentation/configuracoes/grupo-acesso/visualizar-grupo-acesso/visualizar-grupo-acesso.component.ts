import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../services/message.service';
import {GrupoAcessoRepository} from "../../../../repository/grupo-acesso.repository";
import {DialogService} from "../../../../services/dialog.service";

@Component({
  selector: 'visualizar-grupo-acesso',
  templateUrl: 'visualizar-grupo-acesso.component.html',
  styleUrls: ['../grupo-acesso.component.scss']
})
export class VisualizarGrupoAcessoComponent implements OnInit {

  /**
   *
   */
  grupoAcesso: any = {};

  /**
   *
   * @param router
   * @param homeView
   * @param dialogService
   * @param activatedRoute
   * @param messageService
   * @param grupoAcessoRepository
   */
  constructor(private router: Router,
              private homeView: DashboardViewComponent,
              private dialogService: DialogService,
              public activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private grupoAcessoRepository: GrupoAcessoRepository) {

    this.grupoAcesso.id = +this.activatedRoute.snapshot.params.id || null;
    homeView.toolbar.subhead = 'Grupo de Acesso / Detalhes';

  }

  /**
   *
   */
  ngOnInit() {
    if (this.grupoAcesso && this.grupoAcesso.id) {
      this.findById();
    } else {
      this.router.navigate(["/grupos-acesso"]);
    }
  }

  /**
   *
   */
  public findById() {
    this.grupoAcessoRepository.findById(this.grupoAcesso.id)
      .subscribe((result) => {
        this.grupoAcesso = result;
      });
  }

  /**
   * Função para confirmar a exclusão de um registro permanentemente
   * @param grupoAcesso
   */
  public openDeleteDialog(grupoAcesso) {

    this.dialogService.confirmDelete(grupoAcesso, 'Grupo de Acesso')
      .then((accept: boolean) => {

        if (accept) {
          this.grupoAcessoRepository.delete(grupoAcesso.id)
            .then(() => {
              this.router.navigate(['configuracoes/grupos-acesso']);
              this.messageService.toastSuccess('Registro excluído com sucesso.')
            });
        }
      });
  }

}
