import {Component} from '@angular/core';
import {MatDialog, MatDialogRef} from "@angular/material";
import {viewAnimation} from "../../../../../../utils/utils";

// @ts-ignore
@Component({
  selector: 'aprovar-execucao-inventario',
  templateUrl: './aprovar-execucao-inventario.component.html',
  styleUrls: ['../../inventarios.scss'],
  animations: [
    viewAnimation
  ]
})
export class AprovarExecucaoInventarioComponent {

  /**
   *
   * @param dialogRef
   * @param dialog
   */
  constructor(private dialogRef: MatDialogRef<AprovarExecucaoInventarioComponent>, private dialog: MatDialog) {
  }

}
