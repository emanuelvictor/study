import {
  Component,
  ElementRef,
  EventEmitter,
  Inject,
  Input,
  IterableDiffers,
  OnInit,
  Output,
  Renderer,
  ViewChild
} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatChipInputEvent, MatSnackBar} from '@angular/material';
import {AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms"
import 'rxjs/add/operator/debounceTime';
import {MessageService} from "../../../../../services/message.service";
import {DashboardViewComponent} from "../../../dashboard-view.component";
import {Pais} from 'sistema/domain/entity/endereco/pais.model';
import {EnderecoRepository} from 'sistema/domain/repository/endereco.repository';
import {Estado} from 'sistema/domain/entity/endereco/estado.model';
import {Cidade} from 'sistema/domain/entity/endereco/cidade.model';
import {Fornecedor} from 'sistema/domain/entity/fornecedor/fornecedor.model';
import {textMasks} from 'sistema/application/controls/text-masks/text-masks';
import {HttpClient} from '@angular/common/http';
import {obrigatorio} from "../../../../../../application/controls/validators/validators";

@Component({
  selector: 'feedback-form',
  templateUrl: './feedback-form.component.html',
  styleUrls: ['../../fornecedor.component.scss']
})
export class FeedbackFormComponent {

  @Input()
  entity: Fornecedor;

}

