import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {TipoDocumento} from "../entity/tipo-documento.model";

@Injectable()
export class TipoDocumentoRepository extends BaseRepository<TipoDocumento> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'tipos-documentos');
  }

}
