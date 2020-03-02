import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {MessageService} from '../../../../../services/message.service';
import {TipoCadastro} from 'sistema/domain/entity/tipo-cadastro.model';
import {TipoCadastroRepository} from 'sistema/domain/repository/tipo-cadastro.repository';

@Component({
  selector: 'alterar-tipo-cadastro',
  templateUrl: 'alterar-tipo-cadastro.component.html',
  styleUrls: ['../tipo-cadastro.component.scss']
})
export class AlterarTipoCadastroComponent implements OnInit {


  /**
   *
   */
  tipoCadastro: TipoCadastro = new TipoCadastro();

  /**
   *
   * @param router
   * @param homeView
   * @param activatedRoute
   * @param messageService
   * @param tipoCadastroRepository
   */
  constructor(private router: Router,
              private homeView: DashboardViewComponent,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private tipoCadastroRepository: TipoCadastroRepository) {

    homeView.toolbar.subhead = 'Tipo Cadastro / Editar';
    this.tipoCadastro.id = +this.activatedRoute.snapshot.params.id || null;

  }

  back() {
    if (this.activatedRoute.snapshot.routeConfig.path === 'editar/:id')
      this.router.navigate(['cadastros/tipos-cadastros']);
    else
      this.router.navigate(['cadastros/tipos-cadastros/' + this.tipoCadastro.id]);
  }

  ngOnInit() {
    if (this.tipoCadastro && this.tipoCadastro.id) {
      this.findById();
    }
  }

  public findById() {
    this.tipoCadastroRepository.findById(this.tipoCadastro.id)
      .subscribe((result) =>
        this.tipoCadastro = result
      );
  }

  public save(form) {

    if (form.invalid) {
      this.messageService.toastWarning();
      return;
    }

    this.tipoCadastroRepository.save(this.tipoCadastro)
      .then((result) => {
        this.router.navigate(['cadastros/tipos-cadastros']);
        this.messageService.toastSuccess();
      });
  }

}
