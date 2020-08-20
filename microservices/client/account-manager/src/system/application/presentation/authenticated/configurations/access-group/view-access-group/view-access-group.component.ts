import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticatedViewComponent} from '../../../authenticated-view.component';
import {MessageService} from '../../../../../../domain/services/message.service';
import {AccessGroupRepository} from "../../../../../../domain/repository/access-group.repository";
import {DialogService} from "../../../../../../domain/services/dialog.service";
import {AccessGroup} from "../../../../../../domain/entity/access-group.model";

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
  accessGroup: AccessGroup = new AccessGroup();

  /**
   *
   * @param router
   * @param homeView
   * @param dialogService
   * @param activatedRoute
   * @param messageService
   * @param accessGroupRepository
   */
  constructor(private router: Router,
              private dialogService: DialogService,
              public activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: AuthenticatedViewComponent,
              private accessGroupRepository: AccessGroupRepository) {

    this.accessGroup.id = +this.activatedRoute.snapshot.params.id || null;
    homeView.toolbar.subhead = 'Grupo de Acesso / Detalhes';

  }

  /**
   *
   */
  ngOnInit() {
    if (this.accessGroup && this.accessGroup.id) {
      this.findById();
    } else {
      this.router.navigate(['/grupos-acesso'])
    }
  }

  /**
   *
   */
  public findById() {
    this.accessGroupRepository.findById(this.accessGroup.id)
      .subscribe((result) => this.accessGroup = result)
  }

  /**
   * Função para confirmar a exclusão de um registro permanentemente
   * @param accessGroup
   */
  public openDeleteDialog(accessGroup) {

    this.dialogService.confirmDelete(accessGroup, 'Grupo de Acesso')
      .then((accept: boolean) => {

        if (accept) {
          this.accessGroupRepository.delete(accessGroup.id)
            .then(() => {
              this.router.navigate(['configuracoes/grupos-acesso']);
              this.messageService.toastSuccess('Registro excluído com sucesso')
            })
        }
      })
  }

}
