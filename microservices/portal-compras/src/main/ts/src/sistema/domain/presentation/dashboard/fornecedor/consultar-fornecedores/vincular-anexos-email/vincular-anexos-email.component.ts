import {Component, EventEmitter, Input, OnInit, Output,} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms"
import {AnexoEmail} from "../../../../../entity/fornecedor/email/anexo-email.model";

@Component({
  selector: 'vincular-anexos-email',
  templateUrl: './vincular-anexos-email.component.html',
  styleUrls: ['./vincular-anexos-email.component.scss']
})
export class VincularAnexosEmailComponent implements OnInit {

  /**
   *
   */
  @Input()
  form: FormGroup;

  /**
   *
   */
  @Input()
  anexosEmail: AnexoEmail[] = [];

  /**
   *
   */
  @Output()
  anexosEmailChange: EventEmitter<any> = new EventEmitter<any>();

  /**
   *
   */
  @Output()
  remove: EventEmitter<number> = new EventEmitter<number>();

  /**
   *
   * @param fb
   */
  constructor(private fb: FormBuilder) {
  }

  /**
   *
   */
  ngOnInit() {
    if (!this.form) {
      this.form = this.fb.group({});
    }
  }

  /**
   *
   */
  addAnexoEmail() {
    this.anexosEmail.push(new AnexoEmail());
  }

  /**
   *
   * @param index
   */
  removeAnexoEmail(index) {
    this.remove.emit(index);
  }

}
