import 'rxjs/add/operator/debounceTime';
import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef, MatSnackBar} from "@angular/material";
import {PatrimonioRepository} from "../../../../../../../../../domain/repository/patrimonio.repository";
import {Patrimonio} from "../../../../../../../../../domain/entity/patrimonio/patrimonio.model";
import {Usuario} from "../../../../../../../../../domain/entity/usuario.model";

// @ts-ignore
@Component({
  selector: 'alterar-sobra-fisica',
  templateUrl: './alterar-sobra-fisica.component.html',
  styleUrls: ['../../../../inventarios.scss']
})
export class AlterarSobraFisicaComponent {

  /**
   *
   */
  public patrimonio: Patrimonio = new Patrimonio();

  /**
   *
   */
  private id: number;

  /**
   *
   * @param patrimonioRepository
   * @param dialogRef
   * @param snackBar
   * @param data
   */
  constructor(private patrimonioRepository: PatrimonioRepository,
              public dialogRef: MatDialogRef<AlterarSobraFisicaComponent>,
              private snackBar: MatSnackBar, @Inject(MAT_DIALOG_DATA) public data: any) {
    this.patrimonio = Object.assign({}, data.patrimonio);
    this.id = this.patrimonio.id
  }


  /**
   *
   */
  excluir(patrimonioId: number) {
    this.patrimonioRepository.delete(patrimonioId)
      .then(() => {
        this.dialogRef.close(true);
        this.openSnackBar('Sobra física excluída com sucesso!')
      })
  }

  /**
   *
   * @param patrimonioDTO
   */
  alterarSobraFisica(patrimonioDTO: any) {
    const patrimonio: Patrimonio = new Patrimonio();
    patrimonio.id = patrimonioDTO.id;

    patrimonio.codigoBase = patrimonioDTO.codigoBase;
    patrimonio.descricao = patrimonioDTO.descricao;
    patrimonio.encontrado = patrimonioDTO.encontrado;
    patrimonio.item = patrimonioDTO.item;
    patrimonio.plaqueta = patrimonioDTO.plaqueta;

    patrimonio.localizacao = patrimonioDTO.localizacao;
    patrimonio.localizacaoAnterior = patrimonioDTO.localizacaoAnterior;

    patrimonio.marca = patrimonioDTO.marca;
    patrimonio.modelo = patrimonioDTO.modelo;

    patrimonio.centroCustoInventario = this.data.patrimonio.centroCustoInventario;

    (patrimonio as any).centroCustoAnterior = patrimonioDTO.centroCustoAnterior ? patrimonioDTO.centroCustoAnterior : patrimonioDTO.centroCusto;

    (patrimonio as any).sobraFisica = patrimonioDTO.sobraFisica;

    patrimonio.departamento = patrimonioDTO.departamento;
    patrimonio.complemento = patrimonioDTO.complemento;
    patrimonio.observacao = patrimonioDTO.observacao;

    if (!patrimonio.id)
      patrimonio.id = this.id;

    patrimonio.centroCustoInventario = {id: patrimonio.centroCustoInventario.id} as any;

    this.patrimonioRepository.alterarSobraFisica(patrimonio.id, patrimonio)
      .then(result => {
        this.dialogRef.close(result);
        this.openSnackBar('Sobra Física atualizada com sucesso!')
      })
  }

  /**
   *
   * @param message
   */
  public openSnackBar(message: string) {
    this.snackBar.open(message, 'Fechar', {
      duration: 5000
    })
  }
}
