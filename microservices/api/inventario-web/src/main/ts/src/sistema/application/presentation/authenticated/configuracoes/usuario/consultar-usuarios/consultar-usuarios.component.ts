import {DialogService} from '../../../../../../domain/services/dialog.service';
import {MessageService} from '../../../../../../domain/services/message.service';
import {PaginationService} from '../../../../../../domain/services/pagination.service';
import {handlePageable} from '../../../../../utils/handle-data-table';
import {UsuarioRepository} from "../../../../../../domain/repository/usuario.repository";
import {ListPageComponent} from 'sistema/application/controls/crud/list/list-page.component';
import {Usuario} from 'sistema/domain/entity/usuario.model';
import {Component, OnInit, ViewChild} from "@angular/core";
import {MatTableDataSource} from "@angular/material";

// @ts-ignore
@Component({
  selector: 'consultar-usuarios',
  templateUrl: './consultar-usuarios.component.html',
  styleUrls: ['../usuario.component.scss']
})
export class ConsultarUsuariosComponent implements OnInit {

  @ViewChild(ListPageComponent, {static: true})
  private usuario: Usuario = new Usuario();

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
    {name: 'username', label: "E-mail"},
    {name: 'ativo', label: 'Ativo'},
    {name: 'interno', label: 'Interno'},
  ];

  public displayedColumns: string[] = this.columns.map(cell => cell.name);

  public dataSource = new MatTableDataSource();

  /**
   * @param dialogService {DialogService}
   * @param paginationService {PaginationService}
   * @param messageService {MessageService}
   * @param usuarioRepository {UsuarioRepository}
   */
  constructor(private dialogService: DialogService,
              paginationService: PaginationService,
              private messageService: MessageService,
              private usuarioRepository: UsuarioRepository) {

    this.displayedColumns.push('acoes');
    this.pageable = paginationService.pageable('nome');

  }

  /**
   *
   */
  ngOnInit() {
    // Seta o size do pageable no size do paginator
    (this.usuario as any).paginator.pageSize = this.pageable.size;

    // Sobrescreve o sortChange do sort bindado
    this.sortChange();
  }

  /**
   *
   */
  public sortChange() {
    (this.usuario as any).sort.sortChange.subscribe(() => {
      const {active, direction} = (this.usuario as any).sort;
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

    const pageable = handlePageable(hasAnyFilter, (this.usuario as any).paginator, this.pageable);
    pageable.ativoFilter = (this.usuario as any).filters.ativoFilter;
    pageable.defaultFilter = (this.usuario as any).filters.defaultFilter;
    this.usuarioRepository.listByFilters(pageable)
      .subscribe(result => {
        this.dataSource = new MatTableDataSource(result.content);
        this.totalElements = result.totalElements;
        this.pageSize = result.size;
        this.pageIndex = result.pageable.pageNumber;    
      });
  }

  /**
   * Função para confirmar a exclusão de um registro permanentemente
   * @param usuario
   */
  public openDeleteDialog(usuario) {

    this.dialogService.confirmDelete(usuario, 'USUÁRIO')
      .then((accept: boolean) => {

        if (accept) {
          this.usuarioRepository.delete(usuario.id)
            .then(() => {
              this.listByFilters();
              this.messageService.toastSuccess('Usuário excluído com sucesso')
            });
        }
      });
  }
}

