import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {viewAnimation} from "../../../../../utils/utils";
import {CentroCustoInventario} from "../../../../../../domain/entity/patrimonio/inventario/centro-custo-inventario.model";
import {CentroCustoRepository} from "../../../../../../domain/repository/centro-custo.repository";
import {AuthenticationService} from "../../../../../../domain/services/authentication.service";
import {Subject} from "rxjs";
import {MatDialog} from "@angular/material";
import {AprovarExecucaoInventarioComponent} from "./aprovar-execucao-inventario/aprovar-execucao-inventario.component";
import {ExtenderDataTerminoComponent} from "./extender-data-termino/extender-data-termino.component";
import {CentroCustoInventarioRepository} from "../../../../../../domain/repository/centro-custo-inventario.repository";

// @ts-ignore
@Component({
  selector: 'executar-inventario',
  templateUrl: './executar-inventario.component.html',
  styleUrls: ['../inventarios.scss'],
  animations: [
    viewAnimation
  ]
})
export class ExecutarInventarioComponent {

  /**
   *
   */
  public filter = {
    descricaoPatrimonioFilter: null,
    descricaoLocalizacaoFilter: null,
    numeroPlaquetaFilter: null,
    encontradoFilter: null,
  };

  /**
   *
   * @type {Subject<string>}
   */
  public modelChange: Subject<any> = new Subject<any>();

  /**
   *
   */
  public changed: boolean = false;

  /**
   *
   */
  centroCustoInventario: CentroCustoInventario = new CentroCustoInventario();

  /**
   *
   * @param router
   * @param dialog
   * @param activatedRoute
   * @param authenticationService
   * @param centroCustoRepository
   * @param centroCustoInventarioRepository
   */
  constructor(public activatedRoute: ActivatedRoute,
              private router: Router, private dialog: MatDialog,
              public authenticationService: AuthenticationService,
              private centroCustoRepository: CentroCustoRepository,
              private centroCustoInventarioRepository: CentroCustoInventarioRepository) {

    // @ts-ignore
    this.modelChange.debounceTime(300).subscribe(() => this.changed = !this.changed);

    this.findByInventarioIdAndCentroCustoCodigo()
  }

  /**
   *
   */
  public findByInventarioIdAndCentroCustoCodigo() {
    this.centroCustoRepository.findByInventarioIdAndCentroCustoCodigo(this.activatedRoute.snapshot.params.id, this.activatedRoute.snapshot.params.codigo)
      .subscribe(result => this.centroCustoInventario = result)
  }

  /**
   *
   * @param id
   * @param codigo
   */
  toAnaliseCentroCustoInventario(id, codigo: string) {
    this.centroCustoRepository.toAnaliseCentroCustoInventario(id, codigo)
      .subscribe(() => this.router.navigate(['/bens/inventarios/' + id]))
  }

  /**
   *
   * @param id
   * @param codigo
   */
  executarCentroCustoInventario(id, codigo: string) {
    this.centroCustoRepository.executarCentroCustoInventario(id, codigo)
      .subscribe(() => this.findByInventarioIdAndCentroCustoCodigo())
  }

  /**
   *
   * @param id
   * @param codigo
   */
  aprovarCentroCustoInventario(id: number, codigo: string) {
    this.centroCustoRepository.hasInventariaveis(codigo).subscribe(result => {
      if (result) {
        return this.dialog.open(AprovarExecucaoInventarioComponent, {
          width: 'auto',
          height: 'auto',
          maxHeight: '600px'
        })
          .afterClosed()
          .toPromise().then(resulted => {
            if (resulted)
              this.centroCustoRepository.aprovarCentroCustoInventario(id, codigo)
                .subscribe(() => this.findByInventarioIdAndCentroCustoCodigo())
          })
      } else
        this.centroCustoRepository.aprovarCentroCustoInventario(id, codigo)
          .subscribe(() => this.findByInventarioIdAndCentroCustoCodigo())
    })
  }

  /**
   *
   * @param centroCustoInventario
   */
  openExtenderDataTermino(centroCustoInventario: CentroCustoInventario) {
    return this.dialog.open(ExtenderDataTerminoComponent, {
      width: '500px',
      height: 'auto',
      data: {data: centroCustoInventario}
    })
      .afterClosed()
      .toPromise().then(resulted => {
        if (resulted && resulted.id)
          this.centroCustoInventarioRepository.extenderDataTermino(resulted.id, resulted)
            .then(result => {
              this.centroCustoInventario.status = result.status;
              this.centroCustoInventario.dataTerminoExtendida = result.dataTerminoExtendida
            })
      })
  }
}
