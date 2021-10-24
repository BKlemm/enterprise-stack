import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseCarparkComponent } from './browse-carpark.component';

describe('ShowCarparksComponent', () => {
  let component: BrowseCarparkComponent;
  let fixture: ComponentFixture<BrowseCarparkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BrowseCarparkComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BrowseCarparkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
