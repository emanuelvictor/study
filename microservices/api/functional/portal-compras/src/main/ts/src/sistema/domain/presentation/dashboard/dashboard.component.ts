import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {HasPermissionDirective} from "../../../application/has-permission/has-permission";
import {FornecedorRepository} from "../../repository/fornecedor.repository";

@Component({
  selector: 'dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  /**
   *
   */
  public emCriacao: number = 0;

  /**
   *
   */
  public emAnalise: number = 0;

  /**
   *
   */
  public aprovados: number = 0;

  /**
   *
   */
  public recusados: number = 0;

  /**
   *
   */
  public vencidos: number = 0;

  /**
   *
   */
  public hasPermission: boolean = false;

  /**
   *
   */
  constructor(private fornecedorRepository: FornecedorRepository,
              private authenticationService: AuthenticationService) {
  }

  /**
   *
   */
  ngOnInit() {

    this.authenticationService.requestContaAutenticada().subscribe(result => {
      const usuarioLogado : any = result;
      this.authenticationService.getAuthoritiesByUsuarioId(this.authenticationService.usuarioAutenticado.id).subscribe(result => {
        usuarioLogado.authorities = result;
        if (HasPermissionDirective.checkPermission(usuarioLogado, ['administrador', 'fornecedores', 'fornecedores/get'])) {

          this.hasPermission = true;

          this.fornecedorRepository.emCriacao().subscribe(result => {
            this.emCriacao = result as number
          });
          this.fornecedorRepository.emAnalise().subscribe(result => {
            this.emAnalise = result as number
          });
          this.fornecedorRepository.aprovados().subscribe(result => {
            this.aprovados = result as number
          });
          this.fornecedorRepository.recusados().subscribe(result => {
            this.recusados = result as number
          });
          this.fornecedorRepository.vencidos().subscribe(result => {
            this.vencidos = result as number
          })
        }
      })
    })
  }

}
