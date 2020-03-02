import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {MatDialog, MatPaginator} from "@angular/material";
import {CentroCustoRepository} from "../../../../../../../../domain/repository/centro-custo.repository";
import {CentroCustoInventario} from "../../../../../../../../domain/entity/patrimonio/inventario/centro-custo-inventario.model";
import {viewAnimation} from "../../../../../../../utils/utils";
import {PatrimonioDTO} from "../../../../../../../../domain/entity/patrimonio/dto/patrimonio.dto.model";
import {InventarioRepository} from "../../../../../../../../domain/repository/inventario.repository";
import {PatrimonioRepository} from "../../../../../../../../domain/repository/patrimonio.repository";
import {AuthenticationService} from "../../../../../../../../domain/services/authentication.service";
import {AlterarLocalizacaoPatrimonioComponent} from "../alterar-localizacao-patrimonio/alterar-localizacao-patrimonio.component";
import {InserirPatrimonioComponent} from "../inserir-patrimonio/inserir-patrimonio.component";

// @ts-ignore
@Component({
  selector: 'novos-patrimonios',
  templateUrl: './novos-patrimonios.component.html',
  styleUrls: ['../../../inventarios.scss'],
  animations: [
    viewAnimation
  ]
})
export class NovosPatrimoniosComponent implements OnChanges{

  /**
   *
   */
  @Output()
  public changedChange: EventEmitter<boolean> = new EventEmitter<any>();

  /**
   *
   */
  @Input()
  public changed: boolean;

  totalElements: any;
  pageSize: any = 20;
  pageIndex: any = 0;

  @ViewChild(MatPaginator, {static: false}) public paginator: MatPaginator;

  /**
   *
   */
  public patrimonios: any[] = [];

  /**
   *
   */
  @Input()
  public entity: CentroCustoInventario = new CentroCustoInventario();

  /**
   *
   */
  @Input()
  public filter: {
    descricaoPatrimonioFilter: string,
    descricaoLocalizacaoFilter: string,
    numeroPlaquetaFilter: string,
  };

  /**
   * @param dialog
   * @param inventarioRepository
   * @param patrimonioRepository
   * @param authenticationService
   * @param activatedRoute
   * @param centroCustoRepository
   */
  constructor(private dialog: MatDialog,
              public activatedRoute: ActivatedRoute,
              private inventarioRepository: InventarioRepository,
              private patrimonioRepository: PatrimonioRepository,
              private authenticationService: AuthenticationService,
              private centroCustoRepository: CentroCustoRepository) {
  }

  /**
   *
   */
  ngOnInit() {
    this.listByFilters()
  }

  /**
   *
   * @param changes
   */
  ngOnChanges(changes: SimpleChanges): void {
    if (changes.filter && !changes.filter.firstChange) {
      if (changes.filter.currentValue && changes.filter.currentValue.length >= 3)
        this.listByFilters(this.filter.descricaoPatrimonioFilter, this.filter.descricaoLocalizacaoFilter, this.filter.numeroPlaquetaFilter)
    }

    if (changes.changed && !changes.changed.firstChange)
      this.listByFilters(this.filter.descricaoPatrimonioFilter, this.filter.descricaoLocalizacaoFilter, this.filter.numeroPlaquetaFilter)
  }

  /**
   *
   * @param descricaoPatrimonioFilter
   * @param descricaoLocalizacaoFilter
   * @param numeroPlaquetaFilter
   */
  public listByFilters(descricaoPatrimonioFilter?: string, descricaoLocalizacaoFilter?: string, numeroPlaquetaFilter?: string) {
    this.patrimonioRepository.listNovosPatrimoniosByCentroCustoCodigoAndFilters({
      centroCustoCodigo: this.entity.centroCusto.codigo,
      descricaoPatrimonioFilter : descricaoPatrimonioFilter,
      descricaoLocalizacaoFilter : descricaoLocalizacaoFilter,
      numeroPlaquetaFilter : numeroPlaquetaFilter,
      size: this.pageSize,
      page: !this.paginator ? 0 : this.paginator.pageIndex
    })
      .subscribe(result => {

        result.content.forEach(patrimonio => {
          if (patrimonio.centroCustoInventario && !patrimonio.centroCustoInventario.id) {
            const patrimoniosAux = result.content.filter(p => {
              return p.centroCustoInventario && p.centroCustoInventario.id === (patrimonio.centroCustoInventario as any)
            });

            patrimonio.centroCustoInventario = patrimoniosAux.length ? patrimoniosAux[0].centroCustoInventario : null
          }

          if (patrimonio.centroCustoAnterior && !patrimonio.centroCustoAnterior.id) {
            const patrimoniosAux = result.content.filter(p => {
              return p.centroCustoAnterior && p.centroCustoAnterior.id === (patrimonio.centroCustoAnterior as any)
            });

            patrimonio.centroCustoAnterior = patrimoniosAux.length ? patrimoniosAux[0].centroCustoAnterior : patrimonio.centroCustoAnterior
          }
        });

        result.content.forEach(patrimonio => {
          if (patrimonio.centroCustoAnterior && !patrimonio.centroCustoAnterior.id) {
            const patrimoniosAux = result.content.filter(p => {
              return p.centroCustoInventario.centroCusto && p.centroCustoInventario.centroCusto.id === (patrimonio.centroCustoAnterior as any)
            });

            patrimonio.centroCustoAnterior = patrimoniosAux.length ? patrimoniosAux[0].centroCustoInventario.centroCusto : patrimonio.centroCustoAnterior
          }
        });

        result.content.forEach(patrimonio => {
          if (patrimonio.centroCustoInventario.centroCusto && !patrimonio.centroCustoInventario.centroCusto.id) {
            let patrimoniosAux = result.content.filter(p => p.centroCustoInventario.centroCusto && p.centroCustoInventario.centroCusto.id === (patrimonio.centroCustoInventario.centroCusto as any));

            if (!patrimoniosAux.length){
              patrimoniosAux = result.content.filter(p => p.centroCustoAnterior && p.centroCustoAnterior.id === (patrimonio.centroCustoInventario.centroCusto as any));
              patrimonio.centroCustoInventario.centroCusto = patrimoniosAux.length ? patrimoniosAux[0].centroCustoAnterior : patrimonio.centroCustoInventario.centroCusto
            } else {
              patrimoniosAux = result.content.filter(p => p.centroCustoInventario.centroCusto && p.centroCustoInventario.centroCusto.id === (patrimonio.centroCustoInventario.centroCusto as any));
              patrimonio.centroCustoInventario.centroCusto = patrimoniosAux.length ? patrimoniosAux[0].centroCustoInventario.centroCusto : patrimonio.centroCustoInventario.centroCusto
            }
          }
        });

        this.patrimonios = result.content;
        this.totalElements = result.totalElements;
        this.pageSize = result.size;
        this.pageIndex = result.pageable.pageNumber
      })
  }

  /**
   *
   * @param patrimonio
   */
  openAlterarLocalizacaoPatrimonioDialog(patrimonio: PatrimonioDTO) {
    return this.dialog.open(AlterarLocalizacaoPatrimonioComponent, {
      width: 'auto',
      height: 'auto',
      maxHeight: '600px',
      data: {patrimonio}
    })
      .afterClosed()
      .toPromise().then(result => {
        if (result)
          this.changedChange.emit(!this.changed)
      })
  }

  /**
   *
   * @param centroCustoInventario
   */
  openInserirNovoPatrimonioDialog(centroCustoInventario: CentroCustoInventario) {
    return this.dialog.open(InserirPatrimonioComponent, {
      width: 'auto',
      height: 'auto',
      maxHeight: '600px',
      data: {centroCustoInventario}
    })
      .afterClosed()
      .toPromise().then(result => {
        if (result)
          this.changedChange.emit(!this.changed)
      })
  }
}
