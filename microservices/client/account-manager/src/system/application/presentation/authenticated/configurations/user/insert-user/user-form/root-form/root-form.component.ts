import {Component, ElementRef, EventEmitter, Inject, Input, OnDestroy, OnInit, Output, Renderer} from '@angular/core';
import {debounce} from "../../../../../../../utils/debounce";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms"
import {AccessGroupRepository} from "../../../../../../../../domain/repository/access-group.repository";
import {AccessGroup} from "../../../../../../../../domain/entity/access-group.model";

// @ts-ignore
@Component({
  selector: 'root-form',
  templateUrl: './root-form.component.html',
  styleUrls: ['../../../user.component.scss']
})
export class RootFormComponent implements OnInit, OnDestroy {

  /**
   *
   */
  accessGroups: any = [];

  /**
   *
   */
  @Input()
  accessGroup: AccessGroup = new AccessGroup();

  /**
   *
   */
  @Output()
  public accessGroupChange = new EventEmitter();

  error: boolean;

  public debounce = debounce;

  @Input()
  form: FormGroup;

  /**
   *
   * @param element
   * @param fb
   * @param renderer
   * @param accessGroupRepository
   */
  constructor(@Inject(ElementRef) private element: ElementRef,
              private fb: FormBuilder, private renderer: Renderer,
              private accessGroupRepository: AccessGroupRepository) {

  }

  /**
   *
   */
  ngOnInit() {

    const formGroup = new FormGroup({
      accessGroup: new FormControl('accessGroup', [Validators.required]),
    });

    if (!this.form) {
      this.form = this.fb.group({});
    }

    this.form.addControl('accessGroup', formGroup);

  }

  /**
   * Exibe organizações para o autocomplete
   */
  public listAccessGroups() {
    let nameAccessGroup = this.accessGroup || null;
    if (this.isString(nameAccessGroup) && !(nameAccessGroup as any).length) {
      this.accessGroups = [];
      return;
    }

    this.accessGroupRepository.listByFilters({defaultFilter: this.accessGroup, ativoFilter: true})
      .subscribe((result) => {
        this.accessGroups = result.content;
      });
  }

  /**
   * Expressão específica para autocomplete
   */
  public displayNameAccessGroup(accessGroup) {
    return accessGroup && accessGroup.name ? accessGroup.name : null;
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
  public normalizeAccessGroup() {
    if (this.isString(this.accessGroup)) {
      this.error = false;
      this.accessGroup = null;
    }
  }

  /**
   *
   */
  ngOnDestroy(): void {
    /**
     * Remove o control quando o componente é destruído
     */
    this.form.removeControl('accessGroup');
  }

}
