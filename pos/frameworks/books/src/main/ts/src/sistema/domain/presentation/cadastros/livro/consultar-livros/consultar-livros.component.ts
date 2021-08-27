import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {DialogService} from '../../../../services/dialog.service';
import {MessageService} from '../../../../services/message.service';
import {PaginationService} from '../../../../services/pagination.service';
import {ListPageComponent} from '../../../../../application/controls/crud/list/list-page.component';
import {Livro} from "../../../../entity/livro.model";
import {LivroRepository} from "../../../../repository/livro.repository";
import {handlePageable} from "../../../../../application/utils/handle-data-table";

@Component({
  selector: 'consultar-livros',
  templateUrl: 'consultar-livros.component.html',
  styleUrls: ['../livro.component.scss']
})
export class ConsultarLivrosComponent implements OnInit {

  // Bind com o component ListPageComponent
  @ViewChild(ListPageComponent)
  private livro: Livro = new Livro();

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
    {name: 'editora.nome', label: 'Editora'},
  ];

  public displayedColumns: string[] = this.columns.map(cell => cell.name);

  public dataSource = new MatTableDataSource();

  /**
   * @param dialogService {DialogService}
   * @param paginationService {PaginationService}
   * @param messageService {MessageService}
   * @param livroRepository {LivroRepository}
   */
  constructor(private dialogService: DialogService,
              paginationService: PaginationService,
              private messageService: MessageService,
              private livroRepository: LivroRepository) {

    this.displayedColumns.push('acoes');
    this.pageable = paginationService.pageable('nome');

  }

  /**
   *
   */
  ngOnInit() {
    // Seta o size do pageable no size do paginator
    (this.livro as any).paginator.pageSize = this.pageable.size;

    // Sobrescreve o sortChange do sort bindado
    this.sortChange();
  }

  /**
   *
   */
  public sortChange() {
    (this.livro as any).sort.sortChange.subscribe(() => {
      const {active, direction} = (this.livro as any).sort;
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

    const pageable = handlePageable(hasAnyFilter, (this.livro as any).paginator, this.pageable);
    pageable.ativoFilter = (this.livro as any).filters.ativoFilter;
    pageable.defaultFilter = (this.livro as any).filters.defaultFilter;
    (this.livro as any).paginator.pageSize = this.pageable.size;

    this.livroRepository.listByFilters(pageable)
      .subscribe(result => {
        console.log(result.content);
        this.dataSource = new MatTableDataSource(result.content);
        this.totalElements = result.totalElements;
        this.pageSize = result.size;
        this.pageIndex = result.pageable.pageNumber;
      });
  }

  /**
   * Função para confirmar a exclusão de um registro permanentemente
   * @param livro
   */
  public openDeleteDialog(livro) {

    this.dialogService.confirmDelete(livro, 'livro')
      .then((accept: boolean) => {

        if (accept) {
          this.livroRepository.delete(livro.id)
            .then(() => {
              this.listByFilters();
              this.messageService.toastSuccess('Registro excluído com sucesso.')
            });
        }
      });
  }
}
