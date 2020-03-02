import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../../services/message.service';
import {AvisoEdital} from "../../../../../entity/publicacao/aviso-edital.model";
import {AvisoEditalRepository} from "../../../../../repository/aviso-edital.repository";
import {Status} from "../../../../../entity/publicacao/status.enum";

@Component({
  selector: 'inserir-aviso-edital',
  templateUrl: 'inserir-aviso-edital.component.html',
  styleUrls: ['../avisos-editais-view.component.scss']
})
export class InserirAvisoEditalComponent {

  /**
   *
   */
  avisoEdital: AvisoEdital = new AvisoEdital();

  /**
   *
   * @param router
   * @param activatedRoute
   * @param messageService
   * @param homeView
   * @param avisoEditalRepository
   */
  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: DashboardViewComponent,
              private avisoEditalRepository: AvisoEditalRepository) {

    homeView.toolbar.subhead = 'Aviso de Edital / Adicionar';

    this.avisoEdital.status = Status[Status.ABERTO];

  }

  /**
   *
   * @param entity
   */
  public save(entity) {
    this.avisoEditalRepository.saveWithAnexos(entity, [])
      .then(() => {
        this.router.navigate(['publicacoes/avisos-editais']);
        this.messageService.toastSuccess();
      });
  }

  /**
   *
   * @param $event
   */
  public removeAnexoPublicacao($event) {
    if (($event || $event === 0) && this.avisoEdital.anexos && this.avisoEdital.anexos.length && this.avisoEdital.anexos[$event])
      this.avisoEdital.anexos.splice($event, 1);
  }

}
