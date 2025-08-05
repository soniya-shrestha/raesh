import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../../service/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-workouts',
  templateUrl: './workouts.component.html',
  styleUrl: './workouts.component.scss'
})
export class WorkoutsComponent implements OnInit {
  workouts: any[] = [];
  selectedWorkout: any = null;

  constructor(private adminService: AdminService, private router: Router) {}

  ngOnInit(): void {
    this.fetchWorkouts();
  }

  fetchWorkouts(): void {
  this.adminService.getAllWorkouts().subscribe({
    next: (response) => {
      console.log('API Response:', response); // (Optional) to debug
      this.workouts = response.data || [];    // ✅ Fix: assign only the array
    },
    error: (err) => {
      console.error('Error loading workouts:', err);
    }
  });
}

  toggleMenu(workout: any): void {
    this.selectedWorkout = this.selectedWorkout === workout ? null : workout;
  }
 
  openWorkoutForm() {
    this.router.navigate(['/admin/add-workout']); 
  }

  onEdit(workout: any): void {
    this.router.navigate(['/admin/edit-workout', workout.id]);
  }

  onDelete(id: number): void {
    if (confirm('Are you sure you want to delete this workout?')) {
      this.adminService.deleteWorkout(id).subscribe({
        next: () => {
          this.workouts = this.workouts.filter(w => w.id !== id);
          this.selectedWorkout = null;
        },
        error: (err) => {
          console.error('Failed to delete workout:', err);
        }
      });
    }
  }
}