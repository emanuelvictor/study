import {Abstract} from "./abstract/abstract.model";
import {AccessGroupPermission} from "./access-group-permission.model";

export class AccessGroup extends Abstract {

  /**
   *
   */
  public name: string;

  /**
   *
   */
  public enable: boolean;

  /**
   *
   */
  public accessGroupPermissions: AccessGroupPermission[];


}