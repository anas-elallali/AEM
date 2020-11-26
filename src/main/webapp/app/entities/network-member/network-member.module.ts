import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AemSharedModule } from 'app/shared/shared.module';
import { NetworkMemberComponent } from './network-member.component';
import { NetworkMemberDetailComponent } from './network-member-detail.component';
import { NetworkMemberUpdateComponent } from './network-member-update.component';
import { NetworkMemberDeleteDialogComponent } from './network-member-delete-dialog.component';
import { networkMemberRoute } from './network-member.route';

@NgModule({
  imports: [AemSharedModule, RouterModule.forChild(networkMemberRoute)],
  declarations: [NetworkMemberComponent, NetworkMemberDetailComponent, NetworkMemberUpdateComponent, NetworkMemberDeleteDialogComponent],
  entryComponents: [NetworkMemberDeleteDialogComponent],
})
export class AemNetworkMemberModule {}
