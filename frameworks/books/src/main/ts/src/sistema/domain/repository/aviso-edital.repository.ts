import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {AvisoEdital} from "../entity/publicacao/aviso-edital.model";
import {FileRepository} from "../../application/upload-file-repository/file.repository";

@Injectable()
export class AvisoEditalRepository extends BaseRepository<AvisoEdital> {

  /**
   *
   * @param httpClient
   * @param fileRepository
   */
  constructor(httpClient: HttpClient, fileRepository: FileRepository) {
    super(httpClient, 'avisos-editais', fileRepository);
  }

}
