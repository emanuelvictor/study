import {Component, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {DialogService} from '../../../../services/dialog.service';
import {MessageService} from '../../../../services/message.service';
import {PaginationService} from '../../../../services/pagination.service';
import {ListPageComponent} from '../../../../../application/controls/crud/list/list-page.component';
import {Editora} from "../../../../entity/editora.model";
import {EditoraRepository} from "../../../../repository/editora.repository";
import {handlePageable} from "../../../../../application/utils/handle-data-table";

@Component({
  selector: 'consultar-editoras',
  templateUrl: 'consultar-editoras.component.html',
  styleUrls: ['../editora.component.scss']
})
export class ConsultarEditorasComponent /*implements OnInit */ {

  // Bind com o component ListPageComponent
  @ViewChild(ListPageComponent)
  private editora: Editora = new Editora();

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
  ];

  public displayedColumns: string[] = this.columns.map(cell => cell.name);

  public dataSource = new MatTableDataSource();

  /**
   * @param dialogService {DialogService}
   * @param paginationService {PaginationService}
   * @param messageService {MessageService}
   * @param editoraRepository {EditoraRepository}
   */
  constructor(private dialogService: DialogService,
              paginationService: PaginationService,
              private messageService: MessageService,
              private editoraRepository: EditoraRepository) {

    this.displayedColumns.push('acoes');
    this.pageable = paginationService.pageable('nome');

  }

  /**
   *
   */
  ngOnInit() {
    // Seta o size do pageable no size do paginator
    (this.editora as any).paginator.pageSize = this.pageable.size;

    // Sobrescreve o sortChange do sort bindado
    this.sortChange();
  }

  /**
   *
   */
  public sortChange() {
    (this.editora as any).sort.sortChange.subscribe(() => {
      const {active, direction} = (this.editora as any).sort;
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

    const pageable = handlePageable(hasAnyFilter, (this.editora as any).paginator, this.pageable);
    pageable.ativoFilter = (this.editora as any).filters.ativoFilter;
    pageable.defaultFilter = (this.editora as any).filters.defaultFilter;
    (this.editora as any).paginator.pageSize = this.pageable.size;

    this.editoraRepository.listByFilters(pageable)
      .subscribe(result => {
        result.content.forEach(editora => editora.ativo ? editora.ativo = 'Ativo' : editora.ativo = 'Inativo');
        this.dataSource = new MatTableDataSource(result.content);
        this.totalElements = result.totalElements;
        this.pageSize = result.size;
        this.pageIndex = result.pageable.pageNumber;
      });
  }

  /**
   * Função para confirmar a exclusão de um registro permanentemente
   * @param editora
   */
  public openDeleteDialog(editora) {

    this.dialogService.confirmDelete(editora, 'editora')
      .then((accept: boolean) => {

        if (accept) {
          this.editoraRepository.delete(editora.id)
            .then(() => {
              this.listByFilters();
              this.messageService.toastSuccess('Registro excluído com sucesso.')
            });
        }
      });
  }
}
