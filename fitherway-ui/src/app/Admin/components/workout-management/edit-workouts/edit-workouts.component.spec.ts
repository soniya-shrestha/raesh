import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditWorkoutsComponent } from './edit-workouts.component';

describe('EditWorkoutsComponent', () => {
  let component: EditWorkoutsComponent;
  let fixture: ComponentFixture<EditWorkoutsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditWorkoutsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditWorkoutsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
