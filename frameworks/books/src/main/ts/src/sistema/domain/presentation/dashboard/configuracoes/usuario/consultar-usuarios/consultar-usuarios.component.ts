import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {DashboardViewComponent} from '../../../dashboard-view.component';
import {DialogService} from '../../../../../services/dialog.service';
import {MessageService} from '../../../../../services/message.service';
import {PaginationService} from '../../../../../services/pagination.service';
import {handlePageable} from '../../../../../../application/utils/handle-data-table';
import {debounce} from '../../../../../../application/utils/debounce';
import {getLocalStorage, setLocalStorage} from '../../../../../../application/utils/handle-local-storage';
import {ActivatedRoute} from '@angular/router';
import {UsuarioRepository} from "../../../../../repository/usuario.repository";

@Component({
  selector: 'consultar-usuarios',
  templateUrl: './consultar-usuarios.component.html',
  styleUrls: ['../usuario.component.scss']
})
export class ConsultarUsuariosComponent implements OnInit {

  // Bind com o objeto paginator
  @ViewChild(MatPaginator) paginator: MatPaginator;

  // Bind com objeto sort
  @ViewChild(MatSort) sort: MatSort;

  private pageable: any;
  public totalElements: any;
  public pageIndex: any;
  public pageSize: any;

  public debounce = debounce;
  public listByFiltersStatement = () => this.listByFilters(true);

  // Estado inicial dos filtros
  public filters: any = {defaultFilter: '', ativoFilter: ''};

  public columns: any[] = [
    {name: 'nome', label: 'Nome'},
    {name: 'email', label: 'E-mail'}
  ];

  public displayedColumns: string[] = this.columns.map(cell => cell.name);

  public dataSource = new MatTableDataSource();

  /**
   *
   * @param homeView
   * @param dialogService
   * @param messageService
   * @param activatedRoute
   * @param usuarioRepository
   * @param paginationService
   */
  constructor(public homeView: DashboardViewComponent,
              private dialogService: DialogService,
              private messageService: MessageService,
              private activatedRoute: ActivatedRoute,
              private usuarioRepository: UsuarioRepository,
              private paginationService: PaginationService) {

    homeView.toolbar.headline = 'Configurações';
    homeView.toolbar.subhead = 'Usuário';

    this.displayedColumns.push('username', 'ativo', 'interno', 'acoes');
    this.pageable = paginationService.pageable('nome');

  }

  /**
   *
   */
  ngOnInit() {
    // Seta o size do pageable no size do paginator
    this.paginator.pageSize = this.pageable.size;

    // Verifica e mantém o estado dos filtros
    this.filters = getLocalStorage(this.filters, this.activatedRoute.component['name']);

    // Listagem inicial
    this.listByFilters();

    // Sobrescreve o sortChange do sort bindado
    this.sortChange();
  }

  /**
   *
   */
  private sortChange() {
    this.sort.sortChange.subscribe(() => {
      const {active, direction} = this.sort;
      this.pageable.sort = {'properties': active, 'direction': direction};
      this.listByFilters();
    });
  }

  /**
   *
   * @param hasAnyFilter Verifica se há algum filtro,
   * caso exista, então será redirecionado para a primeira página
   */
  public listByFilters(hasAnyFilter: boolean = false) {

    // Define o estado atual dos filtros
    setLocalStorage(this.filters, this.activatedRoute.component['name']);

    const pageable = handlePageable(hasAnyFilter, this.paginator, this.pageable);

    pageable.ativoFilter = this.filters.ativoFilter;
    pageable.defaultFilter = this.filters.defaultFilter;

    this.usuarioRepository.listByFilters(pageable)
      .subscribe(result => {
        this.dataSource = new MatTableDataSource(result.content);
        this.totalElements = result.totalElements;
        this.pageSize = result.size;
        this.pageIndex = result.pageable.pageNumber;
      });
  }

  /**
   * Restaura os filtros para o estado inicial
   */
  public clearFilters() {
    const {defaultFilter, ativoFilter} = this.filters;

    if (defaultFilter || ativoFilter !== '') {
      this.filters = {defaultFilter: '', ativoFilter: ''};
      this.listByFilters();
    }
  };
}
