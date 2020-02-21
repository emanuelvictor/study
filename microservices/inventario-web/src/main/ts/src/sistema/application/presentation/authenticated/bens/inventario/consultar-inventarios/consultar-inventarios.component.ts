import {DialogService} from '../../../../../../domain/services/dialog.service';
import {MessageService} from '../../../../../../domain/services/message.service';
import {PaginationService} from '../../../../../../domain/services/pagination.service';
import {handlePageable} from '../../../../../utils/handle-data-table';
import {ListPageComponent} from 'sistema/application/controls/crud/list/list-page.component';
import {InventarioRepository} from "../../../../../../domain/repository/inventario.repository";
import {Inventario} from "../../../../../../domain/entity/patrimonio/inventario/inventario.model";
import {AuthenticationService} from "../../../../../../domain/services/authentication.service";
import {Component, OnInit, ViewChild} from "@angular/core";
import {MatTableDataSource} from "@angular/material";

// @ts-ignore
@Component({
  selector: 'consultar-inventarios',
  templateUrl: './consultar-inventarios.component.html',
  styleUrls: ['../inventarios.scss']
})
export class ConsultarInventariosComponent implements OnInit {

  @ViewChild(ListPageComponent, {static: true})
  private inventario: Inventario = new Inventario();


  public filters: any = {defaultFilter: '', ativoFilter: 'true'}; // Estado inicial dos filtros

  public pageable: any = {
    size: 20,
    page: 0,
    sort: null,
    defaultFilter: [],
    ativoFilter: true
  };

  public totalElements: any;
  public pageIndex: any;
  public pageSize: any;

  public columns: any[] = [
    {name: 'nome', label: 'Nome'},
    {name: 'dataInicio', label: 'Data de Início'},
    {name: 'dataTermino', label: 'Data de Término'}
  ];

  public displayedColumns: string[] = this.columns.map(cell => cell.name);

  public dataSource = new MatTableDataSource();


  public subhead : string = '';
  /**
   * @param dialogService {DialogService}
   * @param paginationService {PaginationService}
   * @param messageService {MessageService}
   * @param inventarioRepository {InventarioRepository}
   * @param authenticationService
   */
  constructor(private dialogService: DialogService,
              paginationService: PaginationService,
              private messageService: MessageService,
              private inventarioRepository: InventarioRepository,
              private authenticationService: AuthenticationService) {

    this.subhead = authenticationService.usuarioAutenticado.root || (authenticationService.usuarioAutenticado as any).isPatrimonio ? 'Inventários' : 'Meus Inventários';

    this.displayedColumns.push('acoes');
    this.pageable = paginationService.pageable('nome');

  }

  /**
   *
   */
  ngOnInit() {
    // Seta o size do pageable no size do paginator
    (this.inventario as any).paginator.pageSize = this.pageable.size;

    // Sobrescreve o sortChange do sort bindado
    this.sortChange();
  }

  /**
   *
   */
  public sortChange() {
    (this.inventario as any).sort.sortChange.subscribe(() => {
      const {active, direction} = (this.inventario as any).sort;
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

    const pageable = handlePageable(hasAnyFilter, (this.inventario as any).paginator, this.pageable);
    pageable.ativoFilter = (this.inventario as any).filters.ativoFilter;
    pageable.defaultFilter = (this.inventario as any).filters.defaultFilter;
    this.inventarioRepository.listByFilters(pageable)
      .subscribe(result => {
        this.dataSource = new MatTableDataSource(result.content);
        this.totalElements = result.totalElements;
        this.pageSize = result.size;
        this.pageIndex = result.pageable.pageNumber;    
      });
  }

  /**
   * Função para confirmar a exclusão de um registro permanentemente
   * @param inventario
   */
  public openDeleteDialog(inventario) {

    this.dialogService.confirmDelete(inventario, 'INVENTÁRIO')
      .then((accept: boolean) => {

        if (accept) {
          this.inventarioRepository.delete(inventario.id)
            .then(() => {
              this.listByFilters();
              this.messageService.toastSuccess('Inventário excluído com sucesso')
            })
        }
      })
  }
}

