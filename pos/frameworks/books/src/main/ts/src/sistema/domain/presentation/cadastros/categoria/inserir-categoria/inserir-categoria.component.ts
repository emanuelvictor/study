import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../../services/message.service';
import {CategoriaRepository} from "../../../../../repository/categoria.repository";
import {Categoria} from "../../../../../entity/categoria.model";

@Component({
  selector: 'inserir-categoria',
  templateUrl: 'inserir-categoria.component.html',
  styleUrls: ['../categoria.component.scss']
})
export class InserirCategoriaComponent {

  /**
   *
   */
  categoria: Categoria = new Categoria();

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
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private categoriaRepository: CategoriaRepository) {

    if (!this.activatedRoute.snapshot.params.id) {
      homeView.toolbar.subhead = 'Categoria / Adicionar';
    } else {
      homeView.toolbar.subhead = 'Categoria / Editar';
      this.categoria.id = +this.activatedRoute.snapshot.params.id;
    }

  }

  public save(form) {

    if (form.invalid) {
      this.messageService.toastWarning();
      return;
    }

    this.categoriaRepository.save(this.categoria)
      .then(() => {
        this.router.navigate(['cadastros/categorias']);
        this.messageService.toastSuccess();
      });
  }

}
