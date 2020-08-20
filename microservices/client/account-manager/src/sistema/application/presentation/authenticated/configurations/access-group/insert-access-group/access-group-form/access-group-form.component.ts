import {Component, ElementRef, Inject, Input, OnInit, Renderer} from '@angular/core';
import {CrudViewComponent} from "../../../../../../controls/crud/crud-view.component";
import {FormBuilder, Validators} from "@angular/forms";
import {MAT_FORM_FIELD_DEFAULT_OPTIONS, MatFormFieldDefaultOptions, MatSnackBar} from "@angular/material";
import {ActivatedRoute} from "@angular/router";
import {PermissionRepository} from "../../../../../../../domain/repository/permission.repository";
import {Permission} from "../../../../../../../domain/entity/permission.model";
import {AccessGroup} from "../../../../../../../domain/entity/access-group.model";
import {AccessGroupPermission} from "../../../../../../../domain/entity/access-group-permission.model";

const appearance: MatFormFieldDefaultOptions = {
  appearance: 'outline'
};

// @ts-ignore
@Component({
  selector: 'access-group-form',
  templateUrl: 'access-group-form.component.html',
  styleUrls: ['../../access-group.component.scss'],
  providers: [
    {
      provide: MAT_FORM_FIELD_DEFAULT_OPTIONS,
      useValue: appearance
    }
  ]
})
export class AccessGroupFormComponent extends CrudViewComponent implements OnInit {

  /**
   *
   */
  permissions: Permission[];

  /**
   *
   */
  @Input()
  entity: AccessGroup = new AccessGroup();

  /**
   *
   * @param snackBar
   * @param activatedRoute
   * @param element
   * @param permissionRepository
   * @param fb
   * @param renderer
   */
  constructor(public snackBar: MatSnackBar,
              public activatedRoute: ActivatedRoute,
              @Inject(ElementRef) public element: ElementRef,
              private permissionRepository: PermissionRepository,
              public fb: FormBuilder, public renderer: Renderer) {
    super(snackBar, element, fb, renderer, activatedRoute);
  }

  /**
   *
   */
  ngOnInit() {
    this.entity.enable = true;
    this.form = this.fb.group({
      name: ['name', [Validators.required]]
    });

    this.permissionRepository.listByFilters({branch: true})
      .subscribe(result => {
        this.permissions = result.content;

        if (this.entity.id) {
          let permissions = this.entity.accessGroupPermissions.map(a => a.permission);
          permissions = this.organize(permissions);
          this.organizeTheSelecteds(permissions, this.permissions);

          this.entity.accessGroupPermissions = [];

          for (let i = 0; i < permissions.length; i++) {
            if (permissions[i]) {
              const accessGroupPermission: AccessGroupPermission = new AccessGroupPermission();

              // Remove recursividade
              const accessGroup: AccessGroup = new AccessGroup();
              accessGroup.id = this.entity.id;
              accessGroup.enable = this.entity.enable;
              accessGroup.name = this.entity.name;

              accessGroupPermission.accessGroup = accessGroup;
              accessGroupPermission.permission = permissions[i];

              this.entity.accessGroupPermissions.push(accessGroupPermission)
            }
          }
        }
      })
  }

  /**
   *
   * @param permissions
   */
  organize(permissions: Permission[]): Permission[] {

    for (let i = 0; i < permissions.length; i++) {

      if (permissions[i].upperPermission && (permissions[i].upperPermission as any).id)
        permissions[i].upperPermission = (permissions[i].upperPermission as any).id;

      if (!permissions[i].id)
        permissions[i] = this.findPermission(this.permissions, (permissions[i] as any));
      else if (permissions[i].lowerPermissions)
        permissions[i].lowerPermissions = this.organize(permissions[i].lowerPermissions);
    }

    return permissions
  }

  /**
   * Pesqusia a permissão pelo ID
   * @param ownPermissoes
   * @param allPermissoes
   */
  public organizeTheSelecteds(ownPermissoes: Permission[], allPermissoes: Permission[]): void {
    for (let i = 0; i < allPermissoes.length; i++) {
      const permission: Permission = this.findPermission(ownPermissoes, allPermissoes[i].id);

      if (permission) {
        (permission as any).selected = true;
        allPermissoes[i] = permission;
      } else if (allPermissoes[i].lowerPermissions && allPermissoes[i].lowerPermissions.length)
        this.organizeTheSelecteds(ownPermissoes, allPermissoes[i].lowerPermissions)
    }
  }

  /**
   *
   * @param permission
   */
  addPermission(permission: Permission) {

    const accessGroupPermission: AccessGroupPermission = new AccessGroupPermission();

    // Remove recursividade
    const accessGroup: AccessGroup = new AccessGroup();
    accessGroup.id = this.entity.id;
    accessGroup.enable = this.entity.enable;
    accessGroup.name = this.entity.name;

    accessGroupPermission.accessGroup = accessGroup;
    accessGroupPermission.permission = permission;

    this.entity.accessGroupPermissions.push(accessGroupPermission);

    if ((permission.upperPermission && this.findPermission(this.permissions, permission.upperPermission)) && this.findPermission(this.permissions, permission.upperPermission).lowerPermissions.length === this.entity.accessGroupPermissions.map(a => a.permission).filter(a => a.upperPermission === permission.upperPermission).length) {

      this.entity.accessGroupPermissions = this.entity.accessGroupPermissions.filter(a => a.permission.upperPermission !== permission.upperPermission);

      this.addPermission(this.findPermission(this.permissions, permission.upperPermission))

    } else {

      let permissions: Permission[] = this.removeDuplicates(this.organize(this.entity.accessGroupPermissions.map(a => a.permission)));

      // Apenas verificação cautelar
      if (permissions && permissions.length) {
        this.entity.accessGroupPermissions = [];

        permissions.forEach(permission => {
          const accessGroupPermission: AccessGroupPermission = new AccessGroupPermission();

          // Remove recursividade
          const accessGroup: AccessGroup = new AccessGroup();
          accessGroup.id = this.entity.id;
          accessGroup.enable = this.entity.enable;
          accessGroup.name = this.entity.name;

          accessGroupPermission.accessGroup = accessGroup;
          accessGroupPermission.permission = permission;

          this.entity.accessGroupPermissions.push(accessGroupPermission);
        });
      }

      console.log(this.entity.accessGroupPermissions.map(a => a.permission.name));

    }


  }

  /**
   * Remove os ítens repetidos
   * @param permissionsLineares
   */
  removeDuplicates(permissionsLineares: Permission[]): Permission[] {
    for (let i = 0; i < permissionsLineares.length; i++) {
      for (let k = 0; k < permissionsLineares.length; k++) {
        if (permissionsLineares[i] && permissionsLineares[k]) {

          // Se encontrou nos filhos de dentro
          const founded = this.findPermission(permissionsLineares[i].lowerPermissions, permissionsLineares[k].id);
          if (founded) {
            (permissionsLineares[k] as any).toDelete = true;
          }

        }
      }
    }

    // Remove irmãos repetidos
    return permissionsLineares.filter(function (este, i) {
      return permissionsLineares.indexOf(este) === i;
    }).filter(permission => !(permission as any).toDelete);
  }

  /**
   *
   * @param permission
   */
  removePermission(permission: Permission) {

    const permissions: Permission[] = this.runToRemove(permission, this.entity.accessGroupPermissions.map(a => a.permission));

    this.entity.accessGroupPermissions = [];

    for (let i = 0; i < permissions.length; i++) {
      if (permissions[i]) {
        const accessGroupPermission: AccessGroupPermission = new AccessGroupPermission();

        // Remove recursividade
        const accessGroup: AccessGroup = new AccessGroup();
        accessGroup.id = this.entity.id;
        accessGroup.enable = this.entity.enable;
        accessGroup.name = this.entity.name;

        accessGroupPermission.accessGroup = accessGroup;
        accessGroupPermission.permission = permissions[i];

        this.entity.accessGroupPermissions.push(accessGroupPermission)
      }
    }

    console.log(this.entity.accessGroupPermissions.map(a => a.permission.name));
  }


  /**
   * @param permissionToRemove
   * @param linkedPermissions
   */
  public runToRemove(permissionToRemove: Permission, linkedPermissions: Permission[]): Permission[] {
    for (let i = 0; i < linkedPermissions.length; i++) {
      if (linkedPermissions[i] && permissionToRemove) {
        if (linkedPermissions[i].id === permissionToRemove.id) {
          const copia = linkedPermissions.slice();
          copia.splice(i, 1);
          return copia;
        } else if (linkedPermissions[i].lowerPermissions && linkedPermissions[i].lowerPermissions.length) {

          if (this.findPermission(linkedPermissions[i].lowerPermissions, permissionToRemove.id)) {
            const aux = linkedPermissions.slice();
            aux.splice(i, 1);
            return aux.concat(this.runToRemove(permissionToRemove, linkedPermissions[i].lowerPermissions));
          }

        }
      }
    }
    return linkedPermissions;
  }

  /**
   * Pesqusia a permissão pelo ID
   * @param permissions
   * @param id
   */
  public findPermission(permissions: Permission[], id: number): Permission {
    for (let i = 0; i < permissions.length; i++) {
      if (permissions[i]) {
        if (permissions[i].id === id)
          return permissions[i];
        else if (permissions[i].lowerPermissions && permissions[i].lowerPermissions.length) {
          const permission: Permission = this.findPermission(permissions[i].lowerPermissions, id);
          if (permission)
            return permission;
        }
      }
    }
  }
}
