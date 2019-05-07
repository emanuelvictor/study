import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../services/message.service';
import {ExtratoContratacao} from "../../../../entity/publicacao/extrato-contratacao.model";
import {ExtratoContratoRepository} from "../../../../repository/extrato-contrato.repository";
import {Anexo} from "../../../../entity/publicacao/anexo.model";

@Component({
  selector: 'alterar-extrato-contratacao',
  templateUrl: 'alterar-extrato-contrato.component.html',
  styleUrls: ['../extratos-contrato-view.component.scss']
})
export class AlterarExtratoContratoComponent {

  /**
   *
   */
  extratoContratacao: ExtratoContratacao = new ExtratoContratacao();

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
   * @param extratoContratacaoRepository
   */
  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: DashboardViewComponent,
              private extratoContratacaoRepository: ExtratoContratoRepository) {

    homeView.toolbar.subhead = 'Extrato de Contratação / Adicionar';

    this.extratoContratacaoRepository.findById(this.activatedRoute.snapshot.params.id)
      .subscribe(result => {
        this.extratoContratacao = result;
        this.extratoContratacaoRepository.findAllByPublicacaoId(this.extratoContratacao.id, null)
          .subscribe(anexos => this.extratoContratacao.anexos = anexos.content)
      });

  }

  /**
   *
   * @param avisoEdital
   */
  public save(avisoEdital) {
    this.extratoContratacaoRepository.saveWithAnexos(avisoEdital, this.anexosToRemove)
      .then(() => {
        this.router.navigate(['publicacoes/extratos-contratos']);
        this.messageService.toastSuccess();
      });
  }

  /**
   *
   * @param $event
   */
  public removeAnexoPublicacao($event) {
    if (($event || $event === 0) && this.extratoContratacao.anexos && this.extratoContratacao.anexos.length && this.extratoContratacao.anexos[$event]) {
      this.anexosToRemove.push(this.extratoContratacao.anexos[$event]);
      this.extratoContratacao.anexos.splice($event, 1);
    }
  }

}
