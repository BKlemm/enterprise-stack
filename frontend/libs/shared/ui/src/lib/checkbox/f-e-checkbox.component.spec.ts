import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FECheckboxComponent } from './f-e-checkbox.component';

describe('CheckboxComponent', () => {
  let component: FECheckboxComponent;
  let fixture: ComponentFixture<FECheckboxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FECheckboxComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FECheckboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
