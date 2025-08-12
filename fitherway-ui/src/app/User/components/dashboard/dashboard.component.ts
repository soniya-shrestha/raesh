import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';
import { ChartConfiguration, ChartData, ChartOptions } from 'chart.js';
// Adjust path as needed 

 interface Workout {
  workoutName: string;
  sets: number;
  reps: number;
  equipmentRequired: boolean;
  workoutVideo: string;  
  
 
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {
  fullName = '';
  username = '';
  profileImageUrl = '';
  weight ='' ;
  height ='' ;
  age  ='' ; 
  bmiLevel: string = '';
  selectedDate: string = new Date().toISOString().split('T')[0];  
  todaysWorkouts: Workout[] = [];  

    recommendedFoods: any[] = [];

  // You can set these dynamically based on user info or keep as default
  selectedGoalType: string = 'Lose Weight';
  selectedBmiLevel: string = 'NORMAL';

  todayCompletionPercent: number = 0;
todayWorkoutsOverview: string[] = [];

  chartData: ChartData<'line'> = {
  labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
  datasets: [
    {
      data: [],
      label: 'Completed',
      borderColor: '#FF8BA7',
      backgroundColor: 'rgba(255, 139, 167, 0.1)',
      fill: true,
      tension: 0.5,
      pointRadius: 4,
      pointBackgroundColor: '#FF8BA7',
      borderWidth: 3,
    },
    {
      data: [],
      label: 'Remaining',
      borderColor: '#6ED6C5',
      backgroundColor: 'rgba(110, 214, 197, 0.1)',
      fill: true,
      tension: 0.5,
      pointRadius: 4,
      pointBackgroundColor: '#6ED6C5',
      borderWidth: 3,
    }
  ]
};

chartOptions: ChartConfiguration<'line'>['options'] = {
  responsive: true,
  maintainAspectRatio: false,
  plugins: { legend: { display: false } },
  scales: {
    x: { ticks: { color: '#999' }, grid: { display: false } },
    y: { ticks: { color: '#999' }, grid: { color: '#eee' } }
  }
};

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getLoggedInUser().subscribe({
      next: (res) => {
        const data = res?.data;
        this.fullName = data?.userName || '';
        this.username = data?.email || ''; // or any other field you'd prefer
        this.profileImageUrl = data?.profilePicture || 'assets/images/default-profile.png';
      },
      error: (err) => {
        console.error('Failed to fetch logged-in user:', err);
      }
    });  

     this.userService.getUserInfo().subscribe({
    next: (res) => {
      const data = res?.data;
      this.weight = data?.weight || 0;
      this.height = data?.height || 0;
      this.age = data?.age || 0;
      this.bmiLevel = data?.bmiLevel ; // <-- you'll need to add bmiLevel property 
     
    },
    error: (err) => {
      console.error('Failed to fetch user info:', err);
    }
  });
    this.fetchTodaysWorkouts(); 
    this.fetchProgress(); 
     this.loadRecommendedFoods();
  } 

  fetchTodaysWorkouts() {
  this.userService.getUserWorkouts().subscribe({
    next: (res) => {
      this.todaysWorkouts = res?.data || [];
    },
    error: (err) => console.error('Failed to fetch workouts:', err)
  });
} 

fetchProgress() {
  this.userService.getAllprogress().subscribe({
    next: (res) => this.calculateProgressCounts(res.data),
    error: (err) => console.error('Failed to load progress', err)
  });
}

calculateProgressCounts(progressData: any) {
  const labels = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];
  const dayMap: any = {
    Mon: 'MONDAY', Tue: 'TUESDAY', Wed: 'WEDNESDAY',
    Thu: 'THURSDAY', Fri: 'FRIDAY', Sat: 'SATURDAY', Sun: 'SUNDAY'
  };

  let completed: number[] = [];
  let remaining: number[] = []; 

  const todayName = new Date().toLocaleDateString('en-US', { weekday: 'short' });

  labels.forEach(day => {
    const upperDay = dayMap[day];
    const dayData = progressData[upperDay] || [];
    const completedCount = dayData.filter((entry: any) => entry.status === 'COMPLETED').length;
    const totalCount = dayData.length;
    remaining.push(totalCount - completedCount);
    completed.push(completedCount); 

     if (day === todayName) {
      this.todayCompletionPercent = totalCount > 0 ? Math.round((completedCount / totalCount) * 100) : 0;

      // Store workout names (if workoutName not found, fallback to type/reps)
      this.todayWorkoutsOverview = dayData.map((w: any) =>
        w.workoutName || w.type || `${w.reps} reps`
      );
    }
  });

  this.chartData.datasets[0].data = completed;
  this.chartData.datasets[1].data = remaining;
} 

loadRecommendedFoods(): void {
    this.userService.getUserNutrition(this.selectedGoalType, this.selectedBmiLevel).subscribe({
      next: (res) => {
        this.recommendedFoods = Array.isArray(res) ? res : [];
      },
      error: (err) => {
        console.error('Failed to load recommended foods', err);
      }
    });
  }
}
