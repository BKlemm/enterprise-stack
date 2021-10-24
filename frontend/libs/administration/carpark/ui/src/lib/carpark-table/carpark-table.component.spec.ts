import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarparkTableComponent } from './carpark-table.component';

describe('CarparkTableComponent', () => {
  let component: CarparkTableComponent;
  let fixture: ComponentFixture<CarparkTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarparkTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarparkTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
