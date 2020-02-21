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
import {DomSanitizer} from "@angular/platform-browser";
import {AnexoEmail} from "../../../../../../entity/fornecedor/email/anexo-email.model";
import {CrudViewComponent} from "../../../../../../../application/controls/crud/crud-view.component";
import {DialogService} from "../../../../../../services/dialog.service";
import {FileRepository} from "../../../../../../../application/upload-file-repository/file.repository";

@Component({
  selector: 'anexo-email',
  templateUrl: 'anexo-email.component.html',
  styleUrls: ['anexo-email.component.scss'],
})
export class AnexoEmailComponent extends CrudViewComponent implements OnInit, OnDestroy {

  /**
   *
   */
  @Input() form: FormGroup;

  /**
   *
   */
  index = Math.floor(Math.random() * 2000).toString();

  /**
   * Serve para validar os anexosEmail repetidos
   */
  @Input() anexosEmail: AnexoEmail[];

  /**
   *
   */
  @Input() anexoEmail: AnexoEmail;

  /**
   *
   */
  @Output() anexoEmailChange: EventEmitter<AnexoEmail> = new EventEmitter();

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

    (this.anexoEmail as any).index = this.index;

    this.form.addControl(this.index, new FormGroup({
      ['nome' + this.index]: new FormControl('nome' + this.index, this.checkNomeValidator(this.anexosEmail))
    }));

    if (!this.anexoEmail)
      this.anexoEmail = new AnexoEmail();

    else if (this.anexoEmail.caminho)
      this.fileRepository.findOne('api/' + this.anexoEmail.caminho + '?nocache=' + Math.floor(Math.random() * 2000).toString())
        .subscribe(result => {

          const blob = new Blob([result], {type: this.anexoEmail.type});
          this.anexoEmail.conteudo = new File([blob], this.anexoEmail.nome, {type: result.type});
          this.selectChange();
          const reader = new FileReader();
          reader.readAsDataURL(blob);
          // reader.onload = () => this.anexoEmail.caminho = reader.result.toString()

        })
  }

  /**
   *
   * @param anexosEmail {AnexoEmail[]}
   */
  checkNomeValidator(anexosEmail: AnexoEmail[]): ValidatorFn {

    return (c: AbstractControl): { [key: string]: any } => {
      if (anexosEmail.filter(anexo => anexo.nome === c.value && (this.anexoEmail as any).index !== (anexo as any).index).length > 0) return {
        exception: 'Nome já cadastrado'
      };

      if (!c.value || !c.value.length) return {
        exception: 'Campo obrigatório'
      };

      if (!this.anexoEmail.conteudo || !this.anexoEmail.conteudo.name) return {
        exception: 'Insira um anexoEmail'
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

      this.anexoEmail.conteudo = fileList[0];
      this.anexoEmail.type = this.anexoEmail.conteudo.type;
      const reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = () => {
        // this.anexoEmail.caminho = arquivo.target.result;
        this.anexoEmail.nome = this.anexoEmail.conteudo.name;
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
   * @param anexoEmail: {AnexoEmail}
   */
  public visualizarAnexo(anexoEmail: AnexoEmail) {
    this.dialogService.visualizarAnexoEmail(anexoEmail, 'Anexo')
  }

  // /**
  //  * Evento disparado pelo select(mat-select) quando seu model é atualizado (ngModelChange).
  //  * Desta forma é possível a manipulação do dom antes que a view seja renderizada em sí.
  //  * Isso acontece desta forma porque não pode haver uma view apontando para uma validação de forma que ainda não exista no typescript.
  //  * Ou seja, o objeto form é atualizado antes do view form, e é assim que tem que ser.
  //  *
  //  */
  // selectModelChange() {
  //   (this.form.get(this.index) as FormGroup).removeControl('link' + this.index);
  // }

  /**
   * Evento disparado pelo select(mat-select) quando SUA VIEW é atualizada (change).
   * Essa função atualiza o objeto form para conseguinte atualização da view form, via bind.
   *
   */
  selectChange() {
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
