import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../../service/admin.service';

@Component({
  selector: 'app-edit-workouts',
  templateUrl: './edit-workouts.component.html',
  styleUrl: './edit-workouts.component.scss'
})
export class EditWorkoutsComponent implements OnInit {
  workoutForm!: FormGroup;
  workoutId!: number;
  selectedFile!: File;
  goals = ['Lose weight', 'Build muscle', 'General Fitness'];

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private adminService: AdminService
  ) {}

  ngOnInit(): void {
    this.workoutId = Number(this.route.snapshot.paramMap.get('id'));
    this.initForm();
    this.getWorkout();
  }

  initForm(): void {
    this.workoutForm = this.fb.group({
      workoutName: ['', Validators.required],
      description: ['', Validators.required],
      difficulty: ['', Validators.required],
      goalType: ['', Validators.required],
      equipmentRequired: ['', Validators.required],
      equipmentType: [''],
      bmiLevel: ['', Validators.required],
      minBmiLevel: [0, Validators.required],
      maxBmiLevel: [0, Validators.required],
      reps: [0, Validators.required],
      sets: [0, Validators.required],
      caloriesBurnedEstimate: [0, Validators.required],
      restBetweenSetsInSeconds: [0, Validators.required],
      targetedMuscles: ['', Validators.required],
      tags: ['', Validators.required],
       status: ['true', Validators.required]
    });
  }

  getWorkout(): void {
    this.adminService.getWorkoutById(this.workoutId).subscribe(
      response => {
        const data = response.data;

        try {
          const musclesRaw = Array.isArray(data.targetedMuscles)
            ? data.targetedMuscles.join('')
            : '[]';
          data.targetedMuscles = JSON.parse(musclesRaw);
        } catch (e) {
          console.error('Error parsing targetedMuscles:', e);
          data.targetedMuscles = [];
        }

        try {
          const tagsRaw = Array.isArray(data.tags)
            ? data.tags.join('')
            : '[]';
          data.tags = JSON.parse(tagsRaw);
        } catch (e) {
          console.error('Error parsing tags:', e);
          data.tags = [];
        }

        this.workoutForm.patchValue({
          workoutName: data.workoutName,
          description: data.description,
          difficulty: data.difficulty,
          goalType: data.goalType,
          equipmentRequired: data.equipmentRequired?.toString(),
          equipmentType: data.equipmentType,
          bmiLevel: data.bmiLevel,
          minBmiLevel: data.minBmiLevel,
          maxBmiLevel: data.maxBmiLevel,
          reps: data.reps,
          sets: data.sets,
          caloriesBurnedEstimate: data.caloriesBurnedEstimate,
          restBetweenSetsInSeconds: data.restBetweenSetsInSeconds,
          targetedMuscles: data.targetedMuscles?.join(', '),
          tags: data.tags?.join(', '),
          status: data.status ? 'true' : 'false',
        });
      },
      error => {
        console.error('Failed to fetch workout:', error);
      }
    );
  }

  onFileChange(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
    }
  }

  onSubmit(): void {
    if (this.workoutForm.invalid) return;

    const formData = new FormData();
    const formValue = this.workoutForm.value;

    formData.append('workoutName', formValue.workoutName);
    formData.append('description', formValue.description);
    formData.append('difficulty', formValue.difficulty);
    formData.append('goalType', formValue.goalType);
    formData.append('equipmentRequired', formValue.equipmentRequired);
    formData.append('equipmentType', formValue.equipmentType || '');
    formData.append('bmiLevel', formValue.bmiLevel);
    formData.append('minBmiLevel', formValue.minBmiLevel);
    formData.append('maxBmiLevel', formValue.maxBmiLevel);
    formData.append('reps', formValue.reps);
    formData.append('sets', formValue.sets);
    formData.append('caloriesBurnedEstimate', formValue.caloriesBurnedEstimate);
    formData.append('restBetweenSetsInSeconds', formValue.restBetweenSetsInSeconds); 
    formData.append('status', formValue.status);

    formData.append(
      'targetedMuscles',
      JSON.stringify(formValue.targetedMuscles.split(',').map((m: string) => m.trim()))
    );
    formData.append(
      'tags',
      JSON.stringify(formValue.tags.split(',').map((t: string) => t.trim()))
    );

    if (this.selectedFile) {
      formData.append('workoutVideo', this.selectedFile);
    }

    this.adminService.updateWorkout(this.workoutId, formData).subscribe({
      next: () => {
        console.log('Workout updated successfully!');
        this.getWorkout(); 
         this.router.navigate(['/admin/workout']);
      },
      error: (err) => {
        console.error('Update failed:', err);
      }
    });
  }
}
