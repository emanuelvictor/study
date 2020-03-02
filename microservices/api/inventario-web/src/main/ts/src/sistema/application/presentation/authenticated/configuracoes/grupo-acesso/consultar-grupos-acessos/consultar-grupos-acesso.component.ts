import {DialogService} from '../../../../../../domain/services/dialog.service';
import {MessageService} from '../../../../../../domain/services/message.service';
import {PaginationService} from '../../../../../../domain/services/pagination.service';
import {ListPageComponent} from '../../../../../controls/crud/list/list-page.component';
import {handlePageable} from "../../../../../utils/handle-data-table";
import {GrupoAcesso} from "../../../../../../domain/entity/grupo-acesso.model";
import {GrupoAcessoRepository} from "../../../../../../domain/repository/grupo-acesso.repository";
import {Component, ViewChild} from "@angular/core";
import {MatTableDataSource} from "@angular/material";

// @ts-ignore
@Component({
  selector: 'consultar-grupos-acesso',
  templateUrl: 'consultar-grupos-acesso.component.html',
  styleUrls: ['../grupo-acesso.component.scss']
})
export class ConsultarGruposAcessoComponent /*implements OnInit */ {

  // Bind com o component ListPageComponent
  @ViewChild(ListPageComponent, {static : true})
  private grupoAcesso: GrupoAcesso = new GrupoAcesso();

  public filters: any = {defaultFilter: ''}; // Estado inicial dos filtros

  public pageable: any = {
    size: 20,
    page: 0,
    sort: null,
    defaultFilter: []
  };

  public totalElements: any;
  public pageIndex: any;
  public pageSize: any;

  public columns: any[] = [
    {name: 'nome', label: 'Nome'}
  ];

  public displayedColumns: string[] = this.columns.map(cell => cell.name);

  public dataSource = new MatTableDataSource();

  /**
   * @param dialogService {DialogService}
   * @param paginationService {PaginationService}
   * @param messageService {MessageService}
   * @param grupoAcessoRepository {GrupoAcessoRepository}
   */
  constructor(private dialogService: DialogService,
              paginationService: PaginationService,
              private messageService: MessageService,
              private grupoAcessoRepository: GrupoAcessoRepository) {

    this.displayedColumns.push('acoes');
    this.pageable = paginationService.pageable('nome');

  }

  /**
   *
   */
  ngOnInit() {
    // Seta o size do pageable no size do paginator
    (this.grupoAcesso as any).paginator.pageSize = this.pageable.size;

    // Sobrescreve o sortChange do sort bindado
    this.sortChange();
  }

  /**
   *
   */
  public sortChange() {
    (this.grupoAcesso as any).sort.sortChange.subscribe(() => {
      const {active, direction} = (this.grupoAcesso as any).sort;
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

    const pageable = handlePageable(hasAnyFilter, (this.grupoAcesso as any).paginator, this.pageable);
    pageable.defaultFilter = (this.grupoAcesso as any).filters.defaultFilter;

    this.grupoAcessoRepository.listByFilters(pageable)
      .subscribe(result => {
        this.dataSource = new MatTableDataSource(result.content);
        this.totalElements = result.totalElements;
        this.pageSize = result.size;
        this.pageIndex = result.pageable.pageNumber;      
      });
  }

  /**
   * Função para confirmar a exclusão de um registro permanentemente
   * @param grupoAcesso
   */
  public openDeleteDialog(grupoAcesso) {

    this.dialogService.confirmDelete(grupoAcesso, 'GRUPO DE ACESSO')
      .then((accept: boolean) => {

        if (accept) {
          this.grupoAcessoRepository.delete(grupoAcesso.id)
            .then(() => {
              this.listByFilters();
              this.messageService.toastSuccess('Grupo de Acesso excluído com sucesso')
            });
        }
      });
  }
}
