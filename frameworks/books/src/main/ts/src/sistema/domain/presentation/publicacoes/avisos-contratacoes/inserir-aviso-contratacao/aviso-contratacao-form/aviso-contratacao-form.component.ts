import {Component, ElementRef, EventEmitter, Inject, Input, OnInit, Output, Renderer} from '@angular/core';
import {CrudViewComponent} from "../../../../../../application/controls/crud/crud-view.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DateAdapter, MatSnackBar} from "@angular/material";
import {ActivatedRoute} from "@angular/router";
import {Modalidade} from "../../../../../entity/publicacao/modalidade.enum";
import {textMasks} from "../../../../../../application/controls/text-masks/text-masks";
import {enumToArrayString} from "../../../../../../application/utils/utils";

@Component({
  selector: 'aviso-contratacao-form',
  templateUrl: 'aviso-contratacao-form.component.html',
  styleUrls: ['../../avisos-contratacoes-view.component.scss'],
})
export class AvisoContratacaoFormComponent extends CrudViewComponent implements OnInit {

  /**
   *
   */
  masks = textMasks;

  // Armazena o link para voltar para a tela de consulta
  @Input() backLink: string;

  /**
   *
   */
  @Output() removeAnexo: EventEmitter<number> = new EventEmitter<number>();

  modalidades: any;

  form: FormGroup;

  /**
   *
   * @param snackBar
   * @param adapter
   * @param activatedRoute
   * @param element
   * @param fb
   * @param renderer
   */
  constructor(public snackBar: MatSnackBar,
              public adapter: DateAdapter<any>,
              public activatedRoute: ActivatedRoute,
              @Inject(ElementRef) public element: ElementRef,
              public fb: FormBuilder, public renderer: Renderer) {

    super(snackBar, element, fb, renderer, activatedRoute);

  }

  /**
   *
   */
  ngOnInit() {

    this.modalidades = enumToArrayString(Modalidade);

    this.form = this.fb.group({
      numeroModalidade: ['numeroModalidade', [Validators.required]],
      numeroProcesso: ['numeroProcesso', [Validators.required]],
      dataPublicacao: ['dataPublicacao', [Validators.required]],
      objeto: ['objeto', [Validators.required]],
    });

    this.entity.ativo = true;
    this.handleSubhead();

    if (!this.entity.anexos || !this.entity.anexos.length)
      this.entity.anexos = []

  }

  // Manipula o subtítulo da página de FORM
  handleSubhead = () => this.subhead = this.entity && this.entity.id ? `${this.subhead} / Editar` : `${this.subhead} / Adicionar`;

  /**
   *
   * @param form
   */
  public saveEntity(form: any): void {
    if (this.entity.anexos && this.entity.anexos.length) {
      super.saveEntity(form)
    } else this.snackBar.open('Avisos de Contratações devem ter um ou mais anexos')
  }

  /**
   *
   * @param $event
   */
  public removeAnexoPublicacao($event) {
    this.removeAnexo.emit($event);
  }

}
