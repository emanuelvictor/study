import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../services/message.service';
import {Livro} from "../../../../entity/livro.model";
import {LivroRepository} from "../../../../repository/livro.repository";

@Component({
  selector: 'alterar-livro',
  templateUrl: 'alterar-livro.component.html',
  styleUrls: ['../livro.component.scss']
})
export class AlterarLivroComponent implements OnInit {


  /**
   *
   */
  livro: Livro = new Livro();

  /**
   *
   * @param router
   * @param homeView
   * @param activatedRoute
   * @param messageService
   * @param livroRepository
   */
  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: DashboardViewComponent,
              private livroRepository: LivroRepository) {

    homeView.toolbar.subhead = 'Livro / Editar';
    this.livro.id = +this.activatedRoute.snapshot.params.id;

  }

  /**
   *
   */
  back() {
    if (this.activatedRoute.snapshot.routeConfig.path === 'editar/:id')
      this.router.navigate(['cadastros/livros']);
    else
      this.router.navigate(['cadastros/livros/' + this.livro.id]);
  }

  /**
   *
   */
  ngOnInit() {
    if (this.livro && this.livro.id) {
      this.findById();
    }
  }

  /**
   *
   */
  public findById() {
    this.livroRepository.findById(this.livro.id)
      .subscribe((result) => {
        this.livro = result;
      });
  }

  public save(form) {

    if (form.invalid) {
      this.messageService.toastWarning();
      return;
    }

    this.livroRepository.save(this.livro)
      .then(() => {
        this.router.navigate(['cadastros/livros']);
        this.messageService.toastSuccess();
      });
  }

}
