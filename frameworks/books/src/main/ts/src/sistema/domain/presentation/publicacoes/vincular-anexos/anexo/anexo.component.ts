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
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {DateAdapter, MatSnackBar} from "@angular/material";
import {ActivatedRoute} from "@angular/router";
import {CrudViewComponent} from "../../../../../application/controls/crud/crud-view.component";
import {Anexo} from "../../../../entity/publicacao/anexo.model";
import {FileRepository} from "../../../../../application/upload-file-repository/file.repository";
import {DomSanitizer} from "@angular/platform-browser";
import {DialogService} from "../../../../services/dialog.service";

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
  @Input() anexo: Anexo;

  /**
   *
   */
  index = Math.floor(Math.random() * 2000).toString();

  /**
   *
   */
  @Output() anexoChange: EventEmitter<Anexo> = new EventEmitter();

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

    this.form.addControl(this.index, new FormGroup({
      ['nome' + this.index]: new FormControl('nome' + this.index, Validators.required)
      // ,['file' + this.index]: new FormControl({value: 'file' + this.index, disabled: false}, Validators.required)
    }));

    if (!this.anexo)
      this.anexo = new Anexo();

    else if (this.anexo.caminho)
      this.fileRepository.findOne('api/' + this.anexo.caminho + '?nocache=' + Math.floor(Math.random() * 2000).toString())
        .subscribe(result => {

          const blob = new Blob([result], {type: "application/pdf"});
          this.anexo.conteudo = new File([blob], this.anexo.nome, {type: "application/pdf"});

          const reader = new FileReader();
          reader.readAsDataURL(blob);
          // reader.onload = () => this.anexo.caminho = reader.result.toString()

        })
  }

  /**
   *
   * @param event
   */
  onFileChange(event) {
    const that = this;
    const fileList: FileList = event.target.files;

    if (fileList.length > 0) {
      if (fileList[0].type !== 'application/pdf')
        return;
      this.anexo.conteudo = fileList[0];
      const reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = (arquivo: any) => {
        this.anexo.caminho = arquivo.target.result;
        if (!this.anexo.nome)
          this.anexo.nome = this.anexo.conteudo.name;
        (that.form.controls[that.index] as FormGroup).controls['nome' + this.index].setErrors(null);
      };
    }
  }

  /**
   *
   */
  selectFile() {
    const element = this.element.nativeElement.querySelector('#inputField' + this.index);
    this.renderer.invokeElementMethod(element, 'click', []);
  }

  /**
   *
   */
  verifyNome() {
    if (!this.anexo.conteudo.name)
      (this.form.controls[this.index] as FormGroup).controls['nome' + this.index].setErrors({error: "Insira um anexo"});
  }

  /**
   *
   * @param anexo
   */
  public visualizarAnexo(anexo: Anexo) {
    this.dialogService.visualizarAnexo(anexo, 'Anexo');
  }

  /**
   * Remove o control quando o componente é destruído
   */
  ngOnDestroy() {
    this.form.removeControl(this.index);
  }
}
