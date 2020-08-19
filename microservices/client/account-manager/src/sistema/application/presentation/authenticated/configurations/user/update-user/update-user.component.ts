import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticatedViewComponent} from '../../../authenticated-view.component';
import {MessageService} from '../../../../../../domain/services/message.service';
import {debounce} from "../../../../../utils/debounce";
import {FormGroup} from "@angular/forms"
import {UserRepository} from "../../../../../../domain/repository/user.repository";

// @ts-ignore
@Component({
  selector: 'editar-usuario',
  templateUrl: './update-user.component.html',
  styleUrls: ['../user.component.scss']
})
export class UpdateUserComponent implements OnInit {


  /**
   *
   */
  usuario: any = {
    organizacao: {},
    perfis: []
  };

  /**
   *
   */
  itsMe: boolean;
  error: boolean;

  /**
   *
   */
  perfis: any = [];

  /**
   * Utilizado para o autocomplete
   */
  organizacoes: any = [];
  organizacaoNome: string = '';

  /**
   * Exibir senha
   */
  inputType: string = 'password';


  checkboxGroup: FormGroup = new FormGroup({});

  public debounce = debounce;

  /**
   *
   * @param router
   * @param homeView
   * @param activatedRoute
   * @param messageService
   * @param usuarioRepository
   */
  constructor(private router: Router,
              private homeView: AuthenticatedViewComponent,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private usuarioRepository: UserRepository) {

    homeView.toolbar.subhead = 'Usuário / Editar';
    this.usuario.id = +this.activatedRoute.snapshot.params.id;

  }

  back() {
    if (this.activatedRoute.snapshot.routeConfig.path === 'editar/:id')
      this.router.navigate(['configuracoes/usuarios']);
    else
      this.router.navigate(['configuracoes/usuarios/' + this.usuario.id]);
  }

  ngOnInit() {
    if (this.usuario && this.usuario.id) {
      this.findById();
      this.itsMe = this.homeView.itsMe(this.usuario);
    }
  }

  public findById() {
    this.usuarioRepository.findById(this.usuario.id)
      .subscribe(result => this.usuario = result);
  }

  public save(form) {

    if (form.invalid) {
      this.messageService.toastWarning();
      return;
    }

    if (this.isString(this.usuario.grupoAcesso)) {
      this.messageService.toastWarning('Nenhum grupo de acesso válido foi selecionado.');
      return;
    }

    this.usuarioRepository.save(this.usuario)
      .then(() => {
        this.router.navigate(['configuracoes/usuarios']);
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