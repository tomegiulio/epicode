import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveItemComponent } from './active-item.component';

describe('ActiveItemComponent', () => {
  let component: ActiveItemComponent;
  let fixture: ComponentFixture<ActiveItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActiveItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActiveItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
