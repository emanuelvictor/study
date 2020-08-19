import {People} from './people.model';
import {UserDetails} from "../../infrastructure/authentication/user-details";
import {AccessGroup} from "./access-group.model";

export class User extends People implements UserDetails {

  /**
   *
   */
  public email: string;

  /**
   *
   */
  public password: string;

  /**
   *
   */
  public internal: boolean = false;

  /**
   *
   */
  public enable: boolean = true;

  /**
   *
   */
  public accessGroup: AccessGroup;

  /**
   *
   */
  public isManager: boolean = false;

  /**
   *
   */
  public root: boolean = false;

  /**
   *
   */
  public username: string;

  /**
   *
   */
  public authorities: any;

  /**
   *
   */
  constructor(id?: number) {
    super();
    if (id)
      this.id = id;
    else
      this.root = false;
  }

  /**
   *
   */
  get isRoot(): boolean {
    return this.root;
  }

}
