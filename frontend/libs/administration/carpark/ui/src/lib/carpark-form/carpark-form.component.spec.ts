import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarparkFormComponent } from './carpark-form.component';

describe('CarparkFormComponent', () => {
  let component: CarparkFormComponent;
  let fixture: ComponentFixture<CarparkFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarparkFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarparkFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
