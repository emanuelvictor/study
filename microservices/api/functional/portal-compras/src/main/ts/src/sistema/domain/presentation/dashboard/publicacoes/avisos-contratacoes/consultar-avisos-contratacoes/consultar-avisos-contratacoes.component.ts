import {Component, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {DialogService} from '../../../../../services/dialog.service';
import {MessageService} from '../../../../../services/message.service';
import {PaginationService} from '../../../../../services/pagination.service';
import {ListPageComponent} from '../../../../../../application/controls/crud/list/list-page.component';
import {handlePageable} from "../../../../../../application/utils/handle-data-table";
import {AvisoContratacao} from "../../../../../entity/publicacao/aviso-contratacao.model";
import {AvisoContratacaoRepository} from "../../../../../repository/aviso-contratacao.repository";
import {Anexo} from "../../../../../entity/publicacao/anexo.model";
import {FileRepository} from "../../../../../../application/upload-file-repository/file.repository";

@Component({
  selector: 'consultar-avisos-contratacoes',
  templateUrl: 'consultar-avisos-contratacoes.component.html',
  styleUrls: ['../avisos-contratacoes-view.component.scss']
})
export class ConsultarAvisosContratacoesComponent /*implements OnInit */ {

  // Bind com o component ListPageComponent
  @ViewChild(ListPageComponent)
  private avisoContratacao: AvisoContratacao = new AvisoContratacao();

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
  public pageIndex: any;
  public pageSize: any;

  public columns: any[] = [
    {name: 'numeroProcesso', label: 'N°'},
    {name: 'razaoSocial', label: 'Razão Social'},
    {name: 'modalidade', label: 'Modalidade'},
    {name: 'numeroModalidade', label: 'N° da Modalidade'},
    {name: 'dataPublicacao', label: 'Data da Publicação'},
  ];

  public displayedColumns: string[] = this.columns.map(cell => cell.name);

  public dataSource = new MatTableDataSource();

  /**
   * @param dialogService {DialogService}
   * @param paginationService {PaginationService}
   * @param messageService {MessageService}
   * @param fileRepository
   * @param avisoContratacaoRepository {AvisoContratacaoRepository}
   */
  constructor(private avisoContratacaoRepository: AvisoContratacaoRepository,
              private fileRepository: FileRepository, paginationService: PaginationService,
              private dialogService: DialogService, private messageService: MessageService) {

    this.displayedColumns.push('acoes');
    this.pageable = paginationService.pageable('dataPublicacao', this.pageable.sort.direction);

  }

  /**
   *
   */
  ngOnInit() {
    // Seta o size do pageable no size do paginator
    (this.avisoContratacao as any).paginator.pageSize = this.pageable.size;

    // Sobrescreve o sortChange do sort bindado
    this.sortChange();
  }

  /**
   *
   */
  public sortChange() {
    (this.avisoContratacao as any).sort.sortChange.subscribe(() => {
      const {active, direction} = (this.avisoContratacao as any).sort;
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

    const pageable = handlePageable(hasAnyFilter, (this.avisoContratacao as any).paginator, this.pageable);
    pageable.ativoFilter = (this.avisoContratacao as any).filters.ativoFilter;
    pageable.defaultFilter = (this.avisoContratacao as any).filters.defaultFilter;

    /**
     *
     */
    this.avisoContratacaoRepository.listByFilters(pageable)
      .subscribe(result => {
        result.content.forEach(categoria => categoria.ativo ? categoria.ativo = 'Ativo' : categoria.ativo = 'Inativo');
        this.dataSource = new MatTableDataSource(result.content);
        this.totalElements = result.totalElements;
        this.pageSize = result.size;
        this.pageIndex = result.pageable.pageNumber
      })
  }

  /**
   *
   * @param avisoContratacao
   */
  public openAnexoDialog(avisoContratacao: AvisoContratacao) {

    this.dialogService.sendAnexo(avisoContratacao, 'Aviso de Contratação')
      .then((anexo: Anexo) => {
        if (anexo)
          this.fileRepository.save(this.avisoContratacaoRepository.collectionName + '/' + avisoContratacao.id + '/anexos/' + anexo.nome, anexo.conteudo)
            .then(() => {
              this.messageService.toastSuccess('Arquivo anexado com suscesso.')
            })
      })
  }
}
