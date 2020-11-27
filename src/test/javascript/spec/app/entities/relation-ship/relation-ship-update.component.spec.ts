import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AemTestModule } from '../../../test.module';
import { RelationShipUpdateComponent } from 'app/entities/relation-ship/relation-ship-update.component';
import { RelationShipService } from 'app/entities/relation-ship/relation-ship.service';
import { RelationShip } from 'app/shared/model/relation-ship.model';

describe('Component Tests', () => {
  describe('RelationShip Management Update Component', () => {
    let comp: RelationShipUpdateComponent;
    let fixture: ComponentFixture<RelationShipUpdateComponent>;
    let service: RelationShipService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AemTestModule],
        declarations: [RelationShipUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RelationShipUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RelationShipUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RelationShipService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RelationShip(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new RelationShip();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
