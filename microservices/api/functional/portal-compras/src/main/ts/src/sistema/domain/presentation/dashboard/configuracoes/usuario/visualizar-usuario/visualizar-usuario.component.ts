import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../../services/message.service';
import {UsuarioRepository} from "../../../../../repository/usuario.repository";
import {viewAnimation} from "../../../../../../application/utils/utils";
import {AlterarSenhaComponent} from "../alterar-senha/alterar-senha.component";
import {MatDialog} from "@angular/material";

@Component({
  selector: 'visualizar-usuario',
  templateUrl: './visualizar-usuario.component.html',
  styleUrls: ['../usuario.component.scss'],
  animations: [
    viewAnimation
  ]
})
export class VisualizarUsuarioComponent implements OnInit {

  /**
   *
   */
  usuario: any = {};

  /**
   *
   */
  itsMe: boolean;

  /**
   *
   * @param router
   * @param dialog
   * @param homeView
   * @param activatedRoute
   * @param messageService
   * @param usuarioRepository
   */
  constructor(private router: Router,
              private dialog: MatDialog,
              public activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              public homeView: DashboardViewComponent,
              private usuarioRepository: UsuarioRepository) {
    this.usuario.id = +this.activatedRoute.snapshot.params.id || null;
    homeView.toolbar.subhead = 'Usuário / Detalhes'
  }

  /**
   *
   */
  ngOnInit() {
    if (this.usuario && this.usuario.id) {
      this.findById();
      this.itsMe = this.homeView.itsMe(this.usuario)
    } else {
      this.router.navigate(["configuracoes/usuarios"])
    }
  }

  /**
   *
   */
  public findById() {
    this.usuarioRepository.findById(this.usuario.id)
      .subscribe(result => this.usuario = result)
  }

  /**
   *
   */
  public updateAtivo(id: number) {
    this.usuarioRepository.updateAtivo(id)
      .then((ativo) => {
        this.usuario.ativo = ativo;
        this.messageService.toastSuccess(this.usuario.ativo ? 'Ativado com sucesso.' : 'Inativado com sucesso')
      })
  }

  /**
   *
   */
  public alteraSenha() {
    this.dialog.open(AlterarSenhaComponent, {
      data: this.usuario
    })
  }
}