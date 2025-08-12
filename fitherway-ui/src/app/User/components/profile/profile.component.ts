import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent implements OnInit {
  user: any;

  constructor(private userService: UserService,  private route: Router) {}

  ngOnInit(): void {
    this.fetchLoggedInUser();
  }

  fetchLoggedInUser(): void {
    this.userService.getLoggedInUser().subscribe({
      next: (res) => {
        this.user = res?.data;
      },
      error: (err) => {
        console.error('Failed to fetch logged-in user:', err);
      }
    });
  } 

   goBack(): void {
    this.route.navigate(['user/dashboard'])
  }
}