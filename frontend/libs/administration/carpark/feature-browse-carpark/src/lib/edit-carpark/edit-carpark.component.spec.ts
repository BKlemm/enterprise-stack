import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCarparkComponent } from './edit-carpark.component';

describe('EditCarparkComponent', () => {
  let component: EditCarparkComponent;
  let fixture: ComponentFixture<EditCarparkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditCarparkComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCarparkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
