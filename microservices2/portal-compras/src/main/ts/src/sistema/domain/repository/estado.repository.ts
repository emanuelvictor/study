import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {ExistingSansProvider} from '@angular/core/src/di/provider';

@Injectable()
export class PaisRepository extends BaseRepository<ExistingSansProvider> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'estados');
  }

}
