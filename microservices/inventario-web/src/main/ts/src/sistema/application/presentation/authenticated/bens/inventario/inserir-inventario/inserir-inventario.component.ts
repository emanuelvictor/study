import 'rxjs/add/operator/debounceTime';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticatedViewComponent} from '../../../authenticated-view.component';
import {MessageService} from '../../../../../../domain/services/message.service';
import {Inventario} from "../../../../../../domain/entity/patrimonio/inventario/inventario.model";
import {InventarioRepository} from "../../../../../../domain/repository/inventario.repository";
import {CentroCusto} from "../../../../../../domain/entity/pessoal.dto/centro-custo.model";
import {CentroCustoInventario} from "../../../../../../domain/entity/patrimonio/inventario/centro-custo-inventario.model";
import {Component} from '@angular/core';
import {CentroCustoRepository} from "../../../../../../domain/repository/centro-custo.repository";

// @ts-ignore
@Component({
  selector: 'inserir-inventario',
  templateUrl: './inserir-inventario.component.html',
  styleUrls: ['../inventarios.scss']
})
export class InserirInventarioComponent {

  /**
   *
   */
  inventario: Inventario = new Inventario();

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
   * @param router
   * @param homeView
   * @param activatedRoute
   * @param messageService
   * @param inventarioRepository
   * @param centroCustoRepository
   */
  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: AuthenticatedViewComponent,
              private inventarioRepository: InventarioRepository,
              private centroCustoRepository: CentroCustoRepository) {
  }

  /**
   *
   * @param inventario
   */
  public save(inventario: Inventario) {
    this.inventarioRepository.save(inventario).then(() => {
      this.router.navigate(['bens/inventarios']);
      this.messageService.toastSuccess(`Inserido com sucesso`, 5)
    })
  }

  /**
   *
   * @param centroCusto
   */
  public push(centroCusto: CentroCusto): void {
    if (!this.inventario.centrosCusto)
      this.inventario.centrosCusto = [];
    const centroCustoIventario: CentroCustoInventario = new CentroCustoInventario();
    centroCustoIventario.inventario = new Inventario(this.inventario.id);
    centroCustoIventario.centroCusto = new CentroCusto(centroCusto.codigo, centroCusto.descricao, centroCusto.gestor);
    this.inventario.centrosCusto.push(centroCustoIventario)
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
