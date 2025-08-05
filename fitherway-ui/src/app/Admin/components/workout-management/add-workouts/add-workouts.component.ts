import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from '../../../service/admin.service';

@Component({
  selector: 'app-add-workouts',
  templateUrl: './add-workouts.component.html',
  styleUrl: './add-workouts.component.scss'
})
export class AddWorkoutsComponent implements OnInit {

  workoutForm!: FormGroup;
  workoutVideo: File | null = null;
  showEquipmentType = false;

  goals = ['Loss weight', 'Build Muscle', 'General Fitness']; // sample dropdown

  constructor(private fb: FormBuilder, private adminService: AdminService, private router: Router) { }

  ngOnInit() {
    this.initForms();
  }

  initForms() {
    this.workoutForm = this.fb.group({
      workoutName: ['', Validators.required],
      description: ['', Validators.required],
      difficulty: ['', Validators.required],
      goalType: ['', Validators.required],
      status: [true],
      equipmentRequired: [false, Validators.required],
      equipmentType: [''],
      minBmiLevel: [0, [Validators.required, Validators.min(0)]],
      maxBmiLevel: [0, [Validators.required, Validators.min(0)]],
      reps: [0, [Validators.required, Validators.min(1)]],
      sets: [0, [Validators.required, Validators.min(1)]],
      restBetweenSetsInSeconds: [0, [Validators.required, Validators.min(0)]],
      caloriesBurnedEstimate: [0, [Validators.required, Validators.min(0)]],
      targetedMuscles: ['', Validators.required],
      tags: ['', Validators.required],
      bmiLevel: ['', Validators.required]

    });

    this.workoutForm.get('equipmentRequired')?.valueChanges.subscribe(value => {
      this.showEquipmentType = value === 'Yes';
      if (this.showEquipmentType) {
        this.workoutForm.get('equipmentType')?.setValidators(Validators.required);
      } else {
        this.workoutForm.get('equipmentType')?.clearValidators();
        this.workoutForm.get('equipmentType')?.setValue('');
      }
      this.workoutForm.get('equipmentType')?.updateValueAndValidity();
    });
  }

  onFileChange(event: any) {
    const file = event.target.files?.[0];
    if (file) {
      this.workoutVideo = file;
    }
  }

  onSubmit() {
    if (this.workoutForm.invalid) {
      this.workoutForm.markAllAsTouched();
      return;
    }

    const formValues = this.workoutForm.value;
    const formData = new FormData();

    formData.append('workoutName', formValues.workoutName);
    formData.append('description', formValues.description);
    formData.append('difficulty', formValues.difficulty);
    formData.append('goalType', formValues.goalType);
    formData.append('status', formValues.status.toString());
    formData.append('isEquipmentRequired', formValues.equipmentRequired.toString());
    formData.append('equipmentType', formValues.equipmentType || '');
    formData.append('minBmiLevel', formValues.minBmiLevel.toString());
    formData.append('maxBmiLevel', formValues.maxBmiLevel.toString());
    formData.append('reps', formValues.reps.toString());
    formData.append('sets', formValues.sets.toString());
    formData.append('restBetweenSetsInSeconds', formValues.restBetweenSetsInSeconds.toString());
    formData.append('caloriesBurnedEstimate', formValues.caloriesBurnedEstimate.toString());

    formData.append('targetedMuscles', JSON.stringify(formValues.targetedMuscles.split(','))); // ✅ as array
    formData.append('tags', JSON.stringify(formValues.tags.split(',')));
    formData.append('bmiLevel', formValues.bmiLevel);


    if (this.workoutVideo) {
      formData.append('workoutVideo', this.workoutVideo);
    }

    this.adminService.addWorkouts(formData).subscribe({
      next: (res) => {
        console.log('Workout added:', res);
        this.workoutForm.reset();
        this.workoutVideo = null;
      },
      error: (err) => {
        console.error('Error adding workout:', err);
      }
    });
  }
}
