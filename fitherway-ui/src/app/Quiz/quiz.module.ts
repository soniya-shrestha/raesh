import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { StepCardComponent } from './step-card/step-card.component';
import { ProgressIndicatorComponent } from './progress-indicator/progress-indicator.component';
import { QuizRoutingModule } from './quiz-routing.module';
import { QuizComponent } from './quiz/quiz.component';
import { QuizSessionComponent } from './quiz-session/quiz-session.component';



@NgModule({
  declarations: [  
    QuizComponent,
    StepCardComponent,
    ProgressIndicatorComponent,
    QuizSessionComponent,
  ],
  imports: [
    CommonModule,
    FormsModule, 
    QuizRoutingModule,
    ReactiveFormsModule

  ]
})
export class QuizModule { }
