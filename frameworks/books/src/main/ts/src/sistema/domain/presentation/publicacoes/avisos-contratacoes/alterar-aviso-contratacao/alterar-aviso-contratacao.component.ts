import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../services/message.service';
import {AvisoContratacao} from "../../../../entity/publicacao/aviso-contratacao.model";
import {EditoraRepository} from "../../../../repository/editora.repository";
import {Anexo} from "../../../../entity/publicacao/anexo.model";

@Component({
  selector: 'alterar-aviso-contratacao',
  templateUrl: 'alterar-aviso-contratacao.component.html',
  styleUrls: ['../avisos-contratacoes-view.component.scss']
})
export class AlterarAvisoContratacaoComponent {

  /**
   *
   */
  avisoContratacao: AvisoContratacao = new AvisoContratacao();

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
   * @param avisoContratacaoRepository
   */
  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: DashboardViewComponent,
              private avisoContratacaoRepository: EditoraRepository) {

    homeView.toolbar.subhead = 'Aviso de Contratação / Adicionar';

    this.avisoContratacaoRepository.findById(this.activatedRoute.snapshot.params.id)
      .subscribe(result => {
        this.avisoContratacao = result;
        this.avisoContratacaoRepository.findAllByPublicacaoId(this.avisoContratacao.id, null)
          .subscribe(anexos => this.avisoContratacao.anexos = anexos.content)
      });

  }

  /**
   *
   * @param avisoContratacao
   */
  public save(avisoContratacao) {
    this.avisoContratacaoRepository.saveWithAnexos(avisoContratacao, this.anexosToRemove)
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
      this.anexosToRemove.push(this.avisoContratacao.anexos[$event]);
      this.avisoContratacao.anexos.splice($event, 1);
    }
  }
}
