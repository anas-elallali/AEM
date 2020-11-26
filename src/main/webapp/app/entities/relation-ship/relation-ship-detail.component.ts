import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRelationShip } from 'app/shared/model/relation-ship.model';

@Component({
  selector: 'jhi-relation-ship-detail',
  templateUrl: './relation-ship-detail.component.html',
})
export class RelationShipDetailComponent implements OnInit {
  relationShip: IRelationShip | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ relationShip }) => (this.relationShip = relationShip));
  }

  previousState(): void {
    window.history.back();
  }
}
