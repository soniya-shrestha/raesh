import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {

  dropdownOpen = false;
 userName: string = 'Admin';

  constructor(
    private router: Router
  ) {}
  
  ngOnInit() {
  }

getInitial(): string {
  return this.userName ? this.userName.charAt(0).toUpperCase() : '';
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

}
