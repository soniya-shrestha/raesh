import { Component } from '@angular/core';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.scss'
})
export class UsersComponent {
  users = [
    { name: 'Aarju Sharma', email: 'aarju@gmail.com', role: 'Student', active: true },
    { name: 'Ravi Sharma', email: 'ravi@gmail.com', role: 'Teacher', active: false },
    { name: 'Priya Karki', email: 'priya@gmail.com', role: 'Student', active: true }
  ];
}
