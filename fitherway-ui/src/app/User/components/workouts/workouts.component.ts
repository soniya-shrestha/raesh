import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';
import { HttpErrorResponse } from '@angular/common/http';


interface Workout {
  id: number;
  workoutName: string;
  description: string;
  workoutVideo: string;
  difficulty: string;
  status: boolean;
  minBmiLevel: number;
  maxBmiLevel: number;
  targetedMuscles: string[];
  reps: number;
  sets: number;
  restBetweenSetsInSeconds: number;
  equipmentType: string;
  goalType: string;
  tags: string[];
  caloriesBurnedEstimate: number;
  bmiLevel: string; 
  equipmentRequired: boolean; 
   done?: boolean;
}

@Component({
  selector: 'app-workouts',
  templateUrl: './workouts.component.html',
  styleUrls: ['./workouts.component.scss']
})
export class WorkoutsComponent implements OnInit {

  workouts: Workout[] = [];
  loading: boolean = true;

  constructor(private userService: UserService) {}

  // ngOnInit(): void {
  //   this.userService.getUserWorkouts().subscribe({
  //     next: (response) => {
  //       this.workouts = response.data.map((w: any) => {
  //         let parsedTargetedMuscles: string[] = [];
  //         let parsedTags: string[] = [];

  //         try {
  //           parsedTargetedMuscles = JSON.parse(w.targetedMuscles[0] || '[]');
  //         } catch (e) {
  //           console.warn('Invalid targetedMuscles format', w.targetedMuscles);
  //         }

  //         try {
  //           parsedTags = JSON.parse(w.tags[0] || '[]');
  //         } catch (e) {
  //           console.warn('Invalid tags format', w.tags);
  //         }

  //         return {
  //           id: w.id,
  //           workoutName: w.workoutName,
  //           description: w.description,
  //           workoutVideo: w.workoutVideo,
  //           difficulty: w.difficulty,
  //           status: w.status,
  //           minBmiLevel: w.minBmiLevel,
  //           maxBmiLevel: w.maxBmiLevel,
  //           targetedMuscles: parsedTargetedMuscles,
  //           reps: w.reps,
  //           sets: w.sets,
  //           restBetweenSetsInSeconds: w.restBetweenSetsInSeconds,
  //           equipmentType: w.equipmentType,
  //           goalType: w.goalType,
  //           tags: parsedTags,
  //           caloriesBurnedEstimate: w.caloriesBurnedEstimate,
  //           bmiLevel: w.bmiLevel,
  //           equipmentRequired: w.equipmentRequired
  //         } as Workout;
  //       });

  //       this.loading = false;
  //     },
  //     error: (err) => {
  //       console.error('Error fetching workouts', err);
  //       this.loading = false;
  //     }
  //   });
  // }  

  ngOnInit(): void {
  const today = new Date().toISOString().split('T')[0];

  // Fetch both workouts and progress, then merge
  this.userService.getUserWorkouts().subscribe({
    next: (workoutRes) => {
      const workoutsList: Workout[] = workoutRes.data.map((w: any) => ({
        id: w.id,
        workoutName: w.workoutName,
        description: w.description,
        workoutVideo: w.workoutVideo,
        difficulty: w.difficulty,
        status: w.status,
        minBmiLevel: w.minBmiLevel,
        maxBmiLevel: w.maxBmiLevel,
        targetedMuscles: [],
        reps: w.reps,
        sets: w.sets,
        restBetweenSetsInSeconds: w.restBetweenSetsInSeconds,
        equipmentType: w.equipmentType,
        goalType: w.goalType,
        tags: [],
        caloriesBurnedEstimate: w.caloriesBurnedEstimate,
        bmiLevel: w.bmiLevel,
        equipmentRequired: w.equipmentRequired,
        done: false // will be updated if completed today
      }));

      // Now fetch progress data
      this.userService.getAllprogress().subscribe({
        next: (progressRes) => {
          const allProgress = Object.values(progressRes.data).flat();

          // Match workouts with completed progress for today
          workoutsList.forEach(workout => {
            const isDoneToday = allProgress.some((p: any) =>
              p.workoutPlanId === workout.id &&
              p.status === 'COMPLETED' &&
              p.completionDate.split('T')[0] === today
            );
            workout.done = isDoneToday;
          });

          this.workouts = workoutsList;
          this.loading = false;
        },
        error: (err) => {
          console.error("Error fetching progress", err);
          this.workouts = workoutsList; // still show workouts
          this.loading = false;
        }
      });
    },
    error: (err) => {
      console.error("Error fetching workouts", err);
      this.loading = false;
    }
  });
}


    markAsDone(workout: Workout): void {
    const formData = new FormData(); // you can add userId or date here if needed
    this.userService.markCompleted(workout.id, formData).subscribe({
      next: () => {
        workout.done = true;
      },
      error: (err: HttpErrorResponse) => {
        console.error('Failed to mark workout as done', err);
        alert('Something went wrong. Try again.');
      }
    });
  }
}
