import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../services/message.service';
import {InstrumentoJuridico} from "../../../../entity/publicacao/instrumento-juridico.enum";
import {ExtratoContratoRepository} from "../../../../repository/extrato-contrato.repository";
import {ExtratoContratacao} from "../../../../entity/publicacao/extrato-contratacao.model";

@Component({
  selector: 'inserir-extrato-contratacao',
  templateUrl: 'inserir-extrato-contrato.component.html',
  styleUrls: ['../extratos-contrato-view.component.scss']
})
export class InserirExtratoContratoComponent {

  /**
   *
   */
  extratoContratacao: ExtratoContratacao = new ExtratoContratacao();

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

    this.extratoContratacao.instrumentoJuridico = InstrumentoJuridico[InstrumentoJuridico.ATA];

  }

  /**
   *
   * @param extratoContratacao
   */
  public save(extratoContratacao) {
    this.extratoContratacaoRepository.saveWithAnexos(extratoContratacao, [])
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
    if (($event || $event === 0) && this.extratoContratacao.anexos && this.extratoContratacao.anexos.length && this.extratoContratacao.anexos[$event])
      this.extratoContratacao.anexos.splice($event, 1);
  }

}
