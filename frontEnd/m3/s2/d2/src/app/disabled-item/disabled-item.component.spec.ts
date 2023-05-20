import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisabledItemComponent } from './disabled-item.component';

describe('DisabledItemComponent', () => {
  let component: DisabledItemComponent;
  let fixture: ComponentFixture<DisabledItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisabledItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisabledItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
