import { NgModule } from '@angular/core';
import { AemSharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { BreadcrumbComponent } from 'app/shared/breadcrumb/breadcrumb.component';
import { AemDataTableComponent } from 'app/shared/data-table/aem-data-table.component';

@NgModule({
  imports: [AemSharedLibsModule],
  declarations: [
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    HasAnyAuthorityDirective,
    BreadcrumbComponent,
    AemDataTableComponent,
  ],
  exports: [
    AemSharedLibsModule,
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    HasAnyAuthorityDirective,
    BreadcrumbComponent,
    AemDataTableComponent,
  ],
})
export class AemSharedModule {}
