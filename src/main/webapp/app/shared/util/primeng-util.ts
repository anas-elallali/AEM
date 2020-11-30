import { MenuItem } from 'primeng/api';
import { TranslateService } from '@ngx-translate/core';
import { Injectable } from '@angular/core';

@Injectable()
export class PrimeNgUtil {
  constructor(private translate: TranslateService) {}

  public buildBreadcrumb(breadcrumbArray: MenuItem[]): MenuItem[] {
    const items: MenuItem[] = JSON.parse(JSON.stringify(breadcrumbArray));
    items.forEach(item => {
      if (item.title) {
        item.label = this.translate.instant(item.title);
      }
    });
    return items;
  }
}
