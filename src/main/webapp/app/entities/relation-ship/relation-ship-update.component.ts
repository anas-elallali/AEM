import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRelationShip, RelationShip } from 'app/shared/model/relation-ship.model';
import { RelationShipService } from './relation-ship.service';
import { INetworkMember } from 'app/shared/model/network-member.model';
import { NetworkMemberService } from 'app/entities/network-member/network-member.service';

@Component({
  selector: 'jhi-relation-ship-update',
  templateUrl: './relation-ship-update.component.html',
})
export class RelationShipUpdateComponent implements OnInit {
  isSaving = false;
  networkmembers: INetworkMember[] = [];

  editForm = this.fb.group({
    id: [],
    relationShip: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
    networkMemberId: [null, Validators.required],
  });

  constructor(
    protected relationShipService: RelationShipService,
    protected networkMemberService: NetworkMemberService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ relationShip }) => {
      this.updateForm(relationShip);

      this.networkMemberService.query().subscribe((res: HttpResponse<INetworkMember[]>) => (this.networkmembers = res.body || []));
    });
  }

  updateForm(relationShip: IRelationShip): void {
    this.editForm.patchValue({
      id: relationShip.id,
      relationShip: relationShip.relationShip,
      networkMemberId: relationShip.networkMemberId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const relationShip = this.createFromForm();
    if (relationShip.id !== undefined) {
      this.subscribeToSaveResponse(this.relationShipService.update(relationShip));
    } else {
      this.subscribeToSaveResponse(this.relationShipService.create(relationShip));
    }
  }

  private createFromForm(): IRelationShip {
    return {
      ...new RelationShip(),
      id: this.editForm.get(['id'])!.value,
      relationShip: this.editForm.get(['relationShip'])!.value,
      networkMemberId: this.editForm.get(['networkMemberId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRelationShip>>): void {
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
