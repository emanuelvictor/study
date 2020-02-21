import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticatedViewComponent} from '../../../authenticated-view.component';
import {MessageService} from '../../../../../../domain/services/message.service';
import {viewAnimation} from "../../../../../utils/utils";
import {MatDialog} from "@angular/material";
import {InventarioRepository} from "../../../../../../domain/repository/inventario.repository";
import {Inventario} from "../../../../../../domain/entity/patrimonio/inventario/inventario.model";
import {DialogService} from "../../../../../../domain/services/dialog.service";
import {AuthenticationService} from "../../../../../../domain/services/authentication.service";

// @ts-ignore
@Component({
  selector: 'visualizar-inventario',
  templateUrl: './visualizar-inventario.component.html',
  styleUrls: ['../inventarios.scss'],
  animations: [
    viewAnimation
  ]
})
export class VisualizarInventarioComponent implements OnInit {

  /**
   *
   */
  inventario: Inventario = new Inventario();

  /**
   *
   */
  single2: any[] = [];

  /**
   *
   * @param router
   * @param dialog
   * @param dialogService
   * @param homeView
   * @param activatedRoute
   * @param messageService
   * @param authenticationService
   * @param inventarioRepository
   */
  constructor(private router: Router,
              private dialog: MatDialog,
              private dialogService: DialogService,
              public activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              public homeView: AuthenticatedViewComponent,
              private authenticationService: AuthenticationService,
              private inventarioRepository: InventarioRepository) {

    this.inventario.id = +this.activatedRoute.snapshot.params.id || null;
    homeView.toolbar.subhead = 'Inventário / Detalhes'

  }

  /**
   *
   */
  ngOnInit() {
    if (this.inventario && this.inventario.id) {
      this.findById();
    } else {
      this.router.navigate(["bens/inventarios"])
    }
  }

  /**
   *
   */
  public findById() {
    this.inventarioRepository.findById(this.inventario.id).subscribe(result => {
      this.inventario = result;
      const centrosCusto = this.inventario.centrosCusto.map(centroCustoInventario => {
        (centroCustoInventario.centroCusto as any).colaboradores = centroCustoInventario.executores;
        (centroCustoInventario.centroCusto as any).centroCustoValue = true;

        (centroCustoInventario.centroCusto as any).usuarioAutenticadoIsExecutor = (this.authenticationService.usuarioAutenticado as any).executores.filter(executor => executor.centroCustoInventario.id === centroCustoInventario.id).length > 0;
        (centroCustoInventario.centroCusto as any).usuarioAutenticadoIsGestor = (this.authenticationService.usuarioAutenticado as any).centrosCusto.filter(centroCusto => centroCusto.codigo === centroCustoInventario.centroCusto.codigo).length > 0;
        (centroCustoInventario.centroCusto as any).status = centroCustoInventario.status;

        return centroCustoInventario
      }).filter(value => (value.executores.filter(value1 => (value1.usuario.id === this.authenticationService.usuarioAutenticado.id)).length) || (value.centroCusto.gestor.id === this.authenticationService.usuarioAutenticado.id) || this.authenticationService.usuarioAutenticado.isPatrimonio || this.authenticationService.usuarioAutenticado.root);

      this.single2 = [
        {
          'name': 'Em execução',
          'value': centrosCusto.filter(value => value.status === 'EM_EXECUCAO').length
        },
        {
          'name': 'Em análise',
          'value': centrosCusto.filter(value => value.status === 'EM_ANALISE').length
        },
        {
          'name': 'Aprovado',
          'value': centrosCusto.filter(value => value.status === 'APROVADO').length
        },
        {
          'name': 'Finalizado',
          'value': centrosCusto.filter(value => value.status === 'FINALIZADO').length
        }
      ]
    })
  }

  /**
   * Função para confirmar a exclusão de um registro permanentemente
   * @param inventario
   */
  public openDeleteDialog(inventario) {

    this.dialogService.confirmDelete(inventario, 'INVENTÁRIO')
      .then((accept: boolean) => {

        if (accept) {
          this.inventarioRepository.delete(inventario.id)
            .then(() => {
              this.router.navigate(['bens/inventarios']);
              this.messageService.toastSuccess('Inventário excluído com sucesso')
            })
        }
      })
  }
}
