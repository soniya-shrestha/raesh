import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../../service/admin.service';

@Component({
  selector: 'app-edit-workouts',
  templateUrl: './edit-workouts.component.html',
  styleUrl: './edit-workouts.component.scss'
})
export class EditWorkoutsComponent implements  OnInit {
  workoutForm!: FormGroup;
  workoutId!: number;
  selectedFile!: File;
  goals = ['Loss weight', 'Gain muscle', 'Stay fit'];

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private adminService: AdminService
  ) {}

  ngOnInit(): void {
    this.workoutId = Number(this.route.snapshot.paramMap.get('id'));
    this.initForm();

    // this.adminService.getWorkoutById(this.workoutId).subscribe(
    //   response => {
    //   const data = response.data;

    //   // Parse JSON string arrays if necessary
    //   data.targetedMuscles = JSON.parse(data.targetedMuscles?.[0] || '[]');
    //   data.tags = JSON.parse(data.tags?.[0] || '[]');

    //   this.workoutForm.patchValue({
    //     workoutName: data.workoutName,
    //     description: data.description,
    //     difficulty: data.difficulty,
    //     goalType: data.goalType,
    //     equipmentRequired: data.equipmentRequired?.toString(), // convert to string
    //     equipmentType: data.equipmentType,
    //     bmiLevel: data.bmiLevel,
    //     minBmiLevel: data.minBmiLevel,
    //     maxBmiLevel: data.maxBmiLevel,
    //     reps: data.reps,
    //     sets: data.sets,
    //     caloriesBurnedEstimate: data.caloriesBurnedEstimate,
    //     restBetweenSetsInSeconds: data.restBetweenSetsInSeconds,
    //     targetedMuscles: data.targetedMuscles?.join(', '),
    //     tags: data.tags?.join(', ')
    //   });
    // });
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
      tags: ['', Validators.required]
    });
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
    formData.append('targetedMuscles', JSON.stringify(formValue.targetedMuscles.split(',').map((m: string) => m.trim())));
    formData.append('tags', JSON.stringify(formValue.tags.split(',').map((t: string) => t.trim())));

    if (this.selectedFile) {
      formData.append('workoutVideo', this.selectedFile);
    }

    this.adminService.updateWorkout(this.workoutId, formData).subscribe(() => {
      this.router.navigate(['/admin/workouts']);
    });
  }
}