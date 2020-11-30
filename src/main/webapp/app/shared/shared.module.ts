import { NgModule } from '@angular/core';
import { AemSharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { LoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { BreadcrumbComponent } from 'app/shared/breadcrumb/breadcrumb.component';

@NgModule({
  imports: [AemSharedLibsModule],
  declarations: [
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    BreadcrumbComponent,
  ],
  entryComponents: [LoginModalComponent],
  exports: [
    AemSharedLibsModule,
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    BreadcrumbComponent,
  ],
})
export class AemSharedModule {}
