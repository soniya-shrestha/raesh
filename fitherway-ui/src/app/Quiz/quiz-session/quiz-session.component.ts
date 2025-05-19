import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-quiz-session',
  templateUrl: './quiz-session.component.html',
  styleUrl: './quiz-session.component.scss'
})
export class QuizSessionComponent { 

  constructor(private route: Router){}

  continue(){
    this.route.navigate(['/quiz'])
  }
}
