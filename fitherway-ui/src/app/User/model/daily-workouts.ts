 interface Exercise {
  name: string;
  sets: number;
  reps: number;
  equipmentRequired: boolean;
  done: boolean;
}

interface DailyWorkout {
  day: string;
  exercises: Exercise[];
}

