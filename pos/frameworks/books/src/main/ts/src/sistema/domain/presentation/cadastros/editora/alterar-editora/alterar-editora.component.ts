import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../services/message.service';
import {Editora} from "../../../../entity/editora.model";
import {EditoraRepository} from "../../../../repository/editora.repository";

@Component({
  selector: 'alterar-editora',
  templateUrl: 'alterar-editora.component.html',
  styleUrls: ['../editora.component.scss']
})
export class AlterarEditoraComponent implements OnInit {


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

    homeView.toolbar.subhead = 'Editora / Editar';
    this.editora.id = +this.activatedRoute.snapshot.params.id;

  }

  back() {
    if (this.activatedRoute.snapshot.routeConfig.path === 'editar/:id')
      this.router.navigate(['cadastros/editoras']);
    else
      this.router.navigate(['cadastros/editoras/' + this.editora.id]);
  }

  ngOnInit() {
    if (this.editora && this.editora.id) {
      this.findById();
    }
  }

  public findById() {
    this.editoraRepository.findById(this.editora.id)
      .subscribe((result) => {
        this.editora = result;
      });
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
