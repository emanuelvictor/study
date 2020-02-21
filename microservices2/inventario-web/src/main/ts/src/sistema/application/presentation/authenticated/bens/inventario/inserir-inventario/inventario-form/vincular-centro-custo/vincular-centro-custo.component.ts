import {Component, ElementRef, EventEmitter, Input, OnChanges, Output, SimpleChanges, ViewChild} from '@angular/core';
import {CentroCustoRepository} from "../../../../../../../../domain/repository/centro-custo.repository";
import {CentroCusto} from "../../../../../../../../domain/entity/pessoal.dto/centro-custo.model";
import {Subject} from "rxjs";
import {Executor} from "../../../../../../../../domain/entity/patrimonio/inventario/executor.model";
import {Inventario} from "../../../../../../../../domain/entity/patrimonio/inventario/inventario.model";
import {CentroCustoInventario} from "../../../../../../../../domain/entity/patrimonio/inventario/centro-custo-inventario.model";
import {InventarioRepository} from "../../../../../../../../domain/repository/inventario.repository";
import {AuthenticationService} from "../../../../../../../../domain/services/authentication.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatAutocompleteSelectedEvent} from "@angular/material/autocomplete";
import {UsuarioRepository} from "../../../../../../../../domain/repository/usuario.repository";

// @ts-ignore
@Component({
  selector: 'vincular-centro-custo',
  templateUrl: './vincular-centro-custo.component.html',
  styleUrls: ['../../../inventarios.scss']
})
export class VincularCentroCustoComponent implements OnChanges {

  /**
   *
   * @type {Subject<string>}
   */
  private modelChanged: Subject<string> = new Subject<string>();

  /**
   *
   */
  colaboradoresAvulsos: any[] = [];

  /**
   *
   */
  private colaboradoresAvulsosChanged: Subject<string> = new Subject<string>();

  /**
   *
   */
  public centrosCusto: CentroCusto[] = [];

  /**
   * Informar se é apenas  para visualização ou edição
   */
  @Input()
  public toView: boolean = false;

  /**
   *
   */
  @Output()
  public remove: EventEmitter<CentroCusto> = new EventEmitter<CentroCusto>();

  /**
   *
   */
  @Output()
  public push: EventEmitter<CentroCusto> = new EventEmitter<CentroCusto>();

  /**
   *
   */
  @Input()
  public entity: Inventario = new Inventario();

  /**
   *
   */
  @Input()
  public filter: string = '';

  /**
   *
   */
  @Input()
  public centroCustoCodigoFilter: string = '';

  /**
   *
   */
  @ViewChild('executoresAvulsosFilter', {static: false}) executoresAvulsosFilter: ElementRef<HTMLInputElement>;

  /**
   *
   * @param router
   * @param activatedRoute
   * @param usuarioRepository
   * @param inventarioRepository
   * @param centroCustoRepository
   * @param authenticationService
   */
  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private usuarioRepository: UsuarioRepository,
              private inventarioRepository: InventarioRepository,
              private centroCustoRepository: CentroCustoRepository,
              private authenticationService: AuthenticationService) {

    this.colaboradoresAvulsosChanged.debounceTime(300).subscribe(model => {
      this.usuarioRepository.findByLdapUsername((model as string))
        .subscribe(result => {
          if (result && result.email)
            this.colaboradoresAvulsos = [result]
        })
    });

    this.modelChanged.debounceTime(1000).subscribe(() => this.ngOnInit())
  }

  /**
   *
   */
  async ngOnInit() {

    if (this.toView) {

      this.centrosCusto = this.entity.centrosCusto.map(centroCustoInventario => {
        (centroCustoInventario.centroCusto as any).colaboradores = centroCustoInventario.executores;
        (centroCustoInventario.centroCusto as any).centroCustoValue = true;

        (centroCustoInventario.centroCusto as any).usuarioAutenticadoIsExecutor = (this.authenticationService.usuarioAutenticado as any).executores.filter(executor => executor.centroCustoInventario.id === centroCustoInventario.id).length > 0;
        (centroCustoInventario.centroCusto as any).usuarioAutenticadoIsGestor = (this.authenticationService.usuarioAutenticado as any).centrosCusto.filter(centroCusto => centroCusto.codigo === centroCustoInventario.centroCusto.codigo).length > 0;
        (centroCustoInventario.centroCusto as any).status = centroCustoInventario.status;

        return centroCustoInventario.centroCusto
      });

      if (!this.authenticationService.usuarioAutenticado.root && !(this.authenticationService.usuarioAutenticado as any).isPatrimonio)
        this.centrosCusto = this.centrosCusto.filter(centroCustoInventario => (centroCustoInventario as any).usuarioAutenticadoIsExecutor || (centroCustoInventario as any).usuarioAutenticadoIsGestor);

      this.handlerExecutoresAvulsos()

    } else this.centroCustoRepository.listByFilters({
      descricaoFilter: this.filter,
      centroCustoCodigoFilter: this.centroCustoCodigoFilter
    }).subscribe(result => {
      this.centrosCusto = result.content;

      if (this.entity && this.entity.centrosCusto) {
        this.entity.centrosCusto.forEach(centroCusto => {
          this.centrosCusto.forEach(centroCustoInner => {
            if (centroCustoInner.codigo === centroCusto.centroCusto.codigo) {

              this.centroCustoRepository.listColaboradoresByCentroCustoCodigo(centroCusto.centroCusto.codigo)
                .subscribe(result => {
                  result.content.forEach(colaboradorResulted => {
                    const executor = new Executor();
                    executor.usuario.nome = colaboradorResulted.nome;
                    executor.usuario.email = colaboradorResulted.email;
                    executor.centroCustoInventario = new CentroCustoInventario(new Inventario(this.entity.id), new CentroCusto(centroCustoInner.codigo, centroCustoInner.descricao, centroCustoInner.gestor));

                    if (centroCusto.executores.filter(e => e.usuario.email === executor.usuario.email).length)
                      (executor as any).colaboradorValue = true;

                    if (!(centroCustoInner as any).colaboradores)
                      (centroCustoInner as any).colaboradores = [];
                    (centroCustoInner as any).colaboradores.push(executor)
                  });

                  this.handlerExecutoresAvulsos()
                });

              (centroCustoInner as any).centroCustoValue = true
            }
            (centroCustoInner as any).usuarioAutenticadoIsGestor = (this.authenticationService.usuarioAutenticado as any).centrosCusto.filter(centroCusto => centroCusto.codigo === centroCustoInner.codigo).length > 0;
          })
        })
      }
    });

  }

  /**
   *
   */
  private handlerExecutoresAvulsos() {
    for (let i = 0; i < this.centrosCusto.length; i++)
      for (let j = 0; j < this.entity.centrosCusto.length; j++)
        if (this.centrosCusto[i].codigo === this.entity.centrosCusto[j].centroCusto.codigo)
          for (let c = 0; c < this.entity.centrosCusto[j].executores.length; c++)
            if (this.entity.centrosCusto[j].executores[c].avulso) {
              if (!(this.centrosCusto[i] as any).executoresAvulsos)
                (this.centrosCusto[i] as any).executoresAvulsos = [];
              if (!(this.centrosCusto[i] as any).executoresAvulsos.filter(executor => executor.email === this.entity.centrosCusto[j].executores[c].usuario.email).length)
                (this.centrosCusto[i] as any).executoresAvulsos.push(this.entity.centrosCusto[j].executores[c].usuario)
            }
  }

  /**
   *
   * @param changes
   */
  ngOnChanges(changes: SimpleChanges): void {

    if (changes.filter && !changes.filter.firstChange) {
      if (changes.filter.currentValue && changes.filter.currentValue.length >= 3) {
        this.filter = changes.filter.currentValue;
        this.modelChanged.next(changes.filter.currentValue)
      } else {
        this.filter = '';
        this.modelChanged.next('')
      }
    }

    if (changes.centroCustoCodigoFilter && !changes.centroCustoCodigoFilter.firstChange) {
      if (changes.centroCustoCodigoFilter.currentValue && changes.centroCustoCodigoFilter.currentValue.length >= 3) {
        this.centroCustoCodigoFilter = changes.centroCustoCodigoFilter.currentValue;
        this.modelChanged.next(changes.centroCustoCodigoFilter.currentValue);
      } else {
        this.centroCustoCodigoFilter = '';
        this.modelChanged.next('')
      }
    }
  }

  /**
   *
   * @param inventarioId
   * @param centroCusto
   */
  finalizar(inventarioId: number, centroCusto: CentroCusto) {
    this.inventarioRepository.finalizar(inventarioId, centroCusto.codigo)
      .then(result => (centroCusto as any).status = result.status)
  }

  /**
   * Rollback pra suprir as cabacices dos usuários
   * @param inventarioId
   * @param centroCusto
   */
  desfinalizar(inventarioId: number, centroCusto: CentroCusto) {
    this.inventarioRepository.desfinalizar(inventarioId, centroCusto.codigo)
      .then(result => (centroCusto as any).status = result.status)
  }

  /**
   *
   * @param inventarioId
   * @param centroCusto
   */
  executarCentroCustoInventario(inventarioId: number, centroCusto: CentroCusto) {
    this.centroCustoRepository.executarCentroCustoInventario(inventarioId, centroCusto.codigo)
      .subscribe(result => (centroCusto as any).status = result.status)
  }

  /**
   *
   */
  exportar(inventarioId: number, centroCusto: CentroCusto) {
    this.inventarioRepository.exportar(inventarioId, centroCusto.codigo)
  }

  /**
   *
   * @param inventarioId
   * @param centroCusto
   */
  exportarSobrasFisicas(inventarioId: number, centroCusto: CentroCusto) {
    this.inventarioRepository.exportarSobrasFisicas(inventarioId, centroCusto.codigo)
  }

  /**
   *
   * @param colaborador
   */
  clickColaborador(colaborador: any) {
    for (let i = 0; i < this.entity.centrosCusto.length; i++) {

      // Se não tem colaboradores instancia
      if (!this.entity.centrosCusto[i].executores)
        this.entity.centrosCusto[i].executores = [];

      if (colaborador.centroCustoInventario.centroCusto.codigo === this.entity.centrosCusto[i].centroCusto.codigo /*|| colaborador.centroCusto.codigo === this.entity.centrosCusto[i].centroCusto.codigo*/) {
        if (colaborador.colaboradorValue) {
          this.entity.centrosCusto[i].executores.push(colaborador)
        } else {
          for (let j = 0; j < this.entity.centrosCusto[i].executores.length; j++) {
            if (this.entity.centrosCusto[i].executores[j].usuario.email === colaborador.usuario.email) {
              this.entity.centrosCusto[i].executores.splice(j, 1);
              return
            }
          }
        }
      }
    }
  }


  /**
   *
   * @param centroCusto
   */
  changeCentroCusto(centroCusto: CentroCusto) {
    this.emit(centroCusto);
    if ((centroCusto as any).centroCustoValue)
      this.centroCustoRepository.listColaboradoresByCentroCustoCodigo(centroCusto.codigo).subscribe(result => {
        (centroCusto as any).colaboradores = [];

        result.content.forEach(colaboradorResulted => {
          const executor = new Executor();
          executor.matricula = colaboradorResulted.matricula;
          executor.usuario.nome = colaboradorResulted.nome;
          executor.usuario.email = colaboradorResulted.email;
          executor.centroCustoInventario = new CentroCustoInventario(new Inventario(this.entity.id), new CentroCusto(centroCusto.codigo, centroCusto.descricao, centroCusto.gestor));

          for (let i = 0; i < this.entity.centrosCusto.length; i++) {
            if (this.entity.centrosCusto[i].centroCusto.codigo === centroCusto.codigo)
              centroCusto.id = this.entity.centrosCusto[i].id;

            if (!this.entity.centrosCusto[i].executores)
              this.entity.centrosCusto[i].executores = [];

            if (this.entity.centrosCusto[i].executores.filter(e => e.usuario.email === executor.usuario.email).length) {
              executor.id = this.entity.centrosCusto[i].executores.filter(e => e.usuario.email === executor.usuario.email)[0].id;
              (executor as any).colaboradorValue = true
            }
          }

          if (!(centroCusto as any).colaboradores)
            (centroCusto as any).colaboradores = [];
          (centroCusto as any).colaboradores.push(executor)
        })

      })
  }

  /**
   *
   * @param centroCusto
   */
  emit(centroCusto: CentroCusto) {
    if ((centroCusto as any).centroCustoValue)
      this.push.emit(centroCusto);
    else
      this.remove.emit(centroCusto)
  }

  /**
   *
   * @param cc
   * @param executorAvulso
   */
  removeExecutorAvulso(cc: any, executorAvulso: any): void {

    for (let i = 0; i < this.entity.centrosCusto.length; i++)
      for (let j = 0; j < this.entity.centrosCusto[i].executores.length; j++)
        if (this.entity.centrosCusto[i].executores[j].usuario.email === executorAvulso.email)
          this.entity.centrosCusto[i].executores.splice(j, 1);

    const index = cc.executoresAvulsos.indexOf(executorAvulso);
    if (index >= 0)
      cc.executoresAvulsos.splice(index, 1)
  }


  /**
   *
   * @param cc
   * @param event
   * @param executoresAvulsosFilter
   */
  add(cc: any, event: MatAutocompleteSelectedEvent, executoresAvulsosFilter): void {

    for (let i = 0; i < this.entity.centrosCusto.length; i++) {
      if (this.entity.centrosCusto[i].centroCusto.codigo === cc.codigo) {
        if (!this.entity.centrosCusto[i].executores)
          this.entity.centrosCusto[i].executores = [];

        const executor = new Executor();
        executor.matricula = event.option.value.matricula;
        executor.usuario.nome = event.option.value.nome;
        executor.usuario.email = event.option.value.email;
        executor.centroCustoInventario = new CentroCustoInventario(new Inventario(this.entity.id), new CentroCusto(cc.codigo, cc.descricao, cc.gestor));
        executor.avulso = true;

        this.entity.centrosCusto[i].executores.push(executor)
      }
    }

    if (!cc.executoresAvulsos)
      cc.executoresAvulsos = [];
    cc.executoresAvulsos.push(event.option.value);

    executoresAvulsosFilter.value = ''
  }

  /**
   *
   * @param model
   */
  executoresAvulsosFilterChanged(model: string): void {
    this.colaboradoresAvulsosChanged.next(model)
  }

  /**
   *
   * @param centroCusto
   */
  hasExecutoresNormais(centroCusto: any) {
    return centroCusto && centroCusto.colaboradores && centroCusto.colaboradores.length && centroCusto.colaboradores.filter(c => c.colaboradorValue).length;
  }
}
