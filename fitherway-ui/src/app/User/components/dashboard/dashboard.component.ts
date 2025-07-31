import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {
  fullName = 'Mike Rowe';
  username = 'mikerowe';
  profileImageUrl = 'https://example.com/path-to-profile-image.jpg'; // Replace with backend URL
  weight = 65;
  height = 178;
  age = 25;
  selectedDate: string = new Date().toISOString().split('T')[0];

  ngOnInit(): void {
    // You can fetch profile data from service here if needed
  }
}