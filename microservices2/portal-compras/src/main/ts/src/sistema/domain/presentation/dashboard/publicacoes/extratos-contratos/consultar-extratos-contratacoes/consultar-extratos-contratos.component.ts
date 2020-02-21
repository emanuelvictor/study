import {Component, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {DialogService} from '../../../../../services/dialog.service';
import {MessageService} from '../../../../../services/message.service';
import {PaginationService} from '../../../../../services/pagination.service';
import {ListPageComponent} from '../../../../../../application/controls/crud/list/list-page.component';
import {handlePageable} from "../../../../../../application/utils/handle-data-table";
import {ExtratoContratoRepository} from "../../../../../repository/extrato-contrato.repository";
import {ExtratoContratacao} from "../../../../../entity/publicacao/extrato-contratacao.model";
import {Anexo} from "../../../../../entity/publicacao/anexo.model";
import {FileRepository} from "../../../../../../application/upload-file-repository/file.repository";

@Component({
  selector: 'consultar-extratos-contratos',
  templateUrl: 'consultar-extratos-contratos.component.html',
  styleUrls: ['../extratos-contrato-view.component.scss']
})
export class ConsultarExtratosContratosComponent /*implements OnInit */ {

  // Bind com o component ListPageComponent
  @ViewChild(ListPageComponent)
  private extratoContratacao: ExtratoContratacao = new ExtratoContratacao();

  public pageable: any = {
    size: 20,
    page: 0,
    sort: {
      direction: 'DESC'
    },
    defaultFilter: [],
    ativoFilter: null
  };


  public totalElements: any;
  public pageSize: any;
  public pageIndex: any;

  public columns: any[] = [
    {name: 'numeroProcesso', label: 'N°'},
    {name: 'razaoSocial', label: 'Razão Social'},
    {name: 'instrumentoJuridico', label: 'Instrumento Jurídico', translate: true },
    {name: 'dataPublicacao', label: 'Data da Publicação'},
  ];

  public displayedColumns: string[] = this.columns.map(cell => cell.name);

  public dataSource = new MatTableDataSource();

  /**
   * @param dialogService {DialogService}
   * @param paginationService {PaginationService}
   * @param messageService {MessageService}
   * @param fileRepository
   * @param extratoContratacaoRepository {ExtratoContratoRepository}
   */
  constructor(private dialogService: DialogService,
              paginationService: PaginationService,
              private messageService: MessageService,
              private fileRepository: FileRepository,
              private extratoContratacaoRepository: ExtratoContratoRepository) {

    this.displayedColumns.push('acoes');
    this.pageable = paginationService.pageable('dataPublicacao', this.pageable.sort.direction);

  }

  /**
   *
   */
  ngOnInit() {
    // Seta o size do pageable no size do paginator
    (this.extratoContratacao as any).paginator.pageSize = this.pageable.size;

    // Sobrescreve o sortChange do sort bindado
    this.sortChange();
  }

  /**
   *
   */
  public sortChange() {
    (this.extratoContratacao as any).sort.sortChange.subscribe(() => {
      const {active, direction} = (this.extratoContratacao as any).sort;
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

    const pageable = handlePageable(hasAnyFilter, (this.extratoContratacao as any).paginator, this.pageable);
    pageable.ativoFilter = (this.extratoContratacao as any).filters.ativoFilter;
    pageable.defaultFilter = (this.extratoContratacao as any).filters.defaultFilter;

    this.extratoContratacaoRepository.listByFilters(pageable).subscribe(result => {
      result.content.forEach(categoria => categoria.ativo ? categoria.ativo = 'Ativo' : categoria.ativo = 'Inativo');
      this.dataSource = new MatTableDataSource(result.content);
      this.totalElements = result.totalElements;
      this.pageSize = result.size;
      this.pageIndex = result.pageable.pageNumber;
    });
  }

  public openAnexoDialog(extratoContratacao: ExtratoContratacao) {

    this.dialogService.sendAnexo(extratoContratacao, 'Extrato de contratação')
      .then((anexo: Anexo) => {

        if (anexo) {
          this.fileRepository.save(this.extratoContratacaoRepository.collectionName + '/' + extratoContratacao.id + '/anexos/' + anexo.nome, anexo.conteudo)
            .then(() => {
              this.messageService.toastSuccess('Arquivo anexado com suscesso.')
            });
        }
      });
  }
}
