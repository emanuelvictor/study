import {Component, HostListener, OnInit} from '@angular/core';
import {AvisoEditalRepository} from 'sistema/domain/repository/aviso-edital.repository';
import {AvisoEdital} from 'sistema/domain/entity/publicacao/aviso-edital.model';
import {Anexo} from 'sistema/domain/entity/publicacao/anexo.model';

@Component({
  selector: 'principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.scss']
})
export class PrincipalComponent implements OnInit {

  public pageable: any = {
    size: 10,
    page: 0,
    sort: {
      'properties': 'dataPublicacao',
      'direction': 'DESC'
    },
    defaultFilter: [],
    ativoFilter: null,
    status: 'ABERTO'
  };

  hostname: string;

  avisosEditais: Array<AvisoEdital>;

  overflow: boolean;

  constructor(
    private avisoEditalRepository: AvisoEditalRepository
  ) {
    this.hostname = location.protocol + '//' + location.host + location.pathname + 'api/avisos-editais';
  }

  ngOnInit() {
    this.activateOverflow();

    this.listByDataPublicacao();
  }

  activateOverflow() {
    if (window.innerWidth < 1280) {
      this.overflow = true;
    } else {
      this.overflow = false;
    }
  }

  public listByDataPublicacao() {
   this.avisoEditalRepository.listByFilters(this.pageable).subscribe(result => {
      this.avisosEditais = result.content;

      this.avisosEditais.forEach((avisoEdital : AvisoEdital, index)=> {
        this.avisoEditalRepository.findAllByPublicacaoId(avisoEdital.id, null).subscribe(result => {
          this.avisosEditais[index].anexos = result.content;
          this.avisosEditais[index].dataModificacao = null;
          this.avisosEditais[index].anexos.forEach((anexo: Anexo) =>{
            if(anexo.updated > this.avisosEditais[index].dataModificacao || this.avisosEditais[index].dataModificacao == null){
              this.avisosEditais[index].dataModificacao = anexo.updated;
            }
          });
        })
      });
    });
  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.activateOverflow();
  }


}
