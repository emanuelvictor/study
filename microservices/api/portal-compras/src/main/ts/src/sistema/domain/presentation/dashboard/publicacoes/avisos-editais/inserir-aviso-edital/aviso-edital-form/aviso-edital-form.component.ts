import {Component, ElementRef, EventEmitter, Inject, Input, OnInit, Output, Renderer} from '@angular/core';
import {CrudViewComponent} from "../../../../../../../application/controls/crud/crud-view.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DateAdapter, MatSnackBar} from "@angular/material";
import {ActivatedRoute} from "@angular/router";
import {textMasks} from "../../../../../../../application/controls/text-masks/text-masks";
import {Status} from "../../../../../../entity/publicacao/status.enum";
import {enumToArrayString} from "../../../../../../../application/utils/utils";
import {CategoriaRepository} from "../../../../../../repository/categoria.repository";
import {CategoriaAvisoEdital} from "../../../../../../entity/publicacao/categoria-aviso-edital.model";

@Component({
  selector: 'aviso-edital-form',
  templateUrl: 'aviso-edital-form.component.html',
  styleUrls: ['../../avisos-editais-view.component.scss'],
})
export class AvisoEditalFormComponent extends CrudViewComponent implements OnInit {

  /**
   *
   */
  masks = textMasks;

  // Armazena o link para voltar para a tela de consulta
  /**
   *
   */
  @Input() backLink: string;

  /**
   *
   */
  @Output() removeAnexo: EventEmitter<number> = new EventEmitter<number>();

  /**
   *
   */
  form: FormGroup;

  /**
   *
   */
  statuss: any;

  /**
   *
   */
  toppingList: CategoriaAvisoEdital[] = [];

  /**
   *
   * @param snackBar
   * @param adapter
   * @param activatedRoute
   * @param element
   * @param categoriaRepository
   * @param fb
   * @param renderer
   */
  constructor(public snackBar: MatSnackBar,
              public adapter: DateAdapter<any>,
              public activatedRoute: ActivatedRoute,
              @Inject(ElementRef) public element: ElementRef,
              private categoriaRepository: CategoriaRepository,
              public fb: FormBuilder, public renderer: Renderer) {

    super(snackBar, element, fb, renderer, activatedRoute);

  }

  /**
   *
   */
  ngOnInit() {
    // this.toppingList = this.entity.categoriasAvisosEditais;
    this.categoriaRepository.listByFilters({size: 10000, sort: {properties: ['nome'], direction: 'ASC'}})
      .subscribe(result => {
        result.content.forEach(a => {

          if (a.ativo) {
            const categoriaAvisoEdital = new CategoriaAvisoEdital();
            categoriaAvisoEdital.avisoEdital = this.entity;
            categoriaAvisoEdital.categoria = a;
            this.toppingList.push(categoriaAvisoEdital);
          } else {
            if (this.entity.categoriasAvisosEditais)
              this.entity.categoriasAvisosEditais.forEach(inner => {
                if (a.id === inner.categoria.id) {
                  const categoriaAvisoEdital = new CategoriaAvisoEdital();
                  categoriaAvisoEdital.avisoEdital = this.entity;
                  categoriaAvisoEdital.categoria = a;
                  this.toppingList.push(categoriaAvisoEdital);
                }
              })
          }

          if (this.entity.categoriasAvisosEditais)
            this.entity.categoriasAvisosEditais.forEach(a => {
              this.toppingList.forEach(b => {
                if (b.categoria.id === a.categoria.id)
                  b.id = a.id;
              })
            })
        })
      });

    this.statuss = enumToArrayString(Status);

    this.form = this.fb.group({
      numeroEdital: ['numeroEdital', [Validators.required]],
      numeroProcesso: ['numeroProcesso', [Validators.required]],
      prazoPropostas: ['prazoPropostas', [Validators.required]],
      dataPublicacao: ['dataPublicacao', [Validators.required]],
      categorias: ['categorias', [Validators.required]],
      objeto: ['objeto', [Validators.required]],
    });

    this.entity.ativo = true;

    if (!this.entity.anexos || !this.entity.anexos.length)
      this.entity.anexos = []

  }

  /**
   *
   * @param o1
   * @param o2
   */
  compareObjects(o1: any, o2: any): boolean {
    return (o1 && o2 && o1.categoria && o2.categoria) && o1.categoria.id === o2.categoria.id;
  }

  /**
   *
   * @param form
   */
  public saveEntity(form: any): void {
    if (this.entity.anexos && this.entity.anexos.length) {
      super.saveEntity(form)
    } else this.snackBar.open('Avisos de Editais devem ter um ou mais anexos')
  }

  /**
   *
   * @param $event
   */
  public removeAnexoPublicacao($event) {
    this.removeAnexo.emit($event);
  }

}
