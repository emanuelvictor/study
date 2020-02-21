import {Component, Input} from '@angular/core';
import {CentroCustoInventario} from "../../../../../../../domain/entity/patrimonio/inventario/centro-custo-inventario.model";
import {CentroCustoRepository} from "../../../../../../../domain/repository/centro-custo.repository";
import {CentroCustoInventarioRepository} from "../../../../../../../domain/repository/centro-custo-inventario.repository";

// @ts-ignore
@Component({
  selector: 'resumo',
  templateUrl: 'resumo.component.html',
  styleUrls: ['../../inventarios.scss']
})
export class ResumoComponent {

  /**
   *
   */
  @Input()
  public entity: CentroCustoInventario = new CentroCustoInventario();

  /**
   *
   */
  single: any[] = [];

  /**
   *
   */
  single2: any[] = [];

  /**
   *
   * @param centroCustoRepository
   * @param centroCustoInventarioRepository
   */
  constructor(private centroCustoRepository: CentroCustoRepository,
              private centroCustoInventarioRepository: CentroCustoInventarioRepository) {
  }

  /**
   *
   */
  ngOnInit() {
    this.centroCustoInventarioRepository.subject.subscribe( () => {
      this.listByFilters()
    });
    this.listByFilters()
  }

  /**
   *
   */
  public listByFilters() {
    this.centroCustoRepository.naoInventariados(this.entity.centroCusto.codigo).subscribe(naoInventariados => {
      this.centroCustoRepository.inventariados(this.entity.centroCusto.codigo).subscribe(inventariados => {
        this.single = [
          {
            'name': 'Inventariados',
            'value': inventariados
          },
          {
            'name': 'Não inventariados',
            'value': naoInventariados
          }
        ];

        this.centroCustoRepository.naoEncontrados(this.entity.centroCusto.codigo).subscribe(naoEncontrados => {
          this.centroCustoRepository.encontrados(this.entity.centroCusto.codigo).subscribe(encontrados => {

            this.single2 = [
              {
                'name': 'Encontrados',
                'value': encontrados
              },
              {
                'name': 'Não encontrados',
                'value': naoEncontrados
              },
              {
                'name': 'Não inventariados',
                'value': naoInventariados
              }
            ];

          })
        })

      })
    })
  }

}
