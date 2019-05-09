import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../services/message.service';
import {LivroRepository} from "../../../../repository/livro.repository";

@Component({
  selector: 'visualizar-livro',
  templateUrl: 'visualizar-livro.component.html',
  styleUrls: ['../livro.component.scss']
})
export class VisualizarLivroComponent implements OnInit {

  /**
   *
   */
  livro: any = {};

  /**
   *
   * @param router
   * @param homeView
   * @param activatedRoute
   * @param messageService
   * @param livroRepository
   */
  constructor(private router: Router,
              public activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private livroRepository: LivroRepository,
              private homeView: DashboardViewComponent) {
    this.livro.id = +this.activatedRoute.snapshot.params.id || null;
    homeView.toolbar.subhead = 'Livro / Detalhes';
  }

  /**
   *
   */
  ngOnInit() {
    if (this.livro && this.livro.id) {
      this.findById();
    } else {
      this.router.navigate(["/livros"]);
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

}
