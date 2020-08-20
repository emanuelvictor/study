import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticatedViewComponent} from '../../../authenticated-view.component';
import {MessageService} from '../../../../../../domain/services/message.service';
import {AccessGroup} from "../../../../../../domain/entity/access-group.model";
import {AccessGroupRepository} from "../../../../../../domain/repository/access-group.repository";

// @ts-ignore
@Component({
  selector: 'insert-access-group',
  templateUrl: 'insert-access-group.component.html',
  styleUrls: ['../access-group.component.scss']
})
export class InsertAccessGroupComponent {

  /**
   *
   */
  accessGroup: AccessGroup = new AccessGroup();

  /**
   *
   * @param router
   * @param homeView
   * @param activatedRoute
   * @param messageService
   * @param accessGroupRepository
   */
  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: AuthenticatedViewComponent,
              private accessGroupRepository: AccessGroupRepository) {

    homeView.toolbar.subhead = 'Grupo de Acesso / Adicionar';
    this.accessGroup.accessGroupPermissions = [];

  }

  public save(form) {

    if (form.invalid) {
      this.messageService.toastWarning();
      return;
    }

    this.accessGroupRepository.save(this.accessGroup)
      .then(() => {
        this.router.navigate(['configuracoes/grupos-acesso']);
        this.messageService.toastSuccess();
      });
  }

}
