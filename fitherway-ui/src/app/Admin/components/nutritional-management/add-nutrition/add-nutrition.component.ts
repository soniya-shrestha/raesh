import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../../../service/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-nutrition',
  templateUrl: './add-nutrition.component.html',
  styleUrl: './add-nutrition.component.scss'
})
export class AddNutritionComponent implements OnInit {

  planForm!: FormGroup;
  imageFile: File | null = null;

  constructor(
    private fb: FormBuilder,
    private service: AdminService,
    private router: Router
  ) {}

  ngOnInit() {
    this.planForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      type: ['', Validators.required],
      caloriesPerDay: [0, [Validators.required, Validators.min(1)]],
      bmiLevel: ['', Validators.required],
      goalType: ['', Validators.required],
      status: [true],
      imageUrl: [null]
    });
  }

  onFileChange(event: any) {
    const file = event.target.files?.[0];
    if (file) {
      this.imageFile = file;
    }
  }

  onSubmit() {
    if (this.planForm.invalid) {
      this.planForm.markAllAsTouched();
      return;
    }

    const formValues = this.planForm.value;
    const formData = new FormData();

    // Append fields
    formData.append('title', formValues.title);
    formData.append('description', formValues.description);
    formData.append('type', formValues.type);
    formData.append('caloriesPerDay', formValues.caloriesPerDay.toString());
    formData.append('bmiLevel', formValues.bmiLevel);
    formData.append('goalType', formValues.goalType);
    formData.append('status', formValues.status.toString());

    // Append image file
    if (this.imageFile) {
      formData.append('imageFile', this.imageFile);
    }

    this.service.create(formData).subscribe({
      next: () => {
        this.planForm.reset();
        this.imageFile = null;
        this.router.navigate(['/admin/nutrition']);
      },
      error: (err) => {
        console.error('Error creating nutrition plan:', err);
      }
    });
  }
}