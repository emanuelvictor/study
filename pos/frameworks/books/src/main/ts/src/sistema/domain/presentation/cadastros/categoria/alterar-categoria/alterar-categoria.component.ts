import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../services/message.service';
import {Categoria} from "../../../../entity/categoria.model";
import {CategoriaRepository} from "../../../../repository/categoria.repository";

@Component({
  selector: 'alterar-categoria',
  templateUrl: 'alterar-categoria.component.html',
  styleUrls: ['../categoria.component.scss']
})
export class AlterarCategoriaComponent implements OnInit {


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

    homeView.toolbar.subhead = 'Categoria / Editar';
    this.categoria.id = +this.activatedRoute.snapshot.params.id;

  }

  back() {
    if (this.activatedRoute.snapshot.routeConfig.path === 'editar/:id')
      this.router.navigate(['cadastros/categorias']);
    else
      this.router.navigate(['cadastros/categorias/' + this.categoria.id]);
  }

  ngOnInit() {
    if (this.categoria && this.categoria.id) {
      this.findById();
    }
  }

  public findById() {
    this.categoriaRepository.findById(this.categoria.id)
      .subscribe((result) => {
        this.categoria = result;
      });
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
