import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {MatAutocompleteSelectedEvent, MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
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
import {StatusFornecedor} from "../../../../entity/fornecedor/status-fornecedor.enum";
import {debounce} from "../../../../../application/utils/debounce";
import {AtividadeEconomica} from "../../../../entity/fornecedor/atividade-economica.model";
import {Subject} from "rxjs";
import {AtividadeEconomicaRepository} from "../../../../repository/atividade-economica.repository";
import {CategoriaRepository} from "../../../../repository/categoria.repository";
import {Categoria} from "../../../../entity/categoria.model";
import {Estado} from "../../../../entity/endereco/estado.model";
import {EnderecoRepository} from "../../../../repository/endereco.repository";
import {Cidade} from "../../../../entity/endereco/cidade.model";
import {Anexo} from "../../../../entity/publicacao/anexo.model";

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
    status: StatusFornecedor,
    defaultFilter: '',
    ativoFilter: '',
    atividadesEconomicasFilter: [] = [],
    categoriasFilter: [] = [],
    estadosFilter: [] = [],
    cidadesFilter: [] = [],
    vencidoFilter: false
  };

  public debounce = debounce;
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
   */
  atividadesEconomicas: AtividadeEconomica[] = [];

  /**
   *
   */
  categorias: Categoria[];

  /**
   *
   */
  estados: Estado[];

  /**
   *
   */
  cidades: Cidade[];

  /**
   *
   * @type {Subject<string>}
   */
  private atividadeEconomicaModelChanged: Subject<string> = new Subject<string>();

  /**
   *
   * @type {Subject<string>}
   */
  private estadoModelChanged: Subject<string> = new Subject<string>();

  /**
   *
   */
  private cidadeModelChanged: Subject<string> = new Subject<string>();

  /**
   *
   */
  @ViewChild('atividadesEconomicasInput') atividadesEconomicasInput: ElementRef<HTMLInputElement>;

  /**
   *
   */
  @ViewChild('estadosInput') estadosInput: ElementRef<HTMLInputElement>;

  /**
   *
   */
  @ViewChild('cidadesInput') cidadesInput: ElementRef<HTMLInputElement>;

  /**
   *
   * @param dialogService
   * @param paginationService
   * @param activatedRoute
   * @param messageService
   * @param fileRepository
   * @param enderecoRepository
   * @param categoriaRepository
   * @param fornecedorRepository
   * @param atividadeEconomicaRepository
   */
  constructor(private dialogService: DialogService,
              paginationService: PaginationService,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private fileRepository: FileRepository,
              private enderecoRepository: EnderecoRepository,
              private categoriaRepository: CategoriaRepository,
              private fornecedorRepository: FornecedorRepository,
              private atividadeEconomicaRepository: AtividadeEconomicaRepository) {

    this.displayedColumns.push('acoes');
    this.pageable = paginationService.pageable('created', this.pageable.sort.direction); // TODO deve ser a data do contrato

    this.pageable.defaultFilter = '';
    this.pageable.ativoFilter = '';
    this.pageable.status = StatusFornecedor[StatusFornecedor.EM_ANALISE];

    if ((this.activatedRoute.queryParams as any).value && (this.activatedRoute.queryParams as any).value.status)
      this.pageable.status = (this.activatedRoute.queryParams as any).value.status;

    if ((this.activatedRoute.queryParams as any).value && (this.activatedRoute.queryParams as any).value.vencidoFilter) {
      this.pageable.vencidoFilter = (this.activatedRoute.queryParams as any).value.vencidoFilter;
      this.pageable.status = null;
    }

    this.pageable.atividadesEconomicasFilter = [];
    this.pageable.categoriasFilter = [];
    this.pageable.estadosFilter = [];
    this.pageable.cidadesFilter = []

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

    this.atividadeEconomicaModelChanged.debounceTime(300).subscribe(model => {
      this.atividadeEconomicaRepository.listByFilters({defaultFilter: model})
        .subscribe((result) => {
          this.atividadesEconomicas = result.content
        })
    });

    this.categoriaRepository.listByFilters({size: 10000, sort: {properties: ['nome'], direction: 'ASC'}})
      .subscribe(result => {
        this.categorias = result.content
      });

    this.estadoModelChanged.debounceTime(300).subscribe(model => {
      this.enderecoRepository.listEstadosByFilters({defaultFilter: model})
        .subscribe((result) => {
          this.estados = result.content
        })
    });

    this.cidadeModelChanged.debounceTime(300).subscribe(model => {
      this.enderecoRepository.listCidadesByFilters({
        defaultFilter: model,
        estadosFilter: this.pageable.estadosFilter.map(estado => estado.id)
      })
        .subscribe((result) => {
          this.cidades = result.content
        })
    });
  }

  cidadesChanged(cidadeFiltro: any) {
    if (cidadeFiltro && cidadeFiltro.length)
      this.cidadeModelChanged.next(cidadeFiltro)
  }

  addCidadeFiltro($event: MatAutocompleteSelectedEvent) {
    this.pageable.cidadesFilter.push($event.option.value);
    if (!this.pageable.estadosFilter.filter(estado => estado.id === $event.option.value.estado.id).length)
      this.pageable.estadosFilter.push($event.option.value.estado);
    this.cidadesInput.nativeElement.value = '';
    this.listByFiltersStatement()
  }

  removeCidade(cidade: Cidade) {
    const index = this.pageable.cidadesFilter.indexOf(cidade);
    if (index >= 0) {
      this.pageable.cidadesFilter.splice(index, 1);
      this.listByFiltersStatement()
    }
  }

  estadosChanged(estadoFiltro: any) {
    if (estadoFiltro && estadoFiltro.length)
      this.estadoModelChanged.next(estadoFiltro)
  }

  addEstadoFiltro($event: MatAutocompleteSelectedEvent) {
    this.pageable.estadosFilter.push($event.option.value);
    this.estadosInput.nativeElement.value = '';
    this.listByFiltersStatement()
  }

  removeEstado(estado: Estado) {
    const index = this.pageable.estadosFilter.indexOf(estado);
    if (index >= 0) {
      this.pageable.estadosFilter.splice(index, 1);
      if (this.pageable.cidadesFilter.map(cidade => cidade.estado.id).indexOf(estado.id) < 0) {
        this.listByFiltersStatement();
        return
      }
    }
    this.pageable.cidadesFilter = this.pageable.cidadesFilter.filter(cidade => estado.id !== cidade.estado.id);
    this.listByFiltersStatement()
  }

  atividadesEconomicasChanged(atividadeEconomicaFiltro: any) {
    if (atividadeEconomicaFiltro && atividadeEconomicaFiltro.length)
      this.atividadeEconomicaModelChanged.next(atividadeEconomicaFiltro)
  }

  addAtividadeEconomicaFiltro($event: MatAutocompleteSelectedEvent) {
    this.pageable.atividadesEconomicasFilter.push($event.option.value);
    this.atividadesEconomicasInput.nativeElement.value = '';
    this.listByFiltersStatement()
  }

  removeAtividadeEconomica(atividadeEconomica: AtividadeEconomica) {
    const index = this.pageable.atividadesEconomicasFilter.indexOf(atividadeEconomica);
    if (index >= 0) {
      this.pageable.atividadesEconomicasFilter.splice(index, 1);
      this.listByFiltersStatement()
    }
  }

  /**
   *
   * @param o1
   * @param o2
   */
  compareCategorias(o1: any, o2: any): boolean {
    return o1 && o2 && o1.categoria && o2.categoria && o1.categoria.id === o2.categoria.id;
  }

  /**
   *
   */
  categoriasSelectionChanged() {
    this.listByFiltersStatement()
  }

  /**
   *
   * @param hasAnyFilter Verifica se há algum filtro,
   * caso exista, então será redirecionado para a primeira página
   */
  public listByFilters(hasAnyFilter: boolean = false) {

    if (this.pageable.vencidoFilter)
      this.pageable.status = null;

    // Define o estado atual dos filtros
    setLocalStorage(this.pageable.defaultFilter, this.activatedRoute.component['name']);

    const pageable = handlePageable(hasAnyFilter, this.paginator, this.pageable);
    pageable.defaultFilter = this.pageable.defaultFilter;
    pageable.atividadesEconomicasFilter = this.pageable.atividadesEconomicasFilter.map(atividadeEconomica => atividadeEconomica.code);
    pageable.categoriasFilter = this.pageable.categoriasFilter.map(categoria => categoria.id);
    pageable.estadosFilter = this.pageable.estadosFilter.map(estado => estado.id);
    pageable.cidadesFilter = this.pageable.cidadesFilter ? this.pageable.cidadesFilter.map(cidade => cidade.id) : null;

    this.fornecedorRepository.listByFilters(pageable).subscribe(result => {

      this.dataSource = new MatTableDataSource(result.content);
      this.totalElements = result.totalElements;
      this.pageSize = result.size;
      this.pageIndex = result.pageable.pageNumber;
    });
  }

  /**
   *
   */
  public listByFiltersStatement = () => this.listByFilters(true);

  /**
   * Restaura os filtros para o estado inicial
   */
  clearFilters = () => {
    this.pageable.defaultFilter = '';
    this.pageable.status = null;
    this.pageable.atividadesEconomicasFilter = [];
    this.pageable.categoriasFilter = [];
    this.pageable.estadosFilter = [];
    this.pageable.cidadesFilter = [];
    this.listByFilters()
  };

  /**
   *
   */
  showAdvancedFilters() {
    this.filtrosAvancados = !this.filtrosAvancados;
  }

  openEnviarEmailEmMassaDialog() {
    this.dialogService.openEnviarEmailEmMassaDialog()
  }
}
