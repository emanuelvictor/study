import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../../services/message.service';
import {TipoDocumentoRepository} from "../../../../../repository/tipo-documento.repository";
import {TipoDocumento} from "../../../../../entity/tipo-documento.model";

@Component({
  selector: 'inserir-tipo-documento',
  templateUrl: 'inserir-tipo-documento.component.html',
  styleUrls: ['../tipo-documento.component.scss']
})
export class InserirTipoDocumentoComponent {

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
   * @param tipoDocumentoRepository
   */
  constructor(private router: Router,
              private homeView: DashboardViewComponent,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private tipoDocumentoRepository: TipoDocumentoRepository) {

    if (!this.activatedRoute.snapshot.params.id) {
      homeView.toolbar.subhead = 'Tipo Documento / Adicionar';
    } else {
      homeView.toolbar.subhead = 'Tipo Documento / Editar';
      this.tipoDocumento.id = +this.activatedRoute.snapshot.params.id;
    }

  }

  public save(form) {

    if (form.invalid) {
      this.messageService.toastWarning();
      return;
    }

    this.tipoDocumentoRepository.save(this.tipoDocumento)
      .then((result) => {
        this.router.navigate(['cadastros/tipos-documentos']);
        this.messageService.toastSuccess();
      });
  }

}
