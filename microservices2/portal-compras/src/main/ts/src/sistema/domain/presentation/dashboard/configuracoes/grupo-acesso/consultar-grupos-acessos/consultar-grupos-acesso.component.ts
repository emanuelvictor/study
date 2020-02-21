import {Component, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {DialogService} from '../../../../../services/dialog.service';
import {MessageService} from '../../../../../services/message.service';
import {PaginationService} from '../../../../../services/pagination.service';
import {ListPageComponent} from '../../../../../../application/controls/crud/list/list-page.component';
import {CategoriaRepository} from "../../../../../repository/categoria.repository";
import {handlePageable} from "../../../../../../application/utils/handle-data-table";
import {GrupoAcesso} from "../../../../../entity/grupo-acesso.model";
import {GrupoAcessoRepository} from "../../../../../repository/grupo-acesso.repository";

@Component({
  selector: 'consultar-grupos-acesso',
  templateUrl: 'consultar-grupos-acesso.component.html',
  styleUrls: ['../grupo-acesso.component.scss']
})
export class ConsultarGruposAcessoComponent /*implements OnInit */ {

  // Bind com o component ListPageComponent
  @ViewChild(ListPageComponent)
  private grupoAcesso: GrupoAcesso = new GrupoAcesso();

  public filters: any = {defaultFilter: ''}; // Estado inicial dos filtros

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
    {name: 'nome', label: 'Nome'}
  ];

  public displayedColumns: string[] = this.columns.map(cell => cell.name);

  public dataSource = new MatTableDataSource();

  /**
   * @param dialogService {DialogService}
   * @param paginationService {PaginationService}
   * @param messageService {MessageService}
   * @param grupoAcessoRepository {CategoriaRepository}
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
    pageable.ativoFilter = (this.grupoAcesso as any).filters.ativoFilter;
    pageable.defaultFilter = (this.grupoAcesso as any).filters.defaultFilter;

    this.grupoAcessoRepository.listByFilters(pageable)
      .subscribe(result => {
        result.content.forEach(grupoAcesso => grupoAcesso.ativo ? grupoAcesso.ativo = 'Ativo' : grupoAcesso.ativo = 'Inativo');
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

    this.dialogService.confirmDelete(grupoAcesso, 'Grupo de Acesso')
      .then((accept: boolean) => {

        if (accept) {
          this.grupoAcessoRepository.delete(grupoAcesso.id)
            .then(() => {
              this.listByFilters();
              this.messageService.toastSuccess('Registro excluído com sucesso.')
            });
        }
      });
  }
}
