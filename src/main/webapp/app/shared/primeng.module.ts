import { NgModule } from '@angular/core';
import { AccordionModule } from 'primeng/accordion';
import { ToastModule } from 'primeng/toast';
import { ButtonModule } from 'primeng/button';
import { BreadcrumbModule } from 'primeng/breadcrumb';
import { PrimeNgUtil } from 'app/shared/util/primeng-util';
import {RippleModule} from "primeng/ripple";
import {TableModule} from "primeng/table";

@NgModule({
  exports: [
    AccordionModule,
    ButtonModule,
    ToastModule,
    BreadcrumbModule,
    RippleModule,
    TableModule,
  ],
  providers: [PrimeNgUtil],
})
export class PrimengModule {}
