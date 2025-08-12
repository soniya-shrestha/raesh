import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';
import { ChartConfiguration, ChartData } from 'chart.js';

@Component({
  selector: 'app-progress',
  templateUrl: './progress.component.html',
  styleUrls: ['./progress.component.scss']
})
export class ProgressComponent implements OnInit {
  progressData: any;
  completedCount: number = 0;
  inProgressCount: number = 0; 
  motivationMessage: string = '';

  labels = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];

  chartData: ChartData<'line'> = {
    labels: this.labels,
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
    plugins: {
      legend: {
        position: 'top',
        labels: {
          color: '#333',
          font: {
            size: 14
          }
        }
      }
    },
    scales: {
      x: {
        ticks: { color: '#999' },
        grid: { display: false }
      },
      y: {
        ticks: { color: '#999' },
        grid: { color: '#eee' }
      }
    }
  };

  chartType: 'line' = 'line';

  constructor(private userService: UserService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.fetchProgress();
  }

  fetchProgress() {
    this.userService.getAllprogress().subscribe({
      next: (res) => {
        this.progressData = res.data;
        this.calculateProgressCounts();
      },
      error: (err) => console.error('Failed to load progress', err)
    });
  } 

  

getMotivationMessage(): string {
  const total = this.completedCount + this.inProgressCount;

  if (total === 0) {
    return "🏁 Let’s get started! Your journey begins today.";
  } else if (this.completedCount === 0) {
    return "⏳ Time to move! Just one step can start a big change.";
  } else if (this.completedCount < total / 2) {
    return "🌱 You're getting there! Stay consistent and push forward.";
  } else if (this.completedCount < total) {
    return "💪 You're on the right track. Keep pushing!";
  } else {
    return "🔥 Amazing job! You're smashing your goals!";
  }
}

  calculateProgressCounts() {
    const labels = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];
    this.labels = labels;

    let completed: number[] = [];
    let remaining: number[] = [];

    const dayMap: any = {
      Mon: 'MONDAY',
      Tue: 'TUESDAY',
      Wed: 'WEDNESDAY',
      Thu: 'THURSDAY',
      Fri: 'FRIDAY',
      Sat: 'SATURDAY',
      Sun: 'SUNDAY'
    };

    labels.forEach(day => {
      const upperDay = dayMap[day];
      const dayData = this.progressData[upperDay] || [];

      const completedCount = dayData.filter((entry: any) => entry.status === 'COMPLETED').length;
      const totalCount = dayData.length;
      const remainingCount = totalCount - completedCount;

      completed.push(completedCount);
      remaining.push(remainingCount);
    });

    this.completedCount = completed.reduce((a, b) => a + b, 0);
    this.inProgressCount = remaining.reduce((a, b) => a + b, 0);

    this.chartData = {
      labels: this.labels,
      datasets: [
        {
          data: completed,
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
          data: remaining,
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
    this.motivationMessage = this.getMotivationMessage();
  }

  ngAfterViewInit(): void {
    this.cdr.detectChanges();
  }
}
