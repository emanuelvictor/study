import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../services/message.service';
import {EditoraRepository} from "../../../../repository/editora.repository";

@Component({
  selector: 'visualizar-editora',
  templateUrl: 'visualizar-editora.component.html',
  styleUrls: ['../editora.component.scss']
})
export class VisualizarEditoraComponent implements OnInit {

  /**
   *
   */
  editora: any = {};

  /**
   *
   * @param router
   * @param homeView
   * @param activatedRoute
   * @param messageService
   * @param editoraRepository
   */
  constructor(private router: Router,
              private homeView: DashboardViewComponent,
              public activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private editoraRepository: EditoraRepository) {
    this.editora.id = +this.activatedRoute.snapshot.params.id || null;
    homeView.toolbar.subhead = 'Editora / Detalhes';
  }

  /**
   *
   */
  ngOnInit() {
    if (this.editora && this.editora.id) {
      this.findById();
    } else {
      this.router.navigate(["/editoras"]);
    }
  }

  /**
   *
   */
  public findById() {
    this.editoraRepository.findById(this.editora.id)
      .subscribe((result) => {
        this.editora = result;
      });
  }

  /**
   *
   */
  public updateAtivo(id: number) {
    this.editoraRepository.updateAtivo(id)
      .then((ativo) => {
        this.editora.ativo = ativo;
        this.messageService.toastSuccess(this.editora.ativo ? 'Ativado com sucesso.' : 'Inativado com sucesso');
      });
  }
}
