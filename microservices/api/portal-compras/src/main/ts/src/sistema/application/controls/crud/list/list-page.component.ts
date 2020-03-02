import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {MatPaginator, MatSort} from '@angular/material';
import {CrudViewComponent} from '../crud-view.component';
import {debounce} from '../../../utils/debounce';
import {getLocalStorage, setLocalStorage} from '../../../utils/handle-local-storage';

@Component({
  selector: 'list-page',
  templateUrl: 'list-page.component.html'
})
export class ListPageComponent extends CrudViewComponent implements OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator; // Bind com o objeto paginator
  @ViewChild(MatSort) sort: MatSort; // Bind com objeto sort

  public filters: any = {defaultFilter: ''}; // Estado inicial dos filtros

  public debounce = debounce;
  public listByFiltersStatement = () => this.listByFilters(true);

  @Input() editavel: boolean = true;
  @Input() anexavel: boolean = false;
  @Input() desativavel: boolean = true;
  @Input() deletavel: boolean = false;

  @Input() rolesToAdd: string[] = ['administrador'];
  @Input() rolesToEdit: string[] = ['administrador'];
  @Input() rolesToDelelte: string[] = ['administrador'];
  @Input() rolesToView: string[] = ['administrador'];

  // Tabela
  @Input() dataSource: any;
  @Input() columns: any;
  @Input() displayedColumns: any;
  @Input() totalElements: any;
  @Input() pageSize: any;
  @Input() pageIndex: any;

  // Emite um evento de acordo com a função passada para o mesmo
  @Output() list = new EventEmitter();
  @Output() delete = new EventEmitter();
  @Output() anexo = new EventEmitter();

  /**
   *
   */
  ngOnInit() {

    this.rolesToAdd.push('administrador');
    this.rolesToEdit.push('administrador');
    this.rolesToDelelte.push('administrador');
    this.rolesToView.push('administrador');

    this.handleLabelStatus();

    this.pageSize = 20;

    // Verifica e mantém o estado dos filtros
    this.filters = getLocalStorage(this.filters, this.activatedRoute.component['name']);

    // Listagem inicial
    this.listByFilters();
  }

  /**
   * Restaura os filtros para o estado inicial
   */
  clearFilters = () => {
    const {defaultFilter, ativoFilter} = this.filters;

    if (defaultFilter || ativoFilter !== '') {
      this.filters = {defaultFilter: '', ativoFilter: ''};
      this.listByFilters();
    }
  };

  /**
   * Emite um evento para chamar a função no componente que o está invocando
   */
  listByFilters = (hasAnyFilter: boolean = false) => {
    
    setLocalStorage(this.filters, this.activatedRoute.component['name']);
    this.list.emit(hasAnyFilter);
    this.paginator.pageSize = this.pageSize;

  };

  openDeleteDialog = (data) => this.delete.emit(data);

  openAnexoDialog = (data) => this.anexo.emit(data);
}
