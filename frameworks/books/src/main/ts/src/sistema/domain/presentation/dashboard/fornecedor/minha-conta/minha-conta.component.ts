import {Component, ElementRef, Inject, OnInit, Renderer, ViewChild} from '@angular/core';
import {Fornecedor} from "../../../../entity/fornecedor/fornecedor.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MatSnackBar, MatStepper} from "@angular/material";
import {ActivatedRoute, Router} from "@angular/router";
import {MessageService} from "../../../../services/message.service";
import {DashboardViewComponent} from "../../dashboard-view.component";
import {FornecedorRepository} from "../../../../repository/fornecedor.repository";
import {AuthenticationService} from "../../../../services/authentication.service";
import {Endereco} from "../../../../entity/endereco/endereco.model";

@Component({
  selector: 'minha-conta',
  templateUrl: './minha-conta.component.html',
  styleUrls: ['../fornecedor.component.scss']
})
export class MinhaContaComponent implements OnInit {

  /**
   *
   */
  fornecedor: Fornecedor;

  /**
   *
   */
  visibleInfo: boolean = true;

  /**
   *
   */
  form: FormGroup = new FormGroup({
    dadosGeraisGroup: new FormGroup({}),
    contatosFormGroup: new FormGroup({}),
    dadosBancariosFormGroup: new FormGroup({}),
    documentosFormGroup: new FormGroup({})
  });

  /**
   *
   */
  @ViewChild('stepper')
  stepper: MatStepper;

  /**
   *
   * @param activatedRoute
   * @param messageService
   * @param homeView
   * @param element
   * @param fornecedorRepository
   * @param authenticationService
   * @param renderer
   * @param snackBar
   * @param router
   * @param _formBuilder
   */
  constructor(private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: DashboardViewComponent,
              @Inject(ElementRef) private element: ElementRef,
              private fornecedorRepository: FornecedorRepository,
              private authenticationService: AuthenticationService,
              private renderer: Renderer, public snackBar: MatSnackBar,
              private router: Router, private _formBuilder: FormBuilder) {
  }

  /**
   *
   */
  ngOnInit() {
    const usuarioAutenticado = this.authenticationService.usuarioAutenticado;
    if (usuarioAutenticado.isFornecedor)
      this.fornecedorRepository.findById(usuarioAutenticado.fornecedor.id)
        .subscribe(result => {
          if (!result.endereco)
            result.endereco = new Endereco('', '', '', '', '', null, 0, 0);

          if (!result.emails)
            result.emails = [];
          if (!result.telefones)
            result.telefones = [];

          this.fornecedor = result;
          (this.fornecedor as any).isRascunho = new Fornecedor(result).isRascunho;
        });
    else
      this.router.navigate(['dashboard'])
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
          if (control.parent.parent.parent.key === '#dadosGeraisGroup' || control.parent.parent.key === '#dadosGeraisGroup' || control.parent.key === '#dadosGeraisGroup')
            console.log('va para #dadosGeraisGroup');
          if (control.parent.parent.parent.key === '#contatosFormGroup' || control.parent.parent.key === '#contatosFormGroup' || control.parent.key === '#contatosFormGroup')
            console.log('va para #contatosFormGroup');
          if (control.parent.parent.parent.key === '#dadosBancariosFormGroup' || control.parent.parent.key === '#dadosBancariosFormGroup' || control.parent.key === '#dadosBancariosFormGroup')
            console.log('va para #dadosBancariosFormGroup');
          if (control.parent.parent.parent.key === '#documentosFormGroup' || control.parent.parent.key === '#documentosFormGroup' || control.parent.key === '#documentosFormGroup')
            console.log('va para #documentosFormGroup');
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
      this.submit(this.fornecedor);
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
          if (control.parent.parent.parent.key === '#dadosGeraisGroup' || control.parent.parent.key === '#dadosGeraisGroup' || control.parent.key === '#dadosGeraisGroup') {
            console.log('#dadosGeraisGroup');
            this.stepper.selectedIndex = 0;
            return valid;
          }
          if (control.parent.parent.parent.key === '#contatosFormGroup' || control.parent.parent.key === '#contatosFormGroup' || control.parent.key === '#contatosFormGroup') {
            console.log('#contatosFormGroup');
            this.stepper.selectedIndex = 1;
            return valid;
          }
          if (control.parent.parent.parent.key === '#dadosBancariosFormGroup' || control.parent.parent.key === '#dadosBancariosFormGroup' || control.parent.key === '#dadosBancariosFormGroup') {
            console.log('#dadosBancariosFormGroup');
            this.stepper.selectedIndex = 2;
            return valid;
          }
          if (control.parent.parent.parent.key === '#documentosFormGroup' || control.parent.parent.key === '#documentosFormGroup' || control.parent.key === '#documentosFormGroup') {
            console.log('#documentosFormGroup');
            this.stepper.selectedIndex = 3;
            return valid;
          }
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
  save(fornecedor: Fornecedor) {
    this.fornecedorRepository.save(fornecedor).then(() =>
      this.openSnackBar('Registro atualizado com sucesso!')
    );
  }

  /**
   *
   * @param fornecedor
   */
  submit(fornecedor: Fornecedor) {
    fornecedor.documentos = fornecedor.documentos.filter(value => value.nome);
    this.fornecedorRepository.save(fornecedor).then(() =>
      this.fornecedorRepository.sendToApprove(fornecedor).then(() =>
        this.openSnackBar('Enviado para aprovação!')
      )
    );
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

  /**
   *
   */
  hideInfo() {
    this.visibleInfo = !this.visibleInfo;
  }

  /**
   *
   */
  changeStep() {
    if (this.stepper.selectedIndex == 3) {
      this.visibleInfo = true;
    }
  }

}
