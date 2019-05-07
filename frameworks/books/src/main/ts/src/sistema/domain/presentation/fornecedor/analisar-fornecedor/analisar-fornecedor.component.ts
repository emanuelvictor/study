import {Component, ElementRef, Inject, OnInit, Renderer} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DashboardViewComponent} from "../../dashboard-view.component";
import {MessageService} from "../../../services/message.service";
import {FormBuilder, FormGroup} from '@angular/forms';
import {Fornecedor} from 'sistema/domain/entity/fornecedor/fornecedor.model';
import {Endereco} from "../../../entity/endereco/endereco.model";
import {FornecedorRepository} from "../../../repository/fornecedor.repository";
import {MatSnackBar} from "@angular/material";
import {DialogService} from "../../../services/dialog.service";

@Component({
  selector: 'analisar-fornecedor',
  templateUrl: './analisar-fornecedor.component.html',
  styleUrls: ['../fornecedor.component.scss']
})
export class AnalisarFornecedorComponent implements OnInit {

  /**
   *
   */
  form: FormGroup;

  /**
   *
   */
  fornecedor: Fornecedor;

  /**
   *
   * @param router
   * @param _formBuilder
   * @param dialogService
   * @param activatedRoute
   * @param messageService
   * @param homeView
   * @param element
   * @param fornecedorRepository
   * @param snackBar
   * @param renderer
   */
  constructor(private router: Router,
              private _formBuilder: FormBuilder,
              private dialogService: DialogService,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: DashboardViewComponent,
              @Inject(ElementRef) private element: ElementRef,
              private fornecedorRepository: FornecedorRepository,
              private snackBar: MatSnackBar, private renderer: Renderer) {

  }

  /**
   *
   */
  ngOnInit() {

    this.form = new FormGroup({
      dadosGeraisGroup: new FormGroup({}),
      contatosFormGroup: new FormGroup({}),
      dadosBancariosFormGroup: new FormGroup({}),
      documentosFormGroup: new FormGroup({})
    });

    this.fornecedorRepository.findById(this.activatedRoute.snapshot.params.id).subscribe(result => {
      if (!result.endereco)
        result.endereco = new Endereco('', '', '', '', '', null, 0, 0);

      if (!result.emails)
        result.emails = [];
      if (!result.telefones)
        result.telefones = [];

      this.fornecedor = result;
    })
  }

  /**
   *
   */
  public saveEntity(form: any): void {
    let valid = true;
    const controls: any = [];
    Object.keys(form.controls).map(function (key) {
      if (form.controls[key].invalid) {
        const control = form.controls[key];
        control.key = '#' + key;
        if (control.controls && Object.keys(control.controls).length) {
          Object.keys(control.controls).map(function (key) {
            if (control.controls[key].invalid) {
              const controlInner = control.controls[key];
              controlInner.key = '#' + key;
              controls.push(controlInner);
            }
          });
        } else {
          controls.push(control);
        }
      }
    });

    for (const control of controls) {
      if (control) {
        const element = this.element.nativeElement.querySelector(control.key);
        if (element && control.invalid) {
          if (control.errors.exception)
            this.error(control.errors.exception);
          if (control.errors.errorMessage)
            this.error(control.errors.errorMessage);
          this.renderer.invokeElementMethod(element, 'focus', []);
          valid = false;
          if (control.errors.exception)
            this.error(control.errors.exception);
          break;
        }
        if (control.controls && Object.keys(control.controls).length && control.invalid) {
          if (valid)
            valid = this.validateForm(control)
        }
      }
    }

    if (form.valid && valid) {
      this.aprovar(this.fornecedor);
    }
  }

  /**
   *
   * @param form
   */
  validateForm(form: any): boolean {
    let valid = true;
    const controls: any = [];
    Object.keys(form.controls).map(function (key) {
      if (form.controls[key].invalid) {
        const control = form.controls[key];
        control.key = '#' + key;
        if (control.controls && Object.keys(control.controls).length) {
          Object.keys(control.controls).map(function (key) {
            if (control.controls[key].invalid) {
              const controlInner = control.controls[key];
              controlInner.key = '#' + key;
              controls.push(controlInner);
            }
          });
        } else {
          controls.push(control);
        }
      }
    });

    for (const control of controls) {
      if (control) {
        const element = this.element.nativeElement.querySelector(control.key);
        if (element && control.invalid) {
          if (control.errors.exception)
            this.error(control.errors.exception);
          this.renderer.invokeElementMethod(element, 'focus', []);
          valid = false;
          break;
        }
        if (control.controls && Object.keys(control.controls).length && control.invalid) {
          valid = this.validateForm(control);
        }
      }
    }
    return valid;
  }

  /**
   *
   * @param fornecedor
   */
  public save(fornecedor: Fornecedor) {
    fornecedor.documentos = fornecedor.documentos.filter(value => value.nome);
    this.fornecedorRepository.save(fornecedor as any).then(() => {
      this.openSnackBar('Registro atualizado com sucesso!');
      this.router.navigate(['fornecedores'])
    });
  }

  /**
   *
   * @param fornecedor
   */
  aprovar(fornecedor: Fornecedor) {
    fornecedor.documentos = fornecedor.documentos.filter(value => value.nome);
    this.fornecedorRepository.save(fornecedor).then(() =>
      this.fornecedorRepository.aprovar(fornecedor).then(() => {
        this.openSnackBar('Aprovado com sucesso!');
        this.router.navigate(['fornecedores'])
      })
    );
  }

  /**
   *
   * @param fornecedor
   */
  recusar(fornecedor: Fornecedor) {
    fornecedor.documentos = fornecedor.documentos.filter(value => value.nome);
    this.dialogService.recusar(fornecedor, 'Fornecedor')
      .then((recusar: any) => {
        if (recusar) {
          this.fornecedorRepository.save(fornecedor).then(() =>
            this.fornecedorRepository.recusar(fornecedor).then(() => {
              this.openSnackBar('Recusado com sucesso!');
              this.router.navigate(['fornecedores'])
            })
          );
        }
      });
  }

  /**
   *
   * @param message
   */
  public error(message: string) {
    this.openSnackBar(message);
  }

  /**
   *
   * @param message
   */
  public openSnackBar(message: string) {
    this.snackBar.open(message, 'Fechar', {
      duration: 5000
    });
  }

}
