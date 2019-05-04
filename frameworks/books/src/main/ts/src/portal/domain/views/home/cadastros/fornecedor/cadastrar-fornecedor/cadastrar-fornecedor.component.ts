import {Component, ElementRef, Inject, OnInit, Renderer} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {textMasks} from 'sistema/application/controls/text-masks/text-masks';
import {Fornecedor} from 'sistema/domain/entity/fornecedor/fornecedor.model';
import {RecaptchaComponent} from "ng-recaptcha";
import {cnpjValidator} from "../../../../../../../sistema/application/controls/validators/validators";
import {MatSnackBar} from "@angular/material";
import {FornecedorRepository} from "../../../../../../../sistema/domain/repository/fornecedor.repository";
import {Router} from "@angular/router";
import {MessageService} from "../../../../../../../sistema/domain/services/message.service";

@Component({
  selector: 'cadastrar-fornecedor',
  templateUrl: './cadastrar-fornecedor.component.html',
  styleUrls: ['./cadastrar-fornecedor.component.scss']
})
export class CadastrarFornecedorComponent implements OnInit {

  masks = textMasks;

  url: string = 'https://www.receitaws.com.br/v1/cnpj/';

  fornecedor: Fornecedor = new Fornecedor();

  reCaptcha: RecaptchaComponent;

  form: FormGroup;

  done = false;

  /**
   *
   * @param router
   * @param element
   * @param renderer
   * @param snackBar
   * @param fornecedorRepository
   * @param fb
   * @param http
   * @param messageService
   */
  constructor(@Inject(ElementRef) private element: ElementRef,
              private fornecedorRepository: FornecedorRepository,
              private router: Router, private renderer: Renderer,
              private snackBar: MatSnackBar, private fb: FormBuilder,
              private http: HttpClient, public messageService: MessageService) {

    this.fornecedor.telefones.push();
    this.fornecedor.telefones.push()

  }

  /**
   *
   */
  ngOnInit() {
    this.http.get('api/fornecedores/sitekey', {responseType: 'text'})
      .subscribe(result => {
        this.fornecedor.siteKey = result
      });

    this.form = this.fb.group({
      cnpj: ['cnpj', [Validators.required, cnpjValidator()]],
      razaoSocial: ['razaoSocial', [Validators.required]],
      nomeFantasia: ['nomeFantasia', [Validators.required]],
      telefone1: ['telefone1', [Validators.required]],
      email: ['email', [Validators.required, Validators.email]],
      recaptchaReactive: new FormControl(null, Validators.required)
    });
  }

  /**
   *
   * @param {string} captchaResponse
   */
  resolved(captchaResponse: string) {
    this.fornecedor.recap = captchaResponse;
  }

  /**
   *
   */
  reset() {
    this.reCaptcha.reset();
  }

  /**
   *
   */
  findCnpj(): void {
    if (this.fornecedor.usuario.documento) {
      let filterCnpj = this.fornecedor.usuario.documento.replace(/\./g, "").replace(/\//g, "").replace(/-/g, "");

      this.http.jsonp(this.url + filterCnpj, 'callback').subscribe((data: any) => {

        if (data.status === 'OK') {
          this.fornecedor.razaoSocial = data.nome;
          this.fornecedor.usuario.nome = data.fantasia;
          this.fornecedor.usuario.email = data.email;

        }

      });
    }
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
          this.renderer.invokeElementMethod(element, 'focus', []);
          valid = false;
          break;
        }
        if (control.controls && Object.keys(control.controls).length && control.invalid) {
          valid = this.validateForm(control)
        }
      }
    }

    if (form.valid && valid) {
      this.emit(this.fornecedor);
    }
  }

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
   * @param entity
   */
  emit(entity: Fornecedor) {
    const entityToSave = Object.assign({}, entity);
    entityToSave.telefones = entityToSave.telefones.filter(value => value);
    this.fornecedorRepository.save(entityToSave, entityToSave.recap).then(() => this.done = true);
    this.reset()
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
   * @param rawValue
   */
  public phoneMask = function (rawValue) {
    var numbers = rawValue.match(/\d/g);
    var numberLength = 0;
    if (numbers) {
      numberLength = numbers.join('').length;
    }
    if (numberLength <= 10) {
      return textMasks.phone8;
    } else {
      return textMasks.phone9;
    }
  }

}
