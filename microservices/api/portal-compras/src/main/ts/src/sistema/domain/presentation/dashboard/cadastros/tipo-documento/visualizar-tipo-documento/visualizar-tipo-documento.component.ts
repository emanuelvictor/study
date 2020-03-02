import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../../services/message.service';
import {TipoDocumento} from "../../../../../entity/tipo-documento.model";
import {TipoDocumentoRepository} from "../../../../../repository/tipo-documento.repository";

@Component({
  selector: 'visualizar-tipo-documento',
  templateUrl: 'visualizar-tipo-documento.component.html',
  styleUrls: ['../tipo-documento.component.scss']
})
export class VisualizarTipoDocumentoComponent implements OnInit {

  /**
   *
   */
  tipoDocumento: TipoDocumento = new TipoDocumento();

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
              private tipoCategoriaRepository: TipoDocumentoRepository) {

    this.tipoDocumento.id = +this.activatedRoute.snapshot.params.id || null;
    homeView.toolbar.subhead = 'Tipo Documento / Detalhes';

  }

  /**
   *
   */
  ngOnInit() {
    if (this.tipoDocumento && this.tipoDocumento.id) {
      this.findById();
    } else {
      this.router.navigate(["/tipos-documentos"]);
    }
  }

  /**
   *
   */
  public findById() {
    this.tipoCategoriaRepository.findById(this.tipoDocumento.id)
      .subscribe((result) => {
        this.tipoDocumento = result;
      });
  }

  /**
   *
   */
  public updateAtivo(id: number) {
    this.tipoCategoriaRepository.updateAtivo(id)
      .then((ativo) => {
        this.tipoDocumento.ativo = ativo;
        this.messageService.toastSuccess(this.tipoDocumento.ativo ? 'Ativado com sucesso.' : 'Inativado com sucesso');
      });
  }
}
