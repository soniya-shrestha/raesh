import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  fullName: any;
  dropdownOpen = false;


  constructor(
    private router: Router
  ) {
  }
  ngOnInit() {
  }


toggleDropdown(): void {
  this.dropdownOpen = !this.dropdownOpen;
}


  onLogoutClick(): void {
    localStorage.clear();
    this.router.navigate(['/']);
  }

  toggleSideNav() {
    const sidenav = document.getElementById('sidebar-admin') as HTMLElement;
    const main = document.getElementById('main') as HTMLElement;
    main.classList.toggle('main-admin-active');
    sidenav.classList.toggle('sidebar-admin-active');
  }

  onProfileClick(){
    this.router.navigate(['/user/profile']);
  }

}
