import {Component, ElementRef, EventEmitter, Inject, Input, OnDestroy, OnInit, Output, Renderer} from '@angular/core';
import {debounce} from "../../../../../../../application/utils/debounce";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms"
import {GrupoAcessoRepository} from "../../../../../../repository/grupo-acesso.repository";
import {GrupoAcesso} from "../../../../../../entity/grupo-acesso.model";

@Component({
  selector: 'administrador-form',
  templateUrl: './administrador-form.component.html',
  styleUrls: ['../../../usuario.component.scss']
})
export class AdministradorFormComponent implements OnInit, OnDestroy {

  /**
   *
   */
  gruposAcesso: any = [];

  /**
   *
   */
  @Input()
  grupoAcesso: GrupoAcesso = new GrupoAcesso();

  /**
   *
   */
  @Output()
  public grupoAcessoChange = new EventEmitter();

  error: boolean;

  public debounce = debounce;

  @Input()
  form: FormGroup;

  /**
   *
   * @param element
   * @param fb
   * @param renderer
   * @param grupoAcessoRepository
   */
  constructor(@Inject(ElementRef) private element: ElementRef,
              private fb: FormBuilder, private renderer: Renderer,
              private grupoAcessoRepository: GrupoAcessoRepository) {

  }

  /**
   *
   */
  ngOnInit() {

    const formGroup = new FormGroup({
      grupoAcesso: new FormControl('grupoAcesso', [Validators.required]),
    });

    if (!this.form) {
      this.form = this.fb.group({});
    }

    this.form.addControl('grupoAcesso', formGroup);

  }

  /**
   * Exibe organizações para o autocomplete
   */
  public listGruposAcesso() {
    let nomeGrupoAcesso = this.grupoAcesso || null;
    if (this.isString(nomeGrupoAcesso) && !(nomeGrupoAcesso as any).length) {
      this.gruposAcesso = [];
      return;
    }

    this.grupoAcessoRepository.listByFilters({defaultFilter: this.grupoAcesso, ativoFilter: true})
      .subscribe((result) => {
        this.gruposAcesso = result.content;
      });
  }

  /**
   * Expressão específica para autocomplete
   */
  public displayNomeGrupoAcesso(grupoAcesso) {
    return grupoAcesso && grupoAcesso.nome ? grupoAcesso.nome : null;
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
  public normalizeGrupoAcesso() {
    if (this.isString(this.grupoAcesso)) {
      this.error = false;
      this.grupoAcesso = null;
    }
  }

  /**
   *
   */
  ngOnDestroy(): void {
    /**
     * Remove o control quando o componente é destruído
     */
    this.form.removeControl('grupoAcesso');
  }

}
