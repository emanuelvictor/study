import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {ActivatedRoute} from '@angular/router';
import {DialogService} from "../../../../services/dialog.service";
import {MessageService} from "../../../../services/message.service";
import {PaginationService} from "../../../../services/pagination.service";
import {AvisoEdital} from 'sistema/domain/entity/publicacao/aviso-edital.model';
import {FileRepository} from 'sistema/application/upload-file-repository/file.repository';
import {Status} from 'sistema/domain/entity/publicacao/status.enum';
import {enumToArrayString} from 'sistema/application/utils/utils';
import {handlePageable} from "../../../../../application/utils/handle-data-table";
import {setLocalStorage} from "../../../../../application/utils/handle-local-storage";
import {FornecedorRepository} from "../../../../repository/fornecedor.repository";

@Component({
  selector: 'consultar-fornecedores',
  templateUrl: './consultar-fornecedores.component.html',
  styleUrls: ['../fornecedor.component.scss']
})
export class ConsultarFornecedoresComponent implements OnInit {

  statuss: any;

  public pageable: any = {
    size: 20,
    page: 0,
    sort: {
      direction: 'DESC'
    },
    status: '',
    defaultFilter: '',
    ativoFilter: ''
  };

  public totalElements: any;
  public pageIndex: any;
  public pageSize: any;

  /**
   *
   */
  public columns: any[] = [
    {name: 'usuario.documento', label: 'Documento'},
    {name: 'razaoSocial', label: 'Razão Social'},
    {name: 'usuario.nome', label: 'Nome Fantasia'},
    {name: 'created', label: 'Data de Registro'},
    {name: 'status', label: 'Status'},
  ];

  /**
   *
   */
  public displayedColumns: string[] = this.columns.map(cell => cell.name);

  /**
   *
   * dataSource com os usuários
   * @type {MatTableDataSource<Usuario>}
   */
  dataSource = new MatTableDataSource<AvisoEdital>();

  /**
   *
   */
  filtrosAvancados: boolean = false;

  /**
   * Bind com o objeto paginator
   */
  @ViewChild(MatPaginator) paginator: MatPaginator;

  /**
   * Bind com objeto sort
   */
  @ViewChild(MatSort) sort: MatSort;

  /**
   *
   * @param dialogService
   * @param paginationService
   * @param activatedRoute
   * @param messageService
   * @param fileRepository
   * @param fornecedorRepository
   */
  constructor(private dialogService: DialogService,
              paginationService: PaginationService,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private fileRepository: FileRepository,
              private fornecedorRepository: FornecedorRepository) {

    this.displayedColumns.push('acoes');
    this.pageable = paginationService.pageable('created', this.pageable.sort.direction); // TODO deve ser a data do contrato

    this.pageable.defaultFilter = '';
    this.pageable.ativoFilter = '';

  }


  /**
   *
   */
  ngOnInit() {
    this.statuss = enumToArrayString(Status);
    // Seta o size do pageable no size do paginator
    this.paginator.pageSize = this.pageable.size;

    // Sobrescreve o sortChange do sort bindado
    this.sort.sortChange.subscribe(() => {
      const {active, direction} = this.sort;
      this.pageable.sort = {'properties': active, 'direction': direction};
      this.listByFilters();
    });

    this.listByFilters(false);

  }

  /**
   *
   * @param hasAnyFilter Verifica se há algum filtro,
   * caso exista, então será redirecionado para a primeira página
   */
  public listByFilters(hasAnyFilter: boolean = false) {
    // Define o estado atual dos filtros
    setLocalStorage(this.pageable.defaultFilter, this.activatedRoute.component['name']);

    const pageable = handlePageable(hasAnyFilter, this.paginator, this.pageable);
    pageable.ativoFilter = this.pageable.ativoFilter;
    pageable.defaultFilter = this.pageable.defaultFilter;

    this.fornecedorRepository.listByFilters(pageable).subscribe(result => {

      this.dataSource = new MatTableDataSource(result.content);
      this.totalElements = result.totalElements;
      this.pageSize = result.size;
      this.pageIndex = result.pageable.pageNumber;
    });
  }


  /**
   * Restaura os filtros para o estado inicial
   */
  clearFilters = () => {
    if (this.pageable.defaultFilter || this.pageable.ativoFilter !== '') {
      this.pageable.defaultFilter = '';
      this.pageable.ativoFilter = '';
      this.listByFilters();
    }
  };

  /**
   *
   */
  showAdvancedFilters() {
    this.filtrosAvancados = !this.filtrosAvancados;
    if (this.filtrosAvancados == false) {

    }
  }
}
