import { LoginPageComponent } from 'app/layouts/login/login-page.component';
import { Route } from '@angular/router';

export const LOGIN_PAGE_ROUTE: Route = {
  path: 'login',
  component: LoginPageComponent,
  data: {
    authorities: [],
    pageTitle: 'login.title',
  },
};
