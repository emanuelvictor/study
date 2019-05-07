import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../services/message.service';
import {AvisoEdital} from "../../../../entity/publicacao/aviso-edital.model";
import {LivroRepository} from "../../../../repository/livro.repository";
import {Anexo} from "../../../../entity/publicacao/anexo.model";

@Component({
  selector: 'alterar-aviso-edital',
  templateUrl: 'alterar-aviso-edital.component.html',
  styleUrls: ['../avisos-editais-view.component.scss']
})
export class AlterarAvisoEditalComponent {

  /**
   *
   */
  avisoEdital: AvisoEdital = new AvisoEdital();

  /**
   *
   */
  anexosToRemove: Anexo[] = [];

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
              private avisoEditalRepository: LivroRepository) {

    homeView.toolbar.subhead = 'Aviso de Edital / Adicionar';

    this.avisoEditalRepository.findById(this.activatedRoute.snapshot.params.id)
      .subscribe(result => {
        this.avisoEdital = result;
        this.avisoEditalRepository.findAllByPublicacaoId(this.avisoEdital.id, null)
          .subscribe(anexos => this.avisoEdital.anexos = anexos.content)
      });

  }

  /**
   *
   * @param avisoEdital
   */
  public save(avisoEdital) {
    this.avisoEditalRepository.saveWithAnexos(avisoEdital, this.anexosToRemove)
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
    if (($event || $event === 0) && this.avisoEdital.anexos && this.avisoEdital.anexos.length && this.avisoEdital.anexos[$event]) {
      this.anexosToRemove.push(this.avisoEdital.anexos[$event]);
      this.avisoEdital.anexos.splice($event, 1);
    }
  }

}
