import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {Component, ViewChild} from '@angular/core';
import {DialogService} from '../../../../../services/dialog.service';
import {MessageService} from '../../../../../services/message.service';
import {PaginationService} from '../../../../../services/pagination.service';
import {handlePageable} from "../../../../../../application/utils/handle-data-table";
import {AvisoEdital} from "../../../../../entity/publicacao/aviso-edital.model";
import {AvisoEditalRepository} from "../../../../../repository/aviso-edital.repository";
import {debounce} from "../../../../../../application/utils/debounce";
import {setLocalStorage} from '../../../../../../application/utils/handle-local-storage';
import {ActivatedRoute} from "@angular/router";
import {enumToArrayString} from 'sistema/application/utils/utils';
import {Status} from 'sistema/domain/entity/publicacao/status.enum';
import {Anexo} from "../../../../../entity/publicacao/anexo.model";
import {FileRepository} from "../../../../../../application/upload-file-repository/file.repository";

@Component({
  selector: 'consultar-avisos-editais',
  templateUrl: 'consultar-avisos-editais.component.html',
  styleUrls: ['../avisos-editais-view.component.scss']
})
export class ConsultarAvisosEditaisComponent {

  public debounce = debounce;

  statuss: any;

  public pageable: any = {
    size: 20,
    page: 0,
    sort: {
      direction: 'DESC'
    },
    defaultFilter: '',
    ativoFilter: ''
  };

  public totalElements: any;
  public pageIndex: any;
  public pageSize: any;

  public columns: any[] = [
    {name: 'numeroProcesso', label: 'N° do Processo'},
    {name: 'objeto', label: 'Objeto'},
    {name: 'categoria', label: 'Categorias'},
    {name: 'dataPublicacao', label: 'Data da Publicação'},
    {name: 'prazoPropostas', label: 'Prazo para Propostas'},
    {name: 'status', label: 'Status'},
  ];

  public displayedColumns: string[] = this.columns.map(cell => cell.name);

  /**
   *
   * dataSource com os usuários
   * @type {MatTableDataSource<Usuario>}
   */
  dataSource = new MatTableDataSource<AvisoEdital>();

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
   * @param avisoEditalRepository
   */
  constructor(private dialogService: DialogService,
              paginationService: PaginationService,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private fileRepository: FileRepository,
              private avisoEditalRepository: AvisoEditalRepository) {

    this.displayedColumns.push('acoes');
    this.pageable = paginationService.pageable('dataPublicacao', this.pageable.sort.direction);

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

    this.avisoEditalRepository.listByFilters(pageable).subscribe(result => {
      this.dataSource = new MatTableDataSource(result.content);
      this.totalElements = result.totalElements;
      this.pageSize = result.size;
      this.pageIndex = result.pageable.pageNumber;
    });
  }


  /**
   *
   * @param avisoEdital
   */
  public openAnexoDialog(avisoEdital: AvisoEdital) {

    this.dialogService.sendAnexo(avisoEdital, 'Aviso de Edital')
      .then((anexo: Anexo) => {

        if (anexo) {
          this.fileRepository.save(this.avisoEditalRepository.collectionName + '/' + avisoEdital.id + '/anexos/' + anexo.nome, anexo.conteudo)
            .then(() => {
              this.messageService.toastSuccess('Arquivo anexado com suscesso.')
            });
        }
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
}
