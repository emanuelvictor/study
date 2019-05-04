import {IWrite} from '../interfaces/IWrite';
import {IRead} from '../interfaces/IRead';
import {HttpClient} from '@angular/common/http';
import {PageSerialize} from '../../page-serialize/page-serialize';
import {Observable} from 'rxjs';
import {environment} from "../../../../environments/environment";
import {Anexo} from "../../../domain/entity/publicacao/anexo.model";
import {FileRepository} from "../../../application/upload-file-repository/file.repository";
import {Arquivo} from "../../../domain/entity/publicacao/arquivo.model";
import {formatI18nPlaceholderName} from "@angular/compiler/src/render3/view/i18n/util";
import {forEach} from "@angular-devkit/schematics";


export abstract class BaseRepository<T> implements IWrite<T>, IRead<T> {

  public collectionName: string = environment.apiContext + '/';

  constructor(public httpClient: HttpClient, public collection: string, public fileRepository?: FileRepository) {
    if (collection)
      this.collectionName = this.collectionName + collection;
    else
      this.collectionName = this.collectionName + this.constructor.name.replace('Repository', '').toLowerCase() + 's';
  }

  async save(item: T): Promise<T> {
    const aux: any = item;
    if (aux.id)
      return this.update(aux.id, item);
    return this.httpClient.post<T>(this.collectionName, item).toPromise();
  }

  async saveWithAnexos(item: T, anexosToRemove?: Arquivo[], documentosToRemove?: Arquivo[]): Promise<T> {
    return new Promise<T>((resolve) => {
      const aux: any = item;
      if (aux.id)
        this.update(aux.id, item).then(result => {

          if ((!(item as any).anexos || !(item as any).anexos.length) && (!anexosToRemove || !anexosToRemove.length) && (!(item as any).documentos || !(item as any).documentos.length) && (!documentosToRemove || !documentosToRemove.length))
            resolve(result);

          if ((item as any).anexos && (item as any).anexos.length)
            for (let i = 0; i < (item as any).anexos.length; i++) {
              const arquivo = (item as any).anexos[i];
              if (!arquivo.id)
                this.fileRepository.save(this.collectionName + '/' + (result as any).id + '/anexos/' + arquivo.nome, arquivo.conteudo);
              else
                this.fileRepository.update(this.collectionName + '/' + (result as any).id + '/anexos/' + arquivo.nome + '/' + arquivo.id, arquivo.conteudo)
            }

          if (anexosToRemove && anexosToRemove.length)
            for (let i = 0; i < anexosToRemove.length; i++) {
              const arquivo = anexosToRemove[i];
              if (arquivo.id)
                this.fileRepository.remove(this.collectionName + '/' + (result as any).id + '/anexos/' + arquivo.id)
            }

          if ((item as any).documentos && (item as any).documentos.length)
            for (let i = 0; i < (item as any).documentos.length; i++) {
              const arquivo = (item as any).documentos[i];
              if (!arquivo.id)
                this.fileRepository.save(this.collectionName + '/' + (result as any).id + '/documentos/' + arquivo.nome + '/tipoCadastroTipoDocumentoId/' + arquivo.tipoCadastroTipoDocumento.id, arquivo.conteudo)
                  .then(() => {
                    if (i === (item as any).documentos.length - 1)
                      resolve(result)
                  });
              else if (arquivo.aprovado !== undefined)
                this.fileRepository.update(this.collectionName + '/' + (result as any).id + '/documentos/' + arquivo.nome + '/' + arquivo.id + '/' + arquivo.aprovado, arquivo.conteudo)
                  .then(() => {
                    if (i === (item as any).documentos.length - 1)
                      resolve(result)
                  });
              else
                this.fileRepository.update(this.collectionName + '/' + (result as any).id + '/documentos/' + arquivo.nome + '/' + arquivo.id, arquivo.conteudo)
                  .then(() => {
                    if (i === (item as any).documentos.length - 1)
                      resolve(result)
                  })
            }

          if (documentosToRemove && documentosToRemove.length)
            for (let i = 0; i < documentosToRemove.length; i++) {
              const arquivo = documentosToRemove[i];
              if (arquivo.id)
                this.fileRepository.remove(this.collectionName + '/' + (result as any).id + '/documentos/' + arquivo.id)
            }
        });
      else
        this.httpClient.post<T>(this.collectionName, item).toPromise().then(result => {

          if (!(item as any).anexos || !(item as any).anexos.length || !anexosToRemove || !anexosToRemove.length || !(item as any).documentos || !(item as any).documentos.length || !documentosToRemove || !documentosToRemove.length)
            resolve(result);

          if ((item as any).anexos && (item as any).anexos.length)
            for (let i = 0; i < (item as any).anexos.length; i++) {
              const arquivo = (item as any).anexos[i];
              if (!arquivo.id)
                this.fileRepository.save(this.collectionName + '/' + (result as any).id + '/anexos/' + arquivo.nome, arquivo.conteudo);
              else
                this.fileRepository.update(this.collectionName + '/' + (result as any).id + '/anexos/' + arquivo.nome + '/' + arquivo.id, arquivo.conteudo)
            }

          if (anexosToRemove && anexosToRemove.length)
            for (let i = 0; i < anexosToRemove.length; i++) {
              const arquivo = anexosToRemove[i];
              if (arquivo.id)
                this.fileRepository.remove(this.collectionName + '/' + (result as any).id + '/anexos/' + arquivo.id)
            }

          if ((item as any).documentos && (item as any).documentos.length)
            for (let i = 0; i < (item as any).documentos.length; i++) {
              const arquivo = (item as any).documentos[i];
              if (!arquivo.id)
                this.fileRepository.save(this.collectionName + '/' + (result as any).id + '/documentos/' + arquivo.nome + '/tipoCadastroTipoDocumentoId/' + arquivo.tipoCadastroTipoDocumento.id, arquivo.conteudo);
              else {
                if (arquivo.aprovado !== undefined)
                  this.fileRepository.update(this.collectionName + '/' + (result as any).id + '/documentos/' + arquivo.nome + '/' + arquivo.id + '/' + arquivo.aprovado, arquivo.conteudo);
                else
                  this.fileRepository.update(this.collectionName + '/' + (result as any).id + '/documentos/' + arquivo.nome + '/' + arquivo.id, arquivo.conteudo)
              }

            }

          if (documentosToRemove && documentosToRemove.length)
            for (let i = 0; i < documentosToRemove.length; i++) {
              const arquivo = documentosToRemove[i];
              if (arquivo.id)
                this.fileRepository.remove(this.collectionName + '/' + (result as any).id + '/documentos/' + arquivo.id)
            }

        })
    })
  }

  update(id: number, item: T): Promise<T> {
    return this.httpClient.put<T>(this.collectionName + '/' + id, item).toPromise();
  }

  delete(id: number): Promise<void> {
    return this.httpClient.delete<void>(this.collectionName + '/' + id).toPromise();
  }

  findById(id: number): Observable<T> {
    return this.httpClient.get<T>(this.collectionName + '/' + id);
  }

  listByFilters(pageable: any): Observable<any> {

    const params = PageSerialize.getHttpParamsFromPageable(pageable);

    return this.httpClient.get(this.collectionName, {
      params: params
    })

  }

  findAll(): Observable<T[]> {
    return this.httpClient.get<T[]>(this.collectionName);
  }

  updateAtivo(id: number): Promise<boolean> {
    return this.httpClient.put<boolean>(this.collectionName + '/ativo', id).toPromise();
  }

  /**
   *
   * @param publicacaoId
   * @param pageable
   */
  public findAllByPublicacaoId(publicacaoId: number, pageable: any): Observable<any> {
    const params = PageSerialize.getHttpParamsFromPageable(pageable);

    return this.httpClient.get(this.collectionName + '/' + publicacaoId + '/anexos', {
      params: params
    });
  }

  /**
   *
   * @param fornecedorId
   * @param pageable
   */
  public findAllByFornecedorId(fornecedorId: number, pageable: any): Observable<any> {
    const params = PageSerialize.getHttpParamsFromPageable(pageable);

    return this.httpClient.get(this.collectionName + '/' + fornecedorId + '/documentos', {
      params: params
    });
  }


  public listPaisesByFilters(pageable: any): Observable<any> {
    const params = PageSerialize.getHttpParamsFromPageable(pageable);

    return this.httpClient.get(this.collectionName + '/paises', {
      params: params
    });
  }

  public listEstadosByFilters(pageable: any): Observable<any> {
    const params = PageSerialize.getHttpParamsFromPageable(pageable);

    return this.httpClient.get(this.collectionName + '/estados', {
      params: params
    });
  }

  public listCidadesByFilters(pageable: any): Observable<any> {
    const params = PageSerialize.getHttpParamsFromPageable(pageable);

    return this.httpClient.get(this.collectionName + '/cidades', {
      params: params
    });
  }

}
