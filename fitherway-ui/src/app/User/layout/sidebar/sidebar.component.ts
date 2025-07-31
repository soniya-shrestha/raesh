import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent implements OnInit {
  constructor(private router: Router) { }

  ngOnInit(): void { }


  navigateTo(route: string) {
    this.router.navigate([`${route}`]);
  }

  onLogOut() {
    localStorage.clear();
    this.router.navigate(['/']);
  }

  isActive(menu: any): boolean {
    return this.router.isActive(menu.url, true);
  }

  toggleSubmenu(menu: any) {
    if (menu.submenu) {
      menu.open = !menu.open;
    }
  }

  menus: Array<any> = [
    {
      title: 'Dashboard',
      url: '/user/dashboard',
      icon: 'fas fa-tachometer-alt'
    },
    {
      title: 'Workout',
      url: '/user/workout',
      icon: 'fas fa-calendar-alt',
      
    },
    {
      title: 'Nutrition',
      url: '/user/nutrition',
      icon: 'fas fa-tasks',
    
    },
    {
      title: 'Progress',
      url: '/user/progress',
      icon: 'fas fa-chart-line'
    },
    {
      title: 'Settings',
      url: '/user/settings',
      icon: 'fas fa-file-alt'
    },
  ];
  
}
