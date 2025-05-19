import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BaseComponent } from './home/base/base.component';
import { QuizSessionComponent } from './Quiz/quiz-session/quiz-session.component';

const routes: Routes = [
    { path: '', component: BaseComponent },
    
  { path: 'quiz-session', component: QuizSessionComponent },
  {
    path: 'quiz',
    loadChildren: () =>
      import('./Quiz/quiz.module').then((m) => m.QuizModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
