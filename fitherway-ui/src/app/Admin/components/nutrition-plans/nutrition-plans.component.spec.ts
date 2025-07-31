import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NutritionPlansComponent } from './nutrition-plans.component';

describe('NutritionPlansComponent', () => {
  let component: NutritionPlansComponent;
  let fixture: ComponentFixture<NutritionPlansComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NutritionPlansComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NutritionPlansComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
