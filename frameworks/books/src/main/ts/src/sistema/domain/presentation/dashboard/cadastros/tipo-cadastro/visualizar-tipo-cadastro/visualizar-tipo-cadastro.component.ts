import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../../services/message.service';
import {TipoCadastro} from 'sistema/domain/entity/tipo-cadastro.model';
import {TipoCadastroRepository} from 'sistema/domain/repository/tipo-cadastro.repository';

@Component({
  selector: 'visualizar-tipo-cadastro',
  templateUrl: 'visualizar-tipo-cadastro.component.html',
  styleUrls: ['../tipo-cadastro.component.scss']
})
export class VisualizarTipoCadastroComponent implements OnInit {

  /**
   *
   */
  tipoCadastro: TipoCadastro = new TipoCadastro();

  /**
   *
   * @param router
   * @param homeView
   * @param activatedRoute
   * @param messageService
   * @param tipoCategoriaRepository
   */
  constructor(private router: Router,
              private homeView: DashboardViewComponent,
              public activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private tipoCadastroRepository: TipoCadastroRepository) {

    this.tipoCadastro.id = +this.activatedRoute.snapshot.params.id || null;
    homeView.toolbar.subhead = 'Tipo Cadastro / Detalhes';

  }

  /**
   *
   */
  ngOnInit() {
    if (this.tipoCadastro && this.tipoCadastro.id) {
      this.findById();
    } else {
      this.router.navigate(["/tipos-cadastros"]);
    }
  }

  /**
   *
   */
  public findById() {
    this.tipoCadastroRepository.findById(this.tipoCadastro.id)
      .subscribe((result) => {
        this.tipoCadastro = result;
      });
  }

  /**
   *
   */
  public updateAtivo(id: number) {
    this.tipoCadastroRepository.updateAtivo(id)
      .then((ativo) => {
        this.tipoCadastro.ativo = ativo;
        this.messageService.toastSuccess(this.tipoCadastro.ativo ? 'Ativado com sucesso.' : 'Inativado com sucesso');
      });
  }
}
