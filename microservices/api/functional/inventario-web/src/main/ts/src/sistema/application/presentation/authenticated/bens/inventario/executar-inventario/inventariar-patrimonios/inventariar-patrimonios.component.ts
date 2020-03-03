import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges, ViewChild} from '@angular/core';
import {CentroCustoInventario} from "../../../../../../../domain/entity/patrimonio/inventario/centro-custo-inventario.model";
import {InventarioRepository} from "../../../../../../../domain/repository/inventario.repository";
import {AuthenticationService} from "../../../../../../../domain/services/authentication.service";
import {PatrimonioRepository} from "../../../../../../../domain/repository/patrimonio.repository";
import {PatrimonioDTO} from "../../../../../../../domain/entity/patrimonio/dto/patrimonio.dto.model";
import {MatDialog, MatPaginator, MatSnackBar} from "@angular/material";
import {Patrimonio} from "../../../../../../../domain/entity/patrimonio/patrimonio.model";
import {ActivatedRoute} from "@angular/router";
import {CentroCustoRepository} from "../../../../../../../domain/repository/centro-custo.repository";
import {AlterarLocalizacaoPatrimonioComponent} from "./alterar-localizacao-patrimonio/alterar-localizacao-patrimonio.component";
import {Usuario} from "../../../../../../../domain/entity/usuario.model";
import {TdLoadingService} from "@covalent/core";
import {CentroCustoInventarioRepository} from "../../../../../../../domain/repository/centro-custo-inventario.repository";

// @ts-ignore
@Component({
  selector: 'inventariar-patrimonios',
  templateUrl: 'inventariar-patrimonios.component.html',
  styleUrls: ['../../inventarios.scss']
})
export class InventariarPatrimoniosComponent implements OnChanges {

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
  public patrimonios: PatrimonioDTO[] = [];

  /**
   * Informar se é apenas  para visualização ou edição
   */
  @Input()
  public toView: boolean = false;

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
    encontradoFilter: boolean,
  };

  /**
   * @param dialog
   * @param snackBar
   * @param _loadingService
   * @param inventarioRepository
   * @param patrimonioRepository
   * @param authenticationService
   * @param activatedRoute
   * @param centroCustoRepository
   * @param centroCustoInventarioRepository
   */
  constructor(private dialog: MatDialog,
              private snackBar: MatSnackBar,
              public activatedRoute: ActivatedRoute,
              public _loadingService: TdLoadingService,
              private inventarioRepository: InventarioRepository,
              private patrimonioRepository: PatrimonioRepository,
              private authenticationService: AuthenticationService,
              private centroCustoRepository: CentroCustoRepository,
              private centroCustoInventarioRepository: CentroCustoInventarioRepository) {
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
      this.listByFilters(this.filter.descricaoPatrimonioFilter, this.filter.descricaoLocalizacaoFilter, this.filter.numeroPlaquetaFilter, this.filter.encontradoFilter)
  }

  /**
   *
   * @param descricaoPatrimonioFilter
   * @param descricaoLocalizacaoFilter
   * @param numeroPlaquetaFilter
   * @param encontradoFilter
   */
  public listByFilters(descricaoPatrimonioFilter?: string, descricaoLocalizacaoFilter?: string, numeroPlaquetaFilter?: string, encontradoFilter?: boolean) {

    // Funcionalidade que atende a solicitação do usuário para trazer apenas os encontrados ou não encontrados. Filtrando pelos mesmos
    if (encontradoFilter !== undefined && (encontradoFilter === true || encontradoFilter === false)) {
      this.patrimonioRepository.listAllByFilters({
        descricaoPatrimonioFilter: descricaoPatrimonioFilter,
        descricaoLocalizacaoFilter: descricaoLocalizacaoFilter,
        numeroPlaquetaFilter: numeroPlaquetaFilter,
        centroCustoCodigoFilter: this.entity.centroCusto.codigo,
        encontradoFilter: encontradoFilter,
        sobraFisicaFilter: false,
        transferidoFilter: null,
        size: this.pageSize,
        page: !this.paginator ? '0' : this.paginator.pageIndex,
      }).subscribe(result => {
        this.totalElements = result.totalElements;
        this.pageSize = result.size;
        this.pageIndex = result.pageable.pageNumber;
        this.patrimonios = result.content;

        this.patrimonios.forEach(patrimonio => {
          if (patrimonio.centroCustoInventario && !patrimonio.centroCustoInventario.id) {
            const patrimoniosAux = this.patrimonios.filter(p => {
              return p.centroCustoInventario && p.centroCustoInventario.id === (patrimonio.centroCustoInventario as any)
            });

            patrimonio.centroCustoInventario = patrimoniosAux.length ? patrimoniosAux[0].centroCustoInventario : null
          }

          if (patrimonio.centroCustoAnterior && !patrimonio.centroCustoAnterior.id) {
            const patrimoniosAux = this.patrimonios.filter(p => {
              return p.centroCustoInventario && p.centroCustoInventario.centroCusto && p.centroCustoInventario.centroCusto.id === (patrimonio.centroCustoAnterior as any)
            });

            patrimonio.centroCustoAnterior = patrimoniosAux.length ? patrimoniosAux[0].centroCustoInventario.centroCusto : null
          }
        });

        this._loadingService.resolve('overlayStarSyntax')
      });

      // Termina função
      return;
    }


    let sort;
    if (this.entity.dataTerminoExtendida) {
      sort = {
        'properties': 'encontrado',
        'direction': 'asc'
      };
    } else {
      sort = null;
    }

    if (sort) {
      this.patrimonioRepository.listAllByFilters({
        defaultFilter: descricaoPatrimonioFilter,
        descricaoLocalizacaoFilter: descricaoLocalizacaoFilter,
        numeroPlaquetaFilter: numeroPlaquetaFilter,
        centroCustoCodigoFilter: this.entity.centroCusto.codigo,
        sobraFisicaFilter: false,
        transferidoFilter: null,
        size: this.pageSize,
        page: !this.paginator ? '0' : this.paginator.pageIndex,
        sort: sort
      }).subscribe(result => {
        this.totalElements = result.totalElements;
        this.pageSize = result.size;
        this.pageIndex = result.pageable.pageNumber;
        this.patrimonios = result.content;

        this.patrimonios.forEach(patrimonio => {
          if (patrimonio.centroCustoInventario && !patrimonio.centroCustoInventario.id) {
            const patrimoniosAux = this.patrimonios.filter(p => {
              return p.centroCustoInventario && p.centroCustoInventario.id === (patrimonio.centroCustoInventario as any)
            });

            patrimonio.centroCustoInventario = patrimoniosAux.length ? patrimoniosAux[0].centroCustoInventario : null
          }

          if (patrimonio.centroCustoAnterior && !patrimonio.centroCustoAnterior.id) {
            const patrimoniosAux = this.patrimonios.filter(p => {
              return p.centroCustoInventario && p.centroCustoInventario.centroCusto && p.centroCustoInventario.centroCusto.id === (patrimonio.centroCustoAnterior as any)
            });

            patrimonio.centroCustoAnterior = patrimoniosAux.length ? patrimoniosAux[0].centroCustoInventario.centroCusto : null
          }
        });

        this._loadingService.resolve('overlayStarSyntax')
      });
    } else if (!sort) {
      this.patrimonioRepository.listPatrimoniosByCentroCustoCodigoAndFilters({
        centroCustoCodigo: this.entity.centroCusto.codigo,
        descricaoPatrimonioFilter: descricaoPatrimonioFilter,
        descricaoLocalizacaoFilter: descricaoLocalizacaoFilter,
        numeroPlaquetaFilter: numeroPlaquetaFilter,
        encontradoFilter: encontradoFilter,
        size: this.pageSize,
        page: !this.paginator ? '0' : this.paginator.pageIndex
      })
        .subscribe(result => {

          this.totalElements = result.totalElements;
          this.pageSize = result.size;
          this.pageIndex = result.pageable.pageNumber;
          this.patrimonios = result.content;

          this.patrimonios.forEach(patrimonio => {
            if (patrimonio.centroCustoInventario && !patrimonio.centroCustoInventario.id) {
              const patrimoniosAux = this.patrimonios.filter(p => {
                return p.centroCustoInventario && p.centroCustoInventario.id === (patrimonio.centroCustoInventario as any)
              });

              patrimonio.centroCustoInventario = patrimoniosAux.length ? patrimoniosAux[0].centroCustoInventario : null
            }

            if (patrimonio.centroCustoAnterior && !patrimonio.centroCustoAnterior.id) {
              const patrimoniosAux = this.patrimonios.filter(p => {
                return p.centroCustoInventario && p.centroCustoInventario.centroCusto && p.centroCustoInventario.centroCusto.id === (patrimonio.centroCustoAnterior as any)
              });

              patrimonio.centroCustoAnterior = patrimoniosAux.length ? patrimoniosAux[0].centroCustoInventario.centroCusto : null
            }
          });

          this._loadingService.resolve('overlayStarSyntax')
        })
    }
  }

  /**
   *
   * @param patrimonio
   */
  openAlterarLocalizacaoPatrimonioDialog(patrimonio: any) {
    // Não permite que um patrímonío encontrado em outro centro de custo seja alterado por alguém vinculado ao original
    // Somente quem está no centro de custo em que o patrimônio foi localizado pode alterar o patrimônio
    // Todo replicar esse regra no back-end
    if ((!patrimonio.centroCustoAnterior || patrimonio.centroCustoInventario.centroCusto.codigo === patrimonio.centroCustoAnterior.codigo)) {
      this.centroCustoRepository.findByInventarioIdAndCentroCustoCodigo(this.activatedRoute.snapshot.params.id, this.activatedRoute.snapshot.params.codigo)
        .subscribe(result => {

          patrimonio.centroCustoInventario = result;

          return this.dialog.open(AlterarLocalizacaoPatrimonioComponent, {
            width: 'auto',
            height: 'auto',
            maxHeight: '600px',
            data: {patrimonio}
          })
            .afterClosed()
            .toPromise().then(result => {
              if (result) {
                patrimonio.id = result.id;
                patrimonio = (result as any);

                for (let i = 0; i < this.patrimonios.length; i++) {
                  if (this.patrimonios[i].plaqueta === patrimonio.plaqueta)
                    this.patrimonios[i] = patrimonio
                }
              }

            })
            .catch(() => {
              this._loadingService.register('overlayStarSyntax');
              this.changedChange.emit(!this.changed)
            })
        })
    } else
      patrimonio.encontrado = !patrimonio.encontrado
  }

  /**
   *
   * @param patrimonioDTO
   */
  encontrar(patrimonioDTO: PatrimonioDTO) {
    // // Não permite que um patrímonío encontrado em outro centro de custo seja alterado por alguém vinculado ao original
    // // Somente quem está no centro de custo em que o patrimônio foi localizado pode alterar o patrimônio
    // // Todo replicar esse regra no back-end
    // if ((!patrimonioDTO.centroCustoAnterior || patrimonioDTO.centroCustoInventario.centroCusto.codigo === patrimonioDTO.centroCustoAnterior.codigo)) {
    this.centroCustoRepository.findByInventarioIdAndCentroCustoCodigo(this.activatedRoute.snapshot.params.id, this.activatedRoute.snapshot.params.codigo)
      .subscribe(result => {
        const patrimonio: Patrimonio = new Patrimonio();
        patrimonio.id = patrimonioDTO.id;

        patrimonio.codigoBase = patrimonioDTO.codigoBase;
        patrimonio.descricao = patrimonioDTO.descricao;
        patrimonio.encontrado = patrimonioDTO.encontrado;
        patrimonio.item = patrimonioDTO.item;
        patrimonio.plaqueta = patrimonioDTO.plaqueta;

        patrimonio.localizacao = patrimonioDTO.localizacao;

        patrimonio.marca = patrimonioDTO.marca;
        patrimonio.modelo = patrimonioDTO.modelo;

        patrimonio.centroCustoInventario = result;

        if (patrimonio.centroCustoInventario && patrimonio.centroCustoInventario.centroCusto && patrimonio.centroCustoInventario.centroCusto.gestor)
          patrimonio.centroCustoInventario.centroCusto.gestor = new Usuario(patrimonio.centroCustoInventario.centroCusto.gestor.id);

        // this._loadingService.register('overlayStarSyntax');
        this.patrimonioRepository.encontrar(patrimonio)
          .then(result => {
            patrimonioDTO.id = result.id;
            patrimonioDTO = (result as any);

            for (let i = 0; i < this.patrimonios.length; i++) {
              if (this.patrimonios[i].plaqueta === patrimonioDTO.plaqueta)
                this.patrimonios[i] = patrimonioDTO
            }

            this.centroCustoInventarioRepository.subject.next(true)

          })
          .catch(() => {
            this.changedChange.emit(!this.changed)
          })
      })
    // }
    // else
    //   patrimonioDTO.encontrado = !patrimonioDTO.encontrado
  }

  /**
   *
   * @param message
   */
  public openSnackBar(message: string) {
    this.snackBar.open(message, 'Fechar', {
      duration: 5000
    })
  }
}
