import {Component} from '@angular/core';
import {DefaultCanActivate} from "../../../has-permission/default-can-activate";
import {AuthenticationService} from "../../../../domain/services/authentication.service";
import {Router} from "@angular/router";
import {PatrimonioRepository} from "../../../../domain/repository/patrimonio.repository";
import {HttpClient} from "@angular/common/http";

// @ts-ignore
@Component({
  selector: 'bens-view',
  templateUrl: './bens-view.component.html',
  styleUrls: ['./bens-view.component.scss']
})
export class BensViewComponent extends DefaultCanActivate {

  /**
   *
   * @param router
   * @param httpClient
   * @param patrimonioRepository
   * @param authenticationService
   */
  constructor(router: Router, private httpClient: HttpClient,
              private patrimonioRepository: PatrimonioRepository,
              public authenticationService: AuthenticationService) {

    super(authenticationService, router);

    this.fallbackRoute = 'minha-conta';

    this.permissions = ['root', 'inventarios', 'inventarios/get']

  }

  /**
   *
   */
  export() {

    const anexo : any = {};
    window.open('api/patrimonios/export?nocache=' + Math.floor(Math.random() * 2000).toString(), '_blank');
    // this.httpClient.get('api/patrimonios/export?nocache=' + Math.floor(Math.random() * 2000).toString(), {responseType: 'blob'})
    //   .subscribe(result => {
    //
    //     const blob = new Blob([result], {type: 'csv'});
    //     anexo.conteudo = new File([blob], 'export', {type: result.type});
    //     const reader = new FileReader();
    //     reader.readAsDataURL(blob);
    //     // reader.onload = () => this.anexo.caminho = reader.result.toString()
    //
    //     window.open(window.URL.createObjectURL(anexo.conteudo), '_blank')
    //   })
  }
}
