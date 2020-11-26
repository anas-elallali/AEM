export interface IRelationShip {
  id?: number;
  relationShip?: string;
  networkMemberId?: number;
}

export class RelationShip implements IRelationShip {
  constructor(public id?: number, public relationShip?: string, public networkMemberId?: number) {}
}
