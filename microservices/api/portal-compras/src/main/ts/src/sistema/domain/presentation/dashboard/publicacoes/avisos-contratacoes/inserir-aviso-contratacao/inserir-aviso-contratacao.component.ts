import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../../services/message.service';
import {AvisoContratacao} from "../../../../../entity/publicacao/aviso-contratacao.model";
import {AvisoContratacaoRepository} from "../../../../../repository/aviso-contratacao.repository";
import {Modalidade} from "../../../../../entity/publicacao/modalidade.enum";

@Component({
  selector: 'inserir-aviso-contratacao',
  templateUrl: 'inserir-aviso-contratacao.component.html',
  styleUrls: ['../avisos-contratacoes-view.component.scss']
})
export class InserirAvisoContratacaoComponent {

  /**
   *
   */
  avisoContratacao: AvisoContratacao = new AvisoContratacao();

  /**
   *
   * @param router
   * @param activatedRoute
   * @param messageService
   * @param homeView
   * @param avisoContratacaoRepository
   */
  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: DashboardViewComponent,
              private avisoContratacaoRepository: AvisoContratacaoRepository) {

    homeView.toolbar.subhead = 'Aviso de Contratação / Adicionar';

    this.avisoContratacao.modalidade = Modalidade[Modalidade.EDITAL];

  }

  public save(form) {

    if (form.invalid) {
      this.messageService.toastWarning();
      return;
    }

    this.avisoContratacaoRepository.saveWithAnexos(this.avisoContratacao,[])
      .then(() => {
        this.router.navigate(['publicacoes/avisos-contratacoes']);
        this.messageService.toastSuccess();
      });
  }

  /**
   *
   * @param $event
   */
  public removeAnexoPublicacao($event) {
    if (($event || $event === 0) && this.avisoContratacao.anexos && this.avisoContratacao.anexos.length && this.avisoContratacao.anexos[$event]) {
      this.avisoContratacao.anexos.splice($event, 1);
    }
  }

}
