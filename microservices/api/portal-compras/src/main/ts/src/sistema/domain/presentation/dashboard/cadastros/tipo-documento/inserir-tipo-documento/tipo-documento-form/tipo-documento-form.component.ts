import {Component, ElementRef, Inject, Input, OnInit, Renderer} from '@angular/core';
import {CrudViewComponent} from "../../../../../../../application/controls/crud/crud-view.component";
import {AbstractControl, FormBuilder, ValidatorFn, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material";
import {ActivatedRoute} from "@angular/router";
import {isURL} from "../../../../../../../application/utils/utils";

@Component({
  selector: 'tipo-documento-form',
  templateUrl: 'tipo-documento-form.component.html',
  styleUrls: ['../../tipo-documento.component.scss']
})
export class TipoDocumentoFormComponent extends CrudViewComponent implements OnInit {

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
      nome: ['nome', [Validators.required]],
      modelo: ['modelo', [this.linkValidator()]]
    });

    this.handleSubhead();
  }


  /**
   *
   */
  linkValidator(): ValidatorFn {

    return (c: AbstractControl): { [key: string]: any } => {

      if (!c.value || !c.value.length) return null;

      if (c.value.indexOf('http') < 0) return {
        exception: 'Insira o protocolo (http ou https)'
      };

      if (!isURL(c.value)) return {
        exception: 'URL Inválida'
      };

      return null
    }
  }

  // Manipula o subtítulo da página de FORM
  handleSubhead = () => this.subhead = this.entity && this.entity.id ? `${this.subhead} / Editar` : `${this.subhead} / Adicionar`;
}
