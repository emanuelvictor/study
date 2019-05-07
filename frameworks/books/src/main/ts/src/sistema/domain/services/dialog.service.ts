import {Injectable} from '@angular/core';
import {MatDialog} from '@angular/material';
import {DeleteDialogComponent} from '../../application/controls/delete-dialog/delete-dialog.component';
import {AnexoDialogComponent} from "../../application/controls/anexo-dialog/anexo-dialog.component";
import {Anexo} from "../entity/publicacao/anexo.model";
import {VisualizarAnexoDialogComponent} from "../presentation/publicacoes/vincular-anexos/anexo/visualizar-anexo-dialog/visualizar-anexo-dialog.component";
import {Documento} from '../entity/fornecedor/documento.model';
import {VisualizarDocumentoDialogComponent} from '../presentation/fornecedor/inserir-fornecedor/documentos-form/vincular-documentos/documento/visualizar-documento-dialog/visualizar-documento-dialog.component';
import {ConfirmacaoRecusaComponent} from "../../application/controls/confirmacao-recusa/confirmacao-recusa.component";

@Injectable()
export class DialogService {

  /**
   *
   * @param dialog
   */
  constructor(public dialog: MatDialog) {
  }

  /**
   *
   * @param entity
   * @param type
   */
  public confirmDelete(entity: any, type: string): Promise<any> {
    return this.dialog.open(DeleteDialogComponent, {
      width: '325px',
      height: 'auto',
      data: {entity, type}
    })
      .afterClosed()
      .toPromise();
  }

  /**
   *
   * @param entity
   * @param type
   */
  public sendAnexo( entity: any, type: string ): Promise<any> {
    return this.dialog.open(AnexoDialogComponent, {
      width: 'auto',
      height: 'auto',
      data: { entity, type }
    })
      .afterClosed()
      .toPromise();
  }

  /**
   *
   * @param entity
   * @param type
   */
  public recusar( entity: any, type: string ): Promise<any> {
    return this.dialog.open(ConfirmacaoRecusaComponent, {
      width: '500px',
      height: 'auto',
      data: { entity, type }
    })
      .afterClosed()
      .toPromise();
  }

  /**
   *
   * @param anexo
   * @param type
   */
  visualizarAnexo(anexo: Anexo, type: string) {
    return this.dialog.open(VisualizarAnexoDialogComponent, {
      width: 'auto',
      height: 'auto',
      data: { anexo, type }
    })
      .afterClosed()
      .toPromise();
  }

  /**
   *
   * @param documento
   * @param type
   */
  visualizarDocumento(documento: Documento, type: string) {
    return this.dialog.open(VisualizarDocumentoDialogComponent, {
      width: 'auto',
      height: 'auto',
      data: { documento, type }
    })
      .afterClosed()
      .toPromise();
  }
}
