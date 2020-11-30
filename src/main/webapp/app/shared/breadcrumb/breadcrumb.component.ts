import { Component, OnDestroy, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { ActivatedRoute } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { PrimeNgUtil } from 'app/shared/util/primeng-util';
import { Subscription } from 'rxjs';

@Component({
  selector: 'aem-breadcrumb',
  template: ` <div *ngIf="items && items.length > 0" class="mt-2 mb-2">
    <p-breadcrumb [model]="items" [home]="home"></p-breadcrumb>
  </div>`,
})
export class BreadcrumbComponent implements OnInit, OnDestroy {
  languageSubscription?: Subscription;
  items: MenuItem[] = [];
  home: MenuItem = { icon: 'pi pi-home', routerLink: ['/'] };

  constructor(protected activatedRoute: ActivatedRoute, private translate: TranslateService, private pngUtils: PrimeNgUtil) {}

  ngOnInit(): void {
    this.initBreadcrumb();
  }

  private initBreadcrumb(): void {
    let breadcrumb: MenuItem[] = this.activatedRoute.snapshot.data['breadcrumb'] ? this.activatedRoute.snapshot.data['breadcrumb'] : [];
    this.items = this.pngUtils.buildBreadcrumb(breadcrumb);
    this.languageSubscription = this.translate.onLangChange.subscribe(() => {
      breadcrumb = this.activatedRoute.snapshot.data['breadcrumb'] ? this.activatedRoute.snapshot.data['breadcrumb'] : [];
      this.items = this.pngUtils.buildBreadcrumb(breadcrumb);
    });
  }

  ngOnDestroy(): void {
    if (this.languageSubscription) {
      this.languageSubscription.unsubscribe();
    }
  }
}
