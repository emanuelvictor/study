import {Component, ElementRef, Inject, OnInit, Renderer} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MAT_FORM_FIELD_DEFAULT_OPTIONS, MatFormFieldDefaultOptions, MatSnackBar} from '@angular/material';
import {AuthenticatedViewComponent} from '../../../../authenticated-view.component';
import {MessageService} from '../../../../../../../domain/services/message.service';
import {AbstractControl, FormBuilder, FormControl, ValidatorFn, Validators} from "@angular/forms"
import {InventarioRepository} from "../../../../../../../domain/repository/inventario.repository";
import {CrudViewComponent} from "../../../../../../controls/crud/crud-view.component";
import 'rxjs/add/operator/debounceTime';
import {AuthenticationService} from "../../../../../../../domain/services/authentication.service";
import {textMasks} from "../../../../../../controls/text-masks/text-masks";

import * as moment from 'moment';

const appearance: MatFormFieldDefaultOptions = {
  appearance: 'outline'
};

// @ts-ignore
@Component({
  selector: 'inventario-form',
  templateUrl: './inventario-form.component.html',
  styleUrls: ['../../inventarios.scss'],
  providers: [
    {
      provide: MAT_FORM_FIELD_DEFAULT_OPTIONS,
      useValue: appearance
    }
  ]
})
export class InventarioFormComponent extends CrudViewComponent implements OnInit {


  /**
   *
   */
  masks = textMasks;


  /**
   *
   * @param router
   * @param snackBar
   * @param homeView
   * @param activatedRoute
   * @param messageService
   * @param inventarioRepository
   * @param element
   * @param fb
   * @param renderer
   * @param authenticationService
   * @param inventarioRepository
   */
  constructor(private router: Router,
              public snackBar: MatSnackBar,
              public activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: AuthenticatedViewComponent,
              private inventarioRepository: InventarioRepository,
              @Inject(ElementRef) public element: ElementRef,
              public fb: FormBuilder, public renderer: Renderer,
              public authenticationService: AuthenticationService) {

    super(snackBar, element, fb, renderer, activatedRoute)

  }

  /**
   *
   */
  ngOnInit() {

    this.form = this.fb.group({
      nome: new FormControl({value: '', disabled: false}, Validators.required),
      dataInicio: ['dataInicio', [this.dataInicioValidator()]],
      dataTermino: ['dataTermino', [this.dataTerminoValidator()]],
    })

  }


  /**
   *
   * @param exception
   * @param validatorFn
   */
  public dataInicioValidator(exception?: string, validatorFn?: ValidatorFn): ValidatorFn {
    if (validatorFn) {
      return validatorFn;
    }
    return (c: AbstractControl): { [key: string]: any } => {

      if (!c || !c.value)
        return {
          exception: exception ? exception : 'Defina uma data de início'
        };

      if (c.value.length < 8)
        return {
          exception: 'Data inválida'
        };

      // Data de hoje
      const momentData = moment(c.value, "DD-MM-YYYY");

      if (!this.entity.id) {
        if (momentData.isBefore(moment().subtract("1", "days"))) {
          return {
            exception: 'Insira uma data posterior à hoje'
          };
        }
      }

      if (c.value === this.entity.dataTermino)
        return null;

      if (this.entity.dataTermino && momentData.isAfter(moment(this.entity.dataTermino, "DD-MM-YYYY"))) {
        return {
          exception: 'Insira uma data anterior à ' + this.entity.dataTermino
        };
      }

      return null
    }
  }

  /**
   *
   * @param exception
   * @param validatorFn
   */
  public dataTerminoValidator(exception?: string, validatorFn?: ValidatorFn): ValidatorFn {
    if (validatorFn) {
      return validatorFn;
    }
    return (c: AbstractControl): { [key: string]: any } => {

      if (!c || !c.value)
        return {
          exception: exception ? exception : 'Defina uma data de término'
        };

      if (c.value === this.entity.dataInicio)
        return null;

      if (c.value.length < 8)
        return {
          exception: 'Data inválida'
        };

      const momentData = moment(c.value, "DD-MM-YYYY");
      if (this.entity.dataInicio && momentData.isBefore(moment(this.entity.dataInicio, "DD-MM-YYYY"))) {
        return {
          exception: 'Insira uma data posterior à ' + this.entity.dataInicio
        };
      }

      return null
    }
  }


  emit(entity: any) {
    this.save.emit(entity)
  }

}
