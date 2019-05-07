import {Component, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {DialogService} from '../../../../services/dialog.service';
import {MessageService} from '../../../../services/message.service';
import {PaginationService} from '../../../../services/pagination.service';
import {ListPageComponent} from '../../../../../application/controls/crud/list/list-page.component';
import {Categoria} from "../../../../entity/categoria.model";
import {CategoriaRepository} from "../../../../repository/categoria.repository";
import {handlePageable} from "../../../../../application/utils/handle-data-table";

@Component({
  selector: 'consultar-categorias',
  templateUrl: 'consultar-categorias.component.html',
  styleUrls: ['../categoria.component.scss']
})
export class ConsultarCategoriasComponent /*implements OnInit */ {

  // Bind com o component ListPageComponent
  @ViewChild(ListPageComponent)
  private categoria: Categoria = new Categoria();

  public pageable: any = {
    size: 20,
    page: 0,
    sort: null,
    defaultFilter: [],
    ativoFilter: null
  };

  public totalElements: any;
  public pageSize: any;
  public pageIndex: any;

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
   * @param categoriaRepository {CategoriaRepository}
   */
  constructor(private dialogService: DialogService,
              paginationService: PaginationService,
              private messageService: MessageService,
              private categoriaRepository: CategoriaRepository) {

    this.displayedColumns.push('acoes');
    this.pageable = paginationService.pageable('nome');

  }

  /**
   *
   */
  ngOnInit() {
    // Seta o size do pageable no size do paginator
    (this.categoria as any).paginator.pageSize = this.pageable.size;

    // Sobrescreve o sortChange do sort bindado
    this.sortChange();
  }

  /**
   *
   */
  public sortChange() {
    (this.categoria as any).sort.sortChange.subscribe(() => {
      const {active, direction} = (this.categoria as any).sort;
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

    const pageable = handlePageable(hasAnyFilter, (this.categoria as any).paginator, this.pageable);
    pageable.ativoFilter = (this.categoria as any).filters.ativoFilter;
    pageable.defaultFilter = (this.categoria as any).filters.defaultFilter;
    (this.categoria as any).paginator.pageSize = this.pageable.size;

    this.categoriaRepository.listByFilters(pageable)
      .subscribe(result => {
        result.content.forEach(categoria => categoria.ativo ? categoria.ativo = 'Ativo' : categoria.ativo = 'Inativo');
        this.dataSource = new MatTableDataSource(result.content);
        this.totalElements = result.totalElements;
        this.pageSize = result.size;
        this.pageIndex = result.pageable.pageNumber;
      });
  }

  /**
   * Função para confirmar a exclusão de um registro permanentemente
   * @param categoria
   */
  public openDeleteDialog(categoria) {

    this.dialogService.confirmDelete(categoria, 'categoria')
      .then((accept: boolean) => {

        if (accept) {
          this.categoriaRepository.delete(categoria.id)
            .then(() => {
              this.listByFilters();
              this.messageService.toastSuccess('Registro excluído com sucesso.')
            });
        }
      });
  }
}
