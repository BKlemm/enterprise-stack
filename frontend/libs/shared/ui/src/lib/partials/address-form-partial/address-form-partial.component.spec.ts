import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddressFormPartialComponent } from './address-form-partial.component';

describe('AddressFormPartialComponent', () => {
  let component: AddressFormPartialComponent;
  let fixture: ComponentFixture<AddressFormPartialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddressFormPartialComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddressFormPartialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
