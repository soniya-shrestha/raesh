import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-base',
  templateUrl: './base.component.html',
  styleUrl: './base.component.scss'
})
export class BaseComponent {
   constructor(private router: Router) {}

  openQuiz() {
    this.router.navigate(['/quiz-session']);
  }
}
