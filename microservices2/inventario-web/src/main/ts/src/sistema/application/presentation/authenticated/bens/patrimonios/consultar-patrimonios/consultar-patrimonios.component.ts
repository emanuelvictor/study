import {DialogService} from '../../../../../../domain/services/dialog.service';
import {MessageService} from '../../../../../../domain/services/message.service';
import {PaginationService} from '../../../../../../domain/services/pagination.service';
import {handlePageable} from '../../../../../utils/handle-data-table';
import {UsuarioRepository} from "../../../../../../domain/repository/usuario.repository";
import {Component, OnInit, ViewChild} from "@angular/core";
import {
  MAT_FORM_FIELD_DEFAULT_OPTIONS,
  MatDialog,
  MatFormFieldDefaultOptions,
  MatPaginator,
  MatSort,
  MatTableDataSource
} from "@angular/material";
import {PatrimonioRepository} from "../../../../../../domain/repository/patrimonio.repository";
import {debounce} from '../../../../../utils/debounce';
import {viewAnimation} from "../../../../../utils/utils";
import {PatrimonioDTO} from "../../../../../../domain/entity/patrimonio/dto/patrimonio.dto.model";
import {VisualizarPatrimonioComponent} from "../visualizar-patrimonio/visualizar-patrimonio.component";
import {ExportarPatrimoniosComponent} from "./exportar-patrimonios/exportar-patrimonios.component";

const appearance: MatFormFieldDefaultOptions = {
  appearance: 'outline'
};

// @ts-ignore
@Component({
  selector: 'consultar-patrimonios',
  templateUrl: './consultar-patrimonios.component.html',
  styleUrls: ['../patrimonios.scss'],
  animations: [
    viewAnimation
  ],
  providers: [
    {
      provide: MAT_FORM_FIELD_DEFAULT_OPTIONS,
      useValue: appearance
    }
  ]
})
export class ConsultarPatrimoniosComponent implements OnInit {

  public debounce = debounce;
  public listByFiltersStatement = () => this.listByFilters(true);

  // private patrimonio: Patrimonio = new Patrimonio();

  @ViewChild(MatPaginator, {static: true}) public paginator: MatPaginator;

  /**
   * Bind com objeto sort
   */
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  // Estado inicial dos filtros
  public filters: {
    defaultFilter: string,
    centroCustoCodigoFilter: string,
    sobraFisicaFilter: boolean,
    naoEncontradoFilter: boolean,
    encontradoFilter: boolean,
    transferidoFilter: boolean
  } = {
    defaultFilter: '',
    centroCustoCodigoFilter: '',
    sobraFisicaFilter: null,
    naoEncontradoFilter: null,
    encontradoFilter: null,
    transferidoFilter: null
  };

  public pageable: any = {
    size: 100,
    page: 0,
    sort: null,
    defaultFilter: [],
    ativoFilter: true
  };

  totalElements: any;
  pageSize: any = 100;
  pageIndex: any = 0;

  public columns: any[] = [
    {name: 'descricao', label: 'Descrição'},
    {name: 'observacao', label: 'Observação'},
    {name: 'centroCustoInventario.centroCusto.descricao', label: 'Centro de Custo'},
    {name: 'centroCustoAnterior.descricao', label: 'Centro de Custo Anterior'},
    {name: 'localizacao.descricao', label: 'Localização'},
    {name: 'localizacaoAnterior.descricao', label: 'Localização Anterior'},
  ];

  public displayedColumns: string[] = this.columns.map(cell => cell.name);

  public dataSource = new MatTableDataSource();

  /**
   * @param dialog
   * @param dialogService {DialogService}
   * @param paginationService {PaginationService}
   * @param messageService {MessageService}
   * @param patrimonioRepository {UsuarioRepository}
   */
  constructor(private dialog: MatDialog,
              private dialogService: DialogService,
              paginationService: PaginationService,
              private messageService: MessageService,
              private patrimonioRepository: PatrimonioRepository) {

    this.pageable = paginationService.pageable('descricao')

  }

  /**
   *
   */
  ngOnInit() {
    this.paginator.pageSize = 100;

    this.listByFilters();

    // Sobrescreve o sortChange do sort bindado
    this.sortChange()
  }

  /**
   *
   * @param patrimonio
   */
  openVisualizarPatrimonioDialog(patrimonio: PatrimonioDTO) {
    return this.dialog.open(VisualizarPatrimonioComponent, {
      width: 'auto',
      height: 'auto',
      maxHeight: '600px',
      data: {patrimonio}
    })
      .afterClosed()
      .toPromise().then(() => {
      })
  }

  /**
   *
   */
  public sortChange() {
    this.sort.sortChange.subscribe(() => {
      const {active, direction} = this.sort;
      this.pageable.sort = {'properties': active, 'direction': direction};
      this.listByFilters()
    })
  }

  /**
   *
   * @param hasAnyFilter Verifica se há algum filtro,
   * caso exista, então será redirecionado para a primeira página
   */
  public listByFilters(hasAnyFilter: boolean = false) {

    const pageable = handlePageable(hasAnyFilter, this.paginator, this.pageable);
    pageable.defaultFilter = this.filters.defaultFilter;
    pageable.centroCustoCodigoFilter = this.filters.centroCustoCodigoFilter;
    pageable.encontradoFilter = this.filters.encontradoFilter;
    pageable.sobraFisicaFilter = this.filters.sobraFisicaFilter;
    pageable.transferidoFilter = this.filters.transferidoFilter;

    pageable.size = !this.paginator ? 100 : this.paginator.pageSize;
    pageable.page = !this.paginator ? 0 : this.paginator.pageIndex;

    this.patrimonioRepository.listAllByFilters(pageable).subscribe(result => {
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

          if (!patrimoniosAux.length) {
            patrimoniosAux = result.content.filter(p => p.centroCustoAnterior && p.centroCustoAnterior.id === (patrimonio.centroCustoInventario.centroCusto as any));
            patrimonio.centroCustoInventario.centroCusto = patrimoniosAux.length ? patrimoniosAux[0].centroCustoAnterior : patrimonio.centroCustoInventario.centroCusto
          } else {
            patrimoniosAux = result.content.filter(p => p.centroCustoInventario.centroCusto && p.centroCustoInventario.centroCusto.id === (patrimonio.centroCustoInventario.centroCusto as any));
            patrimonio.centroCustoInventario.centroCusto = patrimoniosAux.length ? patrimoniosAux[0].centroCustoInventario.centroCusto : patrimonio.centroCustoInventario.centroCusto
          }
        }
      });

      this.dataSource = new MatTableDataSource(result.content);
      this.totalElements = result.totalElements;
      this.pageSize = result.size;
      this.pageIndex = result.pageable.pageNumber
    })
  }

  /**
   *
   */
  exportar(hasAnyFilter: boolean = false) {

    return this.dialog.open(ExportarPatrimoniosComponent, {
      width: 'auto',
      height: 'auto',
      maxHeight: '600px',
      data: {'pageable': {size : !this.paginator ? 100 : this.paginator.pageSize}}
    })
      .afterClosed()
      .toPromise().then(result => {
        if (result) {
          const pageable = handlePageable(hasAnyFilter, this.paginator, this.pageable);
          pageable.defaultFilter = this.filters.defaultFilter;
          pageable.centroCustoCodigoFilter = this.filters.centroCustoCodigoFilter;
          pageable.encontradoFilter = this.filters.encontradoFilter;
          pageable.sobraFisicaFilter = this.filters.sobraFisicaFilter;
          pageable.transferidoFilter = this.filters.transferidoFilter;

          console.log(result);

          pageable.size = result;
          pageable.page = !this.paginator ? 0 : this.paginator.pageIndex;

          this.patrimonioRepository.exportar(pageable)
            .subscribe(result => {

              const anexo: any = {};

              const blob = new Blob([result], {type: 'csv'});
              anexo.conteudo = new File([blob], 'export', {type: result.type});
              const reader = new FileReader();
              reader.readAsDataURL(blob);
              // reader.onload = () => this.anexo.caminho = reader.result.toString()

              window.open(window.URL.createObjectURL(anexo.conteudo), '_blank')
            })
        }
      })


  }
}

