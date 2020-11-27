import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { INetwork, Network } from 'app/shared/model/network.model';
import { NetworkService } from './network.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IAddress } from 'app/shared/model/address.model';
import { AddressService } from 'app/entities/address/address.service';
import { IProfile } from 'app/shared/model/profile.model';
import { ProfileService } from 'app/entities/profile/profile.service';

type SelectableEntity = INetwork | IAddress | IProfile;

@Component({
  selector: 'jhi-network-update',
  templateUrl: './network-update.component.html',
})
export class NetworkUpdateComponent implements OnInit {
  isSaving = false;
  parentnetworks: INetwork[] = [];
  addresses: IAddress[] = [];
  profiles: IProfile[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    description: [null, [Validators.maxLength(500)]],
    avatar: [],
    avatarContentType: [],
    type: [null, [Validators.required]],
    status: [null, [Validators.required]],
    parentNetworkId: [],
    addressId: [],
    ownerId: [],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected networkService: NetworkService,
    protected addressService: AddressService,
    protected profileService: ProfileService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ network }) => {
      this.updateForm(network);

      this.networkService
        .query({ filter: 'network-is-null' })
        .pipe(
          map((res: HttpResponse<INetwork[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: INetwork[]) => {
          if (!network.parentNetworkId) {
            this.parentnetworks = resBody;
          } else {
            this.networkService
              .find(network.parentNetworkId)
              .pipe(
                map((subRes: HttpResponse<INetwork>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: INetwork[]) => (this.parentnetworks = concatRes));
          }
        });

      this.addressService
        .query({ filter: 'network-is-null' })
        .pipe(
          map((res: HttpResponse<IAddress[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IAddress[]) => {
          if (!network.addressId) {
            this.addresses = resBody;
          } else {
            this.addressService
              .find(network.addressId)
              .pipe(
                map((subRes: HttpResponse<IAddress>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IAddress[]) => (this.addresses = concatRes));
          }
        });

      this.profileService.query().subscribe((res: HttpResponse<IProfile[]>) => (this.profiles = res.body || []));
    });
  }

  updateForm(network: INetwork): void {
    this.editForm.patchValue({
      id: network.id,
      name: network.name,
      description: network.description,
      avatar: network.avatar,
      avatarContentType: network.avatarContentType,
      type: network.type,
      status: network.status,
      parentNetworkId: network.parentNetworkId,
      addressId: network.addressId,
      ownerId: network.ownerId,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: any, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('aemApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null,
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const network = this.createFromForm();
    if (network.id !== undefined) {
      this.subscribeToSaveResponse(this.networkService.update(network));
    } else {
      this.subscribeToSaveResponse(this.networkService.create(network));
    }
  }

  private createFromForm(): INetwork {
    return {
      ...new Network(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      avatarContentType: this.editForm.get(['avatarContentType'])!.value,
      avatar: this.editForm.get(['avatar'])!.value,
      type: this.editForm.get(['type'])!.value,
      status: this.editForm.get(['status'])!.value,
      parentNetworkId: this.editForm.get(['parentNetworkId'])!.value,
      addressId: this.editForm.get(['addressId'])!.value,
      ownerId: this.editForm.get(['ownerId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INetwork>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
