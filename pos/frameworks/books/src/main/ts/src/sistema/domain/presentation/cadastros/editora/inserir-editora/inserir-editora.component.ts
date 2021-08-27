import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {Editora} from "../../../../entity/editora.model";
import {EditoraRepository} from "../../../../repository/editora.repository";
import {MessageService} from "../../../../services/message.service";

@Component({
  selector: 'inserir-editora',
  templateUrl: 'inserir-editora.component.html',
  styleUrls: ['../editora.component.scss']
})
export class InserirEditoraComponent {

  /**
   *
   */
  editora: Editora = new Editora();

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
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private editoraRepository: EditoraRepository) {

    if (!this.activatedRoute.snapshot.params.id) {
      homeView.toolbar.subhead = 'Editora / Adicionar';
    } else {
      homeView.toolbar.subhead = 'Editora / Editar';
      this.editora.id = +this.activatedRoute.snapshot.params.id;
    }

  }

  public save(form) {

    if (form.invalid) {
      this.messageService.toastWarning();
      return;
    }

    this.editoraRepository.save(this.editora)
      .then(() => {
        this.router.navigate(['cadastros/editoras']);
        this.messageService.toastSuccess();
      });
  }

}
