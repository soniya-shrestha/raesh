import { AfterViewInit, Component } from '@angular/core';
import { Chart, registerables } from 'chart.js';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements AfterViewInit{ 

  constructor() {
    Chart.register(...registerables);
  }

  ngAfterViewInit(): void {
    // Bar Chart: User Registrations
    new Chart('barChart', {
      type: 'bar',
      data: {
        labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May'],
        datasets: [{
          label: 'New Users',
          data: [120, 190, 300, 500, 230],
          backgroundColor: '#42A5F5'
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            display: false
          },
          title: {
            display: true,
            text: 'Monthly User Registrations'
          }
        }
      }
    });

} 
}