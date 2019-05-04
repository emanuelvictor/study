import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {Fornecedor} from "../entity/fornecedor/fornecedor.model";
import {FileRepository} from "../../application/upload-file-repository/file.repository";

@Injectable()
export class FornecedorRepository extends BaseRepository<Fornecedor> {

  /**
   *
   * @param httpClient
   * @param fileRepository
   */
  constructor(httpClient: HttpClient, fileRepository: FileRepository) {
    super(httpClient, 'fornecedores', fileRepository);
  }

  /**
   *
   * @param fornecedor
   * @param recap
   */
  save(fornecedor: Fornecedor, recap?: string): Promise<Fornecedor> {
    if (recap)
      return this.httpClient.post<Fornecedor>(this.collectionName + '/' + recap, fornecedor).toPromise();
    else
      return super.saveWithAnexos(fornecedor);
  }

  /**
   *
   * @param fornecedor
   */
  aprovar(fornecedor: any): Promise<Fornecedor> {
    return this.httpClient.put<Fornecedor>(this.collectionName + '/' + fornecedor.id + '/aprovar', fornecedor).toPromise();
  }

  /**
   *
   * @param fornecedor
   */
  recusar(fornecedor: any): Promise<Fornecedor> {
    return this.httpClient.put<Fornecedor>(this.collectionName + '/' + fornecedor.id + '/recusar', fornecedor).toPromise();
  }

  sendToApprove(fornecedor: Fornecedor) {
    return this.httpClient.put<Fornecedor>(this.collectionName + '/' + fornecedor.id + '/sendToApprove', fornecedor).toPromise();
  }
}
