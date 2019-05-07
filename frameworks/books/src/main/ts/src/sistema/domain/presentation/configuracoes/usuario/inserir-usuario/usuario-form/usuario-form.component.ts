import {Component, ElementRef, EventEmitter, Inject, Input, OnInit, Output, Renderer} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material';
import {DashboardViewComponent} from '../../../../dashboard-view.component';
import {MessageService} from '../../../../../services/message.service';
import {debounce} from "../../../../../../application/utils/debounce";
import {FormBuilder, FormControl, Validators} from "@angular/forms"
import {UsuarioRepository} from "../../../../../repository/usuario.repository";
import {CrudViewComponent} from "../../../../../../application/controls/crud/crud-view.component";
import {Usuario} from "../../../../../entity/livro.model";
import 'rxjs/add/operator/debounceTime';
import {debounceTime, switchMap} from 'rxjs/operators';

@Component({
  selector: 'usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['../../usuario.component.scss']
})
export class UsuarioFormComponent extends CrudViewComponent implements OnInit {

  @Input() entity: Usuario = new Usuario();

  @Output() save: EventEmitter<Usuario> = new EventEmitter();

  usuarios: any;

  usuario: Usuario = new Usuario();

  public debounce = debounce;

  /**
   *
   * @param router
   * @param snackBar
   * @param homeView
   * @param activatedRoute
   * @param messageService
   * @param usuarioRepository
   * @param element
   * @param fb
   * @param renderer
   * @param usuarioRepository
   */
  constructor(private router: Router,
              public snackBar: MatSnackBar,
              private homeView: DashboardViewComponent,
              public activatedRoute: ActivatedRoute,
              private messageService: MessageService,
              private usuarioRepository: UsuarioRepository,
              @Inject(ElementRef) public element: ElementRef,
              public fb: FormBuilder, public renderer: Renderer) {

    super(snackBar, element, fb, renderer, activatedRoute);

  }

  /**
   *
   */
  ngOnInit() {
    this.entity.ativo = true;

    this.form = this.fb.group({
      nome: new FormControl({value: '', disabled: false}, Validators.required),
      email: ['email', [Validators.required, Validators.email]],
    });

    this.form
      .get('email')
      .valueChanges
      .pipe(
        debounceTime(100),
        switchMap(value =>
          this.usuarioRepository.findByLdapUsername((value as string))
        )
      )
      .subscribe(usuario => {
        if (usuario) {

          this.usuarios = [usuario];

          if (this.form.get('email').value.includes('@')) {

            this.entity.nome = usuario.nome;
            this.form.controls['nome'].disable();

          } else

            this.form.controls['nome'].enable();

        } else {

          this.usuarios = [];
          this.form.controls['nome'].enable();

        }
      });

  }

  emit(entity: any) {

    if (entity.administrador)
      delete entity.grupoAcesso; // Se for administrador não tem grupo de acesso
    else if (entity.grupoAcesso)
      delete entity.grupoAcesso.gruposAcessoPermissoes; // Remove recursividade

    this.save.emit(entity);
  }

  /**
   *
   */
  getEmail() {
    this.entity.email = this.form.get('email').value;
  }

  notFirst: boolean = false;

  /**
   *
   * @param email
   */
  displayFn(email) {
    if (this.notFirst)
      return email;
    else this.notFirst = true;
  }
}
