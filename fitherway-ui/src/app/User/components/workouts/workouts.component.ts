
interface Exercise {
  name: string;
  sets: number;
  reps: number;
  equipmentRequired: boolean;
  done: boolean; 
  imageUrl: string;
}

interface DailyWorkout {
  day: string;
  exercises: Exercise[];
}


import { Component } from '@angular/core';   


@Component({
  selector: 'app-workouts',
  templateUrl: './workouts.component.html',
  styleUrl: './workouts.component.scss'
})
export class WorkoutsComponent {

weeklyRoutine: DailyWorkout[] = [
  {
    day: 'Monday',
    exercises: [
      {
        name: 'Push-ups',
        sets: 3,
        reps: 15,
        equipmentRequired: false,
        done: false,
        imageUrl: 'assets/image/pushup.webp'
      },
      {
        name: 'Bench Press',
        sets: 3,
        reps: 10,
        equipmentRequired: true,
        done: false,
        imageUrl: 'assets/workouts/benchpress.jpg'
      }
    ]
  },
  {
    day: 'Tuesday',
    exercises: [
      {
        name: 'Squats',
        sets: 4,
        reps: 12,
        equipmentRequired: false,
        done: false,
        imageUrl: 'assets/workouts/squats.jpg'
      }
    ]
  }
];


  toggleDone(dayIndex: number, exIndex: number): void {
    this.weeklyRoutine[dayIndex].exercises[exIndex].done = 
      !this.weeklyRoutine[dayIndex].exercises[exIndex].done;
  }
}