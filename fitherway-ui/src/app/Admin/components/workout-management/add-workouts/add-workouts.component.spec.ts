import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddWorkoutsComponent } from './add-workouts.component';

describe('AddWorkoutsComponent', () => {
  let component: AddWorkoutsComponent;
  let fixture: ComponentFixture<AddWorkoutsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddWorkoutsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddWorkoutsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
