import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';
import {enableProdMode} from '@angular/core';

import {PortalModule} from '../domain/portal.module';
import {environment} from '../../environments/environment';

import 'hammerjs';

if (environment.production) {
    enableProdMode();
}

platformBrowserDynamic().bootstrapModule(PortalModule)
    .catch(err => console.log(err));
