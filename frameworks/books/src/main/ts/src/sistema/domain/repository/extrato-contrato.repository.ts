import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {ExtratoContratacao} from "../entity/publicacao/extrato-contratacao.model";
import {FileRepository} from "../../application/upload-file-repository/file.repository";

@Injectable()
export class ExtratoContratoRepository extends BaseRepository<ExtratoContratacao> {

  /**
   *
   * @param httpClient
   * @param fileRepository
   */
  constructor(httpClient: HttpClient, fileRepository: FileRepository) {
    super(httpClient, 'extratos-contratos', fileRepository);
  }

}
