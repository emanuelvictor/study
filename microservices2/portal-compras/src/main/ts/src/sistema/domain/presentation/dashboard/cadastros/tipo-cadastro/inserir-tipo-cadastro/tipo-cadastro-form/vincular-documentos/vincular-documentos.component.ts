import {Component, ElementRef, EventEmitter, Inject, Input, OnInit, Renderer,} from '@angular/core';
import {FormBuilder} from "@angular/forms"
import {TipoCadastroTipoDocumento} from 'sistema/domain/entity/tipo-cadastro-tipo-documento';
import {TipoDocumento} from 'sistema/domain/entity/tipo-documento.model';
import {TipoDocumentoRepository} from 'sistema/domain/repository/tipo-documento.repository';

@Component({
  selector: 'vincular-documentos',
  templateUrl: './vincular-documentos.component.html',
  styleUrls: ['../../../tipo-cadastro.component.scss']
})
export class VincularDocumentosComponent implements OnInit {

  /**
   *
   */
  @Input()
  documentos: TipoCadastroTipoDocumento[] = [];

  /**
   *
   */
  @Input()
  documentosChange: EventEmitter<any> = new EventEmitter<any>();

  /**
   *
   */
  tiposDocumentos: TipoDocumento[] = [];

  /**
   *
   * @param element
   * @param fb
   * @param renderer
   * @param tipoDocumentoRepository
   */
  constructor(@Inject(ElementRef) private element: ElementRef,
              private fb: FormBuilder, private renderer: Renderer,
              private tipoDocumentoRepository: TipoDocumentoRepository) {

  }

  /**
   *
   */
  ngOnInit() {
    this.tipoDocumentoRepository.listByFilters({ativoFilter: true})
      .subscribe(result => {
        this.tiposDocumentos = result.content;
      });
  }

  /**
   *
   * @param o1
   * @param o2
   */
  compareObjects(o1: any, o2: any): boolean {
    return o1.id === o2.id;
  }

  /**
   *
   */
  addDocument(){
    const documento: TipoCadastroTipoDocumento = new TipoCadastroTipoDocumento();
    documento.tipoDocumento = new TipoDocumento();
    this.documentos.push(documento);
  }

  /**
   *
   * @param index
   */
  removeDocument(index){
    this.documentos.splice(index,1);
  }

}
