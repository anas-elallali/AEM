import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INetwork, Network } from 'app/shared/model/network.model';
import { NetworkService } from '../network/network.service';
import { StructureComponent } from './structure.component';

@Injectable({ providedIn: 'root' })
export class NetworkResolve implements Resolve<INetwork> {
  constructor(private service: NetworkService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INetwork> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((network: HttpResponse<Network>) => {
          if (network.body) {
            return of(network.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Network());
  }
}

export const structureRoute: Routes = [
  {
    path: '',
    component: StructureComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'aemApp.structure.home.title',
      breadcrumb: [
        {
          title: 'aemApp.structure.home.title',
        },
      ],
    },
    canActivate: [UserRouteAccessService],
  }
];
