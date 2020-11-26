import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AemTestModule } from '../../../test.module';
import { NetworkMemberDetailComponent } from 'app/entities/network-member/network-member-detail.component';
import { NetworkMember } from 'app/shared/model/network-member.model';

describe('Component Tests', () => {
  describe('NetworkMember Management Detail Component', () => {
    let comp: NetworkMemberDetailComponent;
    let fixture: ComponentFixture<NetworkMemberDetailComponent>;
    const route = ({ data: of({ networkMember: new NetworkMember(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AemTestModule],
        declarations: [NetworkMemberDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(NetworkMemberDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NetworkMemberDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load networkMember on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.networkMember).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
