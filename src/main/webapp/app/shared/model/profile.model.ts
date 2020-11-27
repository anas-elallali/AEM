import { Moment } from 'moment';
import { INetwork } from 'app/shared/model/network.model';
import { INetworkMember } from 'app/shared/model/network-member.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface IProfile {
  id?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  avatarContentType?: string;
  avatar?: any;
  gender?: Gender;
  birthDate?: Moment;
  phoneNumber?: string;
  addressId?: number;
  userId?: number;
  networks?: INetwork[];
  networkMembers?: INetworkMember[];
}

export class Profile implements IProfile {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public avatarContentType?: string,
    public avatar?: any,
    public gender?: Gender,
    public birthDate?: Moment,
    public phoneNumber?: string,
    public addressId?: number,
    public userId?: number,
    public networks?: INetwork[],
    public networkMembers?: INetworkMember[]
  ) {}
}
