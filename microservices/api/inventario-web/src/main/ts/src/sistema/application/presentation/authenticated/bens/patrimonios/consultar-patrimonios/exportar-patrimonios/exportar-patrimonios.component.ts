import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material";
import {viewAnimation} from "../../../../../../utils/utils";

// @ts-ignore
@Component({
  selector: 'exportar-patrimonio',
  templateUrl: './exportar-patrimonios.component.html',
  styleUrls: ['../../patrimonios.scss'],
  animations: [
    viewAnimation
  ]
})
export class ExportarPatrimoniosComponent {

  pageable : any;


  /**
   *
   * @param data
   * @param dialog
   * @param dialogRef
   */
  constructor(@Inject(MAT_DIALOG_DATA) private data: any, private dialog: MatDialog,
              private dialogRef: MatDialogRef<ExportarPatrimoniosComponent>) {
    this.pageable = data.pageable
  }

}
