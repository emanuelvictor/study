import {Component, OnInit, ViewChild} from '@angular/core';
import {AvisoEdital} from 'sistema/domain/entity/publicacao/aviso-edital.model';
import {MatPaginator, MatTableDataSource, PageEvent} from '@angular/material';
import {enumToArrayString} from 'sistema/application/utils/utils';
import {textMasks} from 'sistema/application/controls/text-masks/text-masks';
import {setLocalStorage} from 'sistema/application/utils/handle-local-storage';
import {handlePageable} from 'sistema/application/utils/handle-data-table';
import {ActivatedRoute} from '@angular/router';
import * as moment from 'moment';
import 'moment/locale/pt-br';
import {AvisoContratacao} from 'sistema/domain/entity/publicacao/aviso-contratacao.model';
import {Modalidade} from 'sistema/domain/entity/publicacao/modalidade.enum';
import {AvisoContratacaoRepository} from 'sistema/domain/repository/aviso-contratacao.repository';
import {Anexo} from 'sistema/domain/entity/publicacao/anexo.model';


@Component({
  selector: 'avisos-contratacoes',
  templateUrl: './consultar-avisos-contratacoes.component.html',
  styleUrls: ['./consultar-avisos-contratacoes.component.scss']
})
export class ConsultarAvisosContratacoesComponent implements OnInit {

  masks = textMasks;

  public totalElements: any;
  public pageIndex: any = 5;
  public pageSize: any = 5;

    // MatPaginator Output
    pageEvent: PageEvent

    modalidades: any;

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
    modalidade: null,
    dataInicio: null,
    dataFim: null,
  };

  hostname: string;

  avisosContratacoes: Array<AvisoContratacao>;

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
    private avisoContratacaoRepository: AvisoContratacaoRepository,
    private activatedRoute: ActivatedRoute,
  ) {
    this.hostname = location.protocol + '//' + location.host + location.pathname + 'api/avisos-contratacoes';
  }

  ngOnInit() {

    this.modalidades = enumToArrayString(Modalidade);

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
      case 'MODALIDADE':
        this.pageable.sort.properties = 'numeroModalidade';
        this.pageable.sort.direction = 'ASC';
        break;
      case 'PROCESSO':
        this.pageable.sort.properties = 'numeroProcesso';
        this.pageable.sort.direction = 'ASC';
        break;
      
    }


    const pageable = handlePageable(hasAnyFilter, this.paginator, this.pageable);
    pageable.ativoFilter = this.pageable.ativoFilter;
    pageable.defaultFilter = this.pageable.defaultFilter;
    pageable.modalidade = this.pageable.modalidade;

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
  
    this.avisoContratacaoRepository.listByFilters(pageable).subscribe(result => {

      result.content.forEach((avisoEdital : AvisoEdital, index)=> {
        this.avisoContratacaoRepository.findAllByPublicacaoId(avisoEdital.id, null).subscribe(anexos => {
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
    this.pageable.modalidade = null;

    this.listByFilters(true);
  }

  showAdvancedFilters() {
    this.filtrosAvancados = !this.filtrosAvancados;
    if(this.filtrosAvancados == false){
      this.cleanFilters();
    }
  }

}
