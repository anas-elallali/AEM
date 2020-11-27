import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRelationShip } from 'app/shared/model/relation-ship.model';

type EntityResponseType = HttpResponse<IRelationShip>;
type EntityArrayResponseType = HttpResponse<IRelationShip[]>;

@Injectable({ providedIn: 'root' })
export class RelationShipService {
  public resourceUrl = SERVER_API_URL + 'api/relation-ships';

  constructor(protected http: HttpClient) {}

  create(relationShip: IRelationShip): Observable<EntityResponseType> {
    return this.http.post<IRelationShip>(this.resourceUrl, relationShip, { observe: 'response' });
  }

  update(relationShip: IRelationShip): Observable<EntityResponseType> {
    return this.http.put<IRelationShip>(this.resourceUrl, relationShip, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRelationShip>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRelationShip[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
