import { Role } from 'app/shared/model/enumerations/role.model';

export interface IRoles {
  id?: number;
  role?: Role;
  networkMemberId?: number;
}

export class Roles implements IRoles {
  constructor(public id?: number, public role?: Role, public networkMemberId?: number) {}
}
