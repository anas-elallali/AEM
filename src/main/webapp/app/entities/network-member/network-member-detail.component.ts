import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INetworkMember } from 'app/shared/model/network-member.model';

@Component({
  selector: 'jhi-network-member-detail',
  templateUrl: './network-member-detail.component.html',
})
export class NetworkMemberDetailComponent implements OnInit {
  networkMember: INetworkMember | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ networkMember }) => (this.networkMember = networkMember));
  }

  previousState(): void {
    window.history.back();
  }
}
