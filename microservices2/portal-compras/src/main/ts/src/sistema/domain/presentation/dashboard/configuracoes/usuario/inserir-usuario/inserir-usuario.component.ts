import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../../services/message.service';
import {debounce} from "../../../../../../application/utils/debounce";
import {FormGroup} from "@angular/forms"
import {UsuarioRepository} from "../../../../../repository/usuario.repository";
import {Usuario} from "../../../../../entity/usuario.model";

@Component({
  selector: 'inserir-usuario',
  templateUrl: './inserir-usuario.component.html',
  styleUrls: ['../usuario.component.scss']
})
export class InserirUsuarioComponent implements OnInit {

  /**
   *
   */
  usuario: Usuario;

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

    if (!this.activatedRoute.snapshot.params.id) {
      homeView.toolbar.subhead = 'Usuário / Adicionar';
    }

  }

  ngOnInit() {
    if (this.usuario && this.usuario.id) {
      this.findById();
      this.itsMe = this.homeView.itsMe(this.usuario);
    }

    // this.listPerfis();
  }

  public findById() {
    this.usuarioRepository.findById(this.usuario.id)
      .subscribe(result => this.usuario = result);
  }

  public save(usuario) {
    if (!usuario.administrador && this.isString(usuario.grupoAcesso)) {
      this.messageService.toastWarning('Nenhum grupo de acesso válido foi selecionada.');
      return;
    }

    this.usuarioRepository.save(usuario)
      .then(() => {
        this.router.navigate(['configuracoes/usuarios']);
        this.messageService.toastSuccess(`Novo usuário cadastrado. Instruções de acesso foram enviadas para o seu e-mail.`, 5);
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
