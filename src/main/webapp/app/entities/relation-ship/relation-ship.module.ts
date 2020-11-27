import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AemSharedModule } from 'app/shared/shared.module';
import { RelationShipComponent } from './relation-ship.component';
import { RelationShipDetailComponent } from './relation-ship-detail.component';
import { RelationShipUpdateComponent } from './relation-ship-update.component';
import { RelationShipDeleteDialogComponent } from './relation-ship-delete-dialog.component';
import { relationShipRoute } from './relation-ship.route';

@NgModule({
  imports: [AemSharedModule, RouterModule.forChild(relationShipRoute)],
  declarations: [RelationShipComponent, RelationShipDetailComponent, RelationShipUpdateComponent, RelationShipDeleteDialogComponent],
  entryComponents: [RelationShipDeleteDialogComponent],
})
export class AemRelationShipModule {}
