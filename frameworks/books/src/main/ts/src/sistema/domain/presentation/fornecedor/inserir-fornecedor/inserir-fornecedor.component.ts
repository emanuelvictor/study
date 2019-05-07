import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup} from "@angular/forms"
import {MessageService} from "../../../services/message.service";
import {DashboardViewComponent} from "../../dashboard-view.component";
import {MatSnackBar, MatStepper} from "@angular/material";
import {Fornecedor} from "../../../entity/fornecedor/fornecedor.model";
import {Endereco} from "../../../entity/endereco/endereco.model";
import {FornecedorRepository} from "../../../repository/fornecedor.repository";

@Component({
  selector: 'inserir-fornecedor',
  templateUrl: './inserir-fornecedor.component.html',
  styleUrls: ['../fornecedor.component.scss']
})
export class InserirFornecedorComponent implements OnInit {

  /**
   *
   */
  fornecedor: Fornecedor;

  /**
   *
   */
  form: FormGroup = new FormGroup({
    dadosGeraisGroup: new FormGroup({}),
    contatosFormGroup: new FormGroup({}),
    dadosBancariosFormGroup: new FormGroup({}),
    documentosFormGroup: new FormGroup({})
  });

  /**
   *
   */
  @ViewChild('stepper') stepper: MatStepper;

  /**
   *
   * @param _formBuilder
   * @param activatedRoute
   * @param messageService
   * @param homeView
   * @param fornecedorRepository
   * @param router
   * @param snackBar
   */
  constructor(private _formBuilder: FormBuilder,
              private activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private homeView: DashboardViewComponent,
              private fornecedorRepository: FornecedorRepository,
              private router: Router, private snackBar: MatSnackBar) {

  }

  ngOnInit() {
    this.fornecedor = new Fornecedor();
    if (!this.fornecedor.endereco)
      this.fornecedor.endereco = new Endereco('', '', '', '', '', null, 0, 0)
  }

  /**
   *
   * @param fornecedor
   */
  public save(fornecedor: Fornecedor) {
    fornecedor.documentos = fornecedor.documentos.filter(value => value.nome);
    this.fornecedorRepository.save(fornecedor).then(() => {
      this.openSnackBar('Registro inserido com sucesso!');
      this.router.navigate(['fornecedores'])
    });
  }

  /**
   *
   * @param message
   */
  public error(message: string) {
    this.openSnackBar(message);
  }

  /**
   *
   * @param message
   */
  public openSnackBar(message: string) {
    this.snackBar.open(message, 'Fechar', {
      duration: 5000
    });
  }

}
