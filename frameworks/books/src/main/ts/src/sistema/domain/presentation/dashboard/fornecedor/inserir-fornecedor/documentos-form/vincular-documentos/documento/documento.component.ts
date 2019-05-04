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
import {FileRepository} from "../../../../../../../../application/upload-file-repository/file.repository";
import {DomSanitizer} from "@angular/platform-browser";
import {DialogService} from "../../../../../../../services/dialog.service";
import {Documento} from 'sistema/domain/entity/fornecedor/documento.model';
import {obrigatorio} from "../../../../../../../../application/controls/validators/validators";
import {TdLoadingService} from "@covalent/core";

@Component({
  selector: 'documento',
  templateUrl: 'documento.component.html',
  styleUrls: ['documento.component.scss'],
})
export class DocumentoComponent implements OnInit, OnDestroy {

  /**
   *
   */
  @Input() form: FormGroup;

  /**
   *
   */
  @Input() documento: Documento;

  /**
   *
   */
  @Output() documentoChange: EventEmitter<Documento> = new EventEmitter();

  /**
   *
   * Identificador da foto "person"
   */
  public identifier: string;

  /**
   *
   * @param snackBar
   * @param cd
   * @param adapter
   * @param _sanitizer
   * @param dialogService
   * @param activatedRoute
   * @param fileRepository
   * @param _loadingService
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
              private _loadingService: TdLoadingService,
              @Inject(ElementRef) public element: ElementRef,
              public fb: FormBuilder, public renderer: Renderer) {

  }

  /**
   *
   */
  ngOnInit() {
    this.identifier = Math.floor(Math.random() * 2000).toString();

    this._loadingService.register(this.identifier);
    let value: number = 10;
    const interval = setInterval(() => {
      this._loadingService.setValue(this.identifier, value);
      value = value + 10;
    }, 0);

    if (!this.documento || !this.documento.caminho) {
      this.form.addControl('nome' + this.documento.tipoCadastroTipoDocumento.id, new FormControl('nome' + this.documento.tipoCadastroTipoDocumento.id, this.documento.tipoCadastroTipoDocumento.obrigatorio ? obrigatorio(null, this.anexoObrigatorio('O anexo é obrigatório')) : null))
      this._loadingService.resolve(this.identifier);
      this._loadingService.resolve('carregando-documentos');
      clearInterval(interval);
    } else if (this.documento.caminho)
      this.fileRepository.findOne('api/' + this.documento.caminho + '?nocache=' + Math.floor(Math.random() * 2000).toString())
        .subscribe(result => {
          const blob = new Blob([result], {type: this.documento.type});
          this.documento.conteudo = new File([blob], this.documento.nome, {type: result.type});

          const reader = new FileReader();
          reader.readAsDataURL(blob);
          // reader.onload = () => this.documento.caminho = reader.result.toString();

          this.form.addControl('nome' + this.documento.tipoCadastroTipoDocumento.id, new FormControl('nome' + this.documento.tipoCadastroTipoDocumento.id, this.documento.tipoCadastroTipoDocumento.obrigatorio ? obrigatorio(null, this.anexoObrigatorio('O anexo é obrigatório')) : null))

          this._loadingService.resolve(this.identifier);
          this._loadingService.resolve('carregando-documentos');
          clearInterval(interval);
        })
  }

  /**
   *
   * @param exception
   */
  anexoObrigatorio(exception?: string): ValidatorFn {

    return (c: AbstractControl): { [key: string]: any } => {

      if (!c.value || !c.value.length) return {
        exception: 'Campo obrigatório'
      };

      if (!this.documento.nome || !this.documento.conteudo || !this.documento.conteudo.name) return {
        exception: exception ? exception : 'O anexo é obrigatório'
      };

      return null;
    }
  }

  /**
   *
   * @param event
   */
  onFileChange(event) {
    const fileList: FileList = event.target.files;

    if (fileList.length > 0) {
      if (fileList[0].type !== 'application/pdf' && fileList[0].type !== 'image/jpg' && fileList[0].type !== 'image/jpeg' && fileList[0].type !== 'image/png')
        return;
      this.documento.conteudo = fileList[0];
      this.documento.type = this.documento.conteudo.type;
      console.log(this.documento.type);
      const reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = (arquivo: any) => {
        this.documento.caminho = arquivo.target.result;
        if (!this.documento.nome)
          this.documento.nome = this.documento.conteudo.name;
        this.form.controls['nome' + this.documento.tipoCadastroTipoDocumento.id].setErrors(null);
      };
    }
  }

  /**
   *
   */
  selectFile() {
    const element = this.element.nativeElement.querySelector('#inputField' + this.documento.tipoCadastroTipoDocumento.id);
    this.renderer.invokeElementMethod(element, 'click', []);
  }

  /**
   *
   */
  verifyNome() {
    // if (!this.documento.conteudo.name)
    //   (this.form.controls['nome' + this.documento.tipoCadastroTipoDocumento.tipoDocumento.id] as FormGroup).setErrors({error: "Insira um documento"});
  }

  /**
   *
   * @param documento
   */
  public visualizarDocumento(documento: Documento) {
    this.dialogService.visualizarDocumento(documento, 'Documento');
  }

  /**
   * Remove o control quando o componente é destruído
   */
  ngOnDestroy() {
    this.form.removeControl('nome' + this.documento.tipoCadastroTipoDocumento.id);
  }

}
