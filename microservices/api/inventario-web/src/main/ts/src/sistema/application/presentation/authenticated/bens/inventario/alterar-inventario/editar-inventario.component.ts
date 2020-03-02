import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticatedViewComponent} from '../../../authenticated-view.component';
import {MessageService} from '../../../../../../domain/services/message.service';
import {InventarioRepository} from "../../../../../../domain/repository/inventario.repository";
import {Inventario} from "../../../../../../domain/entity/patrimonio/inventario/inventario.model";
import {CentroCusto} from "../../../../../../domain/entity/pessoal.dto/centro-custo.model";
import {CentroCustoInventario} from "../../../../../../domain/entity/patrimonio/inventario/centro-custo-inventario.model";
import {Usuario} from "../../../../../../domain/entity/usuario.model";

// @ts-ignore
@Component({
  selector: 'editar-inventario',
  templateUrl: './editar-inventario.component.html',
  styleUrls: ['../inventarios.scss']
})
export class EditarInventarioComponent implements OnInit {


  /**
   *
   */
  public filter: string;

  /**
   *
   */
  public centroCustoCodigoFilter: string;

  /**
   *
   */
  inventario: Inventario = new Inventario();

  /**
   *
   * @param router
   * @param homeView
   * @param activatedRoute
   * @param messageService
   * @param inventarioRepository
   */
  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: AuthenticatedViewComponent,
              private inventarioRepository: InventarioRepository) {
    this.inventario.id = +this.activatedRoute.snapshot.params.id
  }

  /**
   *
   */
  ngOnInit() {
    if (this.inventario && this.inventario.id)
      this.findById()
  }

  /**
   *
   */
  back() {
    if (this.activatedRoute.snapshot.routeConfig.path === 'editar/:id')
      this.router.navigate(['bens/inventarios']);
    else
      this.router.navigate(['bens/inventarios/' + this.inventario.id])
  }

  /**
   *
   */
  public findById() {
    this.inventarioRepository.findById(this.inventario.id).subscribe(result => this.inventario = result)
  }

  /**
   *
   * @param inventario
   */
  public save(inventario: Inventario) {
    // inventario.centrosCusto.forEach(centroCustoInventario =>
    //   centroCustoInventario.centroCusto.gestor = ({id: centroCustoInventario.centroCusto.gestor.id} as any)
    // );
    this.inventarioRepository.save(inventario)
      .then(() => {
        this.router.navigate(['bens/inventarios']);
        this.messageService.toastSuccess(`Alterado com sucesso`, 5)
      })
  }

  /**
   *
   * @param centroCusto
   */
  public async push(centroCusto: CentroCusto) {
    if (!this.inventario.centrosCusto)
      this.inventario.centrosCusto = [];

    const oldCentroCustoInventario = (await this.inventarioRepository.findByInventarioIdAndCentroCustoCodigo(this.inventario.id, centroCusto.codigo));
    if (oldCentroCustoInventario)
      this.inventario.centrosCusto.push(oldCentroCustoInventario);
    else {
      const centroCustoInventario: CentroCustoInventario = new CentroCustoInventario();
      centroCustoInventario.inventario = new Inventario(this.inventario.id);
      centroCustoInventario.centroCusto = new CentroCusto(centroCusto.codigo, centroCusto.descricao, centroCusto.gestor);
      this.inventario.centrosCusto.push(centroCustoInventario)
    }
  }

  /**
   *
   * @param centroCusto
   */
  public remove(centroCusto): void {
    if (this.inventario.centrosCusto)
      for (let i = 0; i < this.inventario.centrosCusto.length; i++)
        if (this.inventario.centrosCusto[i].centroCusto.codigo === centroCusto.codigo) {
          this.inventario.centrosCusto.splice(i, 1);
          return
        }
  }

}
