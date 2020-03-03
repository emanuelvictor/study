import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormBuilder, FormGroup} from '@angular/forms';
import {textMasks} from 'sistema/application/controls/text-masks/text-masks';
import {Fornecedor} from 'sistema/domain/entity/fornecedor/fornecedor.model';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../../../../../../sistema/domain/services/authentication.service";
import {MessageService} from "../../../../../../../sistema/domain/services/message.service";
import {environment} from "../../../../../../../environments/environment";

@Component({
  selector: 'login-fornecedor',
  templateUrl: './login-fornecedor.component.html',
  styleUrls: ['./login-fornecedor.component.scss']
})
export class LoginFornecedorComponent implements OnInit {

  masks = textMasks;

  mask: any = textMasks.cnpj;

  url: string = 'https://www.receitaws.com.br/v1/cnpj/';

  fornecedor: Fornecedor = new Fornecedor();

  form: FormGroup;

  realizado: boolean = false;

  constructor(private http: HttpClient,
              public messageService: MessageService,
              private router: Router, private fb: FormBuilder,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.realizado = window.location.href.indexOf('realizado') > 0;

    this.form = this.fb.group({
      cnpj: ['cnpj', []],
      senha: ['', []],
    });
  }

  toSistema() {
    if (environment.production)
      window.location.href = window.location.origin + '/sistema/';
    else window.location.href = 'http://localhost:4200'
  }

  /**
   *
   */
  public login() {
    this.authenticationService.login(this.fornecedor.usuario)
      .then((result) => {
        localStorage.setItem('curUser', result.nome);
        this.authenticationService.requestContaAutenticada()
          .subscribe(authenticated => {
            this.fornecedor.usuario = authenticated;
            this.authenticationService.usuarioAutenticado = this.fornecedor.usuario;
            this.toSistema()
          });
      });
  }

}
