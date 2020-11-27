import { IRoles } from 'app/shared/model/roles.model';

export interface INetworkMember {
  id?: number;
  roles?: IRoles[];
  networkId?: number;
  profileId?: number;
}

export class NetworkMember implements INetworkMember {
  constructor(public id?: number, public roles?: IRoles[], public networkId?: number, public profileId?: number) {}
}
