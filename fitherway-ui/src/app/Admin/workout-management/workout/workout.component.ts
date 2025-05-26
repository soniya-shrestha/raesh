// import { Component } from '@angular/core';
// import { FormBuilder, Validators } from '@angular/forms';
// import { AdminService } from '../../service/admin.service';

// @Component({
//   selector: 'app-workout',
//   templateUrl: './workout.component.html',
//   styleUrl: './workout.component.scss'
// })
// export class WorkoutComponent implements OnInit {
//   form: FormGroup;
//   plans: any[] = [];
//   editingId: number | null = null;

//   constructor(private fb: FormBuilder, private adminService: AdminService) {
//     this.form = this.fb.group({
//       name: ['', Validators.required],
//       description: [''],
//       difficulty: ['Beginner']
//     });
//   }

//   ngOnInit() {
//     this.loadPlans();
//   }

//   loadPlans() {
//     this.adminService.getWorkouts().subscribe(res => this.plans = res);
//   }

//   onSubmit() {
//     const data = this.form.value;
//     if (this.editingId) {
//       this.adminService.editWorkout(this.editingId, data).subscribe(() => {
//         this.loadPlans();
//         this.resetForm();
//         this.snackBar.open('Workout updated', 'Close', { duration: 2000 });
//       });
//     } else {
//       this.adminService.addWorkout(data).subscribe(() => {
//         this.loadPlans();
//         this.resetForm();
//         this.snackBar.open('Workout added', 'Close', { duration: 2000 });
//       });
//     }
//   }

//   edit(plan: any) {
//     this.form.patchValue(plan);
//     this.editingId = plan.id;
//   }

//   delete(id: number) {
//     this.adminService.deleteWorkout(id).subscribe(() => {
//       this.loadPlans();
//       this.snackBar.open('Workout deleted', 'Close', { duration: 2000 });
//     });
//   }

//   resetForm() {
//     this.form.reset({ difficulty: 'Beginner' });
//     this.editingId = null;
//   }
// }
