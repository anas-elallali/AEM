import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AemSharedModule } from 'app/shared/shared.module';
import { StructureComponent } from './structure.component';
import { structureRoute } from './structureRoute';

@NgModule({
  imports: [AemSharedModule, RouterModule.forChild(structureRoute)],
  declarations: [StructureComponent],
  entryComponents: [],
})
export class AemStructureModule {}
