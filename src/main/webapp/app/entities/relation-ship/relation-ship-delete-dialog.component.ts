import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRelationShip } from 'app/shared/model/relation-ship.model';
import { RelationShipService } from './relation-ship.service';

@Component({
  templateUrl: './relation-ship-delete-dialog.component.html',
})
export class RelationShipDeleteDialogComponent {
  relationShip?: IRelationShip;

  constructor(
    protected relationShipService: RelationShipService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.relationShipService.delete(id).subscribe(() => {
      this.eventManager.broadcast('relationShipListModification');
      this.activeModal.close();
    });
  }
}
