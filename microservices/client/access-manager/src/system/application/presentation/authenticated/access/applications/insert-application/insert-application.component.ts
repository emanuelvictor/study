import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticatedViewComponent} from '../../../authenticated-view.component';
import {MessageService} from '../../../../../../domain/services/message.service';
import {ApplicationRepository} from "../../../../../../domain/repository/application.repository";
import {Application} from "../../../../../../domain/entity/application.model";

// @ts-ignore
@Component({
  selector: 'insert-application',
  templateUrl: './insert-application.component.html',
  styleUrls: ['../application.component.scss']
})
export class InsertApplicationComponent implements OnInit {

  /**
   *
   */
  application: Application;

  /**
   *
   */
  itsMe: boolean;

  /**
   *
   */
  error: boolean;

  /**
   *
   * @param router
   * @param homeView
   * @param activatedRoute
   * @param messageService
   * @param applicationRepository
   */
  constructor(private router: Router,
              private homeView: AuthenticatedViewComponent,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private applicationRepository: ApplicationRepository,) {

    if (!this.activatedRoute.snapshot.params.id) {
      homeView.toolbar.subhead = 'Usuário / Adicionar';
    }

  }

  /**
   *
   */
  ngOnInit() {
    if (this.application && this.application.id) {
      this.findById();
      this.itsMe = this.homeView.itsMe(this.application);
    }

    // this.listPerfis();
  }

  /**
   *
   */
  public findById() {
    this.applicationRepository.findById(this.application.id)
      .subscribe(result => this.application = result)
  }

  /**
   *
   * @param application
   */
  public save(application) {
    if (!application.root && this.isString(application.grupoAcesso)) {
      this.messageService.toastWarning('Nenhum grupo de acesso válido foi selecionada.')
      return
    }

    this.applicationRepository.save(application)
      .then(() => {
        this.router.navigate(['access/users']);
        this.messageService.toastSuccess(`Novo usuário cadastrado.`, 5)
      })
  }

  /**
   * Helper
   * @param value
   */
  public isString(value): boolean {
    return typeof value === 'string'
  }

}
