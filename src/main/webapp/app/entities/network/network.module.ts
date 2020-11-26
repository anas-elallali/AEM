import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AemSharedModule } from 'app/shared/shared.module';
import { NetworkComponent } from './network.component';
import { NetworkDetailComponent } from './network-detail.component';
import { NetworkUpdateComponent } from './network-update.component';
import { NetworkDeleteDialogComponent } from './network-delete-dialog.component';
import { networkRoute } from './network.route';

@NgModule({
  imports: [AemSharedModule, RouterModule.forChild(networkRoute)],
  declarations: [NetworkComponent, NetworkDetailComponent, NetworkUpdateComponent, NetworkDeleteDialogComponent],
  entryComponents: [NetworkDeleteDialogComponent],
})
export class AemNetworkModule {}
