import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AemTestModule } from '../../../test.module';
import { NetworkMemberUpdateComponent } from 'app/entities/network-member/network-member-update.component';
import { NetworkMemberService } from 'app/entities/network-member/network-member.service';
import { NetworkMember } from 'app/shared/model/network-member.model';

describe('Component Tests', () => {
  describe('NetworkMember Management Update Component', () => {
    let comp: NetworkMemberUpdateComponent;
    let fixture: ComponentFixture<NetworkMemberUpdateComponent>;
    let service: NetworkMemberService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AemTestModule],
        declarations: [NetworkMemberUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NetworkMemberUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NetworkMemberUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NetworkMemberService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NetworkMember(123);
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
        const entity = new NetworkMember();
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
