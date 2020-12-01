import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { AemSharedModule } from 'app/shared/shared.module';
import { AemCoreModule } from 'app/core/core.module';
import { AemAppRoutingModule } from './app-routing.module';
import { AemHomeModule } from './home/home.module';
import { AemEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginPageComponent } from 'app/layouts/login/login-page.component';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AemSharedModule,
    AemCoreModule,
    AemHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    AemEntityModule,
    AemAppRoutingModule,
  ],
  declarations: [
    MainComponent,
    NavbarComponent,
    ErrorComponent,
    PageRibbonComponent,
    ActiveMenuDirective,
    FooterComponent,
    LoginPageComponent,
  ],
  bootstrap: [MainComponent],
})
export class AemAppModule {}
