import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NutritionPlan } from '../../../model/nutrition-plan';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../../service/admin.service';

@Component({
  selector: 'app-edit-nutrition',
  templateUrl: './edit-nutrition.component.html',
  styleUrl: './edit-nutrition.component.scss'
})
export class EditNutritionComponent implements OnInit {

  planForm!: FormGroup;
  selectedFile: File | null = null;
  planId!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private nutritionService: AdminService
  ) {}

  ngOnInit(): void {
    this.planId = Number(this.route.snapshot.paramMap.get('id'));
    this.initializeForm();
    this.loadPlanData();
  }

  initializeForm(): void {
    this.planForm = this.fb.group({
      title: ['', Validators.required],
      type: ['', Validators.required],
      description: ['', Validators.required],
      caloriesPerDay: [0, [Validators.required, Validators.min(1)]],
      goalType: ['', Validators.required],
      bmiLevel: ['', Validators.required],
      status: [true]
    });
  }

  loadPlanData(): void {
    this.nutritionService.getById(this.planId).subscribe({
      next: (plan: NutritionPlan) => {
        this.planForm.patchValue({
          title: plan.title,
          type: plan.type,
          description: plan.description,
          caloriesPerDay: plan.caloriesPerDay,
          goalType: plan.goalType,
          bmiLevel: plan.bmiLevel,
          status: plan.status
        });
      },
      error: (err) => {
        console.error('Failed to load nutrition plan', err);
      }
    });
  }

  onFileChange(event: any): void {
    const file = event.target.files?.[0];
    if (file) {
      this.selectedFile = file;
    }
  }

  onSubmit(): void {
    if (this.planForm.invalid) return;

    const formData = new FormData();
    const formValues = this.planForm.value;

    formData.append('title', formValues.title);
    formData.append('type', formValues.type);
    formData.append('description', formValues.description);
    formData.append('caloriesPerDay', formValues.caloriesPerDay);
    formData.append('goalType', formValues.goalType);
    formData.append('bmiLevel', formValues.bmiLevel);
    formData.append('status', formValues.status);
    if (this.selectedFile) {
      formData.append('imageFile', this.selectedFile);
    }

    this.nutritionService.update(this.planId, formData).subscribe({
      next: () => {
        alert('Nutrition Plan updated successfully!');
        this.router.navigate(['/admin/nutrition']);
      },
      error: (err) => {
        console.error('Update failed', err);
        alert('Failed to update nutrition plan');
      }
    });
  }
}
