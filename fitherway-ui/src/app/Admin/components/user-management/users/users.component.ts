import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../../service/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.scss'
})
export class UsersComponent implements OnInit {

  constructor(private userService: AdminService, private router: Router) {}

 users :any[] =[];
    
  

selectedUser: any = null;

ngOnInit() {
   this.userService.getAllUsers().subscribe(response => {
    this.users = response.data; // ✅ Correctly access array
  });
}

toggleMenu(user: any) {
  this.selectedUser = this.selectedUser === user ? null : user;
}

onEdit(id: any) {
 this.router.navigate(['admin/edit-users',id]);
}

onDelete(id: number) {
  if (confirm('Are you sure you want to delete this workout?')) {
      this.userService.deleteWorkout(id).subscribe({
        next: () => {
          this.users = this.users.filter(w => w.id !== id);
          this.selectedUser = null;
        },
        error: (err) => {
          console.error('Failed to delete workout:', err);
        }
      });
    }
  }
}

