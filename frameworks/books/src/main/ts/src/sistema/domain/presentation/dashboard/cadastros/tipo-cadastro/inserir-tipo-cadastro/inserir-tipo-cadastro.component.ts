import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../../services/message.service';
import {TipoCadastroRepository} from 'sistema/domain/repository/tipo-cadastro.repository';
import {TipoCadastro} from 'sistema/domain/entity/tipo-cadastro.model';

@Component({
  selector: 'inserir-tipo-cadastro',
  templateUrl: 'inserir-tipo-cadastro.component.html',
  styleUrls: ['../tipo-cadastro.component.scss']
})
export class InserirTipoCadastroComponent {

  /**
   *
   */
  tipoCadastro: TipoCadastro = new TipoCadastro();

  /**
   *
   * @param router
   * @param activatedRoute
   * @param messageService
   * @param homeView
   * @param tipoCadastroRepository
   */
  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: DashboardViewComponent,
              private tipoCadastroRepository: TipoCadastroRepository) {

      homeView.toolbar.subhead = 'Tipo Cadastro / Adicionar';

  }

  public save() {
    this.tipoCadastroRepository.save(this.tipoCadastro)
      .then(() => {
        this.router.navigate(['cadastros/tipos-cadastros']);
        this.messageService.toastSuccess();
      });
  }

}
