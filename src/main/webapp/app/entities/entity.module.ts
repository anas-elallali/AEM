import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'structures',
        loadChildren: () => import('./structures/structure.module').then(m => m.AemStructureModule),
      },
      {
        path: 'network',
        loadChildren: () => import('./network/network.module').then(m => m.AemNetworkModule),
      },
      {
        path: 'address',
        loadChildren: () => import('./address/address.module').then(m => m.AemAddressModule),
      },
      {
        path: 'network-member',
        loadChildren: () => import('./network-member/network-member.module').then(m => m.AemNetworkMemberModule),
      },
      {
        path: 'roles',
        loadChildren: () => import('./roles/roles.module').then(m => m.AemRolesModule),
      },
      {
        path: 'profile',
        loadChildren: () => import('./profile/profile.module').then(m => m.AemProfileModule),
      },
      {
        path: 'relation-ship',
        loadChildren: () => import('./relation-ship/relation-ship.module').then(m => m.AemRelationShipModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class AemEntityModule {}
