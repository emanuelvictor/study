import {
  ChangeDetectorRef,
  Component,
  ElementRef,
  EventEmitter,
  Inject,
  Input,
  OnDestroy,
  OnInit,
  Output,
  Renderer
} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, ValidatorFn} from "@angular/forms";
import {DateAdapter, MatSnackBar} from "@angular/material";
import {ActivatedRoute} from "@angular/router";
import {CrudViewComponent} from "../../../../../../application/controls/crud/crud-view.component";
import {Anexo} from "../../../../../entity/publicacao/anexo.model";
import {FileRepository} from "../../../../../../application/upload-file-repository/file.repository";
import {DomSanitizer} from "@angular/platform-browser";
import {DialogService} from "../../../../../services/dialog.service";
import {isURL} from "../../../../../../application/utils/utils";

@Component({
  selector: 'anexo',
  templateUrl: 'anexo.component.html',
  styleUrls: ['anexo.component.scss'],
})
export class AnexoComponent extends CrudViewComponent implements OnInit, OnDestroy {

  /**
   *
   */
  @Input() form: FormGroup;

  /**
   *
   */
  index = Math.floor(Math.random() * 2000).toString();

  /**
   * Serve para validar os anexos repetidos
   */
  @Input() anexos: Anexo[];

  /**
   *
   */
  @Input() anexo: Anexo;

  /**
   *
   */
  @Output() anexoChange: EventEmitter<Anexo> = new EventEmitter();

  /**
   *
   */
  public formatos: string[] = [
    '.pdf',
    '.png',
    '.jpg',
    '.jpeg',
    '.odt',
    '.rtf'
    // ,
    // '.xls',
    // '.xlsx',
    // '.docx',
    // '.doc'
  ];

  /**
   *
   * @param snackBar
   * @param cd
   * @param adapter
   * @param _sanitizer
   * @param dialogService
   * @param activatedRoute
   * @param fileRepository
   * @param element
   * @param fb
   * @param renderer
   */
  constructor(public snackBar: MatSnackBar,
              private cd: ChangeDetectorRef,
              public adapter: DateAdapter<any>,
              private _sanitizer: DomSanitizer,
              private dialogService: DialogService,
              public activatedRoute: ActivatedRoute,
              private fileRepository: FileRepository,
              @Inject(ElementRef) public element: ElementRef,
              public fb: FormBuilder, public renderer: Renderer) {

    super(snackBar, element, fb, renderer, activatedRoute);

  }

  /**
   *
   */
  ngOnInit() {

    (this.anexo as any).index = this.index;

    this.form.addControl(this.index, new FormGroup({
      ['nome' + this.index]: new FormControl('nome' + this.index, this.checkNomeValidator(this.anexos))
    }));

    if (this.anexo && this.anexo.externo)
      (this.form.get(this.index) as FormGroup).addControl('link' + this.index, new FormControl('link' + this.index, this.linkValidator()));

    if (!this.anexo)
      this.anexo = new Anexo();

    else if (this.anexo.caminho && !this.anexo.externo)
      this.fileRepository.findOne('api/' + this.anexo.caminho + '?nocache=' + Math.floor(Math.random() * 2000).toString())
        .subscribe(result => {

          const blob = new Blob([result], {type: this.anexo.type});
          this.anexo.conteudo = new File([blob], this.anexo.nome, {type: result.type});
          this.selectChange(this.anexo);
          const reader = new FileReader();
          reader.readAsDataURL(blob);
          // reader.onload = () => this.anexo.caminho = reader.result.toString()

        })
  }

  /**
   *
   * @param anexos {Anexo[]}
   */
  checkNomeValidator(anexos: Anexo[]): ValidatorFn {

    return (c: AbstractControl): { [key: string]: any } => {

      if (anexos.filter(anexo => anexo.nome === c.value && (this.anexo as any).index !== (anexo as any).index).length > 0) return {
        exception: 'Nome já cadastrado'
      };

      if (!c.value || !c.value.length) return {
        exception: 'Campo obrigatório'
      };

      if (this.anexo.externo)
        return null;

      if (!this.anexo.conteudo || !this.anexo.conteudo.name) return {
        exception: 'Insira um anexo'
      };

      return null
    }
  }

  /**
   *
   */
  linkValidator(): ValidatorFn {

    return (c: AbstractControl): { [key: string]: any } => {

      if (!c.value || !c.value.length) return {
        exception: 'Campo obrigatório'
      };

      if (c.value.indexOf('http') < 0) return {
        exception: 'Insira o protocolo (http ou https)'
      };


      if (!isURL(c.value)) return {
        exception: 'URL Inválida'
      };

      return null
    }
  }

  /**
   *
   * @param event
   */
  onFileChange(event) {
    const that = this;
    const fileList: FileList = event.target.files;

    if (fileList.length > 0) {
      if (fileList[0].size > 5242880) // Verifica se o arquiv tem mais que 5 megas
        return;
      // if (fileList[0].type !== 'application/pdf' && fileList[0].type !== 'image/jpg' && fileList[0].type !== 'image/jpeg' && fileList[0].type !== 'image/png')
      //   return;

      this.anexo.conteudo = fileList[0];
      this.anexo.type = this.anexo.conteudo.type;
      const reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = (/*arquivo: any*/) => {
        // this.anexo.caminho = arquivo.target.result;
        this.anexo.nome = this.anexo.conteudo.name;
        (that.form.controls[that.index] as FormGroup).controls['nome' + this.index].setErrors(null)
      }
    }
  }

  /**
   *
   */
  selectFile() {
    const element = this.element.nativeElement.querySelector('#inputField' + this.index);
    this.renderer.invokeElementMethod(element, 'click', [])
  }

  /**
   *
   * @param anexo: {Anexo}
   */
  public visualizarAnexo(anexo: Anexo) {
    this.dialogService.visualizarAnexo(anexo, 'Anexo')
  }

  /**
   * Evento disparado pelo select(mat-select) quando seu model é atualizado (ngModelChange).
   * Desta forma é possível a manipulação do dom antes que a view seja renderizada em sí.
   * Isso acontece desta forma porque não pode haver uma view apontando para uma validação de forma que ainda não exista no typescript.
   * Ou seja, o objeto form é atualizado antes do view form, e é assim que tem que ser.
   *
   * @param anexo: {Anexo}
   */
  selectModelChange(anexo: Anexo) {
    if (!anexo.externo)
      (this.form.get(this.index) as FormGroup).removeControl('link' + this.index);
    else
      (this.form.get(this.index) as FormGroup).addControl('link' + this.index, new FormControl('link' + this.index, this.linkValidator()));
  }

  /**
   * Evento disparado pelo select(mat-select) quando SUA VIEW é atualizada (change).
   * Essa função atualiza o objeto form para conseguinte atualização da view form, via bind.
   *
   * @param anexo: {Anexo}
   */
  selectChange(anexo) {
    (this.form.get(this.index) as FormGroup).get('nome' + this.index).markAsTouched();
    (this.form.get(this.index) as FormGroup).get('nome' + this.index).updateValueAndValidity()
  }

  /**
   * Remove o control quando o componente é destruído
   */
  ngOnDestroy() {
    this.form.removeControl(this.index)
  }
}
