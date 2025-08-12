import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {
  fullName: string = '';
  profilePicture: string = '';
  dropdownOpen = false;

  constructor(
    private router: Router,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.fetchUserInfo();
  }

  fetchUserInfo(): void {
    this.userService.getLoggedInUser().subscribe({
      next: (res) => {
        this.fullName = res.data.userName;
        this.profilePicture = res.data.profilePicture;
      },
      error: (err) => {
        console.error('Failed to load user info:', err);
      }
    });
  }

  toggleDropdown(): void {
    this.dropdownOpen = !this.dropdownOpen;
  }

  toggleSideNav(): void {
    const sidenav = document.getElementById('sidebar-admin') as HTMLElement;
    const main = document.getElementById('main') as HTMLElement;
    main.classList.toggle('main-admin-active');
    sidenav.classList.toggle('sidebar-admin-active');
  }

  onProfileClick(): void {
    this.router.navigate(['/user/profile']);
  }

  onLogoutClick(): void {
    localStorage.clear();
    this.router.navigate(['/']);
  }
}