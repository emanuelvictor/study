import {Component, ElementRef, Inject, Input, OnInit, Renderer} from '@angular/core';
import {CrudViewComponent} from "../../../../../../application/controls/crud/crud-view.component";
import {FormBuilder, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'categoria-form',
  templateUrl: 'categoria-form.component.html',
  styleUrls: ['../../categoria.component.scss']
})
export class CategoriaFormComponent extends CrudViewComponent implements OnInit {

  // Armazena o link para voltar para a tela de consulta
  @Input() backLink: string;

  /**
   *
   * @param snackBar
   * @param activatedRoute
   * @param element
   * @param fb
   * @param renderer
   */
  constructor(public snackBar: MatSnackBar,
              public activatedRoute: ActivatedRoute,
              @Inject(ElementRef) public element: ElementRef,
              public fb: FormBuilder, public renderer: Renderer) {

    super(snackBar, element, fb, renderer, activatedRoute);

  }

  ngOnInit() {
    this.entity.ativo = true;
    this.form = this.fb.group({
      nome: ['nome', [Validators.required ]]
    });

    this.handleSubhead();
  }

  // Manipula o subtítulo da página de FORM
  handleSubhead = () => this.subhead = this.entity && this.entity.id ? `${this.subhead} / Editar` : `${this.subhead} / Adicionar`;
}
