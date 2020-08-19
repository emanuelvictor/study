import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticatedViewComponent} from '../../../authenticated-view.component';
import {MessageService} from '../../../../../../domain/services/message.service';
import {AccessGroupRepository} from "../../../../../../domain/repository/access-group.repository";
import {DialogService} from "../../../../../../domain/services/dialog.service";

// @ts-ignore
@Component({
  selector: 'view-access-group',
  templateUrl: 'view-access-group.component.html',
  styleUrls: ['../access-group.component.scss']
})
export class ViewAccessGroupComponent implements OnInit {

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
              private dialogService: DialogService,
              public activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: AuthenticatedViewComponent,
              private grupoAcessoRepository: AccessGroupRepository) {

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
      this.router.navigate(["/grupos-acesso"])
    }
  }

  /**
   *
   */
  public findById() {
    this.grupoAcessoRepository.findById(this.grupoAcesso.id)
      .subscribe((result) =>
        this.grupoAcesso = result
      )
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
              this.messageService.toastSuccess('Registro excluído com sucesso')
            })
        }
      })
  }

}
