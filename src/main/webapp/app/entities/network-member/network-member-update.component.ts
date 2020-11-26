import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INetworkMember, NetworkMember } from 'app/shared/model/network-member.model';
import { NetworkMemberService } from './network-member.service';
import { INetwork } from 'app/shared/model/network.model';
import { NetworkService } from 'app/entities/network/network.service';
import { IProfile } from 'app/shared/model/profile.model';
import { ProfileService } from 'app/entities/profile/profile.service';

type SelectableEntity = INetwork | IProfile;

@Component({
  selector: 'jhi-network-member-update',
  templateUrl: './network-member-update.component.html',
})
export class NetworkMemberUpdateComponent implements OnInit {
  isSaving = false;
  networks: INetwork[] = [];
  profiles: IProfile[] = [];

  editForm = this.fb.group({
    id: [],
    networkId: [null, Validators.required],
    profileId: [null, Validators.required],
  });

  constructor(
    protected networkMemberService: NetworkMemberService,
    protected networkService: NetworkService,
    protected profileService: ProfileService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ networkMember }) => {
      this.updateForm(networkMember);

      this.networkService.query().subscribe((res: HttpResponse<INetwork[]>) => (this.networks = res.body || []));

      this.profileService.query().subscribe((res: HttpResponse<IProfile[]>) => (this.profiles = res.body || []));
    });
  }

  updateForm(networkMember: INetworkMember): void {
    this.editForm.patchValue({
      id: networkMember.id,
      networkId: networkMember.networkId,
      profileId: networkMember.profileId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const networkMember = this.createFromForm();
    if (networkMember.id !== undefined) {
      this.subscribeToSaveResponse(this.networkMemberService.update(networkMember));
    } else {
      this.subscribeToSaveResponse(this.networkMemberService.create(networkMember));
    }
  }

  private createFromForm(): INetworkMember {
    return {
      ...new NetworkMember(),
      id: this.editForm.get(['id'])!.value,
      networkId: this.editForm.get(['networkId'])!.value,
      profileId: this.editForm.get(['profileId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INetworkMember>>): void {
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
