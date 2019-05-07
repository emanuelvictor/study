import {Component} from '@angular/core';
import {TdLoadingService} from '@covalent/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatDialog} from '@angular/material';
import {MessageService} from '../services/message.service';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'dashboard-view',
  templateUrl: './dashboard-view.component.html',
  styleUrls: ['./dashboard-view.component.scss']
})
export class DashboardViewComponent {

  /**
   *
   */
  public toolbar: any = {headline: 'Cadastros', subhead: ''};

  /**
   *
   * @param translate
   * @param activeRoute
   * @param messageService
   * @param loadingService
   * @param dialog
   * @param router
   */
  constructor(private translate: TranslateService,
              private activeRoute: ActivatedRoute,
              private messageService: MessageService,
              private loadingService: TdLoadingService,
              private dialog: MatDialog, private router: Router) {

    // this language will be used as a fallback when a translation isn't found in the current language
    translate.setDefaultLang('pt-br');

    // the lang to use, if the lang isn't available, it will use the current loader to get them
    translate.use('pt-br');

  }

}
