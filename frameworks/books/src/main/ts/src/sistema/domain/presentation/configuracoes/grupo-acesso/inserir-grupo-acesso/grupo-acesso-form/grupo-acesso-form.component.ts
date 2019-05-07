import {Component, ElementRef, Inject, Input, OnInit, Renderer} from '@angular/core';
import {CrudViewComponent} from "../../../../../../application/controls/crud/crud-view.component";
import {FormBuilder, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material";
import {ActivatedRoute} from "@angular/router";
import {PermissaoRepository} from "../../../../../repository/permissao.repository";
import {Permissao} from "../../../../../entity/permissao.model";
import {GrupoAcesso} from "../../../../../entity/grupo-acesso.model";
import {GrupoAcessoPermissao} from "../../../../../entity/grupo-acesso-permissao.model";


@Component({
  selector: 'grupo-acesso-form',
  templateUrl: 'grupo-acesso-form.component.html',
  styleUrls: ['../../grupo-acesso.component.scss'],
})
export class GrupoAcessoFormComponent extends CrudViewComponent implements OnInit {

  /**
   *
   */
  permissoes: Permissao[];

  /**
   *
   */
  @Input()
  entity: GrupoAcesso = new GrupoAcesso();

  /**
   *
   * @param snackBar
   * @param activatedRoute
   * @param element
   * @param permissaoRepository
   * @param fb
   * @param renderer
   */
  constructor(public snackBar: MatSnackBar,
              public activatedRoute: ActivatedRoute,
              @Inject(ElementRef) public element: ElementRef,
              private permissaoRepository: PermissaoRepository,
              public fb: FormBuilder, public renderer: Renderer) {
    super(snackBar, element, fb, renderer, activatedRoute);
  }

  /**
   *
   */
  ngOnInit() {
    this.entity.ativo = true;
    this.form = this.fb.group({
      nome: ['nome', [Validators.required]]
    });

    this.permissaoRepository.listByFilters({branch: true})
      .subscribe(result => {
        this.permissoes = result.content;

        if (this.entity.id) {
          // console.log(this.entity.gruposAcessoPermissoes.map(a => a.permissao));
          let permissoes = this.entity.gruposAcessoPermissoes.map(a => a.permissao);
          permissoes = this.arruma(permissoes);
          this.percorre(permissoes, this.permissoes);

          this.entity.gruposAcessoPermissoes = [];

          for (let i = 0; i < permissoes.length; i++) {
            if (permissoes[i]) {
              const grupoAcessoPermissao: GrupoAcessoPermissao = new GrupoAcessoPermissao();

              // Remove recursividade
              const grupoAcesso: GrupoAcesso = new GrupoAcesso();
              grupoAcesso.id = this.entity.id;
              grupoAcesso.ativo = this.entity.ativo;
              grupoAcesso.nome = this.entity.nome;

              grupoAcessoPermissao.grupoAcesso = grupoAcesso;
              grupoAcessoPermissao.permissao = permissoes[i];

              this.entity.gruposAcessoPermissoes.push(grupoAcessoPermissao)
            }
          }
          // console.log(this.entity.gruposAcessoPermissoes.map(a => a.permissao));
        }

      });
  }

  /**
   *
   * @param permissoes
   */
  arruma(permissoes: Permissao[]): Permissao[] {

    for (let i = 0; i < permissoes.length; i++) {

      if (permissoes[i].permissaoSuperior && (permissoes[i].permissaoSuperior as any).id)
        permissoes[i].permissaoSuperior = (permissoes[i].permissaoSuperior as any).id;

      if (!permissoes[i].id)
        permissoes[i] = this.findPermissao(this.permissoes, (permissoes[i] as any));
      else if (permissoes[i].permissoesInferiores)
        permissoes[i].permissoesInferiores = this.arruma(permissoes[i].permissoesInferiores);
    }

    return permissoes;
  }

  /**
   * Pesqusia a permissão pelo ID
   * @param ownPermissoes
   * @param allPermissoes
   */
  public percorre(ownPermissoes: Permissao[], allPermissoes: Permissao[]): void {
    for (let i = 0; i < allPermissoes.length; i++) {
      const permissao: Permissao = this.findPermissao(ownPermissoes, allPermissoes[i].id);

      if (permissao) {
        (permissao as any).selected = true;
        allPermissoes[i] = permissao;
      } else if (allPermissoes[i].permissoesInferiores && allPermissoes[i].permissoesInferiores.length)
        this.percorre(ownPermissoes, allPermissoes[i].permissoesInferiores)
    }
  }

  /**
   *
   * @param permissao
   */
  addPermissao(permissao: Permissao) {

    const grupoAcessoPermissao: GrupoAcessoPermissao = new GrupoAcessoPermissao();

    // Remove recursividade
    const grupoAcesso: GrupoAcesso = new GrupoAcesso();
    grupoAcesso.id = this.entity.id;
    grupoAcesso.ativo = this.entity.ativo;
    grupoAcesso.nome = this.entity.nome;

    grupoAcessoPermissao.grupoAcesso = grupoAcesso;
    grupoAcessoPermissao.permissao = permissao;

    this.entity.gruposAcessoPermissoes.push(grupoAcessoPermissao);

    if ((permissao.permissaoSuperior && this.findPermissao(this.permissoes, permissao.permissaoSuperior)) && this.findPermissao(this.permissoes, permissao.permissaoSuperior).permissoesInferiores.length === this.entity.gruposAcessoPermissoes.map(a => a.permissao).filter(a => a.permissaoSuperior === permissao.permissaoSuperior).length) {

      this.entity.gruposAcessoPermissoes = this.entity.gruposAcessoPermissoes.filter(a => a.permissao.permissaoSuperior !== permissao.permissaoSuperior);

      this.addPermissao(this.findPermissao(this.permissoes, permissao.permissaoSuperior))

    } else {

      let permissoes: Permissao[] = this.removeRepetidos(this.arruma(this.entity.gruposAcessoPermissoes.map(a => a.permissao)));

      // console.log('Antes', permissoes.map(s => s.nome));

      // Apenas verificação cautelar
      if (permissoes && permissoes.length) {
        this.entity.gruposAcessoPermissoes = [];

        permissoes.forEach(permissao => {
          const grupoAcessoPermissao: GrupoAcessoPermissao = new GrupoAcessoPermissao();

          // Remove recursividade
          const grupoAcesso: GrupoAcesso = new GrupoAcesso();
          grupoAcesso.id = this.entity.id;
          grupoAcesso.ativo = this.entity.ativo;
          grupoAcesso.nome = this.entity.nome;

          grupoAcessoPermissao.grupoAcesso = grupoAcesso;
          grupoAcessoPermissao.permissao = permissao;

          this.entity.gruposAcessoPermissoes.push(grupoAcessoPermissao);
        });
      }

      console.log(this.entity.gruposAcessoPermissoes.map(a => a.permissao.nome));

    }


  }

  /**
   * Remove os ítens repetidos
   * @param permissoesLineares
   */
  removeRepetidos(permissoesLineares: Permissao[]): Permissao[] {
    for (let i = 0; i < permissoesLineares.length; i++) {
      for (let k = 0; k < permissoesLineares.length; k++) {
        if (permissoesLineares[i] && permissoesLineares[k]) {

          // Se encontrou nos filhos de dentro
          const founded = this.findPermissao(permissoesLineares[i].permissoesInferiores, permissoesLineares[k].id);
          if (founded) {
            (permissoesLineares[k] as any).toDelete = true;
          }

        }
      }
    }

    // Remove irmãos repetidos
    return permissoesLineares.filter(function (este, i) {
      return permissoesLineares.indexOf(este) === i;
    }).filter(permissao => !(permissao as any).toDelete);
  }

  /**
   *
   * @param permissao
   */
  removePermissao(permissao: Permissao) {

    const permissoes: Permissao[] = this.percorreToRemove(permissao, this.entity.gruposAcessoPermissoes.map(a => a.permissao));

    this.entity.gruposAcessoPermissoes = [];

    for (let i = 0; i < permissoes.length; i++) {
      if (permissoes[i]) {
        const grupoAcessoPermissao: GrupoAcessoPermissao = new GrupoAcessoPermissao();

        // Remove recursividade
        const grupoAcesso: GrupoAcesso = new GrupoAcesso();
        grupoAcesso.id = this.entity.id;
        grupoAcesso.ativo = this.entity.ativo;
        grupoAcesso.nome = this.entity.nome;

        grupoAcessoPermissao.grupoAcesso = grupoAcesso;
        grupoAcessoPermissao.permissao = permissoes[i];

        this.entity.gruposAcessoPermissoes.push(grupoAcessoPermissao)
      }
    }

    console.log(this.entity.gruposAcessoPermissoes.map(a => a.permissao.nome));
  }


  /**
   * @param permissaoARemover
   * @param permissoesVinculadas
   */
  public percorreToRemove(permissaoARemover: Permissao, permissoesVinculadas: Permissao[]): Permissao[] {
    for (let i = 0; i < permissoesVinculadas.length; i++) {
      if (permissoesVinculadas[i] && permissaoARemover) {
        if (permissoesVinculadas[i].id === permissaoARemover.id) {
          const copia = permissoesVinculadas.slice();
          copia.splice(i, 1);
          return copia;
        } else if (permissoesVinculadas[i].permissoesInferiores && permissoesVinculadas[i].permissoesInferiores.length) {

          if (this.findPermissao(permissoesVinculadas[i].permissoesInferiores, permissaoARemover.id)) {
            const aux = permissoesVinculadas.slice();
            aux.splice(i, 1);
            return aux.concat(this.percorreToRemove(permissaoARemover, permissoesVinculadas[i].permissoesInferiores));
          }

        }
      }
    }
    return permissoesVinculadas;
  }

  /**
   * Pesqusia a permissão pelo ID
   * @param permissoes
   * @param id
   */
  public findPermissao(permissoes: Permissao[], id: number): Permissao {
    for (let i = 0; i < permissoes.length; i++) {
      if (permissoes[i]) {
        if (permissoes[i].id === id)
          return permissoes[i];
        else if (permissoes[i].permissoesInferiores && permissoes[i].permissoesInferiores.length) {
          const permissao: Permissao = this.findPermissao(permissoes[i].permissoesInferiores, id);
          if (permissao)
            return permissao;
        }
      }
    }
  }
}
