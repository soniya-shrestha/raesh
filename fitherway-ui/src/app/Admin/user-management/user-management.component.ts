import { Component, OnInit } from '@angular/core';
import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrl: './user-management.component.scss'
})
export class UserManagementComponent implements OnInit {
  users: any[] = [];
  displayedColumns: string[] = ['name', 'email', 'status'];

  constructor(private adminService: AdminService, ) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers() {
    this.adminService.getUsers().subscribe(users => this.users = users);
  }

  toggleStatus(user: any) {
    this.adminService.toggleUserStatus(user.id, !user.active).subscribe(() => {
      user.active = !user.active;
      // this.snackBar.open(`User ${user.active ? 'activated' : 'deactivated'}`, 'Close', { duration: 2000 });
    });
  }
}
