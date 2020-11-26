import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INetworkMember } from 'app/shared/model/network-member.model';

type EntityResponseType = HttpResponse<INetworkMember>;
type EntityArrayResponseType = HttpResponse<INetworkMember[]>;

@Injectable({ providedIn: 'root' })
export class NetworkMemberService {
  public resourceUrl = SERVER_API_URL + 'api/network-members';

  constructor(protected http: HttpClient) {}

  create(networkMember: INetworkMember): Observable<EntityResponseType> {
    return this.http.post<INetworkMember>(this.resourceUrl, networkMember, { observe: 'response' });
  }

  update(networkMember: INetworkMember): Observable<EntityResponseType> {
    return this.http.put<INetworkMember>(this.resourceUrl, networkMember, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INetworkMember>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INetworkMember[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
