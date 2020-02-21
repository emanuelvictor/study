import {Component, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {DialogService} from '../../../../../services/dialog.service';
import {MessageService} from '../../../../../services/message.service';
import {PaginationService} from '../../../../../services/pagination.service';
import {ListPageComponent} from '../../../../../../application/controls/crud/list/list-page.component';
import {handlePageable} from "../../../../../../application/utils/handle-data-table";
import {TipoCadastro} from "../../../../../entity/tipo-cadastro.model";
import {TipoCadastroRepository} from 'sistema/domain/repository/tipo-cadastro.repository';

@Component({
  selector: 'consultar-tipos-cadastros',
  templateUrl: 'consultar-tipos-cadastros.component.html',
  styleUrls: ['../tipo-cadastro.component.scss']
})
export class ConsultarTiposCadastrosComponent /*implements OnInit */ {

  // Bind com o component ListPageComponent
  @ViewChild(ListPageComponent)
  private tipoCadastro: TipoCadastro = new TipoCadastro();

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
   * @param tipoCadastroRepository {TipoCadastroRepository}
   */
  constructor(private dialogService: DialogService,
              paginationService: PaginationService,
              private messageService: MessageService,
              private tipoCadastroRepository: TipoCadastroRepository) {

    this.displayedColumns.push('acoes');
    this.pageable = paginationService.pageable('nome');

  }

  /**
   *
   */
  ngOnInit() {
    // Seta o size do pageable no size do paginator
    (this.tipoCadastro as any).paginator.pageSize = this.pageable.size;

    // Sobrescreve o sortChange do sort bindado
    this.sortChange();
  }

  /**
   *
   */
  public sortChange() {
    (this.tipoCadastro as any).sort.sortChange.subscribe(() => {
      const {active, direction} = (this.tipoCadastro as any).sort;
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

    const pageable = handlePageable(hasAnyFilter, (this.tipoCadastro as any).paginator, this.pageable);
    pageable.ativoFilter = (this.tipoCadastro as any).filters.ativoFilter;
    pageable.defaultFilter = (this.tipoCadastro as any).filters.defaultFilter;

    this.tipoCadastroRepository.listByFilters(pageable)
      .subscribe(result => {
        result.content.forEach(tipoCadastro => tipoCadastro.ativo ? tipoCadastro.ativo = 'Ativo' : tipoCadastro.ativo = 'Inativo');
        this.dataSource = new MatTableDataSource(result.content);
        this.totalElements = result.totalElements;
        this.pageSize = result.size;
        this.pageIndex = result.pageable.pageNumber;
      });
  }

  /**
   * Função para confirmar a exclusão de um registro permanentemente
   * @param tipoCadastro
   */
  public openDeleteDialog(tipoCadastro) {

    this.dialogService.confirmDelete(tipoCadastro, 'avisoContratacao')
      .then((accept: boolean) => {

        if (accept) {
          this.tipoCadastroRepository.delete(tipoCadastro.id)
            .then(() => {
              this.listByFilters();
              this.messageService.toastSuccess('Registro excluído com sucesso.')
            });
        }
      });
  }
}
