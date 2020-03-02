import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

/**
 *
 */
@Injectable()
export class FileRepository {

  /**
   *
   * @param {HttpClient} httpClient
   */
  constructor(private httpClient: HttpClient) {
  }

  /**
   *
   * @param {string} path
   * @returns {Observable<>}
   */
  findOne(path: string): Observable<any> {
    return this.httpClient.get(path, {responseType: 'blob'});
  }

  /**
   *
   * @param {string} path
   * @param {File} file
   * @returns {Promise<>}
   */
  save(path: string, file: File): Promise<any> {
    const formData: FormData = new FormData();
    if (file != null)
      formData.append('file', file, file.name);

    return this.httpClient
      .post(path, formData, {responseType: 'text'})
      .toPromise();
  }

  /**
   *
   * @param {string} path
   * @param {File} file
   * @returns {Promise<>}
   */
  update(path: string, file: File): Promise<any> {
    const formData: FormData = new FormData();
    if (file != null)
      formData.append('file', file, file.name);

    return this.httpClient
      .post(path, formData, {responseType: 'text'})
      .toPromise();

  }

  /**
   *
   * @param {string} path
   */
  remove(path: string): Promise<boolean> {
    return this.httpClient
      .delete<boolean>(path)
      .toPromise();
  }
}
