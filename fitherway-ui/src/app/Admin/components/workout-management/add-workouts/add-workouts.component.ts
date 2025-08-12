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

  goals = ['Lose weight', 'Build Muscle', 'General Fitness'];  
  muscles = ['Chest', 'Back', 'Arms', 'Legs', 'Shoulders', 'Core'];
  tags = ['Sun', 'Mon', 'Tue', 'Wed', 'Thurs', 'Fri', 'Sat'];// sample dropdown 

selectedMuscles: string[] = [];
selectedTags: string[] = [];

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
      targetedMuscles: [[], Validators.required],
      tags: [[], Validators.required],
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

onMuscleCheckboxChange(event: Event) {
  const checkbox = event.target as HTMLInputElement;
  const value = checkbox.value;

  if (checkbox.checked) {
    if (!this.selectedMuscles.includes(value)) {
      // Create a new array reference to trigger Angular change detection properly
      this.selectedMuscles = [...this.selectedMuscles, value];
    }
  } else {
    this.selectedMuscles = this.selectedMuscles.filter(m => m !== value);
  } 
  this.workoutForm.get('targetedMuscles')?.setValue(this.selectedMuscles);
  this.workoutForm.get('targetedMuscles')?.markAsDirty();
  this.workoutForm.get('targetedMuscles')?.updateValueAndValidity();
}

onTagCheckboxChange(event: Event) {
  const checkbox = event.target as HTMLInputElement;
  const value = checkbox.value;

  if (checkbox.checked) {
    if (!this.selectedTags.includes(value)) {
      this.selectedTags = [...this.selectedTags, value];
    }
  } else {
    this.selectedTags = this.selectedTags.filter(t => t !== value);
  } 
    this.workoutForm.get('tags')?.setValue(this.selectedTags);
  this.workoutForm.get('tags')?.markAsDirty();
  this.workoutForm.get('tags')?.updateValueAndValidity();
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

formData.append('targetedMuscles', JSON.stringify(formValues.targetedMuscles));
formData.append('tags', JSON.stringify(formValues.tags));
    formData.append('bmiLevel', formValues.bmiLevel);


    if (this.workoutVideo) {
      formData.append('workoutVideo', this.workoutVideo);
    }

    this.adminService.addWorkouts(formData).subscribe({
      next: (res) => {
        console.log('Workout added:', res);
        this.workoutForm.reset();
        this.workoutVideo = null; 
        this.router.navigate(['/admin/workout']);
      },
      error: (err) => {
        console.error('Error adding workout:', err);
      }
    });
  }
}
