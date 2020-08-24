import {Injectable} from '@angular/core';
import {BaseRepository} from "../../infrastructure/repository/base/base.repository";
import {HttpClient} from "@angular/common/http";
import {AccessGroup} from "../entity/access-group.model";

@Injectable()
export class AccessGroupRepository extends BaseRepository<AccessGroup> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'account-manager/access-groups');
  }

}
