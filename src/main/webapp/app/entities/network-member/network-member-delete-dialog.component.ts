import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INetworkMember } from 'app/shared/model/network-member.model';
import { NetworkMemberService } from './network-member.service';

@Component({
  templateUrl: './network-member-delete-dialog.component.html',
})
export class NetworkMemberDeleteDialogComponent {
  networkMember?: INetworkMember;

  constructor(
    protected networkMemberService: NetworkMemberService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.networkMemberService.delete(id).subscribe(() => {
      this.eventManager.broadcast('networkMemberListModification');
      this.activeModal.close();
    });
  }
}
