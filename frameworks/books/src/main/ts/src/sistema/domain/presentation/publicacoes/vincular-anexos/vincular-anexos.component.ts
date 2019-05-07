import {Component, EventEmitter, Input, OnInit, Output,} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms"
import {TipoDocumentoRepository} from 'sistema/domain/repository/tipo-documento.repository';
import {Anexo} from "../../../entity/publicacao/anexo.model";

@Component({
  selector: 'vincular-anexos',
  templateUrl: './vincular-anexos.component.html',
  styleUrls: ['./vincular-anexos.component.scss']
})
export class VincularAnexosComponent implements OnInit {

  /**
   *
   */
  @Input()
  form: FormGroup;

  /**
   *
   */
  @Input()
  anexos: Anexo[] = [];

  /**
   *
   */
  @Output()
  anexosChange: EventEmitter<any> = new EventEmitter<any>();

  /**
   *
   */
  @Output()
  remove: EventEmitter<number> = new EventEmitter<number>();

  /**
   *
   * @param fb
   * @param tipoDocumentoRepository
   */
  constructor(private fb: FormBuilder,
              private tipoDocumentoRepository: TipoDocumentoRepository) {
  }

  /**
   *
   */
  ngOnInit() {
    if (!this.form) {
      this.form = this.fb.group({});
    }
  }

  /**
   *
   */
  addAnexoPublicacao() {
    this.anexos.push(new Anexo());
  }

  /**
   *
   * @param index
   */
  removeAnexoPublicacao(index) {
    this.remove.emit(index);
  }

}
