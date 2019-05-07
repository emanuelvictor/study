import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../services/message.service';
import {TipoDocumento} from "../../../../entity/tipo-documento.model";
import {TipoDocumentoRepository} from "../../../../repository/tipo-documento.repository";

@Component({
  selector: 'alterar-tipo-documento',
  templateUrl: 'alterar-tipo-documento.component.html',
  styleUrls: ['../tipo-documento.component.scss']
})
export class AlterarTipoDocumentoComponent implements OnInit {


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

    homeView.toolbar.subhead = 'Tipo Documento / Editar';
    this.tipoDocumento.id = +this.activatedRoute.snapshot.params.id || null;

  }

  back() {
    if (this.activatedRoute.snapshot.routeConfig.path === 'editar/:id')
      this.router.navigate(['cadastros/tipos-documentos']);
    else
      this.router.navigate(['cadastros/tipos-documentos/' + this.tipoDocumento.id]);
  }

  ngOnInit() {
    if (this.tipoDocumento && this.tipoDocumento.id) {
      this.findById();
    }
  }

  public findById() {
    this.tipoDocumentoRepository.findById(this.tipoDocumento.id)
      .subscribe((result) => {
        this.tipoDocumento = result;
      });
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
