import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Chart, ChartData, ChartOptions, registerables } from 'chart.js';
import { AdminService } from '../../service/admin.service';
import dayjs from 'dayjs';
import isoWeek from 'dayjs/plugin/isoWeek';
dayjs.extend(isoWeek);
 

interface User {
  createdAt?: string;
  registrationDate?: string;
  status?: boolean;
  // add other user fields if needed
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  totalUsers = 0;
  activeUsers = 0;
  nutritionCount = 0;
  workoutCount = 0;

  monthlyLabels: string[] = [];
  monthlyData: number[] = [];

  chartData: ChartData<'line'> = {
    labels: [], // weekly labels will go here
    datasets: [
      {
        label: 'New Users',
        data: [],
        borderColor: '#42A5F5',
        backgroundColor: 'rgba(66, 165, 245, 0.3)',
        fill: true,
        tension: 0.3,
        pointRadius: 4,
        pointBackgroundColor: '#42A5F5',
        borderWidth: 3,
      }
    ]
  };

  chartOptions: ChartOptions<'line'> = {
    responsive: true,
    plugins: {
      legend: { display: true },
      title: {
        display: true,
        text: 'Weekly User Registrations'
      }
    },
    scales: {
      y: {
        beginAtZero: true,
        ticks: { stepSize: 1 }
      }
    }
  };

  constructor(
    private service: AdminService,

  ) {
    Chart.register(...registerables);
  }

  ngOnInit(): void {
    this.loadDashboardData();
  }

 

  loadDashboardData() {
    // Users
    this.service.getAllUsers().subscribe(res => {
      const users = res?.data || [];
      this.totalUsers = users.length;
      this.activeUsers = users.filter((u: any) => u.status).length;

      // Example: Generate weekly user registration data (you can adjust logic)
      const weeklyCount: { [key: string]: number } = {};

      users.forEach((u:User) => {
        // Assume your user has 'createdAt' field in ISO string format
        const createdDate = new Date(u.createdAt || u.registrationDate || new Date());
        // Get year and week number (simple custom function below)
        const yearWeek = this.getYearWeek(createdDate);
        weeklyCount[yearWeek] = (weeklyCount[yearWeek] || 0) + 1;
      });

      this.chartData.labels = Object.keys(weeklyCount);
      this.chartData.datasets[0].data = Object.values(weeklyCount);
    });

    


    // Nutrition
    this.service.getAll().subscribe(plans => {
    this.nutritionCount = plans.length;
  });

// Workouts
this.service.getAllWorkouts().subscribe((response: any) => {
  const workouts = response.data || [];
  this.workoutCount = workouts.length;
});
  } 

   private getYearWeek(date: Date): string {
    const oneJan = new Date(date.getFullYear(), 0, 1);
    const numberOfDays = Math.floor((date.getTime() - oneJan.getTime()) / (24 * 60 * 60 * 1000));
    const week = Math.ceil((date.getDay() + 1 + numberOfDays) / 7);
    return `${date.getFullYear()}-W${week}`;
  }

}

