import { AddressType } from 'app/shared/model/enumerations/address-type.model';

export interface IAddress {
  id?: number;
  street?: string;
  additional?: string;
  city?: string;
  type?: AddressType;
  zipCode?: string;
}

export class Address implements IAddress {
  constructor(
    public id?: number,
    public street?: string,
    public additional?: string,
    public city?: string,
    public type?: AddressType,
    public zipCode?: string
  ) {}
}
