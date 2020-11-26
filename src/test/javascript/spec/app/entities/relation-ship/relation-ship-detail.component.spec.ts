import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AemTestModule } from '../../../test.module';
import { RelationShipDetailComponent } from 'app/entities/relation-ship/relation-ship-detail.component';
import { RelationShip } from 'app/shared/model/relation-ship.model';

describe('Component Tests', () => {
  describe('RelationShip Management Detail Component', () => {
    let comp: RelationShipDetailComponent;
    let fixture: ComponentFixture<RelationShipDetailComponent>;
    const route = ({ data: of({ relationShip: new RelationShip(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AemTestModule],
        declarations: [RelationShipDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RelationShipDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RelationShipDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load relationShip on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.relationShip).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
