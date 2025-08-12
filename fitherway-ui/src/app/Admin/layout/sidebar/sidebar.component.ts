import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent implements OnInit { 

  sidebarVisible = true;

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

  
toggleSideNav() {
  this.sidebarVisible = !this.sidebarVisible;
}

  menus: Array<any> = [
    {
      title: 'Dashboard',
      url: '/admin/dashboard',
      icon: 'fas fa-tachometer-alt'
    }, 
     {
      title: 'Workouts',
      url: '/admin/workout',
      icon: 'fas fa-tasks',
    
    },
    {
      title: 'Users',
      url: '/admin/user',
      icon: 'fas fa-calendar-alt',
      
    },
   
    {
      title: 'Nutrition',
      url: '/admin/nutrition',
      icon: 'fas fa-chart-line'
    },
    // {
    //   title: 'Report',
    //   url: '/admin/report',
    //   icon: 'fas fa-file-alt'
    // },
    // {
    //   title: 'Settings',
    //   url: '/admin/settings',
    //   icon: 'fas fa-file-alt'
    // },
  ];
  
}
