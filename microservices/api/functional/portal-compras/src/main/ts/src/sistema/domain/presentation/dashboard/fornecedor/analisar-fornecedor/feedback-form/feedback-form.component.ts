import {Component, Input, OnInit} from '@angular/core';
import 'rxjs/add/operator/debounceTime';
import {Fornecedor} from 'sistema/domain/entity/fornecedor/fornecedor.model';
import {FormBuilder, FormGroup} from "@angular/forms";
import {StatusFornecedor} from "../../../../../entity/fornecedor/status-fornecedor.enum";

@Component({
  selector: 'feedback-form',
  templateUrl: './feedback-form.component.html',
  styleUrls: ['../../fornecedor.component.scss']
})
export class FeedbackFormComponent implements OnInit {

  /**
   *
   */
  @Input()
  entity: Fornecedor;

  /**
   *
   */
  form: FormGroup;

  /**
   *
   * @param fb
   */
  constructor(public fb: FormBuilder) {
  }

  /**
   *
   */
  ngOnInit(): void {
    if (!this.form) {
      this.form = this.fb.group({});
    }

    const formGroup = this.fb.group({
      feedback: [],
    });

    if (StatusFornecedor[this.entity.status] === (StatusFornecedor.APROVADO as any))
      formGroup.disable();

    this.form.addControl('feedback', formGroup)
  }

}

