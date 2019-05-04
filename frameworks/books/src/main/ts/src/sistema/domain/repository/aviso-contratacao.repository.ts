import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {AvisoContratacao} from "../entity/publicacao/aviso-contratacao.model";
import {FileRepository} from "../../application/upload-file-repository/file.repository";

@Injectable()
export class AvisoContratacaoRepository extends BaseRepository<AvisoContratacao> {

  /**
   *
   * @param httpClient
   * @param fileRepository
   */
  constructor(httpClient: HttpClient, fileRepository: FileRepository) {
    super(httpClient, 'avisos-contratacoes', fileRepository);
  }

}
