import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCarparkComponent } from './create-carpark.component';

describe('CreateCarparkComponent', () => {
  let component: CreateCarparkComponent;
  let fixture: ComponentFixture<CreateCarparkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateCarparkComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCarparkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
