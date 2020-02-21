import 'rxjs/add/operator/debounceTime';
import {Component, ElementRef, Inject, OnInit, Renderer} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef, MatSnackBar} from "@angular/material";
import {AbstractControl, FormBuilder, ValidatorFn} from "@angular/forms";
import * as moment from "moment";
import {textMasks} from "../../../../../../controls/text-masks/text-masks";
import {CrudViewComponent} from "../../../../../../controls/crud/crud-view.component";
import {ActivatedRoute} from "@angular/router";
import {AuthenticationService} from "../../../../../../../domain/services/authentication.service";

// @ts-ignore
@Component({
  selector: 'extender-data-termino',
  templateUrl: './extender-data-termino.component.html',
  styleUrls: ['../../inventarios.scss']
})
export class ExtenderDataTerminoComponent extends CrudViewComponent implements OnInit {

  /**
   *
   */
  masks = textMasks;

  /**
   *
   * @param snackBar
   * @param activatedRoute
   * @param data
   * @param element
   * @param fb
   * @param renderer
   * @param authenticationService
   * @param dialogRef
   */
  constructor(public snackBar: MatSnackBar,
              public activatedRoute: ActivatedRoute,
              @Inject(MAT_DIALOG_DATA) public data: any,
              @Inject(ElementRef) public element: ElementRef,
              public fb: FormBuilder, public renderer: Renderer,
              public authenticationService: AuthenticationService,
              public dialogRef: MatDialogRef<ExtenderDataTerminoComponent>) {
    super(snackBar, element, fb, renderer, activatedRoute);
    this.entity = Object.assign({}, data.data)
  }

  /**
   *
   */
  ngOnInit() {

    this.form = this.fb.group({
      dataTerminoExtendida: ['dataTerminoExtendida', [this.dataTerminoExtendidaValidator()]]
    })

  }

  /**
   *
   * @param exception
   * @param validatorFn
   */
  public dataTerminoExtendidaValidator(exception?: string, validatorFn?: ValidatorFn): ValidatorFn {
    if (validatorFn) {
      return validatorFn;
    }
    return (c: AbstractControl): { [key: string]: any } => {

      if (!c || !c.value)
        return {
          exception: exception ? exception : 'Defina uma data'
        };

      if (c.value.length < 8)
        return {
          exception: 'Data inválida'
        };

      const momentData = moment(c.value, "DD-MM-YYYY");

      if (momentData.isBefore(moment())) {
        return {
          exception: 'Insira uma data posterior à hoje'
        };
      }

      if (this.entity.inventario.dataTermino && momentData.isBefore(moment(this.entity.inventario.dataTermino, "DD-MM-YYYY"))) {
        return {
          exception: 'Insira uma data posterior à ' + this.entity.inventario.dataTermino
        };
      }

      return null
    }
  }

  /**
   *
   */
  emit() {
    this.dialogRef.close(this.entity)
  }

  /**
   *
   * @param message
   */
  public openSnackBar(message: string) {
    this.snackBar.open(message, 'Fechar', {
      duration: 5000
    })
  }
}
