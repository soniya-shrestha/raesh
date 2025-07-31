import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BaseComponent } from './layout/base/base.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { NutritionPlansComponent } from './components/nutrition-plans/nutrition-plans.component';
import { WorkoutsComponent } from './components/workout-management/workouts/workouts.component';
import { ProgressComponent } from './components/reports/progress/progress.component';
import { UsersComponent } from './components/user-management/users/users.component';


const routes: Routes = [
   {
      path: '',
      component: BaseComponent,
      children: [
        { path: 'dashboard', component: DashboardComponent  },

        //Users list
        { path: 'user', component: UsersComponent },

        //Workout list
        { path: 'workout', component: WorkoutsComponent},
        
        //nutrition
        { path: 'nutrition', component: NutritionPlansComponent },

        //Reports
        { path: 'progress', component: ProgressComponent },

        //Settings
        // { path: 'settings', component: SettingsComponent },

      ]
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
