import {Component, ElementRef, Inject, Input, OnDestroy, OnInit, Renderer} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef, MatSnackBar} from '@angular/material';
import {FileSystemDirectoryEntry, FileSystemFileEntry, UploadEvent, UploadFile} from "ngx-file-drop";
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {Anexo} from "../../../domain/entity/publicacao/anexo.model";
import {DomSanitizer} from "@angular/platform-browser";
import {CrudViewComponent} from "../crud/crud-view.component";
import {ActivatedRoute} from "@angular/router";

/**
 *
 */
@Component({
  selector: 'anexo-dialog',
  templateUrl: 'anexo-dialog.component.html',
  styleUrls: ['./anexo-dialog.component.scss']
})
export class AnexoDialogComponent extends CrudViewComponent implements OnInit, OnDestroy {

  /**
   *
   */
  public files: UploadFile[] = [];

  /**
   *
   */
  @Input() anexo: Anexo = new Anexo();

  /**
   *
   */
  index = Math.floor(Math.random() * 2000).toString();

  /**
   * @type {string}
   */
  url: any;

  /**
   *
   * @param activatedRoute
   * @param data
   * @param element
   * @param dialogRef
   * @param renderer
   * @param snackBar
   * @param fb
   * @param _sanitizer
   */
  constructor(public activatedRoute: ActivatedRoute,
              @Inject(MAT_DIALOG_DATA) public data: any,
              @Inject(ElementRef) public element: ElementRef,
              private dialogRef: MatDialogRef<AnexoDialogComponent>,
              public renderer: Renderer, public snackBar: MatSnackBar,
              public fb: FormBuilder, private _sanitizer: DomSanitizer) {

    super(snackBar, element, fb, renderer, activatedRoute);

  }

  /**
   *
   */
  ngOnInit() {
    this.form = this.fb.group({
      ['nome' + this.index]: new FormControl('nome' + this.index, Validators.required)
    });
  }

  /**
   *
   * @param event
   */
  public dropped(event: UploadEvent) {

    this.files = event.files;
    for (const droppedFile of event.files) {

      // Is it a file?
      if (droppedFile.fileEntry.isFile) {
        const fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
        fileEntry.file((file: File) => {

          this.anexo.conteudo = file;

          if (this.anexo.conteudo.type !== 'application/pdf') {
            this.removeAnexo()
          } else {
            if (!this.anexo.nome)
              this.anexo.nome = this.anexo.conteudo.name;
            this.form.controls['nome' + this.index].setErrors(null);

            this.parseUrl()
          }

        });
      } else {
        // It was a directory (empty directories are added, otherwise only files)
        const fileEntry = droppedFile.fileEntry as FileSystemDirectoryEntry;
      }
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
      if (fileList[0].type !== 'application/pdf')
        return;
      this.anexo.conteudo = fileList[0];
      const reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = (arquivo: any) => {
        this.anexo.caminho = arquivo.target.result;
        if (!this.anexo.nome)
          this.anexo.nome = this.anexo.conteudo.name;
        that.form.controls['nome' + this.index].setErrors(null);

        this.parseUrl()
      };
    }
  }

  /**
   *
   */
  parseUrl() {
    const urlCreator = window.URL;
    this.url = (this._sanitizer.bypassSecurityTrustResourceUrl(urlCreator.createObjectURL(this.anexo.conteudo)) as any);
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
   * @param entity
   */
  emit(entity: any): void {
    this.dialogRef.close(this.anexo)
  }

  /**
   *
   */
  verifyNome() {
    if (!this.anexo.conteudo.name)
      this.form.controls['nome' + this.index].setErrors({error: "Insira um anexo"});
  }

  /**
   *
   */
  removeAnexo() {
    this.anexo = new Anexo();
    this.url = null
  }

  /**
   * Remove o control quando o componente é destruído
   */
  ngOnDestroy() {
    this.form.removeControl(this.index);
  }
}
