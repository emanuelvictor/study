import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {Livro} from "../../../../entity/livro.model";
import {LivroRepository} from "../../../../repository/livro.repository";
import {MessageService} from "../../../../services/message.service";

@Component({
  selector: 'inserir-livro',
  templateUrl: 'inserir-livro.component.html',
  styleUrls: ['../livro.component.scss']
})
export class InserirLivroComponent {

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
              private homeView: DashboardViewComponent,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private livroRepository: LivroRepository) {

    if (!this.activatedRoute.snapshot.params.id) {
      homeView.toolbar.subhead = 'Livro / Adicionar';
    } else {
      homeView.toolbar.subhead = 'Livro / Editar';
      this.livro.id = +this.activatedRoute.snapshot.params.id;
    }

  }

  /**
   *
   * @param form
   */
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
