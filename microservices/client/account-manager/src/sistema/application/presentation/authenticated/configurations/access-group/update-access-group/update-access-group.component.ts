import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticatedViewComponent} from '../../../authenticated-view.component';
import {MessageService} from '../../../../../../domain/services/message.service';
import {AccessGroupRepository} from "../../../../../../domain/repository/access-group.repository";
import {AccessGroup} from "../../../../../../domain/entity/access-group.model";

// @ts-ignore
@Component({
  selector: 'alterar-grupo-acesso',
  templateUrl: 'update-access-group.component.html',
  styleUrls: ['../access-group.component.scss']
})
export class UpdateAccessGroupComponent implements OnInit {


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
    homeView.toolbar.subhead = 'Grupo de Acesso / Editar';
  }

  /**
   *
   */
  back() {
    if (this.activatedRoute.snapshot.routeConfig.path === 'editar/:id')
      this.router.navigate(['configuracoes/grupos-acesso']);
    else
      this.router.navigate(['configuracoes/grupos-acesso/' + (+this.activatedRoute.snapshot.params.id)]);
  }

  /**
   *
   */
  ngOnInit() {
    this.findById();
  }

  /**
   *
   */
  public findById() {
    this.accessGroupRepository.findById(+this.activatedRoute.snapshot.params.id)
      .subscribe((result) =>
        this.accessGroup = result
      );
  }

  /**
   *
   * @param form
   */
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
