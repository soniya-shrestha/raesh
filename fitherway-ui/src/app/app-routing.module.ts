import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BaseComponent } from './home/base/base.component';
import { QuizSessionComponent } from './Quiz/quiz-session/quiz-session.component';
import { negateAuthGuard } from './core/guards/negate-auth.guard';
import { authGuard } from './core/guards/auth.guard';

const routes: Routes = [
    { path: '', component: BaseComponent },
    
  { path: 'quiz-session', component: QuizSessionComponent },
  {
    path: 'quiz',
    loadChildren: () =>
      import('./Quiz/quiz.module').then((m) => m.QuizModule)
  },

  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule),
   canActivate: [negateAuthGuard],
  }, 
    {
    path: 'user',
    loadChildren: () => import('./User/user.module').then((m) => m.UserModule),
    canActivate: [authGuard],
  },
  {
    path: 'admin',
    loadChildren: () => import('./Admin/admin.module').then((m) => m.AdminModule),
    canActivate: [authGuard],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
