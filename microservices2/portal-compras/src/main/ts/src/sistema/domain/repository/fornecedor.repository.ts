import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {Fornecedor} from "../entity/fornecedor/fornecedor.model";
import {FileRepository} from "../../application/upload-file-repository/file.repository";
import {Email} from "../entity/fornecedor/email/email.model";
import {Observable} from "rxjs";

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
    if (fornecedor.razaoSocial && !fornecedor.razaoSocial.length)
      delete fornecedor.razaoSocial;
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

  /**
   *
   * @param fornecedor
   */
  sendToApprove(fornecedor: Fornecedor) {
    return this.httpClient.put<Fornecedor>(this.collectionName + '/' + fornecedor.id + '/sendToApprove', fornecedor).toPromise();
  }

  /**
   *
   * @param email
   */
  sendMassMail(email: Email): Promise<any> {

    let queryString: string = '?assunto=' + email.assunto + '&conteudo=' + email.conteudo;

    if (email.status)
      queryString = queryString + '&status=' + email.status;

    if (email.categorias.length)
      queryString = queryString + '&categorias=' + email.categorias.map(categoria => categoria.id);

    if (email.anexosEmail.length) {
      const formData: FormData = new FormData();
      queryString = queryString + '&countFiles=' + email.anexosEmail.length;
      for (let i = 0; i < email.anexosEmail.length; i++) {
        if (email.anexosEmail[i].conteudo != null)
          formData.append('file' + i, email.anexosEmail[i].conteudo, email.anexosEmail[i].conteudo.name);
      }
      return this.httpClient.post(this.collectionName + '/sendMassMail/' + queryString, formData).toPromise();
    }

    return this.httpClient.post(this.collectionName + '/sendMassMail/' + queryString, {}).toPromise();
  }

  /**
   *
   */
  emCriacao() {
    return this.httpClient.get(this.collectionName + '/emCriacao');
  }

  /**
   *
   */
  emAnalise() {
    return this.httpClient.get(this.collectionName + '/emAnalise');
  }

  /**
   *
   */
  aprovados() {
    return this.httpClient.get(this.collectionName + '/aprovados');
  }

  /**
   *
   */
  recusados() {
    return this.httpClient.get(this.collectionName + '/recusados');
  }

  /**
   *
   */
  vencidos() {
    return this.httpClient.get(this.collectionName + '/vencidos');
  }

  /**
   *
   * @param cnpj
   */
  findFornecedorWsExternoByCNPJ(cnpj: string):Observable<any> {
    return this.httpClient.get(this.collectionName + '/cnpj/' + cnpj);
  }
}
