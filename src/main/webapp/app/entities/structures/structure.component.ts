import { Component, OnDestroy, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data, ParamMap, Router } from '@angular/router';
import { combineLatest, Subscription } from 'rxjs';
import { JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INetwork } from 'app/shared/model/network.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IFieldsOfStructureList } from 'app/shared/model/data-table/structure.model';
import { FieldsType } from 'app/shared/constants/data-table.constants';
import { StructureService } from 'app/entities/structures/structure.service';

@Component({
  selector: 'structure',
  templateUrl: './structure.component.html',
})
export class StructureComponent implements OnInit, OnDestroy {
  structures?: INetwork[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  structureFieldsType?: IFieldsOfStructureList;

  constructor(
    protected structureService: StructureService,
    protected activatedRoute: ActivatedRoute,
    protected dataUtils: JhiDataUtils,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number, dontNavigate?: boolean, itemsPerPageCount?: number): void {
    const pageToLoad: number = page || this.page || 1;
    this.itemsPerPage = itemsPerPageCount || ITEMS_PER_PAGE;
    //TODO le cas de compoenent generique itemsPerPageCount tjrs null ??

    console.log('AEL pageToLoad ', pageToLoad - 1);
    console.log('AEL itemsPerPage', this.itemsPerPage);
    console.log('AEL sort', this.sort());

    this.structureService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<INetwork[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.structureFieldsType = this.buildStructureFieldsType();
    this.handleNavigation();
    this.registerChangeInNetworks();
  }

  protected handleNavigation(): void {
    combineLatest(this.activatedRoute.data, this.activatedRoute.queryParamMap, (data: Data, params: ParamMap) => {
      const page = params.get('page');
      const size = params.get('size');
      const itemsPerPage = size ? +size : this.itemsPerPage;
      const pageNumber = page !== null ? +page : 1;
      const sort = (params.get('sort') ?? data['defaultSort']).split(',');
      const predicate = sort[0];
      const ascending = sort[1] === 'asc';
      if (pageNumber !== this.page || predicate !== this.predicate || ascending !== this.ascending) {
        this.predicate = predicate;
        this.ascending = ascending;
        this.loadPage(pageNumber, true, itemsPerPage);
      }
    }).subscribe();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INetwork): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInNetworks(): void {
    this.eventSubscriber = this.eventManager.subscribe('networkListModification', () => this.loadPage());
  }

  delete(structure: INetwork): void {
    console.log('AEL network to delete ', structure);
    // const modalRef = this.modalService.open(NetworkDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    // modalRef.componentInstance.network = network;
  }

  sort(): string[] {
    console.log('AEL sort func predicate ', this.predicate);
    console.log('AEL sort func ascending ', this.ascending);
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: INetwork[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    console.log('onSuccess', data);
    if (navigate) {
      this.router.navigate(['/structures'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.structures = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ? this.page : 1;
  }

  private buildStructureFieldsType(): IFieldsOfStructureList {
    return {
      avatar: FieldsType.IMAGE,
      name: FieldsType.TEXT,
      description: FieldsType.TEXT,
      type: FieldsType.TEXT,
      status: FieldsType.TEXT,
    };
  }
}
