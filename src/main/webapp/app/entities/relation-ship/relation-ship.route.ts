import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRelationShip, RelationShip } from 'app/shared/model/relation-ship.model';
import { RelationShipService } from './relation-ship.service';
import { RelationShipComponent } from './relation-ship.component';
import { RelationShipDetailComponent } from './relation-ship-detail.component';
import { RelationShipUpdateComponent } from './relation-ship-update.component';

@Injectable({ providedIn: 'root' })
export class RelationShipResolve implements Resolve<IRelationShip> {
  constructor(private service: RelationShipService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRelationShip> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((relationShip: HttpResponse<RelationShip>) => {
          if (relationShip.body) {
            return of(relationShip.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RelationShip());
  }
}

export const relationShipRoute: Routes = [
  {
    path: '',
    component: RelationShipComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'aemApp.relationShip.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RelationShipDetailComponent,
    resolve: {
      relationShip: RelationShipResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aemApp.relationShip.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RelationShipUpdateComponent,
    resolve: {
      relationShip: RelationShipResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aemApp.relationShip.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RelationShipUpdateComponent,
    resolve: {
      relationShip: RelationShipResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aemApp.relationShip.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
