import {Component} from '@angular/core';

@Component({
  selector: 'alterar-fornecedor',
  templateUrl: './alterar-fornecedor.component.html',
  styleUrls: ['../fornecedor.component.scss']
})
export class AlterarFornecedorComponent /*implements OnInit*/ {

  // /**
  //  *
  //  */
  // fornecedor: any = {
  //   perfis: []
  // };
  //
  // /**
  //  *
  //  */
  // itsMe: boolean;
  // error: boolean;
  //
  // /**
  //  *
  //  */
  // perfis: any = [];
  //
  // /**
  //  * Utilizado para o autocomplete
  //  */
  // organizacoes: any = [];
  // organizacaoNome: string = '';
  //
  // /**
  //  * Exibir senha
  //  */
  // inputType: string = 'password';
  //
  //
  // checkboxGroup: FormGroup = new FormGroup({});
  //
  // public debounce = debounce;
  //
  // /**
  //  *
  //  * @param router
  //  * @param homeView
  //  * @param activatedRoute
  //  * @param messageService
  //  * @param fornecedorRepository
  //  */
  // constructor(private router: Router,
  //             private activatedRoute: ActivatedRoute,
  //             private messageService: MessageService,
  //             private homeView: DashboardViewComponent,
  //             private fornecedorRepository: UsuarioRepository,) {
  //
  //   homeView.toolbar.subhead = 'Usuário / Editar';
  //   this.fornecedor.id = +this.activatedRoute.snapshot.params.id;
  //
  // }
  //
  // back() {
  //   if (this.activatedRoute.snapshot.routeConfig.path === 'editar/:id')
  //     this.router.navigate(['configuracoes/fornecedors']);
  //   else
  //     this.router.navigate(['configuracoes/fornecedors/' + this.fornecedor.id]);
  // }
  //
  // ngOnInit() {
  //   if (this.fornecedor && this.fornecedor.id) {
  //     this.findById();
  //     this.itsMe = this.homeView.itsMe(this.fornecedor);
  //   }
  // }
  //
  // public findById() {
  //   this.fornecedorRepository.findById(this.fornecedor.id)
  //     .subscribe(result => this.fornecedor = result);
  // }
  //
  // public save(form) {
  //
  //   if (form.invalid) {
  //     this.messageService.toastWarning();
  //     return;
  //   }
  //
  //   if (this.isString(this.fornecedor.grupoAcesso)) {
  //     this.messageService.toastWarning('Nenhum grupo de acesso válido foi selecionada.');
  //     return;
  //   }
  //
  //   this.fornecedorRepository.save(this.fornecedor)
  //     .then(() => {
  //       this.router.navigate(['configuracoes/fornecedors']);
  //       this.messageService.toastSuccess(`Alterado com sucesso.`, 5);
  //     });
  // }
  //
  // /**
  //  * Exibir senha
  //  */
  // public showPassword() {
  //   this.inputType = this.inputType === 'password' ? 'text' : 'password';
  // }
  //
  // /**
  //  * Helper
  //  * @param value
  //  */
  // public isString(value): boolean {
  //   return typeof value === 'string';
  // }

}
