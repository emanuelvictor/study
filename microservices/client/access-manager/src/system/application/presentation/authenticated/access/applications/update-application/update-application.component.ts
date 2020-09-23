import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticatedViewComponent} from '../../../authenticated-view.component';
import {MessageService} from '../../../../../../domain/services/message.service';
import {debounce} from "../../../../../utils/debounce";
import {FormGroup} from "@angular/forms"
import {ApplicationRepository} from "../../../../../../domain/repository/application.repository";

// @ts-ignore
@Component({
  selector: 'update-application',
  templateUrl: './update-application.component.html',
  styleUrls: ['../application.component.scss']
})
export class UpdateApplicationComponent implements OnInit {


  /**
   *
   */
  application: any = {
    organizacao: {},
    perfis: []
  };

  /**
   *
   */
  itsMe: boolean;
  error: boolean;

  /**
   * Exibir senha
   */
  inputType: string = 'password';

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
              private applicationRepository: ApplicationRepository) {

    homeView.toolbar.subhead = 'Usuário / Editar';
    this.application.id = +this.activatedRoute.snapshot.params.id;

  }

  /**
   *
   */
  ngOnInit() {
    if (this.application && this.application.id) {
      this.findById();
      this.itsMe = this.homeView.itsMe(this.application);
    }
  }


  /**
   *
   */
  back() {
    if (this.activatedRoute.snapshot.routeConfig.path === 'edit/:id')
      this.router.navigate(['access/users']);
    else
      this.router.navigate(['access/users/' + this.application.id]);
  }

  /**
   *
   */
  public findById() {
    this.applicationRepository.findById(this.application.id)
      .subscribe(result => this.application = result);
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

    if (this.isString(this.application.grupoAcesso)) {
      this.messageService.toastWarning('Nenhum grupo de acesso válido foi selecionado.');
      return;
    }

    this.applicationRepository.save(this.application)
      .then(() => {
        this.router.navigate(['access/users']);
        this.messageService.toastSuccess(`Alterado com sucesso`, 5);
      });
  }

  /**
   * Exibir senha
   */
  public showPassword() {
    this.inputType = this.inputType === 'password' ? 'text' : 'password';
  }

  /**
   * Helper
   * @param value
   */
  public isString(value): boolean {
    return typeof value === 'string';
  }

}
