import {Component, ElementRef, EventEmitter, Inject, Input, OnInit, Output, Renderer} from '@angular/core';
import {CrudViewComponent} from "../../../../../../../application/controls/crud/crud-view.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DateAdapter, MatSnackBar} from "@angular/material";
import {ActivatedRoute} from "@angular/router";
import {textMasks} from "../../../../../../../application/controls/text-masks/text-masks";
import {InstrumentoJuridico} from "../../../../../../entity/publicacao/instrumento-juridico.enum";

@Component({
  selector: 'extrato-contratacao-form',
  templateUrl: 'extrato-contrato-form.component.html',
  styleUrls: ['../../extratos-contrato-view.component.scss'],
})
export class ExtratoContratoFormComponent extends CrudViewComponent implements OnInit {

  /**
   *
   */
  masks = textMasks;

  /**
   *
   */
  @Output() removeAnexo: EventEmitter<number> = new EventEmitter<number>();

  // Armazena o link para voltar para a tela de consulta
  @Input() backLink: string;

  instrumentosJuridicos: any;

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

    this.instrumentosJuridicos = this.enumToArrayString(InstrumentoJuridico);

    this.form = this.fb.group({
      numeroProcesso: ['numeroProcesso', [Validators.required]],
      objeto: ['objeto', [Validators.required]],
      razaoSocial: ['razaoSocial', []]
    });

    this.entity.ativo = true;
    this.handleSubhead();

    if (!this.entity.anexos || !this.entity.anexos.length)
      this.entity.anexos = []
  }

  /**
   *
   * @param enumerator
   */
  enumToArrayString(enumerator): any {
    return Object.keys(enumerator).map(key => enumerator[key]).filter(value => typeof value === 'string') as string[]
  }

  // Manipula o subtítulo da página de FORM
  handleSubhead = () => this.subhead = this.entity && this.entity.id ? `${this.subhead} / Editar` : `${this.subhead} / Adicionar`;

  /**
   *
   * @param $event
   */
  public removeAnexoPublicacao($event) {
    this.removeAnexo.emit($event);
  }
}
