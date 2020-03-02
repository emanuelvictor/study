import 'rxjs/add/operator/debounceTime';
import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef, MatSnackBar} from "@angular/material";
import {Patrimonio} from "../../../../../../../../domain/entity/patrimonio/patrimonio.model";
import {PatrimonioRepository} from "../../../../../../../../domain/repository/patrimonio.repository";
import {CentroCustoInventario} from "../../../../../../../../domain/entity/patrimonio/inventario/centro-custo-inventario.model";
import {Usuario} from "../../../../../../../../domain/entity/usuario.model";

// @ts-ignore
@Component({
  selector: 'inserir-patrimonio',
  templateUrl: './inserir-patrimonio.component.html',
  styleUrls: ['../../../inventarios.scss']
})
export class InserirPatrimonioComponent {

  /**
   *
   */
  public patrimonio: Patrimonio = new Patrimonio();

  /**
   *
   * @param snackBar
   * @param data
   * @param patrimonioRepository
   * @param dialogRef
   */
  constructor(private snackBar: MatSnackBar,
              @Inject(MAT_DIALOG_DATA) private data: any,
              private patrimonioRepository: PatrimonioRepository,
              private dialogRef: MatDialogRef<InserirPatrimonioComponent>) {
    this.patrimonio.centroCustoInventario = data.centroCustoInventario
  }

  /**
   *
   * @param patrimonioDTO
   */
  inserirNovoPatrimonio(patrimonioDTO: any) {
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

    patrimonio.centroCustoInventario = this.data.centroCustoInventario;

    (patrimonio as any).centroCustoAnterior = patrimonioDTO.centroCusto;

    (patrimonio as any).sobraFisica = patrimonioDTO.sobraFisica;

    patrimonio.departamento = patrimonioDTO.departamento;
    patrimonio.complemento = patrimonioDTO.complemento;
    patrimonio.observacao = patrimonioDTO.observacao;

    if (patrimonio.centroCustoInventario && patrimonio.centroCustoInventario.centroCusto && patrimonio.centroCustoInventario.centroCusto.gestor)
      patrimonio.centroCustoInventario.centroCusto.gestor = new Usuario(patrimonio.centroCustoInventario.centroCusto.gestor.id);

    this.patrimonioRepository.inserirNovoPatrimonio(patrimonio)
      .then( result => {
        this.dialogRef.close(result);
        this.openSnackBar('Novo Patrimônio inserido com sucesso')
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
