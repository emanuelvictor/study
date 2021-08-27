import {Component, ElementRef, Inject, Input, OnInit, Renderer} from '@angular/core';
import {CrudViewComponent} from "../../../../../../application/controls/crud/crud-view.component";
import {FormBuilder, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material";
import {ActivatedRoute} from "@angular/router";
import {EditoraRepository} from "../../../../../repository/editora.repository";
import {debounce} from "../../../../../../application/utils/debounce";

@Component({
  selector: 'livro-form',
  templateUrl: 'livro-form.component.html',
  styleUrls: ['../../livro.component.scss']
})
export class LivroFormComponent extends CrudViewComponent implements OnInit {


  /**
   *
   */
  editoras: any = [];

  // /**
  //  *
  //  */
  // @Input()
  // editora: Editora = new Editora();
  //
  // /**
  //  *
  //  */
  // @Output()
  // public editoraChange = new EventEmitter();

  errorr: boolean;

  public debounce = debounce;

  // Armazena o link para voltar para a tela de consulta
  @Input() backLink: string;

  /**
   *
   * @param snackBar
   * @param activatedRoute
   * @param editoraRepository
   * @param element
   * @param fb
   * @param renderer
   */
  constructor(public snackBar: MatSnackBar,
              public activatedRoute: ActivatedRoute,
              private editoraRepository: EditoraRepository,
              @Inject(ElementRef) public element: ElementRef,
              public fb: FormBuilder, public renderer: Renderer) {

    super(snackBar, element, fb, renderer, activatedRoute);

  }

  /**
   *
   */
  ngOnInit() {
    this.entity.ativo = true;
    this.form = this.fb.group({
      nome: ['nome', [Validators.required ]],
      editora: ['editora', [Validators.required ]]
    });

    this.handleSubhead();
  }

  /**
   *
   */
  handleSubhead = () => this.subhead = this.entity && this.entity.id ? `${this.subhead} / Editar` : `${this.subhead} / Adicionar`;

  /**
   * Exibe organizações para o autocomplete
   */
  public listEditoras() {
    let nomeEditora = this.entity.editora || null;
    if (this.isString(nomeEditora) && !(nomeEditora as any).length) {
      this.editoras = [];
      return;
    }

    this.editoraRepository.listByFilters({defaultFilter: this.entity.editora})
      .subscribe((result) => {
        this.editoras = result.content;
      });
  }

  /**
   * Expressão específica para autocomplete
   */
  public displayNomeEditora(editora) {
    return editora && editora.nome ? editora.nome : null;
  }

  /**
   * Helper
   * @param value
   */
  public isString(value): boolean {
    return typeof value === 'string';
  }

  /**
   *
   */
  public normalizeEditora() {
    if (this.isString(this.entity.editora)) {
      this.errorr = false;
      this.entity.editora = null;
    }
  }
}
