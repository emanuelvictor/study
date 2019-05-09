import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../services/message.service';
import {CategoriaRepository} from "../../../../repository/categoria.repository";

@Component({
  selector: 'visualizar-categoria',
  templateUrl: 'visualizar-categoria.component.html',
  styleUrls: ['../categoria.component.scss']
})
export class VisualizarCategoriaComponent implements OnInit {

  /**
   *
   */
  categoria: any = {};

  /**
   *
   * @param router
   * @param homeView
   * @param activatedRoute
   * @param messageService
   * @param categoriaRepository
   */
  constructor(private router: Router,
              private homeView: DashboardViewComponent,
              public activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private categoriaRepository: CategoriaRepository) {
    this.categoria.id = +this.activatedRoute.snapshot.params.id || null;
    homeView.toolbar.subhead = 'Categoria / Detalhes';
  }

  /**
   *
   */
  ngOnInit() {
    if (this.categoria && this.categoria.id) {
      this.findById();
    } else {
      this.router.navigate(["/categorias"]);
    }
  }

  /**
   *
   */
  public findById() {
    this.categoriaRepository.findById(this.categoria.id)
      .subscribe((result) => {
        this.categoria = result;
      });
  }

  /**
   *
   */
  public updateAtivo(id: number) {
    this.categoriaRepository.updateAtivo(id)
      .then((ativo) => {
        this.categoria.ativo = ativo;
        this.messageService.toastSuccess(this.categoria.ativo ? 'Ativado com sucesso.' : 'Inativado com sucesso');
      });
  }
}
