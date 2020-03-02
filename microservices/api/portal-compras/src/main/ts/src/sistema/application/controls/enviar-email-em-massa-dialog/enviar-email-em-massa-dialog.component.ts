import {Component, ElementRef, Inject, OnInit, Renderer} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef, MatSnackBar} from '@angular/material';
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {DomSanitizer} from "@angular/platform-browser";
import {ActivatedRoute} from "@angular/router";
import {Email} from "../../../domain/entity/fornecedor/email/email.model";
import {Categoria} from "../../../domain/entity/categoria.model";
import {CategoriaRepository} from "../../../domain/repository/categoria.repository";
import {FornecedorRepository} from "../../../domain/repository/fornecedor.repository";

/**
 *
 */
@Component({
  selector: 'enviar-email-em-massa-dialog',
  templateUrl: 'enviar-email-em-massa-dialog.component.html',
  styleUrls: ['./enviar-email-em-massa-dialog.component.scss']
})
export class EnviarEmailEmMassaDialogComponent implements OnInit {

  /**
   *
   */
  public email: Email = new Email();

  /**
   *
   */
  public form: any;

  /**
   *
   */
  public categorias: Categoria[];

  /**
   *
   * @param activatedRoute
   * @param data
   * @param element
   * @param categoriaRepository
   * @param fornecedorRepository
   * @param dialogRef
   * @param renderer
   * @param snackBar
   * @param fb
   * @param _sanitizer
   */
  constructor(public activatedRoute: ActivatedRoute,
              @Inject(MAT_DIALOG_DATA) public data: any,
              @Inject(ElementRef) public element: ElementRef,
              private categoriaRepository: CategoriaRepository,
              private fornecedorRepository: FornecedorRepository,
              public renderer: Renderer, public snackBar: MatSnackBar,
              public fb: FormBuilder, private _sanitizer: DomSanitizer,
              private dialogRef: MatDialogRef<EnviarEmailEmMassaDialogComponent>) {
  }

  /**
   *
   */
  ngOnInit() {
    this.categoriaRepository.listByFiltersWithFornecedores({
      size: 10000,
      sort: {properties: ['nome'], direction: 'ASC'}
    }).subscribe(result => {
      this.categorias = result.content
    });

    this.form = this.fb.group({
      ['assunto']: new FormControl('assunto', Validators.required),
      ['conteudo']: new FormControl('conteudo', [Validators.required]),
      ['categorias']: new FormControl('categorias', [])
    })
  }

  /**
   *
   * @param o1
   * @param o2
   */
  compareCategorias(o1: any, o2: any): boolean {
    return o1 && o2 && o1.categoria && o2.categoria && o1.categoria.id === o2.categoria.id
  }

  /**
   *
   * @param $event
   */
  public removeAnexo($event) {
    if (($event || $event === 0) && this.email.anexosEmail && this.email.anexosEmail.length && this.email.anexosEmail[$event]) {
      this.email.anexosEmail.splice($event, 1)
    }
  }

  /**
   *
   * @param message
   */
  public error(message: string) {
    this.openSnackBar(message)
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

  /**
   *
   * @param form
   */
  public saveEntity(form: any): void {
    let valid = true;
    const controls: any = [];
    Object.keys(form.controls).map(function (key) {
      if (form.controls[key].invalid) {
        const control = form.controls[key];
        control.key = '#' + key;
        if (control.controls) {
          Object.keys(control.controls).map(function (key) {
            if (control.controls[key].invalid) {
              const controlInner = control.controls[key];
              controlInner.key = '#' + key;
              controls.push(controlInner)
            }
          })
        } else {
          controls.push(control)
        }
      }
    });

    for (const control of controls) {
      if (control) {
        const element = this.element.nativeElement.querySelector(control.key);
        if (element && control.invalid) {
          this.renderer.invokeElementMethod(element, 'focus', []);
          valid = false;
          if (control.errors.exception)
            this.error(control.errors.exception);
          break
        }
        if (control.controls && control.invalid) {
          for (const controlInner of control.controls) {
            const elemento = this.element.nativeElement.querySelector(controlInner.key);
            if (elemento && controlInner.invalid) {
              this.renderer.invokeElementMethod(elemento, 'focus', []);
              valid = false;
              if (controlInner.errors.exception)
                this.error(controlInner.errors.exception);
              break
            }
          }
        }
      }
    }

    if (form.valid && valid) {
      this.emit(this.email)
    }
  }

  /**
   *
   * @param email
   */
  public emit(email: Email): void {
    this.fornecedorRepository.sendMassMail(email);
    this.openSnackBar('Enviado com sucesso');
    this.dialogRef.close();
  }
}
