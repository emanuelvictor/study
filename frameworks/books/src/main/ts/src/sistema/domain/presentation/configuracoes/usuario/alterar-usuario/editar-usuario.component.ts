import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../services/message.service';
import {debounce} from "../../../../../application/utils/debounce";
import {FormGroup} from "@angular/forms"
import {UsuarioRepository} from "../../../../repository/usuario.repository";

@Component({
  selector: 'editar-usuario',
  templateUrl: './editar-usuario.component.html',
  styleUrls: ['../usuario.component.scss']
})
export class EditarUsuarioComponent implements OnInit {


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
              private homeView: DashboardViewComponent,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private usuarioRepository: UsuarioRepository,) {

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
      this.messageService.toastWarning('Nenhum grupo de acesso válido foi selecionada.');
      return;
    }

    this.usuarioRepository.save(this.usuario)
      .then(() => {
        this.router.navigate(['configuracoes/usuarios']);
        this.messageService.toastSuccess(`Alterado com sucesso.`, 5);
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
