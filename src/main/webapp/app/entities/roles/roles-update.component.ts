import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRoles, Roles } from 'app/shared/model/roles.model';
import { RolesService } from './roles.service';
import { INetworkMember } from 'app/shared/model/network-member.model';
import { NetworkMemberService } from 'app/entities/network-member/network-member.service';

@Component({
  selector: 'jhi-roles-update',
  templateUrl: './roles-update.component.html',
})
export class RolesUpdateComponent implements OnInit {
  isSaving = false;
  networkmembers: INetworkMember[] = [];

  editForm = this.fb.group({
    id: [],
    role: [null, [Validators.required]],
    networkMemberId: [null, Validators.required],
  });

  constructor(
    protected rolesService: RolesService,
    protected networkMemberService: NetworkMemberService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ roles }) => {
      this.updateForm(roles);

      this.networkMemberService.query().subscribe((res: HttpResponse<INetworkMember[]>) => (this.networkmembers = res.body || []));
    });
  }

  updateForm(roles: IRoles): void {
    this.editForm.patchValue({
      id: roles.id,
      role: roles.role,
      networkMemberId: roles.networkMemberId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const roles = this.createFromForm();
    if (roles.id !== undefined) {
      this.subscribeToSaveResponse(this.rolesService.update(roles));
    } else {
      this.subscribeToSaveResponse(this.rolesService.create(roles));
    }
  }

  private createFromForm(): IRoles {
    return {
      ...new Roles(),
      id: this.editForm.get(['id'])!.value,
      role: this.editForm.get(['role'])!.value,
      networkMemberId: this.editForm.get(['networkMemberId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRoles>>): void {
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

  trackById(index: number, item: INetworkMember): any {
    return item.id;
  }
}
