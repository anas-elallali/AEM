import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INetworkMember, NetworkMember } from 'app/shared/model/network-member.model';
import { NetworkMemberService } from './network-member.service';
import { NetworkMemberComponent } from './network-member.component';
import { NetworkMemberDetailComponent } from './network-member-detail.component';
import { NetworkMemberUpdateComponent } from './network-member-update.component';

@Injectable({ providedIn: 'root' })
export class NetworkMemberResolve implements Resolve<INetworkMember> {
  constructor(private service: NetworkMemberService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INetworkMember> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((networkMember: HttpResponse<NetworkMember>) => {
          if (networkMember.body) {
            return of(networkMember.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NetworkMember());
  }
}

export const networkMemberRoute: Routes = [
  {
    path: '',
    component: NetworkMemberComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'aemApp.networkMember.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NetworkMemberDetailComponent,
    resolve: {
      networkMember: NetworkMemberResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aemApp.networkMember.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NetworkMemberUpdateComponent,
    resolve: {
      networkMember: NetworkMemberResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aemApp.networkMember.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NetworkMemberUpdateComponent,
    resolve: {
      networkMember: NetworkMemberResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aemApp.networkMember.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
