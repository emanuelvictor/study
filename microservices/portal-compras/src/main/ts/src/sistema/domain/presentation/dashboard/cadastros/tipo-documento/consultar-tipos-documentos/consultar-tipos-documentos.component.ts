import {Component, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {DialogService} from '../../../../../services/dialog.service';
import {MessageService} from '../../../../../services/message.service';
import {PaginationService} from '../../../../../services/pagination.service';
import {ListPageComponent} from '../../../../../../application/controls/crud/list/list-page.component';
import {handlePageable} from "../../../../../../application/utils/handle-data-table";
import {TipoDocumento} from "../../../../../entity/tipo-documento.model";
import {TipoDocumentoRepository} from "../../../../../repository/tipo-documento.repository";

@Component({
  selector: 'consultar-tipos-documentos',
  templateUrl: 'consultar-tipos-documentos.component.html',
  styleUrls: ['../tipo-documento.component.scss']
})
export class ConsultarTiposDocumentosComponent /*implements OnInit */ {

  // Bind com o component ListPageComponent
  @ViewChild(ListPageComponent)
  private tipoDocumento: TipoDocumento = new TipoDocumento();

  public pageable: any = {
    size: 20,
    page: 0,
    sort: null,
    defaultFilter: [],
    ativoFilter: null
  };

  public totalElements: any;
  public pageIndex: any;
  public pageSize: any;

  public columns: any[] = [
    {name: 'nome', label: 'Nome'},
    {name: 'ativo', label: 'Ativo'},
  ];

  public displayedColumns: string[] = this.columns.map(cell => cell.name);

  public dataSource = new MatTableDataSource();

  /**
   * @param dialogService {DialogService}
   * @param paginationService {PaginationService}
   * @param messageService {MessageService}
   * @param tipoDocumentoRepository {TipoDocumentoRepository}
   */
  constructor(private dialogService: DialogService,
              paginationService: PaginationService,
              private messageService: MessageService,
              private tipoDocumentoRepository: TipoDocumentoRepository) {

    this.displayedColumns.push('acoes');
    this.pageable = paginationService.pageable('nome');

  }

  /**
   *
   */
  ngOnInit() {
    // Seta o size do pageable no size do paginator
    (this.tipoDocumento as any).paginator.pageSize = this.pageable.size;

    // Sobrescreve o sortChange do sort bindado
    this.sortChange();
  }

  /**
   *
   */
  public sortChange() {
    (this.tipoDocumento as any).sort.sortChange.subscribe(() => {
      const {active, direction} = (this.tipoDocumento as any).sort;
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

    const pageable = handlePageable(hasAnyFilter, (this.tipoDocumento as any).paginator, this.pageable);
    pageable.ativoFilter = (this.tipoDocumento as any).filters.ativoFilter;
    pageable.defaultFilter = (this.tipoDocumento as any).filters.defaultFilter;

    this.tipoDocumentoRepository.listByFilters(pageable)
      .subscribe(result => {
        result.content.forEach(tipoDocumento => tipoDocumento.ativo ? tipoDocumento.ativo = 'Ativo' : tipoDocumento.ativo = 'Inativo');
        this.dataSource = new MatTableDataSource(result.content);
        this.totalElements = result.totalElements;
        this.pageSize = result.size;
        this.pageIndex = result.pageable.pageNumber;
      });
  }

  /**
   * Função para confirmar a exclusão de um registro permanentemente
   * @param tipoDocumento
   */
  public openDeleteDialog(tipoDocumento) {

    this.dialogService.confirmDelete(tipoDocumento, 'tipoDocumento')
      .then((accept: boolean) => {

        if (accept) {
          this.tipoDocumentoRepository.delete(tipoDocumento.id)
            .then(() => {
              this.listByFilters();
              this.messageService.toastSuccess('Registro excluído com sucesso.')
            });
        }
      });
  }
}
