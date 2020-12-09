import { NgModule } from '@angular/core';
import { AccordionModule } from 'primeng/accordion';
import { ToastModule } from 'primeng/toast';
import { ButtonModule } from 'primeng/button';
import { BreadcrumbModule } from 'primeng/breadcrumb';
import { PrimeNgUtil } from 'app/shared/util/primeng-util';
import { RippleModule } from 'primeng/ripple';
import { TableModule } from 'primeng/table';
import { DropdownModule } from 'primeng/dropdown';

@NgModule({
  exports: [AccordionModule, ButtonModule, ToastModule, BreadcrumbModule, RippleModule, TableModule, DropdownModule],
  providers: [PrimeNgUtil],
})
export class PrimengModule {}
