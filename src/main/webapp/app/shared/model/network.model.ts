import { INetworkMember } from 'app/shared/model/network-member.model';
import { NetworkType } from 'app/shared/model/enumerations/network-type.model';

export interface INetwork {
  id?: number;
  name?: string;
  description?: string;
  avatarContentType?: string;
  avatar?: any;
  type?: NetworkType;
  status?: boolean;
  parentNetworkId?: number;
  addressId?: number;
  networkMembers?: INetworkMember[];
  ownerId?: number;
}

export class Network implements INetwork {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public avatarContentType?: string,
    public avatar?: any,
    public type?: NetworkType,
    public status?: boolean,
    public parentNetworkId?: number,
    public addressId?: number,
    public networkMembers?: INetworkMember[],
    public ownerId?: number
  ) {
    this.status = this.status || false;
  }
}
