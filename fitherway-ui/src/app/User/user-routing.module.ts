import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BaseComponent } from './layout/base/base.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ProgressComponent } from './components/progress/progress.component';
import { WorkoutsComponent } from './components/workouts/workouts.component';
import { NutritionComponent } from './components/nutrition/nutrition.component';
import { ProfileComponent } from './components/profile/profile.component';


const routes: Routes = [
  {
    path: '',
    component: BaseComponent,
    children: [
       { path: 'dashboard', component: DashboardComponent },

       //Workout
      { path: 'workout', component: WorkoutsComponent },

      
      //Progress
      { path: 'progress', component: ProgressComponent },

      // Nutrition
      { path: 'nutrition', component: NutritionComponent },
      // { path: 'assignment/add', component: AddAssignmentsComponent },

      // //Exam
      // { path: 'exam', component: ExamsComponent },
      // { path: 'exam/add', component: AddExamsComponent },

      //User Profile
      { path: 'profile', component: ProfileComponent },

      //Settings
      // { path: 'settings', component: SettingsComponent },

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
