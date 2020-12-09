import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { ITEMS_PAGINATION_OPTIONS, ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { FieldsType } from 'app/shared/constants/data-table.constants';

@Component({
  selector: 'aem-data-table',
  templateUrl: './aem-data-table.component.html',
  styleUrls: ['aem-data-table.scss'],
})
export class AemDataTableComponent implements OnInit, OnDestroy {
  @Input() entities: any[] = [];

  @Input() entitiesFieldsType?: any = undefined;

  @Input() predicate!: string;
  @Output() predicateChange = new EventEmitter<string>();

  @Input() ascending!: boolean;
  @Output() ascendingChange = new EventEmitter<boolean>();

  @Output() sortCallBack = new EventEmitter<boolean>();

  @Input() nameRoute?: string = undefined;

  @Output() onDeleteItem = new EventEmitter<any>();

  @Input() itemsPerPage = ITEMS_PER_PAGE;
  @Output() itemsPerPageChange: EventEmitter<number> = new EventEmitter<number>();

  @Input() totalItems = 0;

  @Output() onPageChange = new EventEmitter<any>();

  @Input() paginationPage = 1;

  options = ITEMS_PAGINATION_OPTIONS;

  showPaginationPageOptions = false;

  fildsNames: string[] = [];

  FieldsType = FieldsType;

  ngOnInit(): void {
    console.log('');
    this.fildsNames = Object.keys(this.entitiesFieldsType);
    this.showPaginationPageOptions = this.options.includes(this.itemsPerPage);

    console.log('AEL Keys', Object.keys(this.entitiesFieldsType));
  }
  test(entitiesFieldsTypeCol: any): boolean {
    console.log('AEL entitiesFieldsTypeCol == FieldsType.IMAGE  ', entitiesFieldsTypeCol === FieldsType.IMAGE);
    if (entitiesFieldsTypeCol === FieldsType.IMAGE) {
      return true;
    }
    return false;
  }
  onAscendingChange(event: any): void {
    console.log('AEL onAscendingChange call', event);
    this.ascending = event;
    this.ascendingChange.emit(this.ascending);
  }

  onPredicateChange(event: any): void {
    console.log('AEL onPredicateChange call', event);
    this.predicate = event;
    this.predicateChange.emit(this.predicate);
  }

  deleteItem(entity: any): void {
    this.onDeleteItem.emit(entity);
  }

  ngOnDestroy(): void {}

  callBack(e: any): void {
    console.log('AEL callback call ', e);
    this.sortCallBack.emit();
  }

  loadPageDataTable(event: number): void {
    // this.paginationPageChange.emit(this.paginationPage);
    this.onPageChange.emit({ page: this.paginationPage, itemsPerPageCount: this.itemsPerPage });
    console.log('AEL loadPage', event);
  }

  onChangeItemsPerPage(event: number): void {
    console.log('AEL onChangeItemsPerPage', event, this.itemsPerPage);
    this.paginationPage = 1;
    this.itemsPerPage = event;
    this.onPageChange.emit({ page: this.paginationPage, itemsPerPageCount: event });
    // this.itemsPerPageChange.emit(event);
  }
}
