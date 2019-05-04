import {Component, OnInit, ViewChild} from '@angular/core';
import {AvisoEdital} from 'sistema/domain/entity/publicacao/aviso-edital.model';
import {AvisoEditalRepository} from 'sistema/domain/repository/aviso-edital.repository';
import {MatPaginator, MatTableDataSource, PageEvent} from '@angular/material';
import {enumToArrayString} from 'sistema/application/utils/utils';
import {Status} from 'sistema/domain/entity/publicacao/status.enum';
import {setLocalStorage} from 'sistema/application/utils/handle-local-storage';
import {handlePageable} from 'sistema/application/utils/handle-data-table';
import {ActivatedRoute} from '@angular/router';
import {debounce} from 'sistema/application/utils/debounce';
import * as moment from 'moment';
import 'moment/locale/pt-br';
import {Anexo} from 'sistema/domain/entity/publicacao/anexo.model';

@Component({
  selector: 'avisos-editais',
  templateUrl: './consultar-avisos-editais.component.html',
  styleUrls: ['./consultar-avisos-editais.component.scss']
})
export class ConsultarAvisosEditaisComponent implements OnInit {

  public totalElements: any = 5;
  public pageIndex: any = 5;
  public pageSize: any = 5;

    // MatPaginator Output
    pageEvent: PageEvent

    public debounce = debounce;

    statuss: any;

    orderBy: any = 'PUBLICADO';



  public pageable: any = {
    size: 5,
    page: 0,
    sort: {
      'properties': 'dataPublicacao',
      'direction': 'DESC'
    },
    defaultFilter: [],
    ativoFilter: null,
    status: 'ABERTO',
    dataInicio: null,
    dataFim: null,
  };

  hostname: string;

  avisosEditais: Array<AvisoEdital>;

  overflow: boolean;

  filtrosAvancados:boolean = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;

    /**
   *
   * dataSource com os usuários
   * @type {MatTableDataSource<Usuario>}
   */
  dataSource = new MatTableDataSource<AvisoEdital>();

  constructor(
    private avisoEditalRepository: AvisoEditalRepository,
    private activatedRoute: ActivatedRoute,
  ) {
    this.hostname = location.protocol + '//' + location.host + location.pathname + 'api/avisos-editais';
  }

  ngOnInit() {
    this.statuss = enumToArrayString(Status);

    // Seta o size do pageable no size do paginator
    this.paginator.pageSize = this.pageable.size;

    this.listByFilters();
  }

  public listByFilters(hasAnyFilter: boolean = false) {

    // Define o estado atual dos filtros
    setLocalStorage(this.pageable.defaultFilter, this.activatedRoute.component['name']);


    switch(this.orderBy) {
      case 'PUBLICADO':
        this.pageable.sort.properties = 'dataPublicacao';
        this.pageable.sort.direction = 'DESC';
        break;
      case 'PRAZO':
        this.pageable.sort.properties = 'prazoPropostas';
        this.pageable.sort.direction = 'DESC';
        break;
      case 'PROCESSO':
        this.pageable.sort.properties = 'numeroProcesso';
        this.pageable.sort.direction = 'ASC';
        break;
      
    }


    const pageable = handlePageable(hasAnyFilter, this.paginator, this.pageable);
    pageable.ativoFilter = this.pageable.ativoFilter;
    pageable.defaultFilter = this.pageable.defaultFilter;
    pageable.status = this.pageable.status;

    if(this.pageable.dataInicio != null ) {
      pageable.dataInicio =  moment(this.pageable.dataInicio).format("DD/MM/YYYY").toString();
    } else {
      pageable.dataInicio = null;
    }
    if(this.pageable.dataFim != null ) {
      pageable.dataFim =  moment(this.pageable.dataFim).format("DD/MM/YYYY").toString();
    } else {
      pageable.dataFim = null;
    }
  
    this.avisoEditalRepository.listByFilters(pageable).subscribe(result => {

      result.content.forEach((avisoEdital : AvisoEdital, index)=> {
        this.avisoEditalRepository.findAllByPublicacaoId(avisoEdital.id, null).subscribe(anexos => {
          result.content[index].anexos = anexos.content;
          result.content[index].dataModificacao = null;
          result.content[index].anexos.forEach((anexo: Anexo) =>{
            if(anexo.updated > result.content[index].dataModificacao || result.content[index].dataModificacao == null){
              result.content[index].dataModificacao = anexo.updated;
            }
          });
        })
      });


      this.dataSource = new MatTableDataSource(result.content);
      this.totalElements = result.totalElements;
      this.pageSize = result.size;
      this.pageIndex = result.pageable.pageNumber;
    });
  }
   
  cleanFilters(){
    this.pageable.dataInicio = null;
    this.pageable.dataFim = null;

    this.listByFilters(true);
  }

  showAdvancedFilters() {
    this.filtrosAvancados = !this.filtrosAvancados;
    if(this.filtrosAvancados == false){
      this.cleanFilters();
    }
  }

}
