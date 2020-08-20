import {Abstract} from "./abstract/abstract.model";
import {Permission} from "./permission.model";
import {AccessGroup} from "./access-group.model";

export class AccessGroupPermission extends Abstract {

  public permission: Permission;

  public accessGroup: AccessGroup;

}