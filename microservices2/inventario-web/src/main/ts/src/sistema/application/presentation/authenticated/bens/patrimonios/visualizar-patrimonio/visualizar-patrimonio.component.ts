import {Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MessageService} from '../../../../../../domain/services/message.service';
import {viewAnimation} from "../../../../../utils/utils";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material";
import {PatrimonioRepository} from "../../../../../../domain/repository/patrimonio.repository";

// @ts-ignore
@Component({
  selector: 'visualizar-patrimonio',
  templateUrl: './visualizar-patrimonio.component.html',
  styleUrls: ['../patrimonios.scss'],
  animations: [
    viewAnimation
  ]
})
export class VisualizarPatrimonioComponent implements OnInit {

  /**
   *
   */
  patrimonio: any = {};

  /**
   *
   * @param router
   * @param dialog
   * @param activatedRoute
   * @param messageService
   * @param data
   * @param patrimonioRepository
   * @param dialogRef
   */
  constructor(public activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              @Inject(MAT_DIALOG_DATA) private data: any,
              private router: Router, private dialog: MatDialog,
              private patrimonioRepository: PatrimonioRepository,
              private dialogRef: MatDialogRef<VisualizarPatrimonioComponent>) {
    this.patrimonio.id = data.patrimonio.id || null
  }

  /**
   *
   */
  ngOnInit() {
    this.findById();
  }

  /**
   *
   */
  public findById() {
    this.patrimonioRepository.findById(this.patrimonio.id)
      .subscribe(result => this.patrimonio = result)
  }

}
